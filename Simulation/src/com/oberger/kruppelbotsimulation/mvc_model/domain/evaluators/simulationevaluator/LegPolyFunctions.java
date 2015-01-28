package com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator;

import com.oberger.kruppelbotsimulation.mvc_model.function.*;

public class LegPolyFunctions implements ILegPolyFunctions {

    private IPolyFunction functionBR = null;
    private IPolyFunction functionBL = null;
    private IPolyFunction functionFR = null;
    private IPolyFunction functionFL = null;

    public LegPolyFunctions(IPolyFunction functionBR, IPolyFunction functionBL, IPolyFunction functionFR, IPolyFunction functionFL) {
        if (functionBR == null || functionBL == null || functionFR == null || functionFL == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        this.functionBR = functionBR;
        this.functionBL = functionBL;
        this.functionFR = functionFR;
        this.functionFL = functionFL;
    }

    @Override
    public IPolyFunction getLegFunctionBR() {
        return functionBR;
    }

    @Override
    public IPolyFunction getLegFunctionBL() {
        return functionBL;
    }

    @Override
    public IPolyFunction getLegFunctionFR() {
        return functionFR;
    }

    @Override
    public IPolyFunction getLegFunctionFL() {
        return functionFL;
    }

}

