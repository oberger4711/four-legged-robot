package com.oberger.kruppelbotsimulation.mvc_model.function;

import com.oberger.kruppelbotsimulation.util.*;
import java.util.List;

public class LinearInterpolator implements IInterpolator {

    @Override
    public float getValue(List<IReadOnlyVector2> polygons, float x) {
        if (polygons == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        if (polygons.size() < 2) {
            throw new IllegalArgumentException("List must contain at least two polygons.");
        }

        Float interpolatedValue = null;
        // Check inside polygons interval.
        for (int i = 0; i < polygons.size() - 1; i++) {
            IReadOnlyVector2 polygon1 = polygons.get(i);
            IReadOnlyVector2 polygon2 = polygons.get(i + 1);

            if (polygon1.getX() >= polygon2.getX()) {
                throw new IllegalArgumentException("Polygons must be sorted and must lay side by side.");
            }

            if (polygon1.getX() <= x && x <= polygon2.getX()) {
                interpolatedValue = interpolate(polygon1, polygon2, x);
            }
        }

        if (interpolatedValue == null) {
            // Check outside polygons interval.
            if (x < polygons.get(0).getX()) {
                interpolatedValue = interpolate(polygons.get(0), polygons.get(1), x);
            }
            else {
                interpolatedValue = interpolate(polygons.get(polygons.size() - 2), polygons.get(polygons.size() - 1), x);
            }
        }

        return interpolatedValue;
    }

    private float interpolate(IReadOnlyVector2 polygon1, IReadOnlyVector2 polygon2, float x) {
        float a = (polygon2.getY() - polygon1.getY()) / (polygon2.getX() - polygon1.getX());
        float c = polygon2.getY() - a * polygon2.getX();

        return a * x + c;
    }
}
