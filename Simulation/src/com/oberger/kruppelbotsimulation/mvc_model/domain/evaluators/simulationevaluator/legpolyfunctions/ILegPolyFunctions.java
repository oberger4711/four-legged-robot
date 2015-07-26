/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.legpolyfunctions;

import com.oberger.kruppelbotsimulation.mvc_model.function.Interpolator;

/**
 *
 * @author ole
 */
public interface ILegPolyFunctions {

    public ConcatPolyFunction getLegFunctionBL();
    public ConcatPolyFunction getLegFunctionBR();
    public ConcatPolyFunction getLegFunctionFL();
    public ConcatPolyFunction getLegFunctionFR();
    
}
