package com.oberger.kruppelbotsimulation.mvc_model.model;

import com.oberger.kruppelbotsimulation.util.Rotation;
import com.oberger.kruppelbotsimulation.util.Vector2;
import com.oberger.kruppelbotsimulation.util.Weight;

/**
 * Abstract superclass of all simulation objects.
 *
 * @author ole
 */
public abstract class SimObject {

    private IParentSimObject parent = null;

    private Vector2 globalPosition = null;
    private Weight globalWeight = null;
    private Rotation globalRotation = null;
    private Vector2 offsetPosition = null;
    private Weight offsetWeight = null;
    private Rotation offsetRotation = null;

    protected SimObject(Vector2 offsetPosition, Weight offsetWeight, Rotation offsetRotation) {
        if (offsetPosition == null || offsetWeight == null || offsetRotation == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        this.offsetPosition = new Vector2(offsetPosition);
        this.offsetWeight = offsetWeight;
        this.offsetRotation = new Rotation(offsetRotation);
        this.globalPosition = new Vector2(offsetPosition);
        this.globalWeight = offsetWeight;
        this.globalRotation = new Rotation(offsetRotation);
    }

    public void update() {
        Vector2 parentGlobalPosition = null;
        Rotation parentGlobalRotation = null;
        
        if (parent != null) {
            parentGlobalPosition = parent.getGlobalPosition();
            parentGlobalRotation = parent.getGlobalRotation();
        } else {
            parentGlobalPosition = new Vector2();
            parentGlobalRotation = new Rotation(0, true);
        }

        globalRotation.set(parentGlobalRotation);
        globalRotation.add(offsetRotation);
        globalPosition.set(parentGlobalPosition);
        globalPosition.add(offsetPosition);
        globalPosition.rotate(parentGlobalPosition, parentGlobalRotation.getRotationInDegreesCC());
        
        updateChilds();
    }

    protected abstract void updateChilds();

    public abstract BalancePoint getGlobalBalancePoint();

    void setParent(IParentSimObject parent) {
        this.parent = parent;
        update();
    }

    public Vector2 getGlobalPosition() {
        return new Vector2(globalPosition);
    }

    public Weight getGlobalWeight() {
        return globalWeight;
    }

    public Rotation getGlobalRotation() {
        return new Rotation(globalRotation);
    }

    protected Vector2 getOffsetPosition() {
        return new Vector2(offsetPosition);
    }

    protected void setOffsetPosition(Vector2 offsetPosition) {
        if (offsetPosition == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        this.offsetPosition.set(offsetPosition);
        
        updateChilds();
    }

    protected Rotation getOffsetRotation() {
        return offsetRotation;
    }

    protected void setOffsetRotation(Rotation offsetRotation) {
        if (offsetRotation == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        this.offsetRotation.set(offsetRotation);
        
        updateChilds();
    }

    protected Weight getOffsetWeight() {
        return offsetWeight;
    }

    protected void setOffsetWeight(Weight offsetWeight) {
        if (offsetWeight == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        this.offsetWeight = offsetWeight;
    }

}
