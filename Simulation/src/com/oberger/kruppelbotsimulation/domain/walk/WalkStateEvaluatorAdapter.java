/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.walk;

import com.oberger.kruppelbotsimulation.domain.simulation.Simulation;
import com.oberger.kruppelbotsimulation.localsearch.evaluator.IEvaluator;

/**
 *
 * @author ole
 */
public class WalkStateEvaluatorAdapter implements IEvaluator<WalkState> {

    private IEvaluator<Simulation> adaptee = null;

    public WalkStateEvaluatorAdapter(IEvaluator<Simulation> adaptee) {
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
