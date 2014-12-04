package com.oberger.kruppelbotsimulation.mvc_model.localsearch;

import java.util.List;

public abstract class LocalSearchAlgorithm<T extends IImmutableInnerState<T>> {

    public State<T> run(State<T> startState, IExitCriterium exitCriterium) {
        if (startState == null || exitCriterium == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        State<T> currentState = startState;
        List<State<T>> neighbours = null;
        while (!(neighbours = currentState.getNeighbours()).isEmpty() && !exitCriterium.isFinishState(currentState)) {
            currentState = getNextState(neighbours);
        }

        return currentState;
    }

    abstract State<T> getNextState(List<State<T>> neighbourStates);

}
