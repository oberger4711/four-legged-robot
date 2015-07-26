/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator;

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
public class WeightedEvaluatorTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private WeightedEvaluator createWeightedEvaluator(IEvaluator decorated, float weight) {
        return new WeightedEvaluator(decorated, weight);
    }

    private IEvaluator createFakeEvaluator(float score) {
        IEvaluator fakeEvaluator = Mockito.mock(IEvaluator.class);
        Mockito.when(fakeEvaluator.getScore(null)).thenReturn(score);
        
        return fakeEvaluator;
    }

    @Test
    public void constructor_OnPassDecoratedNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createWeightedEvaluator(null, 1f);
    }

    @Test
    public void constructor_OnPassWeightUnderThreshold_ThrowsIllegalArgumentException() {
        float illegalWeight = WeightedEvaluator.MINIMAL_WEIGHT_THRESHOLD / 2f;
        IEvaluator evaluator = createFakeEvaluator(1);

        exception.expect(IllegalArgumentException.class);

        createWeightedEvaluator(evaluator, illegalWeight);
    }

    @Test
    public void constructor_OnCall_SetsWeight() {
        float weight = 0.5f;
        IEvaluator evaluator = createFakeEvaluator(0.2f);
        WeightedEvaluator weightedEvaluator = createWeightedEvaluator(evaluator, weight);

        assertEquals(weight, weightedEvaluator.getWeight(), 0.0001f);
    }

    @Test
    public void getScore_OnCall_ReturnsDecoratedScore() {
        IEvaluator evaluator = createFakeEvaluator(0.5f);
        WeightedEvaluator weightedEvaluator = createWeightedEvaluator(evaluator, 0.5f);

        float scoreResult = weightedEvaluator.getScore(null);
        
        assertEquals(0.5f, scoreResult, 0.00001f);
    }

}
