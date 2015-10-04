/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.simulation.legpolyfunctions;

import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oberger
 */
public class PartialPolyFunction implements Serializable {

    private IReadOnlyVector2 first = null;
    private List<IReadOnlyVector2> inner = null;
    private IReadOnlyVector2 last = null;

    public PartialPolyFunction(IReadOnlyVector2 first, List<IReadOnlyVector2> inner, IReadOnlyVector2 last) {
	if (first == null || inner == null || last == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}
	this.first = first;
	this.inner = inner;
	this.last = last;
    }

    public PartialPolyFunction(List<IReadOnlyVector2> vectors) {
	this(vectors.get(0), new ArrayList(vectors.subList(1, vectors.size() - 1)), vectors.get(vectors.size() - 1));
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
