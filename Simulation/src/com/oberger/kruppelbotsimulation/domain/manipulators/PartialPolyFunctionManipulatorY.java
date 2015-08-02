package com.oberger.kruppelbotsimulation.domain.manipulators;

import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Vector2;

public class PartialPolyFunctionManipulatorY extends PartialPolyFunctionManipulator {

    public PartialPolyFunctionManipulatorY(float manipulationStep) {
	super(manipulationStep);
    }

    @Override
    protected IReadOnlyVector2 manipulatePolygon(IReadOnlyVector2 polygonToManipulate, float manipulationStep) {
	float newX = polygonToManipulate.getX();
	float newY = polygonToManipulate.getY() + manipulationStep;
	IReadOnlyVector2 manipulatedVectorOrNull = new Vector2(newX, newY);

	return manipulatedVectorOrNull;
    }

}
