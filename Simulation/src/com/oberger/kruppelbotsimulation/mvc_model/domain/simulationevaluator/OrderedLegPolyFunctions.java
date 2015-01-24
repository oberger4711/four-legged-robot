package com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator;

import com.oberger.kruppelbotsimulation.mvc_model.function.IPolyFunction;
import com.oberger.kruppelbotsimulation.mvc_model.function.WrappedPolyFunction;

public class OrderedLegPolyFunctions implements ILegPolyFunctions {

    private IPolyFunction originalFunction = null;
    private LegOrder legOrder = null;
    private float periodInS;
    
    private IPolyFunction decoratedFunctionBR = null;
    private IPolyFunction decoratedFunctionBL = null;
    private IPolyFunction decoratedFunctionFR = null;
    private IPolyFunction decoratedFunctionFL = null;

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

    private IPolyFunction decorateLegFunction(LegOrder.LegPosition position) {
        return new WrappedPolyFunction(originalFunction, periodInS, periodInS * legOrder.getOrder().indexOf(position) / LegOrder.LegPosition.values().length);
    }

    public LegOrder getLegOrder() {
        return legOrder;
    }

    public IPolyFunction getOriginalFunction() {
        return originalFunction;
    }
    
    @Override
    public IPolyFunction getLegFunctionBL() {
        return decoratedFunctionBL;
    }

    @Override
    public IPolyFunction getLegFunctionBR() {
        return decoratedFunctionBR;
    }

    @Override
    public IPolyFunction getLegFunctionFL() {
        return decoratedFunctionFL;
    }

    @Override
    public IPolyFunction getLegFunctionFR() {
        return decoratedFunctionFR;
    }

}
