package com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.legpolyfunctions;

public class LegPolyFunctions implements ILegPolyFunctions {

	private ConcatPolyFunction functionBR = null;
	private ConcatPolyFunction functionBL = null;
	private ConcatPolyFunction functionFR = null;
	private ConcatPolyFunction functionFL = null;

	public LegPolyFunctions(ConcatPolyFunction functionBR, ConcatPolyFunction functionBL, ConcatPolyFunction functionFR, ConcatPolyFunction functionFL) {
		if (functionBR == null || functionBL == null || functionFR == null || functionFL == null) {
			throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
		}
		this.functionBR = functionBR;
		this.functionBL = functionBL;
		this.functionFR = functionFR;
		this.functionFL = functionFL;
	}

	@Override
	public ConcatPolyFunction getLegFunctionBL() {
		return functionBL;
	}

	@Override
	public ConcatPolyFunction getLegFunctionBR() {
		return functionBR;
	}

	@Override
	public ConcatPolyFunction getLegFunctionFL() {
		return functionFL;
	}

	@Override
	public ConcatPolyFunction getLegFunctionFR() {
		return functionFR;
	}
	
}
