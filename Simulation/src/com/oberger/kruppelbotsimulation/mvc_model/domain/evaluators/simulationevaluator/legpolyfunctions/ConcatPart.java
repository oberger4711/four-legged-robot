/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.legpolyfunctions;

/**
 *
 * @author oberger
 */
public class ConcatPart {

	private PartialPolyFunction function = null;
	private boolean fixed = false;

	public ConcatPart(PartialPolyFunction function, boolean fixed) {
		this.function = function;
		this.fixed = fixed;
	}

	public PartialPolyFunction getFunction() {
		return function;
	}

	public boolean isFixed() {
		return fixed;
	}

}
