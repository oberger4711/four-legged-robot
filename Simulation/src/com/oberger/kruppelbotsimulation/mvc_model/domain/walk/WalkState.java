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
    private PolyFunction legFunction;
    private Model model;

    public WalkState(OrderedLegMapping legMapping, PolyFunction legFunction, Model model) {
        if (legMapping == null || legFunction == null || model == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        this.legMapping = legMapping;
        this.legFunction = legFunction;
        this.model = model;
    }
    
    public OrderedLegMapping getLegMapping() {
        return legMapping;
    }

    public PolyFunction getLegFunction() {
        return legFunction;
    }

    public Model getModel() {
        return model;
    }

}
