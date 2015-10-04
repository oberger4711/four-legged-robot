package com.oberger.kruppelbotsimulation.util;

import java.util.List;

/**
 * Immutable class that represents a mass equals or higher than zero.
 *
 * @author ole
 */
public class Weight {

    private final static float EQUALS_THRESHOLD = 0.00001f;
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

    @Override
    public boolean equals(Object obj) {
	boolean result;
	if (obj instanceof Weight) {
	    Weight objAsWeight = (Weight) obj;
	    result = ((Math.abs(objAsWeight.getValue() - value)) < EQUALS_THRESHOLD);
	} else {
	    result = super.equals(obj);
	}

	return result;
    }

    @Override
    public int hashCode() {
	int hash = 3;
	hash = 89 * hash + Float.floatToIntBits(this.value);
	hash = 89 * hash + (this.zero ? 1 : 0);
	return hash;
    }

    @Override
    public String toString() {
	return Float.toString(value);
    }

}
