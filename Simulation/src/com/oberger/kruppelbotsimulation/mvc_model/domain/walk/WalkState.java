/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.Model;
import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.legpolyfunctions.OrderedLegPolyFunctions;
import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ole
 */
public class WalkState {

    private OrderedLegPolyFunctions legFunctions = null;
    private Model model = null;

    public WalkState(OrderedLegPolyFunctions legFunctions, Model model) {
        if (legFunctions == null || model == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        this.legFunctions = legFunctions;
        this.model = model;
    }

    public OrderedLegPolyFunctions getLegFunctions() {
        return legFunctions;
    }

    public Model getModel() {
        return model;
    }

}
