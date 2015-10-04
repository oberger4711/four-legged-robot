/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.gui.model;

import com.oberger.kruppelbotsimulation.domain.simulation.Simulation;
import java.util.Observable;

/**
 *
 * @author oberger
 */
public class MvcModel extends Observable {
    
    private Simulation simulationOrNull;
    private float tMax;
    private float t;
    private float scaleFactor;
    
    public MvcModel(Simulation simulationOrNull) {
	this.simulationOrNull = simulationOrNull;
	if (simulationOrNull != null) {
	    tMax = simulationOrNull.getLegFunctions().getLegFunctionBL().getPeriod();
	}
	t = 0;
	scaleFactor = 70;
    }

    public Simulation getSimulationOrNull() {
	return simulationOrNull;
    }

    public void setSimulationOrNull(Simulation simulationOrNull) {
	this.simulationOrNull = simulationOrNull;
	setChanged();
	notifyObservers();
    }
    
    public float getT() {
	return t;
    }

    public void setT(float t) {
	this.t = t;
	if (simulationOrNull != null) {
	    simulationOrNull.simulate(t / 1000);
	}
	setChanged();
	notifyObservers();
    }
    
    public float getTMax() {
	return tMax;
    }

    public void settMax(float tMax) {
	this.tMax = tMax;
	setChanged();
	notifyObservers();
    }
    
    public float getScaleFactor() {
	return scaleFactor;
    }

    public void setScaleFactor(float scaleFactor) {
	this.scaleFactor = scaleFactor;
	setChanged();
	notifyObservers();
    }
    
}
