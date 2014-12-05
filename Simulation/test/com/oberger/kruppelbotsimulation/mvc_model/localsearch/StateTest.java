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

    private State createState(int generation, IImmutableInnerState innerState, List<WeightedEvaluator> weightedEvaluators, List<IManipulator> manipulators) {
        return new State(generation, innerState, weightedEvaluators, manipulators);
    }

    private IImmutableInnerState createFakeInnerState() {
        IImmutableInnerState fakeInnerState = Mockito.mock(IImmutableInnerState.class);

        return fakeInnerState;
    }

    private WeightedEvaluator createFakeWeightedEvaluator(IImmutableInnerState evaluatable, float score, float scoreWeight) {
        WeightedEvaluator fakeWeightedEvaluator = Mockito.mock(WeightedEvaluator.class);
        Mockito.doReturn(score).when(fakeWeightedEvaluator).getScore(evaluatable);
        Mockito.doReturn(scoreWeight).when(fakeWeightedEvaluator).getWeight();

        return fakeWeightedEvaluator;
    }

    private IManipulator createFakeManipulator(IImmutableInnerState parameter, IImmutableInnerState manipulatedParameterCopy) {
        IManipulator fakeManipulator = Mockito.mock(IManipulator.class);
        Mockito.doReturn(manipulatedParameterCopy).when(fakeManipulator).createNeighbour(parameter);

        return fakeManipulator;
    }

    private List<WeightedEvaluator> createDummyWeightedEvaluators() {
        WeightedEvaluator fakeWeightedEvaluator = Mockito.mock(WeightedEvaluator.class);
        return new ArrayList(Arrays.asList(fakeWeightedEvaluator));
    }

    private List<IManipulator> createDummyManipulators() {
        return new ArrayList(Arrays.asList(createFakeManipulator(null, null)));
    }

    @Test
    public void constructor_OnPassInnerStateNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createState(0, null, createDummyWeightedEvaluators(), createDummyManipulators());
    }

    @Test
    public void constructor_OnPassWeightedEvaluatorsNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createState(0, createFakeInnerState(), null, createDummyManipulators());
    }

    @Test
    public void constructor_OnPassEmptyWeightedEvaluators_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createState(0, createFakeInnerState(), Collections.<WeightedEvaluator>emptyList(), createDummyManipulators());
    }

    @Test
    public void constructor_OnPassManipulatorsNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createState(0, createFakeInnerState(), createDummyWeightedEvaluators(), null);
    }

    @Test
    public void constructor_OnPassEmptyManipulators_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createState(0, createFakeInnerState(), createDummyWeightedEvaluators(), Collections.emptyList());
    }
    
    @Test
    public void constructor_OnCall_CopiesWeightedEvaluators() {
        List<WeightedEvaluator> weightedEvaluatorsPassedInConstructor = createDummyWeightedEvaluators();
        
        State state = createState(0, createFakeInnerState(), weightedEvaluatorsPassedInConstructor, createDummyManipulators());
        
        weightedEvaluatorsPassedInConstructor.clear(); // This should not clear the copied list.
        state.getScore(); // No exception should be thrown.
    }
    
    @Test
    public void constructor_OnCall_CopiesManipulators() {
        IImmutableInnerState innerState = createFakeInnerState();
        List<IManipulator> manipulatorsPassedInConstructor = new ArrayList<>(Arrays.asList(createFakeManipulator(innerState, createFakeInnerState())));
        
        State state = createState(0, innerState, createDummyWeightedEvaluators(), manipulatorsPassedInConstructor);
        
        manipulatorsPassedInConstructor.clear(); // This should not clear the copied list.
        state.getNeighbours(); // No exception should be thrown.
    }

    @Test
    public void getScore_WithOneWeightedEvaluator_ReturnsWeightedEvaluatorScore() {
        IImmutableInnerState fakeInnerState = createFakeInnerState();
        List<WeightedEvaluator> weightedEvaluators = Arrays.asList(createFakeWeightedEvaluator(fakeInnerState, 0.7f, 1));
        State state = createState(0, fakeInnerState, weightedEvaluators, createDummyManipulators());

        float returnedScore = state.getScore();

        assertEquals(0.7f, returnedScore, 0.0001f);
    }

    @Test
    public void getScore_WithTwoWeightedEvaluators_ReturnsWeightedScoreMean() {
        IImmutableInnerState fakeInnerState = createFakeInnerState();
        List<WeightedEvaluator> weightedEvaluators = new ArrayList<>();
        weightedEvaluators.add(createFakeWeightedEvaluator(fakeInnerState, 1, 1));
        weightedEvaluators.add(createFakeWeightedEvaluator(fakeInnerState, 2, 2));
        State state = createState(0, fakeInnerState, weightedEvaluators, createDummyManipulators());

        float returnedWeightedScore = state.getScore();

        assertEquals(1.66667, returnedWeightedScore, 0.0001f);
    }

    @Test
    public void getScore_WithThreeWeightedEvaluators_ReturnWeightedScoreMean() {
        IImmutableInnerState fakeInnerState = createFakeInnerState();
        List<WeightedEvaluator> weightedEvaluators = new ArrayList<>();
        weightedEvaluators.add(createFakeWeightedEvaluator(fakeInnerState, 1, 0.5f));
        weightedEvaluators.add(createFakeWeightedEvaluator(fakeInnerState, 2, 2f));
        weightedEvaluators.add(createFakeWeightedEvaluator(fakeInnerState, 3, 0.5f));
        State state = createState(0, fakeInnerState, weightedEvaluators, createDummyManipulators());

        float returnedWeightedScore = state.getScore();

        assertEquals(2, returnedWeightedScore, 0.0001f);
    }

    @Test
    public void getScore_OnSecondCall_UsesCacheAndReturnsSameValue() {
        IImmutableInnerState fakeInnerState = createFakeInnerState();
        WeightedEvaluator fakeWeightedEvaluator = createFakeWeightedEvaluator(fakeInnerState, 12, 1);
        State state = createState(0, fakeInnerState, Arrays.asList(fakeWeightedEvaluator), createDummyManipulators());

        float uncachedScore = state.getScore();
        Mockito.verify(fakeWeightedEvaluator, Mockito.times(1)).getScore(fakeInnerState);

        float cachedScore = state.getScore();
        Mockito.verify(fakeWeightedEvaluator, Mockito.times(1)).getScore(fakeInnerState);

        assertEquals(uncachedScore, cachedScore, 0.00001f);
    }

    @Test
    public void getNeighbours_WithManipulators_ReturnsUnitedListed() {
        IImmutableInnerState fakeInnerState = createFakeInnerState();
        IImmutableInnerState fakeNeighbourInnerState1 = createFakeInnerState();
        IImmutableInnerState fakeNeighbourInnerState2 = createFakeInnerState();
        IManipulator fakeManipulator1 = createFakeManipulator(fakeInnerState, fakeNeighbourInnerState1);
        IManipulator fakeManipulator2 = createFakeManipulator(fakeInnerState, fakeNeighbourInnerState2);
        List<IManipulator> manipulators = Arrays.asList(fakeManipulator1, fakeManipulator2);
        State state = createState(0, fakeInnerState, createDummyWeightedEvaluators(), manipulators);

        List<State> resultNeighbours = state.getNeighbours();

        assertTrue(resultNeighbours.stream().anyMatch(s -> s.getInnerState() == fakeNeighbourInnerState1));
        assertTrue(resultNeighbours.stream().anyMatch(s -> s.getInnerState() == fakeNeighbourInnerState2));
    }

    @Test
    public void getNeighbours_OnSecondCall_UsesCacheAndReturnsSameValue() {
        IImmutableInnerState fakeInnerState = createFakeInnerState();
        IImmutableInnerState fakeNeighbourInnerState = createFakeInnerState();
        IManipulator fakeManipulator = createFakeManipulator(fakeInnerState, fakeNeighbourInnerState);
        State state = createState(0, fakeInnerState, createDummyWeightedEvaluators(), Arrays.asList(fakeManipulator));

        List<State> uncachedNeighbours = state.getNeighbours();
        Mockito.verify(fakeManipulator, Mockito.times(1)).createNeighbour(fakeInnerState);

        List<State> cachedNeighbours = state.getNeighbours();
        Mockito.verify(fakeManipulator, Mockito.times(1)).createNeighbour(fakeInnerState);

        assertEquals(uncachedNeighbours, cachedNeighbours);
    }
}
