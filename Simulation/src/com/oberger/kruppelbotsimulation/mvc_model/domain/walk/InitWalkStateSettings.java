/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.LegOrder;
import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.LegPosition;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author ole
 */
public class InitWalkStateSettings {
     // TODO constructor injection and creating from config file http://www.mkyong.com/java/java-properties-file-examples/
    public final float periodInS;
    public final float repositionTimeInS;
    public final float angleStandYInDegrees;
    public final float stepSizeYInDegrees;
    public final int numberOfPolygonsForward;
    public final int numberOfPolygonsBackward;
    public final LegOrder legOrder;

    public InitWalkStateSettings(float periodInS, float repositionTimeInS, float angleStandYInDegrees, float stepSizeYInDegrees, int numberOfPolygonsForward, int numberOfPolygonsBackward, LegOrder legOrder) {
        if (legOrder == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        this.periodInS = periodInS;
        this.repositionTimeInS = repositionTimeInS;
        this.angleStandYInDegrees = angleStandYInDegrees;
        this.stepSizeYInDegrees = stepSizeYInDegrees;
        this.numberOfPolygonsForward = numberOfPolygonsForward;
        this.numberOfPolygonsBackward = numberOfPolygonsBackward;
        this.legOrder = legOrder;
    }
    
    public int getNumberOfPolygonsTotal() {
        return numberOfPolygonsBackward + numberOfPolygonsForward;
    }
    
}
