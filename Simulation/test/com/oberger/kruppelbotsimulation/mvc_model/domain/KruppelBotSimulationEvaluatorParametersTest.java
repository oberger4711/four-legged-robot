/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain;

import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author ole
 */
public class KruppelBotSimulationEvaluatorParametersTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private KruppelBotSimulationEvaluatorParameters createKruppelBotSimulationevaluatorParameters(List<Float> sampleTimesInS) {
        return new KruppelBotSimulationEvaluatorParameters(sampleTimesInS);
    }
    
    private KruppelBotSimulationEvaluatorParameters createKruppelBotSimulationEvaluatorParameters(float simulationPeriodInS, int samplesPerPeriod) {
        return new KruppelBotSimulationEvaluatorParameters(simulationPeriodInS, samplesPerPeriod);
    }

    @Test
    public void constructor_OnPassSimulationPeriodLowerThanZero_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        createKruppelBotSimulationEvaluatorParameters(-1f, 12);
    }
    
    @Test
    public void constructor_OnPassSamplesPerPeriodLowerThanZero_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        createKruppelBotSimulationEvaluatorParameters(12, -1);
    }
    
    @Test
    public void constructor_OnPassSampleTimesInSNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        createKruppelBotSimulationevaluatorParameters(null);
    }
    
    @Test
    public void constructor_OnPassEmptyList_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        createKruppelBotSimulationevaluatorParameters(Collections.emptyList());
    }
    
    @Test
    public void constructorInterpolation_OnPassSamplesPerPeriodOne_CreatesInterpolatedSampleTimeList() {
        KruppelBotSimulationEvaluatorParameters testee = createKruppelBotSimulationEvaluatorParameters(1f, 1);
        
        List<Float> returnedSampleTimesInS = testee.getSampleTimesInS();
        
        assertEquals(0, returnedSampleTimesInS.get(0), 0.00001f);
    }
    
    @Test
    public void constructorInterpolation_OnPassSamplesPerPeriodThree_CreatesInterpolatedSampleTimeList() {
        KruppelBotSimulationEvaluatorParameters testee = createKruppelBotSimulationEvaluatorParameters(1f, 3);
        
        List<Float> returnedSampleTimesInS = testee.getSampleTimesInS();
        
        assertEquals(0, returnedSampleTimesInS.get(0), 0.00001f);
        assertEquals(0.5f, returnedSampleTimesInS.get(1), 0.00001f);
        assertEquals(1f, returnedSampleTimesInS.get(2), 0.00001f);
    }
    
    @Test
    public void constructorInterpolation_OnPassSamplesPerPeriodOne_CreatesSampleTimeListOfGivenSize() {
        KruppelBotSimulationEvaluatorParameters testee = createKruppelBotSimulationEvaluatorParameters(1f, 1);
        
        List<Float> returnedSampleTimesInS = testee.getSampleTimesInS();
        
        assertEquals(1, returnedSampleTimesInS.size());
    }
    
    @Test
    public void constructorInterpolation_OnPassSamplesPerPeriodThree_CreatesSampleTimeListOfGivenSize() {
        KruppelBotSimulationEvaluatorParameters testee = createKruppelBotSimulationEvaluatorParameters(1f, 3);
        
        List<Float> returnedSampleTimesInS = testee.getSampleTimesInS();
        
        assertEquals(3, returnedSampleTimesInS.size());
    }
    
}
