package com.oberger.kruppelbotsimulation.mvc_model.localsearch;

import java.util.List;

public interface IManipulatable<T extends State> {

    public List<T> getNeighbours();

}
