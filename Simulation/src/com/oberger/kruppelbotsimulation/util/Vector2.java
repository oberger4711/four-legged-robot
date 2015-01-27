package com.oberger.kruppelbotsimulation.util;

/**
 * A two dimensional vector.
 *
 * @author ole
 */
public class Vector2 implements IReadOnlyVector2 {
    
    private final static float EQUALS_THRESHOLD = 0.00001f;

    private float x = 0;
    private float y = 0;
    
    public static Vector2 zero() {
        return new Vector2(0, 0);
    }

    public Vector2() {
        this(0, 0);
    }

    public Vector2(IReadOnlyVector2 vectorToCopy) {
        this(vectorToCopy.getX(), vectorToCopy.getY());
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void set(IReadOnlyVector2 vector) {
        set(vector.getX(), vector.getY());
    }

    /**
     * Returns the sum of the two vectors. Does not modify this vector but
     * returns a new instance.
     *
     * @param otherVector
     * @return
     */
    public Vector2 add(IReadOnlyVector2 otherVector) {
        if (otherVector == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        x = x + otherVector.getX();
        y = y + otherVector.getY();

        return this;
    }

    /**
     * Returns the subtraction of the vectors. Does not modify this vector but
     * returns a new instance.
     *
     * @param otherVector
     * @return
     */
    public Vector2 subtract(IReadOnlyVector2 otherVector) {
        if (otherVector == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        x = x - otherVector.getX();
        y = y - otherVector.getY();

        return this;
    }

    /**
     * Returns a scaled version of this vector. Does not modify this vector but
     * returns a new instance.
     *
     * @param factor
     * @return
     */
    public Vector2 scale(float factor) {
        x = x * factor;
        y = y * factor;

        return this;
    }
    
    public Vector2 rotate(Vector2 center, float degreesCC) {
        if (center == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        double radiansCC = Math.toRadians(degreesCC);
        double rotatedX = Math.cos(radiansCC) * (x - center.getX()) - Math.sin(radiansCC) * (y - center.getY()) + center.getX();
        double rotatedY = Math.sin(radiansCC * (x - center.getX())) + Math.cos(radiansCC) * (y - center.getY()) + center.getY();
        x = (float)rotatedX;
        y = (float)rotatedY;
        
        return this;
    }

    @Override
    public float getLength() {
        return (float)Math.sqrt(x * x + y * y);
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean result;
        if (obj instanceof Vector2) {
            Vector2 objAsVector = (Vector2) obj;
            result = ((Math.abs(objAsVector.getX() - x) < EQUALS_THRESHOLD) && (Math.abs(objAsVector.getY() - y) < EQUALS_THRESHOLD));
        } else {
            result = super.equals(obj);
        }
        
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Float.floatToIntBits(this.x);
        hash = 79 * hash + Float.floatToIntBits(this.y);
        
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("( ");
        sb.append(x);
        sb.append(" | ");
        sb.append(y);
        sb.append(" )");
        
        return sb.toString();
    }

}
