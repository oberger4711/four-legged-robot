/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.localsearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

}
