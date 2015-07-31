package com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions;

import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.LegPosition;

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

    @Override
    public ConcatPolyFunction getLegFunction(LegPosition position) {
	ConcatPolyFunction result = null;
	switch (position) {
	    case BL:
		result = functionBL;
		break;
	    case BR:
		result = functionBR;
		break;
	    case FL:
		result = functionFL;
		break;
	    case FR:
		result = functionFR;
		break;
	}
	
	return result;
    }

}
