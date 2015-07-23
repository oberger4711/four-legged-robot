package com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.legpolyfunctions;

import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.LegOrder;
import com.oberger.kruppelbotsimulation.mvc_model.function.PolyFunction;
import com.oberger.kruppelbotsimulation.mvc_model.function.WrappedPolyFunction;

public class OrderedLegPolyFunctions implements ILegPolyFunctions {

    private PolyFunction originalFunction = null;
    private LegOrder legOrder = null;
    private float periodInS;
    
    private WrappedPolyFunction decoratedFunctionBR = null;
    private WrappedPolyFunction decoratedFunctionBL = null;
    private WrappedPolyFunction decoratedFunctionFR = null;
    private WrappedPolyFunction decoratedFunctionFL = null;

    public OrderedLegPolyFunctions(PolyFunction originalFunction, LegOrder legOrder, float periodInS) {
        if (legOrder == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        this.originalFunction = originalFunction;
        this.legOrder = legOrder;
        this.periodInS = periodInS;
    }

    public LegOrder getLegOrder() {
        return legOrder;
    }

    public PolyFunction getOriginalFunction() {
        return originalFunction;
    }

    public float getPeriodInS() {
        return periodInS;
    }
    
    @Override
    public WrappedPolyFunction getLegFunctionBL() {
        return decoratedFunctionBL;
    }

    @Override
    public WrappedPolyFunction getLegFunctionBR() {
        return decoratedFunctionBR;
    }

    @Override
    public WrappedPolyFunction getLegFunctionFL() {
        return decoratedFunctionFL;
    }

    @Override
    public WrappedPolyFunction getLegFunctionFR() {
        return decoratedFunctionFR;
    }

}
