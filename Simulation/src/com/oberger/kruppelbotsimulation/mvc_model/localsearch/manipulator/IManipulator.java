package com.oberger.kruppelbotsimulation.mvc_model.localsearch.manipulator;

import com.oberger.kruppelbotsimulation.mvc_model.localsearch.IImmutableInnerState;
import java.util.List;

public interface IManipulator<T extends IImmutableInnerState> {

    /**
     * Creates slightly modified copies of the given state.
     * @param originalInnerState
     * @return 
     */
    public List<T> createNeighbours(T originalInnerState);

}
