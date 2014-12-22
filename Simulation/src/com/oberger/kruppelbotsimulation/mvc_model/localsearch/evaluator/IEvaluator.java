package com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator;

public interface IEvaluator<T> {

    public float getScore(T innerState);

}
