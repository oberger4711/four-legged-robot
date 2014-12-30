package com.oberger.kruppelbotsimulation.mvc_model.function;

import com.oberger.kruppelbotsimulation.util.*;

public class LinearInterpolator extends Interpolator {

    @Override
    protected float interpolateY(IReadOnlyVector2 polygon1, IReadOnlyVector2 polygon2, float x) {
        float a = (polygon2.getY() - polygon1.getY()) / (polygon2.getX() - polygon1.getX());
        float c = polygon2.getY() - a * polygon2.getX();

        return a * x + c;
    }
    
}
