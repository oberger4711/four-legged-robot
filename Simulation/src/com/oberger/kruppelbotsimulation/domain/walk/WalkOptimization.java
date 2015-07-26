package com.oberger.kruppelbotsimulation.domain.walk;

import com.oberger.kruppelbotsimulation.localsearch.HillClimbing;
import com.oberger.kruppelbotsimulation.localsearch.LocalSearchAlgorithm;
import com.oberger.kruppelbotsimulation.localsearch.State;
import com.oberger.kruppelbotsimulation.localsearch.evaluator.IEvaluator;
import com.oberger.kruppelbotsimulation.localsearch.manipulator.IManipulator;

public class WalkOptimization {
    
    public static void main(String[] args) {
        WalkState initInnerState = null; // TODO
        IEvaluator<WalkState> evaluator = null; // TODO
        IManipulator<WalkState> manipulator = null; // TODO
        State<WalkState> initState = new State<>(initInnerState, evaluator, manipulator);
        LocalSearchAlgorithm<WalkState> localSearchAlgo = new HillClimbing<>();
        
        System.out.println("Done.");
    }
    
}