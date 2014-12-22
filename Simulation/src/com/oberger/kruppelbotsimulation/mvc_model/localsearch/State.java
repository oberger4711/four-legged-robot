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
public class State<T extends IImmutableInnerState> {

    private int generation = 0;
    private T innerState = null;
    private IEvaluator<T> evaluator = null;
    private List<IManipulator<T>> manipulators = null;
    private List<State<T>> neighboursCache = null;

    State(int generation, T innerState, IEvaluator<T> evaluator, List<IManipulator<T>> manipulators) {
        if (evaluator == null || manipulators == null || innerState == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        if (manipulators.isEmpty()) {
            throw new IllegalArgumentException("Passing empty manipulators is not allowed");
        }
        this.generation = generation;
        this.innerState = innerState;
        this.evaluator = evaluator;
        this.manipulators = new ArrayList<>(manipulators);
    }

    public State(T innerState, IEvaluator<T> weightedEvaluators, List<IManipulator<T>> manipulators) {
        this(0, innerState, weightedEvaluators, manipulators);
    }

    public float getScore() {
        return evaluator.getScore(innerState);
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
            T manipulatedInnerState = manipulator.createNeighbour(innerState);
            State<T> newNeighbourState = new State<>(generation + 1, manipulatedInnerState, evaluator, manipulators);
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
