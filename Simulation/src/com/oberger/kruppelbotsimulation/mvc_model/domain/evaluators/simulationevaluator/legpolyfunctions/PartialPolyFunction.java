/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.legpolyfunctions;

import com.oberger.kruppelbotsimulation.mvc_model.function.IPolyFunction;
import com.oberger.kruppelbotsimulation.mvc_model.function.Interpolator;
import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import java.util.List;

/**
 *
 * @author oberger
 */
public class PartialPolyFunction implements IPolyFunction {

	private Interpolator interpolator = null;
	private IReadOnlyVector2 first = null;
	private List<IReadOnlyVector2> inner = null;
	private IReadOnlyVector2 last = null;

	public PartialPolyFunction(IReadOnlyVector2 first, List<IReadOnlyVector2> inner, IReadOnlyVector2 last) {
		this.first = first;
		this.inner = inner;
		this.last = last;
	}

	@Override
	public float getValue(float x) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<IReadOnlyVector2> getPolygons() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Interpolator getInterpolator() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
