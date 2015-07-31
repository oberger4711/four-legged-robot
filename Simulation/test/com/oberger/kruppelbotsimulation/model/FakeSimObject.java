/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.model;

import com.oberger.kruppelbotsimulation.util.Rotation;
import com.oberger.kruppelbotsimulation.util.Vector2;
import com.oberger.kruppelbotsimulation.util.Weight;

public class FakeSimObject extends SimObject {

    private boolean updateCalled = false;
    private boolean updateChildsCalled = false;

    public FakeSimObject(Vector2 offsetPosition, Weight offsetWeight, Rotation offsetRotation) {
	super(offsetPosition, offsetWeight, offsetRotation);
	updateCalled = false;
	updateChildsCalled = false;
    }

    @Override
    public void update() {
	super.update();
	updateCalled = true;
    }

    @Override
    protected void updateChilds() {
	updateChildsCalled = true;
    }

    @Override
    public BalancePoint getGlobalBalancePoint() {
	return new BalancePoint(getGlobalPosition(), getGlobalWeight());
    }

    @Override
    public Vector2 getOffsetPosition() {
	return super.getOffsetPosition();
    }

    @Override
    protected Weight getOffsetWeight() {
	return super.getOffsetWeight();
    }

    @Override
    protected Rotation getOffsetRotation() {
	return super.getOffsetRotation();
    }

    public boolean isUpdateCalled() {
	return updateCalled;
    }

    public boolean isUpdateChildsCalled() {
	return updateChildsCalled;
    }

}
