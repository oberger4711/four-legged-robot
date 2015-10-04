/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.localsearch.manipulator;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author oberger
 */
public class ConstrainedManipulator<T> implements IManipulator<T> {

    private IManipulator<T> decoratee = null;
    private List<IConstraint<T>> constraints = null;
    
    public ConstrainedManipulator(IManipulator<T> decoratee, List<IConstraint<T>> constraints) {
	if (decoratee == null || constraints == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}
	this.decoratee = decoratee;
	this.constraints = constraints;
    }
    
    @Override
    public List<T> createNeighbours(T originalInnerState) {
	return decoratee.createNeighbours(originalInnerState).stream().filter(i -> 
	{
	    boolean check = true;
	    for (IConstraint<T> constraint : constraints) {
		if (!constraint.check(i)) {
		    check = false;
		}
	    }
	    
	    return check;
	}).collect(Collectors.toList());
    }
    
}
