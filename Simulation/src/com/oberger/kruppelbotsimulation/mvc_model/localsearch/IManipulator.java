package com.oberger.kruppelbotsimulation.mvc_model.localsearch;

public interface IManipulator<T extends IImmutableInnerState> {

    public T manipulateCopy(T originalInnerState);

}
