/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.simulation.legpolyfunctions;

import com.oberger.kruppelbotsimulation.domain.simulation.LegPosition;
import java.io.Serializable;

/**
 *
 * @author ole
 */
public interface ILegPolyFunctions extends Serializable {

    public ConcatPolyFunction getLegFunctionBL();

    public ConcatPolyFunction getLegFunctionBR();

    public ConcatPolyFunction getLegFunctionFL();

    public ConcatPolyFunction getLegFunctionFR();

    public ConcatPolyFunction getLegFunction(LegPosition position);

}
