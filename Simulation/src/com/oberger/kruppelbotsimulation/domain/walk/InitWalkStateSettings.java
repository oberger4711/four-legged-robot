/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.walk;

import com.oberger.kruppelbotsimulation.domain.simulation.LegOrder;

/**
 *
 * @author ole
 */
public class InitWalkStateSettings {

    public final float periodInS;
    public final float repositionTimeInS;
    public final float angleStandYInDegrees;
    public final float stepSizeYInDegrees;
    public final int numberOfPolygonsForward;
    public final int numberOfPolygonsBackward;
    public final LegOrder legOrder;
    public final float polygonManipulationStep;
    public final float polygonManipulationMaxGradient;

    public InitWalkStateSettings(float periodInS, float repositionTimeInS, float angleStandYInDegrees, float stepSizeYInDegrees, int numberOfPolygonsForward, int numberOfPolygonsBackward, LegOrder legOrder, float polygonManipulationStep, float polygonManipulationMaxGradient) {
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
	this.polygonManipulationStep = polygonManipulationStep;
	this.polygonManipulationMaxGradient = polygonManipulationMaxGradient;
    }

    public int getNumberOfPolygonsTotal() {
	return numberOfPolygonsBackward + numberOfPolygonsForward;
    }

}
