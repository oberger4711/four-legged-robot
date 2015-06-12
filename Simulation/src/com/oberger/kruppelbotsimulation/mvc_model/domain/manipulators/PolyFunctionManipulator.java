package com.oberger.kruppelbotsimulation.mvc_model.domain.manipulators;

import com.oberger.kruppelbotsimulation.mvc_model.function.PolyFunction;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.manipulator.IManipulator;
import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.util.LinkedList;
import java.util.List;

public class PolyFunctionManipulator implements IManipulator<PolyFunction> {

    private int startPolygonIndex;
    private int endPolygonIndex;
    private float manipulationStep;
    private float maxAbsGradient;

    public PolyFunctionManipulator(int startPolygonIndex, int endPolygonIndex, float manipulationStep, float maxAbsGradient) {
        if (startPolygonIndex < 0) {
            throw new IllegalArgumentException("startPolygonIndex must be higher than or equals to zero but was " + startPolygonIndex + ".");
        }
        if (endPolygonIndex - startPolygonIndex <= 1) {
            throw new IllegalArgumentException("endPolygonIndex to startPolygonIndex difference must be higher than one but was " + (endPolygonIndex - startPolygonIndex) + ".");
        }
        if (manipulationStep < 0) {
            throw new IllegalArgumentException("maxAbsManipulationStep must not be negative but was " + manipulationStep + ".");
        }
        if (maxAbsGradient < 0) {
            throw new IllegalArgumentException("maxAbsGradient must not be negative but was " + maxAbsGradient + ".");
        }
        this.startPolygonIndex = startPolygonIndex;
        this.endPolygonIndex = endPolygonIndex;
        this.manipulationStep = manipulationStep;
        this.maxAbsGradient = maxAbsGradient;
    }

    @Override
    public List<PolyFunction> createNeighbours(PolyFunction originalInnerState) {
        if (originalInnerState == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        if (originalInnerState.getPolygons().size() <= endPolygonIndex) {
            throw new IllegalArgumentException("Passed function must have at least " + (endPolygonIndex + 1) + " polygons.");
        }

        List<PolyFunction> neighbours = new LinkedList<>();
        for (int i = startPolygonIndex + 1; i < endPolygonIndex; i++) {
            List<IReadOnlyVector2> manipulatedPolygons = new LinkedList<>(originalInnerState.getPolygons());
            
            IReadOnlyVector2 previousPolygon = manipulatedPolygons.get(i - 1);
            IReadOnlyVector2 polygonToManipulate = manipulatedPolygons.get(i);
            IReadOnlyVector2 nextPolygon = manipulatedPolygons.get(i + 1);
            
            IReadOnlyVector2 manipulatedPolygonOrNull = manipulatePolygonIfPossibleOrNull(previousPolygon, polygonToManipulate, nextPolygon);
            if (manipulatedPolygonOrNull != null) {
                manipulatedPolygons.remove(i);
                manipulatedPolygons.add(i, manipulatedPolygonOrNull);
                
                PolyFunction manipulatedFunction = new PolyFunction(originalInnerState.getInterpolator(), manipulatedPolygons);
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
