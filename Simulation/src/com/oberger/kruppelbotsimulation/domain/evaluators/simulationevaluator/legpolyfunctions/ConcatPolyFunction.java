/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions;

import com.oberger.kruppelbotsimulation.function.Interpolator;
import com.oberger.kruppelbotsimulation.function.PolyFunction;
import com.oberger.kruppelbotsimulation.function.WrappedPolyFunction;
import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author oberger
 */
public class ConcatPolyFunction extends WrappedPolyFunction {

    private List<ConcatPart> parts = null;

    public ConcatPolyFunction(Interpolator interpolator, List<ConcatPart> parts, float period, float offset) {
	super(new PolyFunction(interpolator, concatToList(parts)), period, offset);
	this.parts = new ArrayList<>(parts);
    }

    public ConcatPolyFunction(Interpolator interpolator, List<ConcatPart> parts, float offset) {
	super(new PolyFunction(interpolator, concatToList(parts)), offset);
	this.parts = new ArrayList<>(parts);
    }

    private static List<IReadOnlyVector2> concatToList(List<ConcatPart> parts) {
	LinkedList<IReadOnlyVector2> polygons = new LinkedList<>();
	if (parts == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}
	if (parts.size() > 0) {
	    polygons.add(parts.get(0).getFunction().getFirst());
	}
	for (ConcatPart part : parts) {
	    if (!part.getFunction().getFirst().equals(polygons.getLast())) {
		throw new IllegalArgumentException("Part's first Polygon must be equal to previous part last polygon.");
	    }
	    polygons.addAll(part.getFunction().getInner());
	    polygons.add(part.getFunction().getLast());
	}

	return polygons;
    }

    public ConcatPart getPart(float x) {
	ConcatPart containingPart = null;
	float mappedX = wrapOntoPeriod(x);
	for (ConcatPart part : parts) {
	    if (part.isInPart(mappedX)) {
		containingPart = part;
	    }
	}

	return containingPart;
    }

    public List<ConcatPart> getParts() {
	return new ArrayList<>(parts);
    }

}
