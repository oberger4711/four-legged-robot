package com.oberger.kruppelbotsimulation.localsearch;

import com.oberger.kruppelbotsimulation.localsearch.exitcriterium.ExitCriterium;
import java.util.List;

public abstract class LocalSearchAlgorithm<T> {
    
    private ExitCriterium exitCriterium = null;

    public LocalSearchAlgorithm(ExitCriterium exitCriterium) {
	if (exitCriterium == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}
	this.exitCriterium = exitCriterium;
    }
    
    public State<T> run(State<T> startState) {
	if (startState == null || exitCriterium == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}
	exitCriterium.reset();
	State<T> currentState = startState;
	List<State<T>> neighbours = null;
	while (!(neighbours = currentState.getNeighbours()).isEmpty() && !exitCriterium.isFinishState(currentState)) {
	    currentState = getNextState(neighbours);
	}

	return currentState;
    }

    abstract State<T> getNextState(List<State<T>> neighbourStates);

}
