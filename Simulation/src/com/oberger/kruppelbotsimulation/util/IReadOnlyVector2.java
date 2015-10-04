package com.oberger.kruppelbotsimulation.util;

import java.io.Serializable;

public interface IReadOnlyVector2 extends Serializable {

    float getX();

    float getY();

    float getLength();

    @Override
    boolean equals(Object obj);

    @Override
    int hashCode();

}
