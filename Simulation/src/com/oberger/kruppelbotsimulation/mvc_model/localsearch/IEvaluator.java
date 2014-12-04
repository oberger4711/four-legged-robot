package com.oberger.kruppelbotsimulation.mvc_model.localsearch;

public interface IEvaluator<T> {

    public float getScore(T innerState);

}
