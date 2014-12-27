package com.oberger.kruppelbotsimulation.mvc_model.domain;

import com.oberger.kruppelbotsimulation.mvc_model.function.*;

public interface ILegPolyFunctions {

    public IPolyFunction getLegFunctionBR();

    public IPolyFunction getLegFunctionBL();

    public IPolyFunction getLegFunctionFR();

    public IPolyFunction getLegFunctionFL();

}
