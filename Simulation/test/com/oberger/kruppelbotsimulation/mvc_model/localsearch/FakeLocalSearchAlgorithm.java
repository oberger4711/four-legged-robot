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
public class FakeLocalSearchAlgorithm<T extends State<T>> extends LocalSearchAlgorithm<T> {

    public FakeLocalSearchAlgorithm() {
    }
    
    @Override
    protected T getNextState(List<T> neighbours) {
        T neighbour = null;
        if (!neighbours.isEmpty()) {
            neighbour = neighbours.get(0);
        }
        return neighbour;
    }
    
}
