/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.localsearch;

import com.oberger.kruppelbotsimulation.mvc_model.localsearch.exitcriterium.ExitCriterium;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.exitcriterium.LocalMaximumExitCriterium;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author ole
 */
public class IntegrationTest {
    
    private final static int BEST_NUMBER = 5;
    
    @Test
    public void hillClimbing_OnCall_FindsLocalOptimum() {
        NumericInnerState startInnerState = new NumericInnerState(0);
        IEvaluator<NumericInnerState> evaluator = new NumericEvaluator();
        WeightedEvaluator<NumericInnerState> weightedEvaluator = new WeightedEvaluator<>(evaluator, 1);
        IManipulator<NumericInnerState> manipulator = new NumericManipulator();
        State<NumericInnerState> startState = new State<>(startInnerState, Arrays.asList(weightedEvaluator), Arrays.asList(manipulator));
        
        ExitCriterium exitCriterium = new LocalMaximumExitCriterium();
        LocalSearchAlgorithm<NumericInnerState> searchAlgo = new HillClimbing<>();
        
        State<NumericInnerState> optimumState = searchAlgo.run(startState, exitCriterium);
        
        assertEquals(5, optimumState.getInnerState().getNumber());
    }
    
    private static class NumericInnerState implements IImmutableInnerState {
        
        public final static int MAX_NUMBER = 10;
        
        private int number;
        
        public NumericInnerState(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }
        
    }

    private static class NumericEvaluator implements IEvaluator<NumericInnerState> {

        @Override
        public float getScore(NumericInnerState innerState) {
            return NumericInnerState.MAX_NUMBER - Math.abs(innerState.getNumber() - BEST_NUMBER);
        }
    }

    private static class NumericManipulator implements IManipulator<NumericInnerState> {

        public NumericManipulator() {
        }

        @Override
        public NumericInnerState createNeighbour(NumericInnerState originalInnerState) {
            int original = originalInnerState.getNumber();
            int manipulated = (original + 1) % NumericInnerState.MAX_NUMBER;
            
            return new NumericInnerState(manipulated);
        }
    }
    
}
