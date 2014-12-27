package com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator;

import com.oberger.kruppelbotsimulation.mvc_model.function.*;

public class LegPolyFunctions {

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

    public IPolyFunction getLegFunctionBR() {
        return functionBR;
    }

    public IPolyFunction getLegFunctionBL() {
        return functionBL;
    }

    public IPolyFunction getLegFunctionFR() {
        return functionFR;
    }

    public IPolyFunction getLegFunctionFL() {
        return functionFL;
    }

}

