package com.oberger.kruppelbotsimulation.mvc_model.localsearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Represent a state that can be optimized and evaluated using a heuristic.
 *
 * @author ole
 * @param <T> The type of the object in this state.
 */
public class State<T extends IImmutableInnerState<T>> {

    private int generation = 0;
    private T innerState = null;
    private List<WeightedEvaluator<T>> weightedEvaluators = null;
    private List<IManipulator<T>> manipulators = null;
    private Float scoreCache = null;
    private List<State<T>> neighboursCache = null;

    State(int generation, T innerState, List<WeightedEvaluator<T>> weightedEvaluators, List<IManipulator<T>> manipulators) {
        if (weightedEvaluators == null || manipulators == null || innerState == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        if (weightedEvaluators.isEmpty()) {
            throw new IllegalArgumentException("Passing empty weightedEvaluators is not allowed.");
        }
        if (manipulators.isEmpty()) {
            throw new IllegalArgumentException("Passing empty manipulators is not allowed");
        }
        this.generation = generation;
        this.innerState = innerState;
        this.weightedEvaluators = new ArrayList<>(weightedEvaluators);
        this.manipulators = new ArrayList<>(manipulators);
        scoreCache = null;

    }

    protected State(int generation, T innerState) {
        this(generation, innerState, new ArrayList<>(), new ArrayList<>());
    }

    public State(T innerState) {
        this(0, innerState);
    }

    public float getScore() {
        float score;
        if (scoreCache == null) {
            score = createEvaluatedScore();
            scoreCache = score;
        }
        else {
            score = scoreCache;
        }

        return score;
    }
    
    private float createEvaluatedScore() {
        if (weightedEvaluators.isEmpty()) {
            throw new IllegalStateException("No WeightedEvaluators added.");
        }
        float weightedScore = 0;
        float weightSum = 0;

        for (WeightedEvaluator weightedEvaluator : weightedEvaluators) {
            weightedScore += weightedEvaluator.getScore(innerState) * weightedEvaluator.getWeight();
            weightSum += weightedEvaluator.getWeight();
        }
        weightedScore /= weightSum;
        
        return weightedScore;
    }

    public List<State<T>> getNeighbours() {
        List<State<T>> neighbourStates = null;
        if (neighboursCache == null) {
            neighbourStates = createManipulatedNeighbours();
            neighboursCache = neighbourStates;
        } else {
            neighbourStates = neighboursCache;
        }

        return neighbourStates;
    }

    private List<State<T>> createManipulatedNeighbours() {
        List<State<T>> neighbourStates = new LinkedList<>();
        for (IManipulator<T> manipulator : manipulators) {
            T manipulatedInnerState = manipulator.manipulateCopy(innerState);
            State<T> newNeighbourState = new State<>(generation + 1, manipulatedInnerState, weightedEvaluators, manipulators);
            neighbourStates.add(newNeighbourState);
        }
        
        return neighbourStates;
    }

    public int getGeneration() {
        return generation;
    }

    public T getInnerState() {
        return innerState;
    }
    
}
