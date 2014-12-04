/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.localsearch;

import java.util.List;

/**
 * Fake {@link LocalSearchAlgorithm} that always returns the first neighbour of the current state's neighbours or null if no.
 * @author ole
 * @param <T>
 */
public class FakeLocalSearchAlgorithm<T extends IImmutableInnerState<T>> extends LocalSearchAlgorithm<T> {

    public FakeLocalSearchAlgorithm() {
    }

    @Override
    State<T> getNextState(List<State<T>> neighbourStates) {
        State<T> nextState = null;
        if (neighbourStates != null || !neighbourStates.isEmpty()) {
            nextState = neighbourStates.get(0);
        }
        
        return nextState;
    }
    
}
