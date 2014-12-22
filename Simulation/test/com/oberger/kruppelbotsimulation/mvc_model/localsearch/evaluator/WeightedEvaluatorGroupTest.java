/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author ole
 */
@RunWith(MockitoJUnitRunner.class)
public class WeightedEvaluatorGroupTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private WeightedEvaluatorGroup createWeightedEvaluatorGroup(List<WeightedEvaluator> evaluators) {
        return new WeightedEvaluatorGroup(evaluators);
    }
    
    private WeightedEvaluator createFakeWeightedEvaluator(float score, float weight) {
        WeightedEvaluator created = Mockito.mock(WeightedEvaluator.class);
        
        Mockito.doReturn(score).when(created).getScore(Mockito.any());
        Mockito.doReturn(weight).when(created).getWeight();
        
        return created;
    }
    
    @Test
    public void constructor_OnPassNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        WeightedEvaluatorGroup testee = createWeightedEvaluatorGroup(null);
    }
    
    @Test
    public void constructor_OnPassEmptyList_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        WeightedEvaluatorGroup testee = createWeightedEvaluatorGroup(Collections.emptyList());
    }
    
    
    @Test
    public void constructor_OnCall_CopiesEvaluatorList() {
        List<WeightedEvaluator> fakeWeightedEvaluators = new ArrayList<>(Arrays.asList(createFakeWeightedEvaluator(1, 1)));
        WeightedEvaluatorGroup testee = createWeightedEvaluatorGroup(fakeWeightedEvaluators);
        
        List<WeightedEvaluator> returned = testee.getEvaluators();
        
        assertFalse(returned == fakeWeightedEvaluators);
    }
    
    @Test
    public void getEvaluators_OnCall_ReturnsUnmodifiableList() {
        List<WeightedEvaluator> fakeWeightedEvaluators = new ArrayList<>(Arrays.asList(createFakeWeightedEvaluator(1, 1)));
        WeightedEvaluatorGroup testee = createWeightedEvaluatorGroup(fakeWeightedEvaluators);
        
        List<WeightedEvaluator> returned = testee.getEvaluators();
        
        exception.expect(UnsupportedOperationException.class);
        
        returned.clear();
    }
    
    @Test
    public void getScore_WithOneWeightedEvaluator_ReturnsEvaluatorsScore() {
        List<WeightedEvaluator> fakeWeightedEvaluators = new ArrayList<>(Arrays.asList(createFakeWeightedEvaluator(1, 1)));
        WeightedEvaluatorGroup testee = createWeightedEvaluatorGroup(fakeWeightedEvaluators);
        
        float returnedScore = testee.getScore(null);
        
        assertEquals(1, returnedScore, 0.0001);
    }
    
    @Test
    public void getScore_WithTwoWeightedEvaluator_ReturnsEvaluatorsScore() {
        List<WeightedEvaluator> fakeWeightedEvaluators = new ArrayList<>(Arrays.asList(createFakeWeightedEvaluator(1, 1)));
        WeightedEvaluatorGroup testee = createWeightedEvaluatorGroup(fakeWeightedEvaluators);
        
        float returnedScore = testee.getScore(null);
        
        assertEquals(1, returnedScore, 0.0001);
    }
    
    @Test
    public void getScore_WithOneWeightedEvaluator_ReturnsWeightedEvaluatorScore() {
        List<WeightedEvaluator> weightedEvaluators = Arrays.asList(createFakeWeightedEvaluator(0.7f, 1));
        WeightedEvaluatorGroup testee = createWeightedEvaluatorGroup(weightedEvaluators);

        float returnedScore = testee.getScore(null);

        assertEquals(0.7f, returnedScore, 0.0001f);
    }

    @Test
    public void getScore_WithTwoWeightedEvaluators_ReturnsWeightedScoreMean() {
        List<WeightedEvaluator> weightedEvaluators = new ArrayList<>();
        weightedEvaluators.add(createFakeWeightedEvaluator(1, 1));
        weightedEvaluators.add(createFakeWeightedEvaluator(2, 2));
        WeightedEvaluatorGroup testee = createWeightedEvaluatorGroup(weightedEvaluators);

        float returnedWeightedScore = testee.getScore(null);

        assertEquals(1.66667, returnedWeightedScore, 0.0001f);
    }

    @Test
    public void getScore_WithThreeWeightedEvaluators_ReturnWeightedScoreMean() {
        List<WeightedEvaluator> weightedEvaluators = new ArrayList<>();
        weightedEvaluators.add(createFakeWeightedEvaluator(1, 0.5f));
        weightedEvaluators.add(createFakeWeightedEvaluator(2, 2f));
        weightedEvaluators.add(createFakeWeightedEvaluator(3, 0.5f));
        WeightedEvaluatorGroup testee = new WeightedEvaluatorGroup(weightedEvaluators);

        float returnedWeightedScore = testee.getScore(null);

        assertEquals(2, returnedWeightedScore, 0.0001f);
    }

    @Test
    public void getScore_OnSecondCall_UsesCacheAndReturnsSameValue() {
        WeightedEvaluator fakeWeightedEvaluator = createFakeWeightedEvaluator(12, 1);
        WeightedEvaluatorGroup testee = createWeightedEvaluatorGroup(Arrays.asList(fakeWeightedEvaluator));

        float uncachedScore = testee.getScore(null);
        Mockito.verify(fakeWeightedEvaluator, Mockito.times(1)).getScore(null);

        float cachedScore = testee.getScore(null);
        Mockito.verify(fakeWeightedEvaluator, Mockito.times(1)).getScore(null);

        assertEquals(uncachedScore, cachedScore, 0.00001f);
    }
    
}
