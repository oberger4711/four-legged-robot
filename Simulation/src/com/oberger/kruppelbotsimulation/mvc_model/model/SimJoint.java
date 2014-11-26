package com.oberger.kruppelbotsimulation.mvc_model.model;

import java.util.*;
import com.oberger.kruppelbotsimulation.util.*;

/**
 * A composite {@link SimObject} with zero weight that can be rotated.
 * @author ole
 */
public class SimJoint extends SimObject implements IParentSimObject {

    private List<SimObject> childs = null;

    SimJoint(Vector2 offsetPosition, Rotation offsetRotation, List<SimObject> childs) {
        super(offsetPosition, new Weight(), offsetRotation);
        this.childs = childs;
    }
    
    public SimJoint(Vector2 offsetPosition, Rotation offsetRotation) {
        this(offsetPosition, offsetRotation, new ArrayList<>());
    }

    @Override
    public BalancePoint getGlobalBalancePoint() {
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
    
    public void addChild(SimObject child) {
        if (child == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        childs.add(child);
        child.update();
    }
    
    public void removeChild(SimObject child) {
        if (child == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        childs.remove(child);
        child.update();
    }

    @Override
    public void setOffsetPosition(Vector2 newOffsetPosition) {
        super.setOffsetPosition(newOffsetPosition);
    }

    @Override
    public void setOffsetRotation(Rotation newOffsetRotation) {
        super.setOffsetRotation(newOffsetRotation);
    }

}
