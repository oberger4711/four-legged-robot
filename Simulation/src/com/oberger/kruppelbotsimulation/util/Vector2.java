package com.oberger.kruppelbotsimulation.util;

/**
 * A two dimensional vector.
 *
 * @author ole
 */
public class Vector2 {

    private float x = 0;
    private float y = 0;
    
    public static Vector2 zero() {
        return new Vector2(0, 0);
    }

    public Vector2() {
        this(0, 0);
    }

    public Vector2(Vector2 vectorToCopy) {
        this(vectorToCopy.getX(), vectorToCopy.getY());
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return this.x;
    }

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

    /**
     * Returns the sum of the two vectors. Does not modify this vector but
     * returns a new instance.
     *
     * @param otherVector
     * @return
     */
    public Vector2 add(Vector2 otherVector) {
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
    public Vector2 subtract(Vector2 otherVector) {
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

    @Override
    public boolean equals(Object obj) {
        boolean result;
        if (obj instanceof Vector2) {
            Vector2 objAsVector = (Vector2) obj;
            result = (x == objAsVector.x && y == objAsVector.y);
        } else {
            result = super.equals(obj);
        }
        
        return result;
    }

}
