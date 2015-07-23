/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.legpolyfunctions;

import com.oberger.kruppelbotsimulation.mvc_model.function.IPolyFunction;
import com.oberger.kruppelbotsimulation.mvc_model.function.Interpolator;
import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import java.util.ArrayList;
import java.util.LinkedList;
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

    public PartialPolyFunction(IReadOnlyVector2 first, List<IReadOnlyVector2> inner, IReadOnlyVector2 last, Interpolator interpolator) {
        if  (first == null || inner == null || last == null || interpolator == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        boolean allOrdered = true;
        for (int i = 0; i < inner.size(); i++) {
            IReadOnlyVector2 current = inner.get(i);
            if (i == 0) {
                allOrdered &= isOrdered(first, current);
            }
            else {
                allOrdered &= isOrdered(inner.get(i - 1), current);
            }
            if (i == inner.size() - 1) {
                allOrdered &= isOrdered(current, last);
            }
        }
        allOrdered &= isOrdered(first, last);
        if (!allOrdered) { 
            throw new IllegalArgumentException("Vectors must be ordered.");
        }
        this.first = first;
        this.inner = inner;
        this.last = last;
        this.interpolator = interpolator;
    }
    
    private boolean isOrdered(IReadOnlyVector2 v1, IReadOnlyVector2 v2) {
        return v2.getX() > v1.getX();
    }

    @Override
    public float getValue(float x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IReadOnlyVector2> getPolygons() {
        LinkedList<IReadOnlyVector2> polygons = new LinkedList<>();
        polygons.add(first);
        polygons.addAll(inner);
        polygons.add(last);
        
        return polygons;
    }

    @Override
    public Interpolator getInterpolator() {
        return interpolator;
    }

}
