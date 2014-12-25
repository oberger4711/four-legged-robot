/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain;

import com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator.WeightedEvaluatorGroup;
import java.util.Arrays;
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

    private SimulationEvaluator createSimulationEvaluator(SimulationEvaluatorParameters simulationParameter, WeightedEvaluatorGroup<ISimulationState> stateEvaluators) {
        return new SimulationEvaluator(simulationParameter, stateEvaluators);
    }

    private SimulationEvaluatorParameters createDummyParameters() {
        SimulationEvaluatorParameters fake = Mockito.mock(SimulationEvaluatorParameters.class);

        Mockito.doReturn(Arrays.asList(0f)).when(fake).getSampleTimesInS();

        return fake;
    }

    private WeightedEvaluatorGroup createFakeEvaluatorGroup(float score) {
        WeightedEvaluatorGroup fake = Mockito.mock(WeightedEvaluatorGroup.class);

        Mockito.doReturn(score).when(fake).getScore(Mockito.any());

        return fake;
    }
    
    private Simulation createDummySimulation() {
        return Mockito.mock(Simulation.class);
    }

    @Test
    public void constructor_OnPassSimulationParametersNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createSimulationEvaluator(null, createFakeEvaluatorGroup(1));
    }

    @Test
    public void constructor_OnPassStateEvaluatorsNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createSimulationEvaluator(createDummyParameters(), null);
    }

    @Test
    public void getScore_OnPassNull_ThrowsIllegalArgumentException() {
        SimulationEvaluator testee = createSimulationEvaluator(createDummyParameters(), createFakeEvaluatorGroup(1f));

        exception.expect(IllegalArgumentException.class);

        testee.getScore(null);
    }

    @Test
    public void getScore_OnCall_ReturnsAverageMeanOfScores() {
        SimulationEvaluator testee = createSimulationEvaluator(createDummyParameters(), createFakeEvaluatorGroup(2f));
        
        float returnedScore = testee.getScore(createDummySimulation());
        
        assertEquals(2f, returnedScore, 0.0001f);
    }
    
    @Test
    public void getScore_OnCall_SimulatesAndThenEvaluates() {
        WeightedEvaluatorGroup<ISimulationState> fakeEvaluator = createFakeEvaluatorGroup(2f);
        SimulationEvaluator testee = createSimulationEvaluator(createDummyParameters(), fakeEvaluator);
        Simulation fakeSimulation = createDummySimulation();
        
        testee.getScore(fakeSimulation);
        
        InOrder inOrder = Mockito.inOrder(fakeSimulation, fakeEvaluator);
        
        inOrder.verify(fakeSimulation).simulate(0f);
        inOrder.verify(fakeEvaluator).getScore(fakeSimulation);
    }
}
