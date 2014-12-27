package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator.LegMapping;
import com.oberger.kruppelbotsimulation.mvc_model.function.IPolyFunction;
import com.oberger.kruppelbotsimulation.mvc_model.function.WrappedPolyFunction;

public class OrderedLegMapping extends LegMapping {

    private LegOrder legOrder = null;
    private float periodInS;

    public OrderedLegMapping(LegOrder legOrder, float periodInS) {
        if (legOrder == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        this.legOrder = legOrder;
        this.periodInS = periodInS;
    }

    @Override
    protected IPolyFunction getLegFunctionBl(IPolyFunction originalFunction) {
        return decorateLegFunction(originalFunction, LegOrder.LegPosition.BL);
    }

    @Override
    protected IPolyFunction getLegFunctionBr(IPolyFunction originalFunction) {
        return decorateLegFunction(originalFunction, LegOrder.LegPosition.BR);
    }

    @Override
    protected IPolyFunction getLegFunctionFl(IPolyFunction originalFunction) {
        return decorateLegFunction(originalFunction, LegOrder.LegPosition.FL);
    }

    @Override
    protected IPolyFunction getLegFunctionFr(IPolyFunction originalFunction) {
        return decorateLegFunction(originalFunction, LegOrder.LegPosition.FR);
    }

    private IPolyFunction decorateLegFunction(IPolyFunction original, LegOrder.LegPosition position) {
        return new WrappedPolyFunction(original, periodInS, periodInS * legOrder.getOrder().indexOf(position) / LegOrder.LegPosition.values().length);
    }

    public LegOrder getLegOrder() {
        return legOrder;
    }

}
