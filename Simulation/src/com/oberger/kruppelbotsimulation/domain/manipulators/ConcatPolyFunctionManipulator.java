/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.manipulators;

import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.ConcatPart;
import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.ConcatPolyFunction;
import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.EManipulatable;
import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.PartialPolyFunction;
import com.oberger.kruppelbotsimulation.localsearch.manipulator.IManipulator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author oberger
 */
public class ConcatPolyFunctionManipulator implements IManipulator<ConcatPolyFunction> {

    private IManipulator<PartialPolyFunction> adaptee = null;

    public ConcatPolyFunctionManipulator(IManipulator<PartialPolyFunction> adaptee) {
	if (adaptee == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}
	this.adaptee = adaptee;
    }

    @Override
    public List<ConcatPolyFunction> createNeighbours(ConcatPolyFunction concatFunction) {
	if (concatFunction == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}
	List<ConcatPolyFunction> neighbours = new LinkedList<>();

	for (int i = 0; i < concatFunction.getParts().size(); i++) {
	    ConcatPart partToManipulate = concatFunction.getParts().get(i);
	    if (partToManipulate.getManipulatable() == EManipulatable.DYNAMIC) {
		List<PartialPolyFunction> neighbourPartialFunctions = adaptee.createNeighbours(partToManipulate.getFunction());
		for (PartialPolyFunction manipulatedPartialFunction : neighbourPartialFunctions) {
		    List<ConcatPart> parts = new ArrayList<>(concatFunction.getParts());
		    parts.remove(i);
		    parts.add(i, new ConcatPart(manipulatedPartialFunction, partToManipulate.getManipulatable(), partToManipulate.getBalanceMode()));
		    ConcatPolyFunction manipulatedConcatFunction = new ConcatPolyFunction(concatFunction.getInterpolator(), parts, concatFunction.getPeriod(), concatFunction.getOffsetX());
		    neighbours.add(manipulatedConcatFunction);
		}
	    }
	}

	return neighbours;
    }

}
