package com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator;

import com.oberger.kruppelbotsimulation.mvc_model.function.IPolyFunction;
import com.oberger.kruppelbotsimulation.mvc_model.function.WrappedPolyFunction;

public class OrderedLegPolyFunctions implements ILegPolyFunctions {

    private IPolyFunction originalFunction = null;
    private LegOrder legOrder = null;
    private float periodInS;
    
    private WrappedPolyFunction decoratedFunctionBR = null;
    private WrappedPolyFunction decoratedFunctionBL = null;
    private WrappedPolyFunction decoratedFunctionFR = null;
    private WrappedPolyFunction decoratedFunctionFL = null;

    public OrderedLegPolyFunctions(IPolyFunction originalFunction, LegOrder legOrder, float periodInS) {
        if (legOrder == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        this.originalFunction = originalFunction;
        this.legOrder = legOrder;
        this.periodInS = periodInS;
        decoratedFunctionBR = decorateLegFunction(LegOrder.LegPosition.BR);
        decoratedFunctionBL = decorateLegFunction(LegOrder.LegPosition.BL);
        decoratedFunctionFR = decorateLegFunction(LegOrder.LegPosition.FR);
        decoratedFunctionFL = decorateLegFunction(LegOrder.LegPosition.FL);
    }

    private WrappedPolyFunction decorateLegFunction(LegOrder.LegPosition position) {
        return new WrappedPolyFunction(originalFunction, periodInS, periodInS * legOrder.getOrder().indexOf(position) / LegOrder.LegPosition.values().length);
    }

    public LegOrder getLegOrder() {
        return legOrder;
    }

    public IPolyFunction getOriginalFunction() {
        return originalFunction;
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
