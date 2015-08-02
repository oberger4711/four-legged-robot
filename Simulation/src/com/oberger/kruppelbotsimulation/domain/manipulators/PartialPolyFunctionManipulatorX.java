/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.manipulators;

import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.PartialPolyFunction;
import com.oberger.kruppelbotsimulation.localsearch.manipulator.IManipulator;
import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author oberger
 */
public class PartialPolyFunctionManipulatorX extends PartialPolyFunctionManipulator {
    
    public PartialPolyFunctionManipulatorX(float manipulationStep) {
	super(manipulationStep);
    }

    @Override
    protected IReadOnlyVector2 manipulatePolygon(IReadOnlyVector2 polygonToManipulate, float manipulationStep) {
	float newX = polygonToManipulate.getX() + manipulationStep;
	float newY = polygonToManipulate.getY();
	IReadOnlyVector2 manipulatedVectorOrNull = new Vector2(newX, newY);

	return manipulatedVectorOrNull;
    }
    
}
