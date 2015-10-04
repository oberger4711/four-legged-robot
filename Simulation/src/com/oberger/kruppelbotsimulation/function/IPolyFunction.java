package com.oberger.kruppelbotsimulation.function;

import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import java.io.Serializable;
import java.util.List;

public interface IPolyFunction extends Serializable {

    public float getValue(float x);

    public List<IReadOnlyVector2> getPolygons();

    public Interpolator getInterpolator();

}
