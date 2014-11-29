package com.oberger.kruppelbotsimulation.mvc_model.localsearch;

public abstract class LocalSearchAlgorithm {

    private State currentState;
    private ExitCriterium exitCriterium;

    public State run(State startState, ExitCriterium exitCriterium) {
        // TODO - implement LocalSearchAlgorithm.run
        throw new UnsupportedOperationException();
    }

    public abstract State getNextState(State currentState);

}
