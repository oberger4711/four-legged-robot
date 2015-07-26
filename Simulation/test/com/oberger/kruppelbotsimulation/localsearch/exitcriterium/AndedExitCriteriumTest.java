/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.localsearch.exitcriterium;

import com.oberger.kruppelbotsimulation.localsearch.State;
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
public class AndedExitCriteriumTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private AndedExitCriterium createAndedExitCriterium(ExitCriterium firstExitCriterium, ExitCriterium secondExitCriterium) {
        return new AndedExitCriterium(firstExitCriterium, secondExitCriterium);
    }

    private ExitCriterium createFakeExitCriterium(State parameterState, boolean isFinishStateReturnValue) {
        ExitCriterium fakeExitCriterium = Mockito.mock(ExitCriterium.class);
        Mockito.doReturn(isFinishStateReturnValue).when(fakeExitCriterium).isFinishState(parameterState);

        return fakeExitCriterium;
    }
    
    private State createDummyState() {
        return Mockito.mock(State.class);
    }

    @Test
    public void constructor_OnPassFirstExitCriteriumNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createAndedExitCriterium(null, createFakeExitCriterium(null, true));
    }
    
        @Test
    public void constructor_OnPassSecondExitCriteriumNull_ThrowsIllegalParameterException() {
        exception.expect(IllegalArgumentException.class);

        createAndedExitCriterium(createFakeExitCriterium(null, true), null);
    }

    @Test
    public void isFinishState_OnPassNull_ThrowsIllegalParameterException() {
        State fakeState = createDummyState();
        ExitCriterium fakeExitCriterium1 = createFakeExitCriterium(fakeState, false);
        ExitCriterium fakeExitCriterium2 = createFakeExitCriterium(fakeState, true);
        ExitCriterium decoratorCriterium = createAndedExitCriterium(fakeExitCriterium1, fakeExitCriterium2);
        
        exception.expect(IllegalArgumentException.class);

        decoratorCriterium.isFinishState(null);
    }
    
    @Test
    public void isFinishState_OnDecoratedReturnBothTrue_DecoratorReturnsTrue() {
        State fakeState = createDummyState();
        ExitCriterium fakeExitCriterium1 = createFakeExitCriterium(fakeState, true);
        ExitCriterium fakeExitCriterium2 = createFakeExitCriterium(fakeState, true);

        ExitCriterium decoratorCriterium = createAndedExitCriterium(fakeExitCriterium1, fakeExitCriterium2);

        assertTrue(decoratorCriterium.isFinishState(fakeState));
    }

    @Test
    public void isFinishState_OnOneDecoratedReturnsFalse_DecoratorReturnsFalse() {
        State fakeState = createDummyState();
        ExitCriterium fakeExitCriterium1 = createFakeExitCriterium(fakeState, false);
        ExitCriterium fakeExitCriterium2 = createFakeExitCriterium(fakeState, true);

        ExitCriterium decoratorCriterium = createAndedExitCriterium(fakeExitCriterium1, fakeExitCriterium2);

        assertFalse(decoratorCriterium.isFinishState(fakeState));
    }

    @Test
    public void isFinishState_OnCallResetOnDecorator_DecoratorCallsResetOnDecorated() {
        State fakeState = createDummyState();
        ExitCriterium fakeExitCriterium1 = createFakeExitCriterium(fakeState, false);
        ExitCriterium fakeExitCriterium2 = createFakeExitCriterium(fakeState, true);

        ExitCriterium decoratorCriterium = createAndedExitCriterium(fakeExitCriterium1, fakeExitCriterium2);

        decoratorCriterium.reset();

        Mockito.verify(fakeExitCriterium1, Mockito.times(1)).reset();
        Mockito.verify(fakeExitCriterium2, Mockito.times(1)).reset();
    }

}
