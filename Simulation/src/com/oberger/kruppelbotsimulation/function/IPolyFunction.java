package com.oberger.kruppelbotsimulation.function;

import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import java.util.List;

public interface IPolyFunction {

    public float getValue(float x);

    public List<IReadOnlyVector2> getPolygons();

    public Interpolator getInterpolator();

}
