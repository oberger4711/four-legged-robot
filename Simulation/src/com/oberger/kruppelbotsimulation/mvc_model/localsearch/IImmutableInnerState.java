package com.oberger.kruppelbotsimulation.mvc_model.localsearch;

import jdk.nashorn.internal.ir.annotations.Immutable;

@Immutable
public interface IImmutableInnerState<T extends IImmutableInnerState<T>>{

    public T getCopy();

}
