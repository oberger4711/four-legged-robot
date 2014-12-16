package com.oberger.kruppelbotsimulation.mvc_model.function;

import com.oberger.kruppelbotsimulation.util.*;
import java.util.List;

public interface IInterpolator {

    public float getValue(List<IReadOnlyVector2> polygons, float x);

}
