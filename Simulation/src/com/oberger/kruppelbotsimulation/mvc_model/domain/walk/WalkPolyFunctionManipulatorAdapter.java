/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.OrderedLegPolyFunctions;
import com.oberger.kruppelbotsimulation.mvc_model.function.PolyFunction;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.manipulator.IManipulator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ole
 */
public class WalkPolyFunctionManipulatorAdapter implements IManipulator<WalkState> {
    
    private IManipulator<PolyFunction> functionManipulatorAdaptee = null;
    
    public WalkPolyFunctionManipulatorAdapter(IManipulator<PolyFunction> adaptee) {
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
        List<PolyFunction> neighbourFunctions = functionManipulatorAdaptee.createNeighbours(originalInnerState.getLegFunctions().getOriginalFunction());
        
        OrderedLegPolyFunctions originalOrderedFunction = originalInnerState.getLegFunctions();
        List<WalkState> neighbourWalkStates = new ArrayList<>(neighbourFunctions.size());
        for (PolyFunction neighbourFunction : neighbourFunctions) {
            OrderedLegPolyFunctions neighbourOrderedFunction = new OrderedLegPolyFunctions(neighbourFunction, originalOrderedFunction.getLegOrder(), originalOrderedFunction.getPeriodInS());
            WalkState neighbourWalkState = new WalkState(neighbourOrderedFunction, originalInnerState.getModel());
            neighbourWalkStates.add(neighbourWalkState);
        }
        
        return neighbourWalkStates;
    }
    
}
