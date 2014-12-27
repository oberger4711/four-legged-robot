/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator.Model;
import com.oberger.kruppelbotsimulation.mvc_model.function.PolyFunction;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.IImmutableInnerState;

/**
 *
 * @author ole
 */
public class WalkState implements IImmutableInnerState {

    private OrderedLegMapping legMapping;
    private PolyFunction legFunctionForward;
    private PolyFunction legFunctionBackward;
    private Model model;

    public WalkState(OrderedLegMapping legMapping, PolyFunction legFunctionForward, PolyFunction legFunctionBackward, Model model) {
        if (legMapping == null || legFunctionForward == null || legFunctionBackward == null || model == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        this.legMapping = legMapping;
        this.legFunctionForward = legFunctionForward;
        this.legFunctionBackward = legFunctionBackward;
        this.model = model;
    }
    
    public OrderedLegMapping getLegMapping() {
        return legMapping;
    }

    public PolyFunction getLegFunctionForward() {
        return legFunctionForward;
    }

    public PolyFunction getLegFunctionBackward() {
        return legFunctionBackward;
    }

    public Model getModel() {
        return model;
    }

}
