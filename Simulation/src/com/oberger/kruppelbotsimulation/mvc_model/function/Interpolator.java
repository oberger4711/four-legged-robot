package com.oberger.kruppelbotsimulation.mvc_model.function;

import com.oberger.kruppelbotsimulation.util.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class Interpolator {

    public float getValue(List<IReadOnlyVector2> polygons, float x) {
        if (polygons == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        if (polygons.size() < 2) {
            throw new IllegalArgumentException("List must contain at least two polygons.");
        }

        Float interpolatedValue = null;
        // Check inside polygons interval. Interpolate.
        for (int i = 0; i < polygons.size() - 1; i++) {
            IReadOnlyVector2 polygon1 = polygons.get(i);
            IReadOnlyVector2 polygon2 = polygons.get(i + 1);

            if (polygon1.getX() > polygon2.getX()) {
                throw new IllegalArgumentException("Polygons must be sorted and no duplicate X allowed.");
            }

            if (polygon1.getX() == x) {
                interpolatedValue = polygon1.getY();
            } else if (polygon2.getX() == x) {
                interpolatedValue = polygon2.getY();
            } else if (polygon1.getX() < x && x < polygon2.getX()) {
                interpolatedValue = interpolateY(polygon1, polygon2, x);
            }
        }

        if (interpolatedValue == null) {
            // Check outside polygons interval Extrapolate.
            if (polygons.get(0).equals(polygons.get(polygons.size() - 1))) {
                throw new IllegalArgumentException("List must contain at least two different polygons.");
            }
            if (x < polygons.get(0).getX()) {
                interpolatedValue = interpolateY(polygons.get(0), polygons.get(1), x);
            } else {
                interpolatedValue = interpolateY(polygons.get(polygons.size() - 2), polygons.get(polygons.size() - 1), x);
            }
        }

        return interpolatedValue;
    }

    public List<Vector2> getPolygons(IReadOnlyVector2 start, IReadOnlyVector2 end, int numberOfPolygons) {
        if (start == null || end == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        if (numberOfPolygons < 2) {
            throw new IllegalArgumentException("numberOfPolygons must be 2 or higher but was " + numberOfPolygons + ".");
        }
        List<IReadOnlyVector2> startEndAsList = Arrays.asList(start, end);
        float deltaX = end.getX() - start.getX();
        List<Vector2> interpolatedPolygons = new LinkedList<>();
        interpolatedPolygons.add(new Vector2(start));
        for (int i = 1; i < numberOfPolygons - 1; i++) {
            float interpolatedDeltaX = deltaX * ((float) i / (numberOfPolygons - 1));
            Vector2 interpolatedPolygon = new Vector2(start.getX() + interpolatedDeltaX, interpolateY(start, end, interpolatedDeltaX));
            interpolatedPolygons.add(interpolatedPolygon);
        }
        interpolatedPolygons.add(new Vector2(end));

        return interpolatedPolygons;
    }

    protected abstract float interpolateY(IReadOnlyVector2 polygon1, IReadOnlyVector2 polygon2, float x);

}
