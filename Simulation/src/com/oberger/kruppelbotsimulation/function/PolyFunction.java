package com.oberger.kruppelbotsimulation.function;

import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
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
        boolean allOrdered = true;
        if (polygons.get(0) == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        for (int i = 1; i < polygons.size(); i++) {
            if (polygons.get(i) == null) {
                throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
            }
            if (!isOrdered(polygons.get(i - 1), polygons.get(i))) {
                throw new IllegalArgumentException("Vectors must be ordered.");
            }
        }
        this.interpolator = interpolator;
        this.polygons = new ArrayList<>(polygons);
    }

    private boolean isOrdered(IReadOnlyVector2 v1, IReadOnlyVector2 v2) {
        return v2.getX() > v1.getX();
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
