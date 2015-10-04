/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.manipulators;

import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.ConcatPolyFunction;
import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.ILegPolyFunctions;
import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.LegPolyFunctions;
import com.oberger.kruppelbotsimulation.localsearch.manipulator.IManipulator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author oberger
 */
public class LegPolyFunctionsManipulator implements IManipulator<ILegPolyFunctions> {

    private IManipulator<ConcatPolyFunction> concatFunctionManipulator = null;

    public LegPolyFunctionsManipulator(IManipulator<ConcatPolyFunction> concatFunctionManipulator) {
	this.concatFunctionManipulator = concatFunctionManipulator;
    }

    @Override
    public List<ILegPolyFunctions> createNeighbours(ILegPolyFunctions originalInnerState) {
	List<ILegPolyFunctions> neighbours = new LinkedList<>();

	// BR
	{
	    List<ConcatPolyFunction> neighboursBR = concatFunctionManipulator.createNeighbours(originalInnerState.getLegFunctionBR());
	    for (ConcatPolyFunction neighbourBR : neighboursBR) {
		neighbours.add(new LegPolyFunctions(neighbourBR, originalInnerState.getLegFunctionBL(), originalInnerState.getLegFunctionFR(), originalInnerState.getLegFunctionFL()));
	    }
	}

	// BL
	{
	    List<ConcatPolyFunction> neighboursBL = concatFunctionManipulator.createNeighbours(originalInnerState.getLegFunctionBL());
	    for (ConcatPolyFunction neighbourBL : neighboursBL) {
		neighbours.add(new LegPolyFunctions(originalInnerState.getLegFunctionBR(), neighbourBL, originalInnerState.getLegFunctionFR(), originalInnerState.getLegFunctionFL()));
	    }
	}

	// FR
	{
	    List<ConcatPolyFunction> neighboursFR = concatFunctionManipulator.createNeighbours(originalInnerState.getLegFunctionFR());
	    for (ConcatPolyFunction neighbourFR : neighboursFR) {
		neighbours.add(new LegPolyFunctions(originalInnerState.getLegFunctionBR(), originalInnerState.getLegFunctionBL(), neighbourFR, originalInnerState.getLegFunctionFL()));
	    }
	}

	// FL
	{
	    List<ConcatPolyFunction> neighboursFL = concatFunctionManipulator.createNeighbours(originalInnerState.getLegFunctionFL());
	    for (ConcatPolyFunction neighbourFL : neighboursFL) {
		neighbours.add(new LegPolyFunctions(originalInnerState.getLegFunctionBR(), originalInnerState.getLegFunctionBL(), originalInnerState.getLegFunctionFR(), neighbourFL));
	    }
	}

	return neighbours;
    }

}
