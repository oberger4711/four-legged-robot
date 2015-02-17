package com.oberger.kruppelbotsimulation.mvc_model.function;

import com.oberger.kruppelbotsimulation.util.*;
import java.util.List;

public interface IPolyFunction {

    public float getValue(float x);

    public List<IReadOnlyVector2> getPolygons();

    public Interpolator getInterpolator();

}
