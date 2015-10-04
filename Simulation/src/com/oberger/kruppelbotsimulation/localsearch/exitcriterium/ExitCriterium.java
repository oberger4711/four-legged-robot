package com.oberger.kruppelbotsimulation.localsearch.exitcriterium;

import com.oberger.kruppelbotsimulation.localsearch.State;

/**
 * Judges at which state the local search shall stop.
 *
 * @author ole
 */
public abstract class ExitCriterium {

    public ExitCriterium and(final ExitCriterium otherCriterium) {
	if (otherCriterium == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed"));
	}

	return new AndedExitCriterium(this, otherCriterium);
    }

    public ExitCriterium or(final ExitCriterium oredExitCriterium) {
	if (oredExitCriterium == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}

	return new OredExitCriterium(this, oredExitCriterium);
    }

    /**
     * Called before running the local search.
     */
    public abstract void reset();

    /**
     * Returns whether the given state is the final one and the search should
     * stop.
     *
     * @param state
     * @return
     */
    public abstract boolean isFinishState(State state);

}
