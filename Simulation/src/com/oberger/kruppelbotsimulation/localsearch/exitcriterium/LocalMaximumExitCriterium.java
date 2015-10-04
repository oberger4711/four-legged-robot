/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.localsearch.exitcriterium;

import com.oberger.kruppelbotsimulation.localsearch.State;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author ole
 */
public class LocalMaximumExitCriterium extends ExitCriterium {

    @Override
    public void reset() {

    }

    @Override
    public boolean isFinishState(State state) {
	if (state == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}
	List<State> neighbours = state.getNeighbours();

	// Neighbour states all have lower score?
	return neighbours.stream().allMatch(new Predicate<State>() { // Unfortunately, Visual Paradigm does not understand lambda expressions.

	    public boolean test(State s) {
		return s.getScore() <= state.getScore();
	    }
	});
    }

}
