/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.localsearch;

import com.oberger.kruppelbotsimulation.mvc_model.localsearch.exitcriterium.ExitCriterium;
import java.util.Arrays;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 *
 * @author ole
 */
public class LocalSearchAlgorithmTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private FakeLocalSearchAlgorithm createFakeLocalSearchAlgorithm() {
        return new FakeLocalSearchAlgorithm();
    }
    
    private State createFakeState(State... neighbourStates) {
        State fakeState = Mockito.mock(State.class);
        Mockito.doReturn(Arrays.asList(neighbourStates)).when(fakeState).getNeighbours();
        
        return fakeState;
    }
    
    @Test
    public void run_OnPassStartStateNull_ThrowIllegalArgumentException() {
        FakeLocalSearchAlgorithm fakeLocalSearchAlgorithm = createFakeLocalSearchAlgorithm();
        ExitCriterium fakeExitCriterium = Mockito.mock(ExitCriterium.class);
        Mockito.doReturn(true).when(fakeExitCriterium).isFinishState(null);
        
        exception.expect(IllegalArgumentException.class);
        
        fakeLocalSearchAlgorithm.run(null, fakeExitCriterium);
    }
    
    @Test
    public void run_OnPassExitCriteriumNull_ThrowIllegalArgumentException() {
        FakeLocalSearchAlgorithm fakeLocalSearchAlgorithm = createFakeLocalSearchAlgorithm();
        State fakeStartState = createFakeState();
        
        exception.expect(IllegalArgumentException.class);
        
        fakeLocalSearchAlgorithm.run(fakeStartState, null);
    }
    
    @Test
    public void run_WithFirstStateIsFinishState_ReturnsFirstState() {
        State fakeStateNeighbour = createFakeState();
        State fakeStartState = createFakeState(fakeStateNeighbour);
        FakeLocalSearchAlgorithm fakeLocalSearchAlgorithm = createFakeLocalSearchAlgorithm();
        ExitCriterium fakeExitCriterium = Mockito.mock(ExitCriterium.class);
        Mockito.doReturn(true).when(fakeExitCriterium).isFinishState(fakeStartState);
        
        State exitState = fakeLocalSearchAlgorithm.run(fakeStartState, fakeExitCriterium);
        
        assertTrue(fakeStartState == exitState);
    }
    
    @Test
    public void run_WithSecondStateIsFinishState_ReturnsSecondState() {
        State fakeStateNeighbourNeighbour = createFakeState();
        State fakeStateNeighbour = createFakeState(fakeStateNeighbourNeighbour);
        State fakeStartState = createFakeState(fakeStateNeighbour);
        FakeLocalSearchAlgorithm fakeLocalSearchAlgorithm = createFakeLocalSearchAlgorithm();
        ExitCriterium fakeExitCriterium = Mockito.mock(ExitCriterium.class);
        Mockito.doReturn(true).when(fakeExitCriterium).isFinishState(fakeStateNeighbour);
        
        State exitState = fakeLocalSearchAlgorithm.run(fakeStartState, fakeExitCriterium);
        
        assertTrue(fakeStateNeighbour == exitState);
    }
    
    @Test
    public void run_WithStartStateNotExitStateButHavingNoNeighbour_ReturnsStartState() {
        State fakeStartState = createFakeState();
        FakeLocalSearchAlgorithm fakeLocalSearchAlgorithm = createFakeLocalSearchAlgorithm();
        ExitCriterium fakeExitCriterium = Mockito.mock(ExitCriterium.class);
        Mockito.doReturn(false).when(fakeExitCriterium).isFinishState(fakeStartState);
        
        State exitState = fakeLocalSearchAlgorithm.run(fakeStartState, fakeExitCriterium);
        
        assertTrue(fakeStartState == exitState);
    }
    
    @Test
    public void run_OnCall_CallsExitCriteriumReset() {
        State fakeStartState = createFakeState();
        FakeLocalSearchAlgorithm fakeLocalSearchAlgorithm = createFakeLocalSearchAlgorithm();
        ExitCriterium fakeExitCriterium = Mockito.mock(ExitCriterium.class);
        Mockito.doReturn(false).when(fakeExitCriterium).isFinishState(fakeStartState);
        
        State exitState = fakeLocalSearchAlgorithm.run(fakeStartState, fakeExitCriterium);
        
        Mockito.verify(fakeExitCriterium, Mockito.times(1)).reset();
    }
    
}
