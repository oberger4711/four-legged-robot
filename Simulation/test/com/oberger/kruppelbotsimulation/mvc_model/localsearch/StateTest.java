/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.localsearch;

import com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator.WeightedEvaluator;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator.IEvaluator;
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

    private State createState(int generation, IImmutableInnerState innerState, IEvaluator evaluator, List<IManipulator> manipulators) {
        return new State(generation, innerState, evaluator, manipulators);
    }

    private IImmutableInnerState createFakeInnerState() {
        IImmutableInnerState fakeInnerState = Mockito.mock(IImmutableInnerState.class);

        return fakeInnerState;
    }

    private IManipulator createFakeManipulator(IImmutableInnerState parameter, IImmutableInnerState... manipulatedParameterCopies) {
        IManipulator fakeManipulator = Mockito.mock(IManipulator.class);
        Mockito.doReturn(new ArrayList(Arrays.asList(manipulatedParameterCopies))).when(fakeManipulator).createNeighbours(parameter);

        return fakeManipulator;
    }

    private IEvaluator createFakeEvaluator(float score) {
        IEvaluator fake = Mockito.mock(IEvaluator.class);
        
        Mockito.doReturn(score).when(fake).getScore(Mockito.any());
        
        return fake;
    }

    private List<IManipulator> createDummyManipulators() {
        return new ArrayList(Arrays.asList(createFakeManipulator(null, (IImmutableInnerState)null)));
    }

    @Test
    public void constructor_OnPassInnerStateNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createState(0, null, createFakeEvaluator(0), createDummyManipulators());
    }

    @Test
    public void constructor_OnPassEvaluatorNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createState(0, createFakeInnerState(), null, createDummyManipulators());
    }

    @Test
    public void constructor_OnPassManipulatorsNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createState(0, createFakeInnerState(), createFakeEvaluator(0), null);
    }

    @Test
    public void constructor_OnPassEmptyManipulators_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createState(0, createFakeInnerState(), createFakeEvaluator(0), Collections.emptyList());
    }
    
    @Test
    public void constructor_OnCall_CopiesManipulators() {
        IImmutableInnerState innerState = createFakeInnerState();
        List<IManipulator> manipulatorsPassedInConstructor = new ArrayList<>(Arrays.asList(createFakeManipulator(innerState, createFakeInnerState())));
        
        State state = createState(0, innerState, createFakeEvaluator(0), manipulatorsPassedInConstructor);
        
        manipulatorsPassedInConstructor.clear(); // This should not clear the copied list.
        state.getNeighbours(); // No exception should be thrown.
    }

    @Test
    public void getNeighbours_WithManipulators_ReturnsUnitedListed() {
        IImmutableInnerState fakeInnerState = createFakeInnerState();
        IImmutableInnerState fakeNeighbourInnerState1 = createFakeInnerState();
        IImmutableInnerState fakeNeighbourInnerState2 = createFakeInnerState();
        IImmutableInnerState fakeNeighbourInnerState3 = createFakeInnerState();
        IManipulator fakeManipulator1 = createFakeManipulator(fakeInnerState, fakeNeighbourInnerState1);
        IManipulator fakeManipulator2 = createFakeManipulator(fakeInnerState, fakeNeighbourInnerState2, fakeNeighbourInnerState3);
        List<IManipulator> manipulators = Arrays.asList(fakeManipulator1, fakeManipulator2);
        State state = createState(0, fakeInnerState, createFakeEvaluator(0), manipulators);

        List<State> resultNeighbours = state.getNeighbours();

        assertTrue(resultNeighbours.stream().anyMatch(s -> s.getInnerState() == fakeNeighbourInnerState1));
        assertTrue(resultNeighbours.stream().anyMatch(s -> s.getInnerState() == fakeNeighbourInnerState2));
        assertTrue(resultNeighbours.stream().anyMatch(s -> s.getInnerState() == fakeNeighbourInnerState3));
    }

    @Test
    public void getNeighbours_OnSecondCall_UsesCacheAndReturnsSameValue() {
        IImmutableInnerState fakeInnerState = createFakeInnerState();
        IImmutableInnerState fakeNeighbourInnerState = createFakeInnerState();
        IManipulator fakeManipulator = createFakeManipulator(fakeInnerState, fakeNeighbourInnerState);
        State state = createState(0, fakeInnerState, createFakeEvaluator(0), Arrays.asList(fakeManipulator));

        List<State> uncachedNeighbours = state.getNeighbours();
        Mockito.verify(fakeManipulator, Mockito.times(1)).createNeighbours(fakeInnerState);

        List<State> cachedNeighbours = state.getNeighbours();
        Mockito.verify(fakeManipulator, Mockito.times(1)).createNeighbours(fakeInnerState);

        assertEquals(uncachedNeighbours, cachedNeighbours);
    }
}
