/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.model;

import com.oberger.kruppelbotsimulation.util.Rotation;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.io.Serializable;

/**
 *
 * @author ole
 */
public interface IParentSimObject extends Serializable {
    // TODO Remove this class. Use SimObject instead.

    public Vector2 getGlobalPosition();

    public Rotation getGlobalRotation();

}
