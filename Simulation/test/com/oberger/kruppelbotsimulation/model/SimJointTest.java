/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.model;

import com.oberger.kruppelbotsimulation.util.Rotation;
import com.oberger.kruppelbotsimulation.util.Vector2;
import com.oberger.kruppelbotsimulation.util.Weight;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 *
 * @author ole
 */
public class SimJointTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    public static SimJoint createSimJoint(Vector2 offsetPosition, Rotation offsetRotation) {
	return new SimJoint(offsetPosition, offsetRotation);
    }

    public static SimJoint createSimJoint(Vector2 offsetPosition, Rotation offsetRotation, List<SimObject> simObjects) {
	return new SimJoint(offsetPosition, offsetRotation, simObjects);
    }

    private static SimObject createFakeSimObject() {
	SimObject fakeSimObject = mock(SimObject.class);

	return fakeSimObject;
    }

    private static SimObject createFakeSimObject(BalancePoint balancePoint) {
	SimObject fakeSimObject = createFakeSimObject();
	doReturn(balancePoint).when(fakeSimObject).getGlobalBalancePoint();

	return fakeSimObject;
    }

    private static List<SimObject> createSimObjectList(SimObject... objects) {
	return new ArrayList(Arrays.asList(objects));
    }

    @Test
    public void constructor_OnCall_SetsWeightZero() {
	SimJoint simJoint = createSimJoint(new Vector2(), new Rotation(0, true));

	assertTrue(simJoint.getOffsetWeight().isZero());
    }

    @Test
    public void getBalancePoint_WithoutSimMassChilds_ThrowsIllegalStateException() {
	SimJoint simJoint = createSimJoint(new Vector2(), new Rotation(0, true));

	exception.expect(IllegalStateException.class);

	simJoint.getGlobalBalancePoint();
    }

    @Test
    public void getBalancePoint_WithSimMassChilds_MergesSimMassBalancePoints() {
	SimObject fakeChild1 = createFakeSimObject(new BalancePoint(new Vector2(1, 1), new Weight(1)));
	SimObject fakeChild2 = createFakeSimObject(new BalancePoint(new Vector2(2, 2), new Weight(2)));
	SimJoint simJoint = createSimJoint(new Vector2(), new Rotation(0, true), createSimObjectList(fakeChild1, fakeChild2));

	BalancePoint simJointBalancePoint = simJoint.getGlobalBalancePoint();

	assertEquals(new Vector2(1.66666667f, 1.66666667f), simJointBalancePoint.getPosition());
	assertEquals(new Weight(3), simJointBalancePoint.getWeight());
    }

    @Test
    public void addChild_OnPassNull_ThrowsIllegalArgumentException() {
	SimJoint simJoint = createSimJoint(new Vector2(), new Rotation(0, true));

	exception.expect(IllegalArgumentException.class);

	simJoint.addChild(null);
    }

    @Test
    public void addChild_OnCall_AddsChildToList() {
	List<SimObject> childs = new ArrayList<>();
	SimJoint simJoint = createSimJoint(new Vector2(), new Rotation(0, true), childs);
	SimObject fakeChild = createFakeSimObject();

	simJoint.addChild(fakeChild);

	assertTrue(childs.contains(fakeChild));
    }

    @Test
    public void addChild_OnCall_SetsChildsParent() {
	List<SimObject> childs = new ArrayList<>();
	SimJoint simJoint = createSimJoint(new Vector2(), new Rotation(0, true), childs);
	SimObject fakeChild = createFakeSimObject();

	simJoint.addChild(fakeChild);

	verify(fakeChild, times(1)).setParentOrNull(simJoint);
    }

    @Test
    public void removeChild_OnPassNull_ThrowsIllegalArgumentException() {
	SimJoint simJoint = createSimJoint(new Vector2(), new Rotation(0, true));

	exception.expect(IllegalArgumentException.class);

	simJoint.removeChild(null);
    }

    @Test
    public void removeChild_OnCall_RemovesChildFromList() {
	SimObject fakeChild = createFakeSimObject();
	List<SimObject> childs = createSimObjectList(fakeChild);
	SimJoint simJoint = createSimJoint(new Vector2(), new Rotation(0, true), childs);

	simJoint.removeChild(fakeChild);

	assertFalse(childs.contains(fakeChild));
    }

    @Test
    public void removeChild_OnCall_SetsChildsParentNull() {
	SimObject fakeChild = createFakeSimObject();
	List<SimObject> childs = createSimObjectList(fakeChild);
	SimJoint simJoint = createSimJoint(new Vector2(), new Rotation(0, true), childs);

	simJoint.removeChild(fakeChild);

	Mockito.verify(fakeChild, times(1)).setParentOrNull(null);
    }

    @Test
    public void update_OnCall_CallsUpdateOnEveryChild() {
	SimObject fakeChild1 = createFakeSimObject();
	SimObject fakeChild2 = createFakeSimObject();

	List<SimObject> childs = createSimObjectList(fakeChild1, fakeChild2);
	SimJoint simJoint = createSimJoint(new Vector2(), new Rotation(0, true), childs);

	verify(fakeChild1, never()).update();
	verify(fakeChild2, never()).update();

	simJoint.update();

	verify(fakeChild1, times(1)).update();
	verify(fakeChild2, times(1)).update();
    }
}
