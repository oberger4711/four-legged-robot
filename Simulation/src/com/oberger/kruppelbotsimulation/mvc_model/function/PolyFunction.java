package com.oberger.kruppelbotsimulation.mvc_model.function;

import com.oberger.kruppelbotsimulation.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PolyFunction implements IPolyFunction {

    private Interpolator interpolator;
    private List<IReadOnlyVector2> polygons;

    public PolyFunction(Interpolator interpolator, List<IReadOnlyVector2> polygons) {
        if (interpolator == null || polygons == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        if (polygons.size() < 2) {
            throw new IllegalArgumentException("List must contain at least two polygons.");
        }
        this.interpolator = interpolator;
        this.polygons = new ArrayList<>(polygons);
    }

    @Override
    public float getValue(float x) {
        return interpolator.getValue(polygons, x);
    }

    @Override
    public List<IReadOnlyVector2> getPolygons() {
        return Collections.unmodifiableList(polygons);
    }

    @Override
    public Interpolator getInterpolator() {
        return interpolator;
    }
    
}
