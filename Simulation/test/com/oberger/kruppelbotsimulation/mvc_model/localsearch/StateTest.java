/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.localsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author ole
 */
@RunWith(MockitoJUnitRunner.class)
public class StateTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private State createState() {
        return new State();
    }

    private State createState(int generation, List<WeightedEvaluatable> weightedEvaluatables, List<IManipulatable> manipulatables) {
        return new State(generation, weightedEvaluatables, manipulatables);
    }

    private WeightedEvaluatable createFakeWeightedEvaluatable(float score, float scoreWeight) {
        WeightedEvaluatable fakeWeightedEvaluatable = Mockito.mock(WeightedEvaluatable.class);
        Mockito.doReturn(score).when(fakeWeightedEvaluatable).getScore();
        Mockito.doReturn(scoreWeight).when(fakeWeightedEvaluatable).getWeight();

        return fakeWeightedEvaluatable;
    }
    
    private IManipulatable createFakeManipulatable(State... neighbours) {
        IManipulatable fakeManipulatable = Mockito.mock(IManipulatable.class);
        Mockito.doReturn(Arrays.asList(neighbours)).when(fakeManipulatable).getNeighbours();
        
        return fakeManipulatable;
    }

    @Test
    public void constructor_OnPassEvaluatablesNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        State state = createState(0, null, Collections.<IManipulatable>emptyList());
    }

    @Test
    public void constructor_OnPassManipulatablesNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        State state = createState(0, Collections.<WeightedEvaluatable>emptyList(), null);
    }

    @Test
    public void addEvaluatable_OnPassNull_ThrowsIllegalArgumentException() {
        State state = createState();

        exception.expect(IllegalArgumentException.class);

        state.addWeightedEvaluatable(null);
    }

    @Test
    public void addEvaluatable_OnCall_AddsToEvaluatableList() {
        List<WeightedEvaluatable> weightedEvaluatables = new ArrayList<>();
        State state = createState(0, weightedEvaluatables, Collections.<IManipulatable>emptyList());
        WeightedEvaluatable fakeWeightedEvaluatable = createFakeWeightedEvaluatable(1, 1);

        state.addWeightedEvaluatable(fakeWeightedEvaluatable);
    }
    
    @Test
    public void getScore_WithoutEvaluatables_ThrowsIllegalStateException() {
        State state = createState();
        
        exception.expect(IllegalStateException.class);
        state.getScore();
    }
    
    @Test
    public void getScore_WithOneWeightedEvaluatable_ReturnsWeightedEvaluatableScore() {
        State state = createState();
        state.addWeightedEvaluatable(createFakeWeightedEvaluatable(12, 1));
        
        float returnedScore = state.getScore();
        
        assertEquals(12, returnedScore, 0.0001f);
    }
    
    @Test
    public void getScore_WithTwoWeightedEvaluatables_ReturnsWeightedScoreMean() {
        State state = createState();
        state.addWeightedEvaluatable(createFakeWeightedEvaluatable(1, 1));
        state.addWeightedEvaluatable(createFakeWeightedEvaluatable(2, 2));
        
        float returnedWeightedScore = state.getScore();
        
        assertEquals(1.66667, returnedWeightedScore, 0.0001f);
    }
    
    @Test
    public void getScore_WithThreeWeightedEvaluatables_ReturnWeightedScoreMean() {
        State state = createState();
        state.addWeightedEvaluatable(createFakeWeightedEvaluatable(1, 0.5f));
        state.addWeightedEvaluatable(createFakeWeightedEvaluatable(2, 2f));
        state.addWeightedEvaluatable(createFakeWeightedEvaluatable(3, 0.5f));
        
        float returnedWeightedScore = state.getScore();
        
        assertEquals(2, returnedWeightedScore, 0.0001f);
    }
    
    @Test
    public void getNeighbours_WithoutManipulatables_ReturnsEmptyList() {
        State state = createState();
        
        List<State> states = state.getNeighbours();
        
        assertTrue(states.isEmpty());
    }
    
    @Test
    public void getNeighbours_WithManipulatables_ReturnsUnitedListed() {
        State<State> state = createState();
        State neighbourState1 = createState();
        State neighbourState2 = createState();
        State neighbourState3 = createState();
        
        IManipulatable fakeManipulatable1 = createFakeManipulatable(neighbourState1, neighbourState2);
        IManipulatable fakeManipulatable2 = createFakeManipulatable(neighbourState3);
        state.addManipulatable(fakeManipulatable1);
        state.addManipulatable(fakeManipulatable2);
        
        List<State> resultNeighbours = state.getNeighbours();
        
        assertTrue(resultNeighbours.contains(neighbourState1));
        assertTrue(resultNeighbours.contains(neighbourState2));
        assertTrue(resultNeighbours.contains(neighbourState3));
    }
}
