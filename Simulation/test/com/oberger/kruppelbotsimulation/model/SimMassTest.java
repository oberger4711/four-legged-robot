/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.model;

import com.oberger.kruppelbotsimulation.util.Rotation;
import com.oberger.kruppelbotsimulation.util.Vector2;
import com.oberger.kruppelbotsimulation.util.Weight;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 *
 * @author ole
 */
public class SimMassTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private static SimMass createSimMass(Vector2 offsetPosition, Weight offsetWeight) {
	return new SimMass(offsetPosition, offsetWeight);
    }

    private static IParentSimObject createFakeParentSimObject(Vector2 globalPosition, Rotation globalRotation) {
	IParentSimObject fakeParentSimObject = Mockito.mock(IParentSimObject.class);
	Mockito.doReturn(globalPosition).when(fakeParentSimObject).getGlobalPosition();
	Mockito.doReturn(globalRotation).when(fakeParentSimObject).getGlobalRotation();

	return fakeParentSimObject;
    }

    @Test
    public void constructor_OnCall_SetsOffsetRotationZero() {
	SimMass simMass = createSimMass(new Vector2(0, 0), new Weight());

	assertEquals(new Rotation(0, true), simMass.getOffsetRotation());
    }

    @Test
    public void getBalancePoint_OnCall_UsesGlobalPosition() {
	SimMass simMass = createSimMass(new Vector2(2, 4), new Weight(5));

	BalancePoint result = simMass.getGlobalBalancePoint();

	assertEquals(new Vector2(2, 4), result.getPosition());
    }

    @Test
    public void getBalancePoint_OnCall_UsesGlobalWeight() {
	SimMass simMass = createSimMass(new Vector2(2, 4), new Weight(5));

	BalancePoint result = simMass.getGlobalBalancePoint();

	assertEquals(new Weight(5), result.getWeight());
    }

    @Test
    public void getBalancePoint_AfterUpdate_UsesUpdatedGlobalPosition() {
	IParentSimObject parent = createFakeParentSimObject(new Vector2(1, 2), new Rotation(0, true));
	SimMass simMass = createSimMass(new Vector2(2, 4), new Weight(5));
	simMass.setParent(parent);

	BalancePoint result = simMass.getGlobalBalancePoint();

	assertEquals(new Vector2(3, 6), result.getPosition());
    }

}
