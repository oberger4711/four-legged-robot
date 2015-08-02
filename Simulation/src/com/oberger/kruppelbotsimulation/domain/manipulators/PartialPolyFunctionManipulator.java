/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.manipulators;

import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.PartialPolyFunction;
import com.oberger.kruppelbotsimulation.localsearch.manipulator.IManipulator;
import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author oberger
 */
public abstract class PartialPolyFunctionManipulator implements IManipulator<PartialPolyFunction> {

    private float manipulationStep;

    public PartialPolyFunctionManipulator(float manipulationStep) {
	this.manipulationStep = manipulationStep;
    }

    @Override
    public List<PartialPolyFunction> createNeighbours(PartialPolyFunction originalInnerState) {
	if (originalInnerState == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}
	List<PartialPolyFunction> neighbours = new LinkedList<>();
	for (int i = 0; i < originalInnerState.getInner().size(); i++) {
	    List<IReadOnlyVector2> manipulatedInner = new LinkedList<>(originalInnerState.getInner());

	    IReadOnlyVector2 previousPolygon = null;
	    if (i == 0) {
		previousPolygon = originalInnerState.getFirst();
	    } else {
		previousPolygon = manipulatedInner.get(i - 1);
	    }
	    IReadOnlyVector2 polygonToManipulate = manipulatedInner.get(i);
	    IReadOnlyVector2 nextPolygon = null;
	    if (i == originalInnerState.getInner().size() - 1) {
		nextPolygon = originalInnerState.getLast();
	    } else {
		nextPolygon = manipulatedInner.get(i + 1);
	    }
	    IReadOnlyVector2 manipulatedPolygonOrNull = manipulatePolygonOrNull(previousPolygon, polygonToManipulate, nextPolygon, manipulationStep);
	    if (manipulatedPolygonOrNull != null) {
		manipulatedInner.remove(i);
		manipulatedInner.add(i, manipulatedPolygonOrNull);
		PartialPolyFunction manipulatedFunction = new PartialPolyFunction(originalInnerState.getFirst(), manipulatedInner, originalInnerState.getLast());
		neighbours.add(manipulatedFunction);
	    }
	}
	return neighbours;
    }

    protected abstract IReadOnlyVector2 manipulatePolygonOrNull(IReadOnlyVector2 polygonBefore, IReadOnlyVector2 polygonToManipulate, IReadOnlyVector2 polygonAfter, float manipulationStep);

}
