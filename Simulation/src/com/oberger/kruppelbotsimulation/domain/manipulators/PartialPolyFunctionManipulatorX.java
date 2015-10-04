/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.manipulators;

import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Vector2;

/**
 *
 * @author oberger
 */
public class PartialPolyFunctionManipulatorX extends PartialPolyFunctionManipulator {

    public PartialPolyFunctionManipulatorX(float manipulationStep) {
	super(manipulationStep);
    }

    @Override
    protected IReadOnlyVector2 manipulatePolygonOrNull(IReadOnlyVector2 polygonBefore, IReadOnlyVector2 polygonToManipulate, IReadOnlyVector2 polygonAfter, float manipulationStep) {
	IReadOnlyVector2 manipulatedVectorOrNull = null;
	
	float newX = polygonToManipulate.getX() + manipulationStep;
	if (newX < polygonAfter.getX() && newX > polygonBefore.getX()) {
	    float newY = polygonToManipulate.getY();
	    manipulatedVectorOrNull = new Vector2(newX, newY);
	}

	return manipulatedVectorOrNull;
    }

}
