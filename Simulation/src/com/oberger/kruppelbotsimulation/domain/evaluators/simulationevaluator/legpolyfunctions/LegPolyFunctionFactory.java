/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions;

import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.LegOrder;
import com.oberger.kruppelbotsimulation.function.Interpolator;
import java.util.List;

/**
 *
 * @author oberger
 */
public class LegPolyFunctionFactory {

	private Interpolator interpolator;
	private List<ConcatPart> parts = null;
	private float period;

	public LegPolyFunctionFactory(Interpolator interpolator, List<ConcatPart> parts, float period) {
		this.interpolator = interpolator;
		this.parts = parts;
		this.period = period;
	}

	public ILegPolyFunctions create(LegOrder legOrder) {
		float quarterPeriod = period / 4f;
		return new LegPolyFunctions(createLegFunction(legOrder.getIndexOfBR() * quarterPeriod), createLegFunction(legOrder.getIndexOfBL() * quarterPeriod), createLegFunction(legOrder.getIndexOfFR() * quarterPeriod), createLegFunction(legOrder.getIndexOfFL() * quarterPeriod));
	}
	
	private ConcatPolyFunction createLegFunction(float offset) {
		return new ConcatPolyFunction(interpolator, parts, period, offset);
	}
	
}
