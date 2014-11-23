package com.oberger.kruppelbotsimulation.util;

import java.util.List;

/**
 * Immutable class that represents a mass equals or higher than zero.
 * @author ole
 */
public class Weight {

    private final static float ZERO_WEIGHT_THRESHOLD = 0.0001f;

    private float value = 0;
    private boolean zero = true;
    
    public Weight(List<Weight> weights) {
        this();
        if (weights == null) {
            throw new IllegalArgumentException("Passing zero is not allowed.");
        }
        if (weights.isEmpty()) {
            throw new IllegalArgumentException("The list must not be empty.");
        }
        for (Weight summand : weights) {
            if (!summand.isZero()) {
                value += summand.getValue();
                zero = false;
            }
        }
    }

    public Weight(float value) {
        if (value < ZERO_WEIGHT_THRESHOLD) {
            throw new IllegalArgumentException("Value must be higher than " + ZERO_WEIGHT_THRESHOLD);
        }
        this.value = value;
        zero = false;
    }

    public Weight() {
        value = 0;
        zero = true;
    }

    public float getValue() {
        return value;
    }

    public boolean isZero() {
        return zero;
    }

}
