/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.model;

import com.oberger.kruppelbotsimulation.util.Rotation;
import com.oberger.kruppelbotsimulation.util.Vector2;

/**
 *
 * @author ole
 */
public interface IParentSimObject {

    public Vector2 getGlobalPosition();

    public Rotation getGlobalRotation();

}
