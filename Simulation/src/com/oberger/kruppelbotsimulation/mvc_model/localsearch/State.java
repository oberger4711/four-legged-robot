package com.oberger.kruppelbotsimulation.mvc_model.localsearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Represent a state that can be optimized and evaluated using a heuristic.
 * @author ole
 * @param <T> The type of the neighbour {@link State}.
 */
public class State<T extends State<T>> implements IEvaluatable, IManipulatable<T> {

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
        if (weightedEvaluatables.isEmpty()) {
            throw new IllegalStateException("No WeightedEvaluatables added.");
        }
        float weightedScore = 0;
        float weightSum = 0;
        
        for (WeightedEvaluatable weightedEvaluatable : weightedEvaluatables) {
            weightedScore += weightedEvaluatable.getScore() * weightedEvaluatable.getWeight();
            weightSum += weightedEvaluatable.getWeight();
        }
        weightedScore /= weightSum;
        
        return weightedScore;
    }

    @Override
    public List<T> getNeighbours() {
        List<T> neighbours = new LinkedList<>();
        for (IManipulatable<T> manipulatable : manipulatables) {
            neighbours.addAll(manipulatable.getNeighbours());
        }
        
        return neighbours;
    }

    public int getGeneration() {
        return generation;
    }
    
}
