package com.oberger.kruppelbotsimulation.model;

import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Rotation;
import com.oberger.kruppelbotsimulation.util.Vector2;
import com.oberger.kruppelbotsimulation.util.Weight;
import java.io.Serializable;
import java.util.List;

/**
 * Abstract superclass of all simulation objects.
 *
 * @author ole
 */
public abstract class SimObject implements Serializable {

    private IParentSimObject parentOrNull = null;

    private Vector2 globalPosition = null;
    private Weight globalWeight = null;
    private Rotation globalRotation = null;
    private Vector2 offsetPosition = null;
    private Weight offsetWeight = null;
    private Rotation offsetRotation = null;

    protected SimObject(IReadOnlyVector2 offsetPosition, Weight offsetWeight, Rotation offsetRotation) {
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

	if (parentOrNull != null) {
	    parentGlobalPosition = parentOrNull.getGlobalPosition();
	    parentGlobalRotation = parentOrNull.getGlobalRotation();
	} else {
	    parentGlobalPosition = new Vector2();
	    parentGlobalRotation = new Rotation(0, true);
	}

	globalRotation = new Rotation(parentGlobalRotation)
		.add(offsetRotation);
	
	globalPosition = new Vector2(parentGlobalPosition)
		.add(offsetPosition)
		.rotate(parentGlobalPosition, parentGlobalRotation.getRotationInDegreesCC());

	updateChilds();
    }

    protected abstract void updateChilds();
    
    public abstract List<SimObject> getChilds();
    
    public abstract void accept(ISimObjectVisitor visitor);

    public abstract BalancePoint getGlobalBalancePoint();

    void setParentOrNull(IParentSimObject parent) {
	this.parentOrNull = parent;
	update();
    }

    public IParentSimObject getParentOrNull() {
	return parentOrNull;
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

    protected void setOffsetPosition(IReadOnlyVector2 offsetPosition) {
	if (offsetPosition == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}
	this.offsetPosition.set(offsetPosition);

	update();
    }

    protected Rotation getOffsetRotation() {
	return new Rotation(offsetRotation);
    }

    protected void setOffsetRotation(Rotation offsetRotation) {
	if (offsetRotation == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}
	this.offsetRotation.set(offsetRotation);

	update();
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
