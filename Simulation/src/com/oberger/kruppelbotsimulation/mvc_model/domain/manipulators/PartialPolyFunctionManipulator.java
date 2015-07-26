package com.oberger.kruppelbotsimulation.mvc_model.domain.manipulators;

import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.legpolyfunctions.PartialPolyFunction;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.manipulator.IManipulator;
import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.util.LinkedList;
import java.util.List;

public class PartialPolyFunctionManipulator implements IManipulator<PartialPolyFunction> {

    private float manipulationStep;
    private float maxAbsGradient;

    public PartialPolyFunctionManipulator(float manipulationStep, float maxAbsGradient) {
	if (manipulationStep < 0) {
	    throw new IllegalArgumentException("maxAbsManipulationStep must not be negative but was " + manipulationStep + ".");
	}
	if (maxAbsGradient < 0) {
	    throw new IllegalArgumentException("maxAbsGradient must not be negative but was " + maxAbsGradient + ".");
	}
	this.manipulationStep = manipulationStep;
	this.maxAbsGradient = maxAbsGradient;
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
	    }
	    else {
		previousPolygon = manipulatedInner.get(i - 1);
	    }
	    IReadOnlyVector2 polygonToManipulate = manipulatedInner.get(i);
	    IReadOnlyVector2 nextPolygon = null;
	    if (i == originalInnerState.getInner().size() - 1) {
		nextPolygon = originalInnerState.getLast();
	    }
	    else {
		nextPolygon = manipulatedInner.get(i + 1);
	    }

	    IReadOnlyVector2 manipulatedPolygonOrNull = manipulatePolygonIfPossibleOrNull(previousPolygon, polygonToManipulate, nextPolygon);
	    if (manipulatedPolygonOrNull != null) {
		manipulatedInner.remove(i);
		manipulatedInner.add(i, manipulatedPolygonOrNull);

		PartialPolyFunction manipulatedFunction = new PartialPolyFunction(originalInnerState.getFirst(), manipulatedInner, originalInnerState.getLast());
		neighbours.add(manipulatedFunction);
	    }
	}

	return neighbours;
    }

    private IReadOnlyVector2 manipulatePolygonIfPossibleOrNull(IReadOnlyVector2 previousPolygon, IReadOnlyVector2 polygonToManipulate, IReadOnlyVector2 nextPolygon) {
	float newX = polygonToManipulate.getX();
	float newY = polygonToManipulate.getY() + manipulationStep;
	IReadOnlyVector2 manipulatedVectorOrNull = new Vector2(newX, newY);

	if (Math.abs(calcGradient(previousPolygon, manipulatedVectorOrNull)) > maxAbsGradient
		|| Math.abs(calcGradient(manipulatedVectorOrNull, nextPolygon)) > maxAbsGradient) {
	    manipulatedVectorOrNull = null;
	}

	return manipulatedVectorOrNull;
    }

    private float calcGradient(IReadOnlyVector2 v1, IReadOnlyVector2 v2) {
	return (v2.getY() - v1.getY()) / (v2.getX() - v1.getX());
    }

}
