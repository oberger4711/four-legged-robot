/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.localsearch.exitcriterium;

import com.oberger.kruppelbotsimulation.mvc_model.localsearch.State;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
public class LocalMaximumExitCriteriumTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private ExitCriterium createLocalMaximumExitCriterium() {
        return new LocalMaximumExitCriterium();
    }

    private State createFakeState(float score) {
        State state = Mockito.mock(State.class);
        Mockito.doReturn(score).when(state).getScore();

        return state;
    }

    @Test
    public void isFinishState_OnPassNull_ThrowsIllegalArgumentException() {
        ExitCriterium localMaximumExitCriterium = createLocalMaximumExitCriterium();

        exception.expect(IllegalArgumentException.class);

        localMaximumExitCriterium.isFinishState(null);
    }

    @Test
    public void isFinishState_WithNeighbourHavingHigherScore_ReturnsFalse() {
        ExitCriterium localMaximumExitCriterium = createLocalMaximumExitCriterium();
        State lowScoreState = createFakeState(0.3f);
        State highScoreState = createFakeState(0.5f);
        Mockito.doReturn(new ArrayList<>(Arrays.asList(highScoreState))).when(lowScoreState).getNeighbours();

        assertFalse(localMaximumExitCriterium.isFinishState(lowScoreState));
    }

    @Test
    public void isFinishState_WithNeighbourHavingLowerScore_ReturnsTrue() {
        ExitCriterium localMaximumExitCriterium = createLocalMaximumExitCriterium();
        State lowScoreState = createFakeState(0.3f);
        State highScoreState = createFakeState(0.5f);
        Mockito.doReturn(new ArrayList<>(Arrays.asList(lowScoreState))).when(highScoreState).getNeighbours();

        assertTrue(localMaximumExitCriterium.isFinishState(lowScoreState));
    }

    @Test
    public void isFinishState_WithNeighbourHavingSameScore_ReturnsTrue() {
        ExitCriterium localMaximumExitCriterium = createLocalMaximumExitCriterium();
        State lowScoreState = createFakeState(0.3f);
        Mockito.doReturn(new ArrayList<>(Arrays.asList(lowScoreState))).when(lowScoreState).getNeighbours();

        assertTrue(localMaximumExitCriterium.isFinishState(lowScoreState));
    }

}
