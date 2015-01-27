/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator.ILegPolyFunctions;
import com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator.Model;
import com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator.Simulation;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator.IEvaluator;

/**
 *
 * @author ole
 */
public class WalkSimulationEvaluatorAdapter implements IEvaluator<WalkState>{
    
    private IEvaluator<Simulation> adaptee = null;
    
    public WalkSimulationEvaluatorAdapter(IEvaluator<Simulation> adaptee) {
        if (adaptee == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        this.adaptee = adaptee;
    }
    
    @Override
    public float getScore(WalkState innerState) {
        Simulation simulation = createSimulation(innerState);
        
        float score = adaptee.getScore(simulation);
        
        return score;
    }
    
    protected Simulation createSimulation(WalkState walkState) {
        return new Simulation(walkState.getLegFunctions(), walkState.getModel());
    }
    
}