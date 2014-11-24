/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.model;

import com.oberger.kruppelbotsimulation.util.Rotation;
import com.oberger.kruppelbotsimulation.util.Vector2;

public class FakeParentSimObject implements IParentSimObject {
    
    private Vector2 globalPosition = null;
    private Rotation globalRotation = null;
    
    public FakeParentSimObject(Vector2 parentGlobalPosition, Rotation parentGlobalRotation) {
        this.globalPosition = parentGlobalPosition;
        this.globalRotation = parentGlobalRotation;
    }

    @Override
    public Vector2 getGlobalPosition() {
        return globalPosition;
    }

    @Override
    public Rotation getGlobalRotation() {
        return globalRotation;
    }
    
}
