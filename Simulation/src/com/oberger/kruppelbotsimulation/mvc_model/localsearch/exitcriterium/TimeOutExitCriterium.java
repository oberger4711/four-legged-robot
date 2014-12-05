/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.localsearch.exitcriterium;

import com.oberger.kruppelbotsimulation.mvc_model.localsearch.State;
import java.time.Clock;

/**
 * {@link ExitCriterium} that returns true for {@linkplain #isFinishState(com.oberger.kruppelbotsimulation.mvc_model.localsearch.State) } after the given timeout after {@linkplain #reset() } or initialization.
 * @author ole
 */
public class TimeOutExitCriterium extends ExitCriterium {
    
    private Clock clock = null;
    private long timeOutInMs;
    private long timeStampResetInMs;
    
    public TimeOutExitCriterium(Clock clock, long timeOutInMs) {
        if (clock == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        if (timeOutInMs < 0) {
            throw new IllegalArgumentException("A timeOut lower than 0 is not allowed.");
        }
        this.clock = clock;
        this.timeOutInMs = timeOutInMs;
        resetTimestamp();
    }

    public TimeOutExitCriterium(long timeOutInMs) {
        this(Clock.systemUTC(), timeOutInMs);
    }
    
    @Override
    public void reset() {
        resetTimestamp();
    }
    
    private void resetTimestamp() {
        timeStampResetInMs = getMillis();
    }

    @Override
    public boolean isFinishState(State state) {
        if (state == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        
        return getMillis() - timeStampResetInMs > timeOutInMs;
    }
    
    private long getMillis() {
        return clock.millis();
    }
    
}
