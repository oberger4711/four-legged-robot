package com.oberger.kruppelbotsimulation.model;

import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Rotation;
import com.oberger.kruppelbotsimulation.util.Weight;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A composite {@link SimObject} with zero weight that can be rotated.
 *
 * @author ole
 */
public class SimJoint extends SimObject implements IParentSimObject {

    private List<SimObject> childs = null;

    SimJoint(IReadOnlyVector2 offsetPosition, Rotation offsetRotation, List<SimObject> childs) {
	super(offsetPosition, new Weight(), offsetRotation);
	this.childs = childs;
    }

    public SimJoint(IReadOnlyVector2 offsetPosition, Rotation offsetRotation) {
	this(offsetPosition, offsetRotation, new ArrayList<>());
    }

    @Override
    public BalancePoint getGlobalBalancePoint() {
	if (childs.isEmpty()) {
	    throw new IllegalStateException("No childs are added.");
	}
	List<BalancePoint> childBalancePoints = new ArrayList<>();
	for (SimObject child : childs) {
	    childBalancePoints.add(child.getGlobalBalancePoint());
	}

	return BalancePoint.mergeBalancePoints(childBalancePoints);
    }

    @Override
    protected void updateChilds() {
	for (SimObject child : childs) {
	    child.update();
	}
    }

    @Override
    public List<SimObject> getChilds() {
	return Collections.unmodifiableList(childs);
    }

    @Override
    public void accept(ISimObjectVisitor visitor) {
	visitor.visit(this);
    }
    
    public void addChild(SimObject child) {
	if (child == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}
	childs.add(child);
	child.setParentOrNull(this);
    }

    public void removeChild(SimObject child) {
	if (child == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}
	childs.remove(child);
	child.setParentOrNull(null);
    }

    @Override
    public void setOffsetPosition(IReadOnlyVector2 newOffsetPosition) {
	super.setOffsetPosition(newOffsetPosition);
    }

    @Override
    public void setOffsetRotation(Rotation newOffsetRotation) {
	super.setOffsetRotation(newOffsetRotation);
    }

}
