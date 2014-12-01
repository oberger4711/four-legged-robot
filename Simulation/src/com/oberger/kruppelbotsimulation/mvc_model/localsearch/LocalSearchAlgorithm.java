package com.oberger.kruppelbotsimulation.mvc_model.localsearch;

import java.util.List;

public abstract class LocalSearchAlgorithm<T extends State> {

    public T run(T startState, IExitCriterium exitCriterium) {
        if (startState == null || exitCriterium == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        T currentState = startState;
        List<T> neighbours = null;
        while (!(neighbours = currentState.getNeighbours()).isEmpty() && !exitCriterium.isFinishState(currentState)) {
            currentState = getNextState(neighbours);
        }

        return currentState;
    }

    abstract T getNextState(List<T> neighbours);

}
