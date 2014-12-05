package com.oberger.kruppelbotsimulation.mvc_model.localsearch;

public interface IManipulator<T extends IImmutableInnerState> {

    /**
     * Creates slightly modified copies of the given state.
     * @param originalInnerState
     * @return 
     */
    public T createNeighbour(T originalInnerState);

}
