/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.simulation.legpolyfunctions;

import java.io.Serializable;

/**
 *
 * @author oberger
 */
public class ConcatPart implements Serializable {

    private PartialPolyFunction function = null;
    private EManipulatable manipulatable;
    private EBalanceMode balanceMode;

    public ConcatPart(PartialPolyFunction function, EManipulatable manipulatable, EBalanceMode balanceMode) {
	if (function == null) {
	    throw new IllegalArgumentException("Passing null is not allowed.");
	}
	this.function = function;
	this.manipulatable = manipulatable;
	this.balanceMode = balanceMode;
    }

    public boolean isInPart(float x) {
	return function.getFirst().getX() <= x && x <= function.getLast().getX();
    }

    public PartialPolyFunction getFunction() {
	return function;
    }

    public EManipulatable getManipulatable() {
	return manipulatable;
    }

    public EBalanceMode getBalanceMode() {
	return balanceMode;
    }

}
