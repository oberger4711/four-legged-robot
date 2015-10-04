package com.oberger.kruppelbotsimulation.localsearch;

import com.oberger.kruppelbotsimulation.localsearch.exitcriterium.ExitCriterium;
import java.util.List;

public class HillClimbing<T> extends LocalSearchAlgorithm<T> {

    public HillClimbing(ExitCriterium exitCriterium) {
	super(exitCriterium);
    }

    @Override
    State<T> getNextState(List<State<T>> neighbourStates) {
	if (neighbourStates == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}
	State<T> bestNeighbour = null;

	float bestNeighbourScore = Float.NEGATIVE_INFINITY;
	for (State<T> currentNeighbour : neighbourStates) {
	    float currentNeighbourScore = currentNeighbour.getScore();
	    if (currentNeighbourScore > bestNeighbourScore) {
		bestNeighbour = currentNeighbour;
		bestNeighbourScore = currentNeighbourScore;
	    }
	}

	return bestNeighbour;
    }

}
