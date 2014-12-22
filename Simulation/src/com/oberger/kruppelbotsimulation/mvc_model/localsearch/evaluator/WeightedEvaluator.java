/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator;

/**
 * Decorator for {@link IEvaluator} that provides additionally a weight.
 * @author ole
 * @param <T>
 */
public class WeightedEvaluator<T> implements IEvaluator<T> {
    
    static final float MINIMAL_WEIGHT_THRESHOLD = 0.001f;

    private IEvaluator<T> decorated = null;
    private float weight = 0;
    
    public WeightedEvaluator(IEvaluator<T> decorated, float weight) {
        if (decorated == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        if (weight < MINIMAL_WEIGHT_THRESHOLD) {
            throw new IllegalArgumentException("Weight must be higher than " + MINIMAL_WEIGHT_THRESHOLD + ".");
        }
        this.decorated = decorated;
        this.weight = weight;
    }
    
    @Override
    public float getScore(T evaluatable) {
        return decorated.getScore(evaluatable);
    }
    
    public float getWeight() {
        return weight;
    }
    
}
