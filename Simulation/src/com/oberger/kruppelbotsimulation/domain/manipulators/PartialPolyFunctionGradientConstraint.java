/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.manipulators;

import com.oberger.kruppelbotsimulation.domain.simulation.legpolyfunctions.PartialPolyFunction;
import com.oberger.kruppelbotsimulation.localsearch.manipulator.IConstraint;
import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import java.util.List;

/**
 *
 * @author oberger
 */
public class PartialPolyFunctionGradientConstraint implements IConstraint<PartialPolyFunction> {

    private float maxAbsGradient;

    public PartialPolyFunctionGradientConstraint(float maxAbsGradient) {
	if (maxAbsGradient <= 0) {
	    throw new IllegalArgumentException("maxAbsGradient must not be negative or zero but was " + maxAbsGradient + ".");
	}
	this.maxAbsGradient = maxAbsGradient;
    }

    @Override
    public boolean check(PartialPolyFunction manipulated) {
	boolean check = true;
	IReadOnlyVector2 previous = manipulated.getFirst();
	List<IReadOnlyVector2> inner = manipulated.getInner();
	for (IReadOnlyVector2 current : inner) {
	    check &= checkGradient(previous, current);
	    previous = current;
	}
	check &= checkGradient(previous, manipulated.getLast());

	return check;
    }

    private float calcGradient(IReadOnlyVector2 v1, IReadOnlyVector2 v2) {
	return (v2.getY() - v1.getY()) / (v2.getX() - v1.getX());
    }
    
    private boolean checkGradient(IReadOnlyVector2 v1, IReadOnlyVector2 v2) {
	return !(Math.abs(calcGradient(v1, v2)) > maxAbsGradient);
    }

}
