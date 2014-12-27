package com.oberger.kruppelbotsimulation.mvc_model.domain;

import com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator.LegPolyFunctions;
import com.oberger.kruppelbotsimulation.mvc_model.function.*;

public abstract class LegMapping {

    public LegPolyFunctions getLegFunctions(IPolyFunction originalFunction) {
        if (originalFunction == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        return new LegPolyFunctions(getLegFunctionBr(originalFunction), getLegFunctionBl(originalFunction), getLegFunctionFr(originalFunction), getLegFunctionFl(originalFunction));
    }

    protected abstract IPolyFunction getLegFunctionBl(IPolyFunction originalFunction);

    protected abstract IPolyFunction getLegFunctionBr(IPolyFunction originalFunction);

    protected abstract IPolyFunction getLegFunctionFl(IPolyFunction originalFunction);

    protected abstract IPolyFunction getLegFunctionFr(IPolyFunction originalFunction);

}
