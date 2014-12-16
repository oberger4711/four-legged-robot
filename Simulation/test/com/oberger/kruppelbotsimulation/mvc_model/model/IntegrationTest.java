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
import org.junit.Test;

/**
 *
 * @author ole
 */
public class IntegrationTest {
    
    @Test
    public void simObjectHierarchie_OnChange_UpdatesRootBalancePoint() {
        SimJoint root = new SimJoint(new Vector2(), new Rotation(0f, true));
        SimObject childMass = new SimMass(new Vector2(1, 1), new Weight(1));
        root.addChild(childMass);
        
        assertEquals(new Vector2(1, 1), root.getGlobalBalancePoint().getPosition());
        assertEquals(1f, root.getGlobalBalancePoint().getWeight().getValue(), 0.0001f);
        
        SimJoint childJoint = new SimJoint(new Vector2(-1, -1), new Rotation(0, true));
        SimObject childJointChild = new SimMass(new Vector2(-1, -1), new Weight(1));
        childJoint.addChild(childJointChild);
        root.addChild(childJoint);
        
        assertEquals(new Vector2(-0.5f, -0.5f), root.getGlobalBalancePoint().getPosition());
        
        childJoint.setOffsetRotation(new Rotation(180, true));
        
        assertEquals(new Vector2(0, 0), childJoint.getGlobalBalancePoint().getPosition());
        assertEquals(new Vector2(0.5f, 0.5f), root.getGlobalBalancePoint().getPosition());
    }
    
}
