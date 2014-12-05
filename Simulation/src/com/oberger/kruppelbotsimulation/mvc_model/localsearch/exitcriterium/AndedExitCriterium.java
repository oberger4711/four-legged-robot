/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.localsearch.exitcriterium;

import com.oberger.kruppelbotsimulation.mvc_model.localsearch.State;

/**
 *Transparent decorator for {@link ExitCriterium} that ands the {@linkplain #isFinishState(com.oberger.kruppelbotsimulation.mvc_model.localsearch.State) } results.
 * @author ole
 */
class AndedExitCriterium extends ExitCriterium {
    
    private ExitCriterium firstExitCriterium = null;
    private ExitCriterium secondExitCriterium = null;
    
    public AndedExitCriterium(ExitCriterium firstExitCriterium, ExitCriterium secondExitCriterium) {
        if (firstExitCriterium == null || secondExitCriterium == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        this.firstExitCriterium = firstExitCriterium;
        this.secondExitCriterium = secondExitCriterium;
    }

    @Override
    public void reset() {
        firstExitCriterium.reset();
        secondExitCriterium.reset();
    }

    @Override
    public boolean isFinishState(State state) {
        if (state == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        return firstExitCriterium.isFinishState(state) && secondExitCriterium.isFinishState(state);
    }

}
