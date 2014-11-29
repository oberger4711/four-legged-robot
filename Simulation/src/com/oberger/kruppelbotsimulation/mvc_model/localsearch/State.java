package com.oberger.kruppelbotsimulation.mvc_model.localsearch;

import java.util.ArrayList;
import java.util.List;

public class State implements IEvaluatable, IManipulatable {

    private List<WeightedEvaluatable> weightedEvaluatables = null;
    private List<IManipulatable> manipulatables = null;
    private int generation = 0;
    
    State(int generation, List<WeightedEvaluatable> weightedEvaluatables, List<IManipulatable> manipulatables) {
        if (weightedEvaluatables == null || manipulatables == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        this.generation = generation;
        this.weightedEvaluatables = weightedEvaluatables;
        this.manipulatables = manipulatables;
    }
    
    protected State(int generation) {
        this(generation, new ArrayList<>(), new ArrayList<>());
    }
    
    public State() {
        this(0);
    }
    
    protected void addWeightedEvaluatable(WeightedEvaluatable weightedEvaluatable) {
        if (weightedEvaluatable == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        weightedEvaluatables.add(weightedEvaluatable);
    }
    
    protected void addManipulatable(IManipulatable manipulatable) {
        manipulatables.add(manipulatable);
    }

    @Override
    public float getScore() {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public List<State> getNeighbours() {
        // TODO - implement State.getNeighbours
        throw new UnsupportedOperationException();
    }

    public int getGeneration() {
        return generation;
    }
    
}
