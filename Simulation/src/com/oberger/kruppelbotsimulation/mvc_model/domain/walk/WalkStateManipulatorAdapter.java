/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.legpolyfunctions.ILegPolyFunctions;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.manipulator.IManipulator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ole
 */
public class WalkStateManipulatorAdapter implements IManipulator<WalkState> {

    private IManipulator<ILegPolyFunctions> functionManipulatorAdaptee = null;

    public WalkStateManipulatorAdapter(IManipulator<ILegPolyFunctions> adaptee) {
	if (adaptee == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}
	this.functionManipulatorAdaptee = adaptee;
    }

    @Override
    public List<WalkState> createNeighbours(WalkState originalInnerState) {
	if (originalInnerState == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}
	List<ILegPolyFunctions> neighbourFunctions = functionManipulatorAdaptee.createNeighbours(originalInnerState.getLegFunctions());
	List<WalkState> neighbourWalkStates = new ArrayList<>(neighbourFunctions.size());
	for (ILegPolyFunctions neighbourFunction : neighbourFunctions) {
	    WalkState neighbourWalkState = new WalkState(neighbourFunction, originalInnerState.getModel());
	    neighbourWalkStates.add(neighbourWalkState);
	}

	return neighbourWalkStates;
    }

}
