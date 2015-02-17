package com.oberger.kruppelbotsimulation.mvc_model.localsearch.manipulator;

import com.oberger.kruppelbotsimulation.mvc_model.localsearch.State;
import java.util.*;

public class ManipulatorGroup<T> implements IManipulator<T> {

    private List<IManipulator> manipulators;
    private List<State<T>> neighboursCache = null;

    public ManipulatorGroup(List<IManipulator<T>> manipulators) {
        if (manipulators == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        if (manipulators.isEmpty()) {
            throw new IllegalArgumentException("Passing an empty list is not allowed.");
        }
        this.manipulators = new ArrayList<>(manipulators);
    }

    @Override
    public List<T> createNeighbours(T originalInnerState) {
        if (originalInnerState == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        List<T> neighbourStates = new LinkedList<>();

        for (IManipulator<T> manipulator : manipulators) {
            List<T> manipulatedInnerStates = manipulator.createNeighbours(originalInnerState);
            neighbourStates.addAll(manipulatedInnerStates);
        }

        return neighbourStates;
    }

    public List<IManipulator> getManipulators() {
        return Collections.unmodifiableList(manipulators);
    }

}
