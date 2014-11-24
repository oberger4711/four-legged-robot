package com.oberger.kruppelbotsimulation.mvc_model.model;

import java.util.*;
import com.oberger.kruppelbotsimulation.util.*;

public class SimJoint extends SimObject implements IParentSimObject {

    private List<SimObject> childs;

    public SimJoint(Vector2 offsetPosition, Rotation offsetRotation) {
        super(offsetPosition, new Weight(), offsetRotation);
        // TODO - implement SimJoint.SimJoint
        throw new UnsupportedOperationException();
    }

    @Override
    public BalancePoint getGlobalBalancePoint() {
        // TODO - implement SimJoint.getGlobalBalancePoint
        throw new UnsupportedOperationException();
    }

    @Override
    protected void updateChilds() {
        // TODO - implement SimJoint.onUpdated
        throw new UnsupportedOperationException();
    }
    
    public void addChild(SimObject child) {
        
    }
    
    public void removeChild(SimObject child) {
        
    }

    public void setOffsetPosition(Vector2 newOffsetPosition) {
        // TODO - implement SimJoint.setOffsetPosition
        throw new UnsupportedOperationException();
    }

    public void setOffsetRotation(Rotation newOffsetRotation) {
        // TODO - implement SimJoint.setOffsetRotation
        throw new UnsupportedOperationException();
    }

}
