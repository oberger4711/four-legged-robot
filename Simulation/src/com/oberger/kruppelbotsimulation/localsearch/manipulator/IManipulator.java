package com.oberger.kruppelbotsimulation.localsearch.manipulator;

import java.util.List;

public interface IManipulator<T> {

    /**
     * Creates slightly modified copies of the given state.
     * @param originalInnerState
     * @return 
     */
    public List<T> createNeighbours(T originalInnerState);

}
