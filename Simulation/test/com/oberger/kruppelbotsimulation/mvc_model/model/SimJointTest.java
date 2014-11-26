/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.model;

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

    private static FakeSimObject createFakeSimObject(Vector2 offsetPosition, Weight offsetWeight, Rotation offsetRotation) {
        return new FakeSimObject(offsetPosition, offsetWeight, offsetRotation);
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
    public void getBalancePoint_WithSimMassChilds_MergesSimMassBalancePoints() {
        FakeSimObject fakeChild1 = createFakeSimObject(new Vector2(1, 1), new Weight(1f), new Rotation(0, true));
        FakeSimObject fakeChild2 = createFakeSimObject(new Vector2(2, 2), new Weight(2f), new Rotation(0, true));
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
        SimObject newChild = createFakeSimObject(new Vector2(), new Weight(1), new Rotation(0f, true));

        simJoint.addChild(newChild);

        assertTrue(childs.contains(newChild));
    }

    @Test
    public void addChild_OnCall_UpdatesAddedChild() {
        SimJoint simJoint = createSimJoint(new Vector2(1, 1), new Rotation(1, true));
        FakeSimObject newChild = createFakeSimObject(new Vector2(), new Weight(1), new Rotation(0f, true));

        assertFalse(newChild.isUpdateCalled());

        simJoint.addChild(newChild);

        assertTrue(newChild.isUpdateCalled());
    }

    @Test
    public void removeChild_OnPassNull_ThrowsIllegalArgumentException() {
        SimJoint simJoint = createSimJoint(new Vector2(), new Rotation(0, true));

        exception.expect(IllegalArgumentException.class);

        simJoint.removeChild(null);
    }

    @Test
    public void removeChild_OnCall_RemovesChildFromList() {
        SimObject child = createFakeSimObject(new Vector2(), new Weight(1), new Rotation(0f, true));
        List<SimObject> childs = createSimObjectList(child);
        SimJoint simJoint = createSimJoint(new Vector2(), new Rotation(0, true), childs);

        simJoint.removeChild(child);

        assertFalse(childs.contains(child));
    }

    @Test
    public void removeChild_OnCall_UpdatesRemovedChild() {
        FakeSimObject child = createFakeSimObject(new Vector2(), new Weight(1), new Rotation(0f, true));
        List<SimObject> childs = createSimObjectList(child);
        SimJoint simJoint = createSimJoint(new Vector2(), new Rotation(0, true), childs);

        assertFalse(child.isUpdateCalled());

        simJoint.removeChild(child);

        assertTrue(child.isUpdateCalled());
    }

    @Test
    public void update_OnCall_CallsUpdateOnEveryChild() {
        FakeSimObject child1 = createFakeSimObject(new Vector2(), new Weight(1), new Rotation(0f, true));
        FakeSimObject child2 = createFakeSimObject(new Vector2(), new Weight(1), new Rotation(0f, true));

        List<SimObject> childs = createSimObjectList(child1, child2);
        SimJoint simJoint = createSimJoint(new Vector2(), new Rotation(0, true), childs);

        assertFalse(child1.isUpdateCalled());
        assertFalse(child2.isUpdateCalled());

        simJoint.update();

        assertTrue(child1.isUpdateCalled());
        assertTrue(child2.isUpdateCalled());
    }
}
