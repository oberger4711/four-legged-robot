/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.localsearch;

import com.oberger.kruppelbotsimulation.mvc_model.localsearch.manipulator.IManipulator;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator.IEvaluator;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator.WeightedEvaluator;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator.WeightedEvaluatorGroup;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.manipulator.ManipulatorGroup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
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

    private State createState(int generation, Object innerState, IEvaluator evaluator, IManipulator manipulator) {
        return new State(generation, innerState, evaluator, manipulator);
    }

    private Object createDummyInnerState() {
        return Mockito.mock(Object.class);
    }

    private IManipulator createFakeManipulator(Object parameter, Object... manipulatedParameterCopies) {
        IManipulator fakeManipulator = Mockito.mock(IManipulator.class);
        Mockito.doReturn(new ArrayList(Arrays.asList(manipulatedParameterCopies))).when(fakeManipulator).createNeighbours(parameter);

        return fakeManipulator;
    }

    private IEvaluator createFakeEvaluator(float score) {
        IEvaluator fake = Mockito.mock(IEvaluator.class);

        Mockito.doReturn(score).when(fake).getScore(Mockito.any());

        return fake;
    }

    private IManipulator createDummyManipulator() {
        return createFakeManipulator(null, (Object) null);
    }

    @Test
    public void constructor_OnPassGenerationNegative_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        createState(-1, createDummyInnerState(), createFakeEvaluator(0), createDummyManipulator());
    }
    
    @Test
    public void constructor_OnPassInnerStateNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createState(0, null, createFakeEvaluator(0), createDummyManipulator());
    }

    @Test
    public void constructor_OnPassEvaluatorNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createState(0, createDummyInnerState(), null, createDummyManipulator());
    }

    @Test
    public void constructor_OnPassManipulatorsNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createState(0, createDummyInnerState(), createFakeEvaluator(0), null);
    }
    
    @Test
    public void getNeighbours_OnSecondCall_UsesCacheAndReturnsSameValue() {
        Object fakeInnerState = createDummyInnerState();
        Object fakeNeighbourInnerState = createDummyInnerState();
        IManipulator fakeManipulator = createFakeManipulator(fakeInnerState, fakeNeighbourInnerState);
        State testee = createState(0, fakeInnerState, createFakeEvaluator(0), fakeManipulator);

        List<State> uncachedNeighbours = testee.getNeighbours();
        Mockito.verify(fakeManipulator, Mockito.times(1)).createNeighbours(fakeInnerState);

        List<State> cachedNeighbours = testee.getNeighbours();
        Mockito.verify(fakeManipulator, Mockito.times(1)).createNeighbours(fakeInnerState);

        assertEquals(uncachedNeighbours, cachedNeighbours);
    }

    @Test
    public void getScore_OnSecondCall_UsesCacheAndReturnsSameValue() {
        IEvaluator fakeWeightedEvaluator = createFakeEvaluator(12);
        Object fakeInnerState = createDummyInnerState();
        State testee = new State(fakeInnerState, fakeWeightedEvaluator, createDummyManipulator());

        float uncachedScore = testee.getScore();
        Mockito.verify(fakeWeightedEvaluator, Mockito.times(1)).getScore(fakeInnerState);

        float cachedScore = testee.getScore();
        Mockito.verify(fakeWeightedEvaluator, Mockito.times(1)).getScore(fakeInnerState);

        assertEquals(uncachedScore, cachedScore, 0.00001f);
    }

}
