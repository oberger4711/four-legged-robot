/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator;

import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.ISimulationState;
import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.Simulation;
import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.SimulationEvaluatorParameters;
import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.SimulationEvaluator;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator.IEvaluator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InOrder;
import org.mockito.Mockito;

/**
 *
 * @author ole
 */
public class SimulationEvaluatorTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private SimulationEvaluator createSimulationEvaluator(SimulationEvaluatorParameters simulationParameter, IEvaluator<ISimulationState> stateEvaluators) {
        return new SimulationEvaluator(simulationParameter, stateEvaluators);
    }

    private SimulationEvaluatorParameters createDummyParameters() {
        return createFakeParameters(Arrays.asList(0f));
    }
    
    private SimulationEvaluatorParameters createFakeParameters(List<Float> sampleTimes) {
        SimulationEvaluatorParameters fake = Mockito.mock(SimulationEvaluatorParameters.class);

        Mockito.doReturn(sampleTimes).when(fake).getSampleTimesInS();
        
        return fake;
    }

    private IEvaluator createFakeEvaluator(float score) {
        IEvaluator fake = Mockito.mock(IEvaluator.class);

        Mockito.doReturn(score).when(fake).getScore(Mockito.any());

        return fake;
    }
    
    private Simulation createDummySimulation() {
        return Mockito.mock(Simulation.class);
    }

    @Test
    public void constructor_OnPassSimulationParametersNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createSimulationEvaluator(null, createFakeEvaluator(1));
    }

    @Test
    public void constructor_OnPassStateEvaluatorsNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createSimulationEvaluator(createDummyParameters(), null);
    }

    @Test
    public void getScore_OnPassNull_ThrowsIllegalArgumentException() {
        SimulationEvaluator testee = createSimulationEvaluator(createDummyParameters(), createFakeEvaluator(1f));

        exception.expect(IllegalArgumentException.class);

        testee.getScore(null);
    }

    @Test
    public void getScore_OnCall_ReturnsAverageMeanOfScores() {
        SimulationEvaluator testee = createSimulationEvaluator(createDummyParameters(), createFakeEvaluator(2f));
        
        float returnedScore = testee.getScore(createDummySimulation());
        
        assertEquals(2f, returnedScore, 0.0001f);
    }
    
    @Test
    public void getScore_OnCall_SimulatesAndThenEvaluates() {
        IEvaluator<ISimulationState> fakeEvaluator = createFakeEvaluator(2f);
        SimulationEvaluator testee = createSimulationEvaluator(createDummyParameters(), fakeEvaluator);
        Simulation fakeSimulation = createDummySimulation();
        
        testee.getScore(fakeSimulation);
        
        InOrder inOrder = Mockito.inOrder(fakeSimulation, fakeEvaluator);
        
        inOrder.verify(fakeSimulation).simulate(0f);
        inOrder.verify(fakeEvaluator).getScore(fakeSimulation);
    }
    
    @Test
    public void getScore_OnPassThreeSampleTimes_SimulatesEverySampleTimeFromList() {
        IEvaluator<ISimulationState> fakeEvaluator = createFakeEvaluator(2f);
        List<Float> fakeSampleTimes = new ArrayList<>(Arrays.asList(1f, 2f, 3f));
        SimulationEvaluator testee = createSimulationEvaluator(createFakeParameters(fakeSampleTimes), fakeEvaluator);
        Simulation fakeSimulation = createDummySimulation();
        
        testee.getScore(fakeSimulation);
        
        Mockito.verify(fakeSimulation).simulate(1f);
        Mockito.verify(fakeSimulation).simulate(2f);
        Mockito.verify(fakeSimulation).simulate(3f);
    }
    
}
