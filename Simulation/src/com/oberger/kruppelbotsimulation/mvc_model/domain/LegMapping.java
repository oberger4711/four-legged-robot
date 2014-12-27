package com.oberger.kruppelbotsimulation.mvc_model.domain;

import com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator.LegPolyFunctions;
import com.oberger.kruppelbotsimulation.mvc_model.function.*;

public abstract class LegMapping {

    public LegPolyFunctions getLegFunctions(IPolyFunction originalFunction) {
        // TODO - implement ILegMapping.getLegFunctions
        throw new UnsupportedOperationException();
    }

    protected abstract IPolyFunction getLegFunctionBl(IPolyFunction originalFunction);

    protected abstract IPolyFunction getLegFunctionBr(IPolyFunction originalFunction);

    protected abstract IPolyFunction getLegFunctionFl(IPolyFunction originalFunction);

    protected abstract IPolyFunction getLegFunctionFr(IPolyFunction originalFunction);

}
