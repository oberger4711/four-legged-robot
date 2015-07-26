package com.oberger.kruppelbotsimulation.localsearch.evaluator;

public interface IEvaluator<T> {

    public float getScore(T innerState);

}
