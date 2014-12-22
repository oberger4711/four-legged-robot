package com.oberger.kruppelbotsimulation.mvc_model.localsearch;

import com.oberger.kruppelbotsimulation.mvc_model.localsearch.manipulator.IManipulator;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator.IEvaluator;
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
    private IManipulator<T> manipulator = null;
    private List<State<T>> neighboursCache = null;

    State(int generation, T innerState, IEvaluator<T> evaluator, IManipulator<T> manipulator) {
        if (evaluator == null || manipulator == null || innerState == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        this.generation = generation;
        this.innerState = innerState;
        this.evaluator = evaluator;
        this.manipulator = manipulator;
    }

    public State(T innerState, IEvaluator<T> weightedEvaluators, IManipulator<T> manipulator) {
        this(0, innerState, weightedEvaluators, manipulator);
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

        List<T> manipulatedInnerStates = manipulator.createNeighbours(innerState);
        for (T manipulatedInnerState : manipulatedInnerStates) {
            State<T> newNeighbourState = new State<>(generation + 1, manipulatedInnerState, evaluator, manipulator);
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
