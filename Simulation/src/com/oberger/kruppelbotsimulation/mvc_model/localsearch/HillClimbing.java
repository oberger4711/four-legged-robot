package com.oberger.kruppelbotsimulation.mvc_model.localsearch;

import java.util.List;

public class HillClimbing<T extends State<T>> extends LocalSearchAlgorithm<T> {

    @Override
    T getNextState(List<T> neighbours) {
        if (neighbours == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        T bestNeighbour = null;
        float bestNeighbourScore = Float.NEGATIVE_INFINITY;
        for (T currentNeighbour : neighbours) {
            float currentNeighbourScore = currentNeighbour.getScore();
            if (currentNeighbourScore > bestNeighbourScore) {
                bestNeighbour = currentNeighbour;
                bestNeighbourScore = currentNeighbourScore;
            }
        }
        
        return bestNeighbour;
    }

}
