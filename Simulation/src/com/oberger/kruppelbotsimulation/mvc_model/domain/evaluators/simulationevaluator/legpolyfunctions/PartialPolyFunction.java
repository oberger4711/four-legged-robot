/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.legpolyfunctions;

import com.oberger.kruppelbotsimulation.mvc_model.function.Interpolator;
import com.oberger.kruppelbotsimulation.mvc_model.function.PolyFunction;
import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author oberger
 */
public class PartialPolyFunction {

	private IReadOnlyVector2 first = null;
	private List<IReadOnlyVector2> inner = null;
	private IReadOnlyVector2 last = null;

	public PartialPolyFunction(IReadOnlyVector2 first, List<IReadOnlyVector2> inner, IReadOnlyVector2 last) {
		this.first = first;
		this.inner = inner;
		this.last = last;
	}

	public IReadOnlyVector2 getFirst() {
		return first;
	}

	public List<IReadOnlyVector2> getInner() {
		return new ArrayList<>(inner);
	}

	public IReadOnlyVector2 getLast() {
		return last;
	}

}
