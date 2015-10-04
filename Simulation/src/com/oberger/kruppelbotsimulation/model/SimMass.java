package com.oberger.kruppelbotsimulation.model;

import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Rotation;
import com.oberger.kruppelbotsimulation.util.Weight;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SimMass extends SimObject {

    public SimMass(IReadOnlyVector2 offsetPosition, Weight offsetWeight) {
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
    public List<SimObject> getChilds() {
	return Collections.unmodifiableList(new LinkedList<>());
    }
    
    @Override
    public void accept(ISimObjectVisitor visitor) {
	visitor.visit(this);
    }
    
    @Override
    public void setOffsetPosition(IReadOnlyVector2 newOffsetPosition) {
	super.setOffsetPosition(newOffsetPosition);
    }

}
