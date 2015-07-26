/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.legpolyfunctions.ILegPolyFunctions;
import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.Model;
import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.Simulation;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator.IEvaluator;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 *
 * @author ole
 */
public class WalkSimulationEvaluatorAdapterTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private WalkStateEvaluatorAdapter createTestee(IEvaluator<Simulation> adaptee) {
        return new WalkStateEvaluatorAdapter(adaptee);
    }
    
    private WalkStateEvaluatorAdapter createTestee(IEvaluator<Simulation> adaptee, final Simulation fakeSimulation) {
        return new WalkStateEvaluatorAdapter(adaptee) {

            @Override
            protected Simulation createSimulation(WalkState walkState) {
                return fakeSimulation;
            }
            
        };
    }
    
    private Model createDummyModel() {
        return Mockito.mock(Model.class);
    }
    
    private ILegPolyFunctions createDummyPolyLegFunctions() {
        return Mockito.mock(ILegPolyFunctions.class);
    }
    
    private IEvaluator<Simulation> createFakeAdaptee() {
        return Mockito.mock(IEvaluator.class);
    }
    
    private Simulation createFakeSimulation() {
        return Mockito.mock(Simulation.class);
    }
    
    private WalkState createFakeWalkState() {
        return Mockito.mock(WalkState.class);
    }
    
    @Test
    public void constructor_OnPassAdapteeNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        createTestee(null);
    }
    
    @Test
    public void getScore_OnCall_ReturnsEvaluatorScoreOfCreatedSimulation() {
        Simulation fakeSimulation = createFakeSimulation();
        IEvaluator<Simulation> fakeEvaluator = createFakeAdaptee();
        Mockito.doReturn(12f).when(fakeEvaluator).getScore(fakeSimulation);
        WalkStateEvaluatorAdapter testee = createTestee(fakeEvaluator, fakeSimulation);
        WalkState walkState = createFakeWalkState();
        
        assertEquals(12f, testee.getScore(walkState), 0.0001f);
    }
    
}
