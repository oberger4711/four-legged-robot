package com.oberger.kruppelbotsimulation.mvc_model.domain.manipulators;

import com.oberger.kruppelbotsimulation.mvc_model.function.PolyFunction;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.manipulator.IManipulator;
import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PolyFunctionManipulator implements IManipulator<PolyFunction> {

    private int startPolygonIndex;
    private int endPolygonIndex;
    private float maxAbsManipulationStep;
    private float maxAbsGradient;
    private Random randomNextPolygon = null;
    private Random randomNextStep = null;
        
    public PolyFunctionManipulator(int startPolygonIndex, int endPolygonIndex, float maxAbsManipulationStep, float maxAbsGradient) {
        if (startPolygonIndex < 0) {
            throw new IllegalArgumentException("startPolygonIndex must be higher than or equals to zero but was " + startPolygonIndex + ".");
        }
        if (endPolygonIndex - startPolygonIndex <= 1) {
            throw new IllegalArgumentException("endPolygonIndex to startPolygonIndex difference must be higher than one but was " + (endPolygonIndex - startPolygonIndex) + ".");
        }
        if (maxAbsManipulationStep < 0) {
            throw new IllegalArgumentException("maxAbsManipulationStep must not be negative but was " + maxAbsManipulationStep + ".");
        }
        if (maxAbsGradient < 0) {
            throw new IllegalArgumentException("maxAbsGradient must not be negative but was " + maxAbsGradient + ".");
        }
        this.startPolygonIndex = startPolygonIndex;
        this.endPolygonIndex = endPolygonIndex;
        this.maxAbsManipulationStep = maxAbsManipulationStep;
        this.maxAbsGradient = maxAbsGradient;
        this.randomNextPolygon = new Random();
        this.randomNextStep = new Random();
    }
    
    @Override
    public List<PolyFunction> createNeighbours(PolyFunction originalInnerState) {
        if (originalInnerState == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        if (originalInnerState.getPolygons().size() <= endPolygonIndex) {
            throw new IllegalArgumentException("Passed function must have at least " + (endPolygonIndex + 1) + " polygons.");
        }
        
        List<IReadOnlyVector2> manipulatedPolygons = new LinkedList<>(originalInnerState.getPolygons());
        int manipulatePolygonIndex = nextRandomPolygonIndex(startPolygonIndex, endPolygonIndex);
        IReadOnlyVector2 beforePolygon = manipulatedPolygons.get(manipulatePolygonIndex - 1);
        IReadOnlyVector2 manipulatePolygon = manipulatedPolygons.get(manipulatePolygonIndex);
        IReadOnlyVector2 afterPolygon = manipulatedPolygons.get(manipulatePolygonIndex + 1);
        manipulatedPolygons.remove(manipulatePolygonIndex);
        manipulatedPolygons.add(manipulatePolygonIndex, new Vector2(manipulatePolygon.getX(), manipulatePolygon.getY() + getRandomStep(beforePolygon, manipulatePolygon, afterPolygon)));
        
        // TODO: create real neighbour list.
        List<PolyFunction> neighbours = new ArrayList<>();
        PolyFunction manipulatedPolyFunction = new PolyFunction(originalInnerState.getInterpolator(), manipulatedPolygons);
        neighbours.add(manipulatedPolyFunction);
        return neighbours;
    }
    
    protected int nextRandomPolygonIndex(int exclusiveLowerBound, int exclusiveUpperBound) {
        return exclusiveLowerBound + 1 + randomNextPolygon.nextInt(exclusiveUpperBound - exclusiveLowerBound - 2);
    }
    
    protected float nextRandomStep(float maxNegativeStep, float maxPositiveStep) {
        return maxNegativeStep + randomNextStep.nextFloat() * (maxPositiveStep - maxNegativeStep);
    }
    
    private float getRandomStep(IReadOnlyVector2 vectorBefore, IReadOnlyVector2 vectorManipulate, IReadOnlyVector2 vectorAfter) {
        float maxPositiveStepFromBefore = calcMaxStepFromExtrapolation(vectorBefore, vectorManipulate, maxAbsGradient);
        float maxPositiveStepFromAfter = calcMaxStepFromExtrapolation(vectorAfter, vectorManipulate, -maxAbsGradient);
        float maxPostitiveStepFromAbsMax = maxAbsManipulationStep;
        
        float maxNegativeStepFromBefore = calcMaxStepFromExtrapolation(vectorBefore, vectorManipulate, -maxAbsGradient);
        float maxNegativeStepFromAfter = calcMaxStepFromExtrapolation(vectorAfter, vectorManipulate, maxAbsGradient);
        float maxNegativeStepFromAbsMax = -maxAbsManipulationStep;
        
        float maxMaxNegativeStep = Math.max(maxNegativeStepFromAbsMax, Math.max(maxNegativeStepFromBefore, maxNegativeStepFromAfter));
        float maxMaxPositiveStep = Math.min(maxPostitiveStepFromAbsMax, Math.min(maxPositiveStepFromBefore, maxPositiveStepFromAfter));
        
        return nextRandomStep(maxMaxNegativeStep, maxMaxPositiveStep); // TODO
    }
    
    private float calcMaxStepFromExtrapolation(IReadOnlyVector2 startVector, IReadOnlyVector2 manipulateVector, float gradient) {
        return (startVector.getY() + gradient * (manipulateVector.getX() - startVector.getX())) - manipulateVector.getY();
    }

    void setRandomNextPolygon(Random randomNextPolygon) {
        this.randomNextPolygon = randomNextPolygon;
    }

    void setRandomNextStep(Random randomNextStep) {
        this.randomNextStep = randomNextStep;
    }
    
}
