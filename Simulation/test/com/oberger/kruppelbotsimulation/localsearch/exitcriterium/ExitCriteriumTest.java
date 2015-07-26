/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.localsearch.exitcriterium;

import com.oberger.kruppelbotsimulation.localsearch.State;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author ole
 */
@RunWith(MockitoJUnitRunner.class)
public class ExitCriteriumTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private ExitCriterium createDummyExitCriterium() {
        return new ExitCriterium() {

            @Override
            public void reset() {
                throw new UnsupportedOperationException("Not supported.");
            }

            @Override
            public boolean isFinishState(State state) {
                throw new UnsupportedOperationException("Not supported.");
            }
        };
    }

    @Test
    public void and_OnPassNull_ThrowsIllegalArgumentException() {
        ExitCriterium fakeExitCriterium = createDummyExitCriterium();

        exception.expect(IllegalArgumentException.class);
        
        fakeExitCriterium.and(null);
    }
    
    @Test
    public void or_andPassNull_ThrowsIllegalArgumentException() {
        ExitCriterium fakeExitCriterium = createDummyExitCriterium();
        
        exception.expect(IllegalArgumentException.class);
        
        fakeExitCriterium.or(null);
    }

}
