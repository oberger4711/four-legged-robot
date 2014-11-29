/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.model;

import com.oberger.kruppelbotsimulation.util.Rotation;
import com.oberger.kruppelbotsimulation.util.Vector2;
import com.oberger.kruppelbotsimulation.util.Weight;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 *
 * @author ole
 */
public class SimObjectTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private static FakeSimObject createFakeSimObject(Vector2 offsetPosition, Weight offsetWeight, Rotation offsetRotation) {
        return new FakeSimObject(offsetPosition, offsetWeight, offsetRotation);
    }

    private static IParentSimObject createFakeParentSimObject(Vector2 globalPosition, Rotation globalRotation) {
        IParentSimObject fakeParentSimObject = Mockito.mock(IParentSimObject.class);
        Mockito.doReturn(globalPosition).when(fakeParentSimObject).getGlobalPosition();
        Mockito.doReturn(globalRotation).when(fakeParentSimObject).getGlobalRotation();
        
        return fakeParentSimObject;
    }

    @Test
    public void constructor_OnPassOffsetPositionNull_ThrowsIllegalArgumentsException() {
        exception.expect(IllegalArgumentException.class);

        createFakeSimObject(null, new Weight(), new Rotation(0, true));
    }

    @Test
    public void constructor_OnPassOffsetWeightNull_ThrowsIllegalArgumentsException() {
        exception.expect(IllegalArgumentException.class);

        createFakeSimObject(new Vector2(), null, new Rotation(0, true));
    }

    @Test
    public void constructor_OnPassOffsetRotationNull_ThrowsIllegalArgumentsException() {
        exception.expect(IllegalArgumentException.class);

        createFakeSimObject(new Vector2(), new Weight(), null);
    }

    @Test
    public void constructor_OnCall_SetsGlobalPositionToOffsetPositionCopy() {
        Vector2 offsetPosition = new Vector2(1, 2);
        FakeSimObject fakeSimObject = createFakeSimObject(offsetPosition, new Weight(12), new Rotation(45, true));

        assertTrue(new Vector2(1, 2).equals(fakeSimObject.getGlobalPosition()));
        assertFalse(offsetPosition == fakeSimObject.getGlobalPosition());
    }

    @Test
    public void constructor_OnCall_SetsGlobalWeightToOffsetWeight() {
        Weight offsetWeight = new Weight(12);
        FakeSimObject fakeSimObject = createFakeSimObject(new Vector2(1, 2), offsetWeight, new Rotation(45, true));

        assertTrue(offsetWeight == fakeSimObject.getGlobalWeight());
    }

    @Test
    public void constructor_OnCall_SetsGlobalRotationToOffsetRotation() {
        Rotation offsetRotation = new Rotation(45, true);
        FakeSimObject fakeSimObject = createFakeSimObject(new Vector2(1, 2), new Weight(12), offsetRotation);

        assertTrue(offsetRotation.equals(fakeSimObject.getGlobalRotation()));
        assertFalse(offsetRotation == fakeSimObject.getGlobalRotation());
    }

    @Test
    public void update_OnHavingNoParent_UsesOffsetAsGlobal() {
        FakeSimObject fakeSimObject = createFakeSimObject(new Vector2(2, 3), new Weight(2), new Rotation(12, true));

        fakeSimObject.update();

        assertEquals(new Vector2(2, 3), fakeSimObject.getGlobalPosition());
        assertEquals(new Weight(2), fakeSimObject.getGlobalWeight());
        assertEquals(new Rotation(12, true), fakeSimObject.getGlobalRotation());
    }

    @Test
    public void update_OnCall_DoesNotModifyParentAttributes() {
        Rotation parentGlobalRotation = new Rotation(45, true);
        Vector2 parentGlobalPosition = new Vector2(1, 1);
        IParentSimObject parent = createFakeParentSimObject(parentGlobalPosition, parentGlobalRotation);
        FakeSimObject child = createFakeSimObject(new Vector2(4, 3), new Weight(23), new Rotation(30, true));
        child.setParent(parent);

        child.update();

        assertEquals(parentGlobalPosition, parent.getGlobalPosition());
        assertEquals(parentGlobalRotation, parent.getGlobalRotation());
    }

    @Test
    public void update_OnCall_SetsGlobalRotationAsSumOfGlobalParentPositionAndOffsetRotation() {
        IParentSimObject parent = createFakeParentSimObject(new Vector2(1, 2), new Rotation(45, true));
        FakeSimObject child = createFakeSimObject(new Vector2(1, 2), new Weight(), new Rotation(5, true));
        child.setParent(parent);

        child.update();

        assertEquals(50f, child.getGlobalRotation().getRotationInDegreesCC(), 0.0001);
    }

    @Test
    public void update_OnPassRotationZero_SetsGlobalPositionAsSumOfGlobalParentPositionAndOffsetPosition() {
        IParentSimObject parent = createFakeParentSimObject(new Vector2(1, 2), new Rotation(0, true));
        FakeSimObject child = createFakeSimObject(new Vector2(1, 1), new Weight(), new Rotation(0, true));
        child.setParent(parent);

        child.update();

        assertEquals(new Vector2(2, 3), child.getGlobalPosition());
    }

    @Test
    public void update_OnCall_SetsGlobalPositionAsRotatedSumOfGlobalParentPositionAndParentOffsetPosition() {
        IParentSimObject parent = createFakeParentSimObject(new Vector2(2, 2), new Rotation(90, true));
        FakeSimObject child = createFakeSimObject(new Vector2(1, 1), new Weight(), new Rotation(30, true));
        child.setParent(parent);

        child.update();

        assertEquals(new Vector2(1, 3), child.getGlobalPosition());
    }

    @Test
    public void update_OnCall_CallsUpdateChilds() {
        FakeSimObject simObject = createFakeSimObject(new Vector2(), new Weight(), new Rotation(0f, true));

        assertFalse(simObject.isUpdateChildsCalled());

        simObject.update();

        assertTrue(simObject.isUpdateChildsCalled());
    }

    @Test
    public void setOffsetPosition_OnCall_CallsUpdateChilds() {
        FakeSimObject simObject = createFakeSimObject(new Vector2(), new Weight(), new Rotation(0f, true));

        assertFalse(simObject.isUpdateChildsCalled());

        simObject.setOffsetPosition(new Vector2(2, 3));

        assertTrue(simObject.isUpdateChildsCalled());
    }

    @Test
    public void setOffsetRotation_OnCall_CallsUpdateChilds() {
        FakeSimObject simObject = createFakeSimObject(new Vector2(), new Weight(), new Rotation(0f, true));

        assertFalse(simObject.isUpdateChildsCalled());

        simObject.setOffsetRotation(new Rotation(2, true));

        assertTrue(simObject.isUpdateChildsCalled());
    }

    @Test
    public void setOffsetPosition_OnPassNull_ThrowsIllegalArgumentException() {
        FakeSimObject simObject = createFakeSimObject(new Vector2(), new Weight(), new Rotation(0f, true));

        exception.expect(IllegalArgumentException.class);

        simObject.setOffsetPosition(null);
    }

    @Test
    public void setOffsetRotation_OnPassNull_ThrowsIllegalArgumentException() {
        FakeSimObject simObject = createFakeSimObject(new Vector2(), new Weight(), new Rotation(0f, true));

        exception.expect(IllegalArgumentException.class);

        simObject.setOffsetRotation(null);
    }

    @Test
    public void setOffsetWeight_OnPassNull_ThrowsIllegalArgumentException() {
        FakeSimObject simObject = createFakeSimObject(new Vector2(), new Weight(), new Rotation(0f, true));

        exception.expect(IllegalArgumentException.class);

        simObject.setOffsetWeight(null);
    }

    @Test
    public void setParent_OnCall_CallsUpdate() {
        IParentSimObject parent = createFakeParentSimObject(new Vector2(2, 2), new Rotation(90, true));
        FakeSimObject simObject = createFakeSimObject(new Vector2(), new Weight(), new Rotation(0, true));

        simObject.setParent(parent);
        
        assertEquals(new Vector2(2, 2), simObject.getGlobalPosition());
        assertEquals(new Rotation(90, true), simObject.getGlobalRotation());
    }

}
