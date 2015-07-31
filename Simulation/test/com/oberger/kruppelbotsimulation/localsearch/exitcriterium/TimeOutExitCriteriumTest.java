/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.localsearch.exitcriterium;

import com.oberger.kruppelbotsimulation.localsearch.State;
import java.time.Clock;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
public class TimeOutExitCriteriumTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private ExitCriterium createTimeOutExitCriterium(Clock clock, long timeOutInMs) {
	return new TimeOutExitCriterium(clock, timeOutInMs);
    }

    private State createDummyState() {
	return Mockito.mock(State.class);
    }

    @Test
    public void constructor_OnPassTimeoutLowerThanZero_ThrowsIllegalArgumentException() {
	exception.expect(IllegalArgumentException.class);

	createTimeOutExitCriterium(Clock.systemUTC(), -1);
    }

    @Test
    public void isFinishState_OnPassNull_ThrowsIllegalArgumentException() {
	exception.expect(IllegalArgumentException.class);

	ExitCriterium timeOutCriterium = createTimeOutExitCriterium(Clock.systemUTC(), 0);

	timeOutCriterium.isFinishState(null);
    }

    @Test
    public void isFinishState_BeforeTimeout_ReturnsFalse() {
	Clock fakeClock = Mockito.mock(Clock.class);
	Mockito.doReturn(0l).when(fakeClock).millis();
	ExitCriterium timeOutExitCriterium = createTimeOutExitCriterium(fakeClock, 10);

	assertFalse(timeOutExitCriterium.isFinishState(createDummyState()));
    }

    @Test
    public void isFinishState_AfterTimeout_ReturnsTrue() {
	Clock fakeClock = Mockito.mock(Clock.class);
	Mockito.doReturn(0l).when(fakeClock).millis();
	ExitCriterium timeOutCriterium = createTimeOutExitCriterium(fakeClock, 10);
	Mockito.doReturn(11l).when(fakeClock).millis();

	assertTrue(timeOutCriterium.isFinishState(createDummyState()));
    }

    @Test
    public void isFinishState_AfterTimoutAndReset_ReturnsFalseAgain() {
	Clock fakeClock = Mockito.mock(Clock.class);
	Mockito.doReturn(0l).when(fakeClock).millis();
	ExitCriterium timeOutCriterium = createTimeOutExitCriterium(fakeClock, 10);
	Mockito.doReturn(11l).when(fakeClock).millis();
	timeOutCriterium.reset();
	Mockito.doReturn(15l).when(fakeClock).millis();

	assertFalse(timeOutCriterium.isFinishState(createDummyState()));
    }

    @Test
    public void isFinishState_AfterTimeoutAndResetAndTimeout_ReturnsTrueAgain() {
	Clock fakeClock = Mockito.mock(Clock.class);
	Mockito.doReturn(0l).when(fakeClock).millis();
	ExitCriterium timeOutCriterium = createTimeOutExitCriterium(fakeClock, 10);
	Mockito.doReturn(11l).when(fakeClock).millis();
	timeOutCriterium.reset();
	Mockito.doReturn(22l).when(fakeClock).millis();

	assertTrue(timeOutCriterium.isFinishState(createDummyState()));
    }

}
