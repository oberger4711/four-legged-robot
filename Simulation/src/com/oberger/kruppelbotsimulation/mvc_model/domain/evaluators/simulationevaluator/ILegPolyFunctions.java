/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator;

import com.oberger.kruppelbotsimulation.mvc_model.function.IPolyFunction;

/**
 *
 * @author ole
 */
public interface ILegPolyFunctions {

    public IPolyFunction getLegFunctionBL();

    public IPolyFunction getLegFunctionBR();

    public IPolyFunction getLegFunctionFL();

    public IPolyFunction getLegFunctionFR();
    
}
