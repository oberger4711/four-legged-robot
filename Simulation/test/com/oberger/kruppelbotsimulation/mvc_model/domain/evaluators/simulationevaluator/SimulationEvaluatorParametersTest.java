/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator;

import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.SimulationEvaluatorParameters;
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
public class SimulationEvaluatorParametersTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private SimulationEvaluatorParameters createSimulationEvaluatorParameters(List<Float> sampleTimesInS) {
        return new SimulationEvaluatorParameters(sampleTimesInS);
    }
    
    private SimulationEvaluatorParameters createSimulationEvaluatorParameters(float simulationPeriodInS, int samplesPerPeriod) {
        return new SimulationEvaluatorParameters(simulationPeriodInS, samplesPerPeriod);
    }

    @Test
    public void constructor_OnPassSimulationPeriodLowerThanZero_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        createSimulationEvaluatorParameters(-1f, 12);
    }
    
    @Test
    public void constructor_OnPassSamplesPerPeriodLowerThanZero_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        createSimulationEvaluatorParameters(12, -1);
    }
    
    @Test
    public void constructor_OnPassSampleTimesInSNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        createSimulationEvaluatorParameters(null);
    }
    
    @Test
    public void constructor_OnPassEmptyList_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        createSimulationEvaluatorParameters(Collections.emptyList());
    }
    
    @Test
    public void constructorInterpolation_OnPassSamplesPerPeriodOne_CreatesInterpolatedSampleTimeList() {
        SimulationEvaluatorParameters testee = createSimulationEvaluatorParameters(1f, 1);
        
        List<Float> returnedSampleTimesInS = testee.getSampleTimesInS();
        
        assertEquals(0, returnedSampleTimesInS.get(0), 0.00001f);
    }
    
    @Test
    public void constructorInterpolation_OnPassSamplesPerPeriodThree_CreatesInterpolatedSampleTimeList() {
        SimulationEvaluatorParameters testee = createSimulationEvaluatorParameters(1f, 3);
        
        List<Float> returnedSampleTimesInS = testee.getSampleTimesInS();
        
        assertEquals(0, returnedSampleTimesInS.get(0), 0.00001f);
        assertEquals(0.5f, returnedSampleTimesInS.get(1), 0.00001f);
        assertEquals(1f, returnedSampleTimesInS.get(2), 0.00001f);
    }
    
    @Test
    public void constructorInterpolation_OnPassSamplesPerPeriodOne_CreatesSampleTimeListOfGivenSize() {
        SimulationEvaluatorParameters testee = createSimulationEvaluatorParameters(1f, 1);
        
        List<Float> returnedSampleTimesInS = testee.getSampleTimesInS();
        
        assertEquals(1, returnedSampleTimesInS.size());
    }
    
    @Test
    public void constructorInterpolation_OnPassSamplesPerPeriodThree_CreatesSampleTimeListOfGivenSize() {
        SimulationEvaluatorParameters testee = createSimulationEvaluatorParameters(1f, 3);
        
        List<Float> returnedSampleTimesInS = testee.getSampleTimesInS();
        
        assertEquals(3, returnedSampleTimesInS.size());
    }
    
}
