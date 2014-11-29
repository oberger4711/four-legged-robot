/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.localsearch;

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
public class WeightedEvaluatableTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private WeightedEvaluatable createWeightedEvaluatable(IEvaluatable decorated, float weight) {
        return new WeightedEvaluatable(decorated, weight);
    }

    private IEvaluatable createFakeEvaluatable(float score) {
        IEvaluatable evaluatableMock = Mockito.mock(IEvaluatable.class);
        Mockito.when(evaluatableMock.getScore()).thenReturn(score);
        
        return evaluatableMock;
    }

    @Test
    public void constructor_OnPassDecoratedNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createWeightedEvaluatable(null, 1f);
    }

    @Test
    public void constructor_OnPassWeightUnderThreshold_ThrowsIllegalArgumentException() {
        float illegalWeight = WeightedEvaluatable.MINIMAL_WEIGHT_THRESHOLD / 2f;
        IEvaluatable evaluatable = createFakeEvaluatable(1);

        exception.expect(IllegalArgumentException.class);

        createWeightedEvaluatable(evaluatable, illegalWeight);
    }

    @Test
    public void constructor_OnCall_SetsWeight() {
        float weight = 0.5f;
        IEvaluatable evaluatable = createFakeEvaluatable(0.2f);
        WeightedEvaluatable weightedEvaluatable = createWeightedEvaluatable(evaluatable, weight);

        assertEquals(weight, weightedEvaluatable.getWeight(), 0.0001f);
    }

    @Test
    public void getScore_OnCall_ReturnsDecoratedScore() {
        IEvaluatable evaluatable = createFakeEvaluatable(0.5f);
        WeightedEvaluatable weightedEvaluatable = createWeightedEvaluatable(evaluatable, 0.5f);

        assertEquals(0.5f, weightedEvaluatable.getScore(), 0.00001f);
    }

}
