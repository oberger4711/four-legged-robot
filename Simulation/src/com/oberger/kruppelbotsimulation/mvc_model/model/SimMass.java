package com.oberger.kruppelbotsimulation.mvc_model.model;

import com.oberger.kruppelbotsimulation.util.*;

public class SimMass extends SimObject {

    public SimMass(Vector2 offsetPosition, Weight offsetWeight) {
        super(offsetPosition, offsetWeight, new Rotation(0f, true));
    }

    @Override
    public BalancePoint getGlobalBalancePoint() {
        return new BalancePoint(getGlobalPosition(), getGlobalWeight());
    }

    @Override
    protected void updateChilds() {
        
    }

    @Override
    public void setOffsetPosition(Vector2 newOffsetPosition) {
        super.setOffsetPosition(newOffsetPosition);
    }

}
