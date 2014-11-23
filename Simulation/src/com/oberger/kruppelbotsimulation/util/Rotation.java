package com.oberger.kruppelbotsimulation.util;

/**
 * A rotation mapped to the interval 0 to {@link #FULL_ROTATION_IN_DEGREES}. Provides getters and setters for clockwise and counterclockwise rotation.
 * @author ole
 */
public class Rotation {

    private final static float EQUALS_THRESHOLD = 0.00001f;
    private final static float FULL_ROTATION_IN_DEGREES = 360;
    
    private float rotationInDegreesCC = 0;

    public Rotation(float rotationInDegrees, boolean counterClockwise) {
        if (counterClockwise) {
            setRotationCC(rotationInDegrees);
        }
        else {
            setRotationCW(rotationInDegrees);
        }
    }
    
    public Rotation(Rotation rotationToCopy) {
        this.rotationInDegreesCC = rotationToCopy.rotationInDegreesCC;
    }
    
    public Rotation add(Rotation secondSummand) {
        if (secondSummand == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        setRotationCC(rotationInDegreesCC + secondSummand.getRotationInDegreesCC());
        
        return this;
    }
    
    public Rotation subtract(Rotation subtrahend) {
        if (subtrahend == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        setRotationCC(rotationInDegreesCC - subtrahend.getRotationInDegreesCC());
        
        return this;
    }
    
    public final void setRotationCC(float rotationInDegreesCounterClockwise) {
        rotationInDegreesCC = mapOntoZeroToFullRotationInDegrees(rotationInDegreesCounterClockwise);
    }

    public final void setRotationCW(float rotationInDegreesClockwise) {
        rotationInDegreesCC = FULL_ROTATION_IN_DEGREES - mapOntoZeroToFullRotationInDegrees(rotationInDegreesClockwise);
    }

    public float getRotationInDegreesCC() {
        return rotationInDegreesCC;
    }

    public float getRotationInDegreesCW() {
        return FULL_ROTATION_IN_DEGREES - rotationInDegreesCC;
    }

    @Override
    public boolean equals(Object obj) {
        boolean areEqual;
        if (obj instanceof Rotation) {
            Rotation otherRotation = (Rotation) obj;
            
            areEqual = Math.abs(rotationInDegreesCC - otherRotation.rotationInDegreesCC) < EQUALS_THRESHOLD;
        }
        else {
            areEqual = super.equals(obj);
        }
        return areEqual;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Float.floatToIntBits(this.rotationInDegreesCC);
        return hash;
    }
    
    private static float mapOntoZeroToFullRotationInDegrees(float rotationInDegrees) {
        float mappedRotationInDegrees = rotationInDegrees;
        while (mappedRotationInDegrees < 0) {
            mappedRotationInDegrees += FULL_ROTATION_IN_DEGREES;
        }
        mappedRotationInDegrees %= FULL_ROTATION_IN_DEGREES; // Map rotation into positive.
        
        return mappedRotationInDegrees;
    }

}
