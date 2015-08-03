/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.localsearch;

import com.oberger.kruppelbotsimulation.localsearch.exitcriterium.ExitCriterium;
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

    private FakeLocalSearchAlgorithm createFakeLocalSearchAlgorithm(ExitCriterium exitCriterium) {
	return new FakeLocalSearchAlgorithm(exitCriterium);
    }

    private State createFakeState(State... neighbourStates) {
	State fakeState = Mockito.mock(State.class);
	Mockito.doReturn(Arrays.asList(neighbourStates)).when(fakeState).getNeighbours();

	return fakeState;
    }

    @Test
    public void constructor_OnPassExitCriteriumNull_ThrowIllegalArgumentException() {

	exception.expect(IllegalArgumentException.class);

	FakeLocalSearchAlgorithm fakeLocalSearchAlgorithm = createFakeLocalSearchAlgorithm(null);
    }

    @Test
    public void run_OnPassStartStateNull_ThrowIllegalArgumentException() {
	ExitCriterium fakeExitCriterium = Mockito.mock(ExitCriterium.class);
	Mockito.doReturn(true).when(fakeExitCriterium).isFinishState(null);
	FakeLocalSearchAlgorithm fakeLocalSearchAlgorithm = createFakeLocalSearchAlgorithm(fakeExitCriterium);

	exception.expect(IllegalArgumentException.class);

	fakeLocalSearchAlgorithm.run(null);
    }

    @Test
    public void run_WithFirstStateIsFinishState_ReturnsFirstState() {
	State fakeStateNeighbour = createFakeState();
	State fakeStartState = createFakeState(fakeStateNeighbour);
	ExitCriterium fakeExitCriterium = Mockito.mock(ExitCriterium.class);
	Mockito.doReturn(true).when(fakeExitCriterium).isFinishState(fakeStartState);
	FakeLocalSearchAlgorithm fakeLocalSearchAlgorithm = createFakeLocalSearchAlgorithm(fakeExitCriterium);

	State exitState = fakeLocalSearchAlgorithm.run(fakeStartState);

	assertTrue(fakeStartState == exitState);
    }

    @Test
    public void run_WithSecondStateIsFinishState_ReturnsSecondState() {
	State fakeStateNeighbourNeighbour = createFakeState();
	State fakeStateNeighbour = createFakeState(fakeStateNeighbourNeighbour);
	State fakeStartState = createFakeState(fakeStateNeighbour);
	ExitCriterium fakeExitCriterium = Mockito.mock(ExitCriterium.class);
	Mockito.doReturn(true).when(fakeExitCriterium).isFinishState(fakeStateNeighbour);
	FakeLocalSearchAlgorithm fakeLocalSearchAlgorithm = createFakeLocalSearchAlgorithm(fakeExitCriterium);

	State exitState = fakeLocalSearchAlgorithm.run(fakeStartState);

	assertTrue(fakeStateNeighbour == exitState);
    }

    @Test
    public void run_WithStartStateNotExitStateButHavingNoNeighbour_ReturnsStartState() {
	State fakeStartState = createFakeState();
	ExitCriterium fakeExitCriterium = Mockito.mock(ExitCriterium.class);
	Mockito.doReturn(false).when(fakeExitCriterium).isFinishState(fakeStartState);
	FakeLocalSearchAlgorithm fakeLocalSearchAlgorithm = createFakeLocalSearchAlgorithm(fakeExitCriterium);

	State exitState = fakeLocalSearchAlgorithm.run(fakeStartState);

	assertTrue(fakeStartState == exitState);
    }

    @Test
    public void run_OnCall_CallsExitCriteriumReset() {
	State fakeStartState = createFakeState();
	ExitCriterium fakeExitCriterium = Mockito.mock(ExitCriterium.class);
	Mockito.doReturn(false).when(fakeExitCriterium).isFinishState(fakeStartState);
	FakeLocalSearchAlgorithm fakeLocalSearchAlgorithm = createFakeLocalSearchAlgorithm(fakeExitCriterium);

	State exitState = fakeLocalSearchAlgorithm.run(fakeStartState);

	Mockito.verify(fakeExitCriterium, Mockito.times(1)).reset();
    }

}
