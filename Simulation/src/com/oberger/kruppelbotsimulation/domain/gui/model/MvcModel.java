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
    private int tMaxInMs;
    private int tInMs;
    private int scaleFactor;
    
    public MvcModel(Simulation simulationOrNull) {
	this.simulationOrNull = simulationOrNull;
	if (simulationOrNull != null) {
	    tMaxInMs = (int)(simulationOrNull.getLegFunctions().getLegFunctionBL().getPeriod() * 1000);
	}
	tInMs = 0;
	scaleFactor = 70;
    }

    public Simulation getSimulationOrNull() {
	return simulationOrNull;
    }

    public void setSimulationOrNull(Simulation simulationOrNull) {
	this.simulationOrNull = simulationOrNull;
	if (simulationOrNull != null) {
	    tMaxInMs = (int)(simulationOrNull.getLegFunctions().getLegFunctionBL().getPeriod() * 1000);
	}
	tInMs = 0;
	setChanged();
	notifyObservers();
    }
    
    public int getTInMs() {
	return tInMs;
    }

    public void setTInMs(int t) {
	this.tInMs = t;
	if (simulationOrNull != null) {
	    simulationOrNull.simulate(t / 1000f);
	}
	setChanged();
	notifyObservers();
    }
    
    public int getTMax() {
	return tMaxInMs;
    }

    public void settMax(int tMax) {
	this.tMaxInMs = tMax;
	setChanged();
	notifyObservers();
    }
    
    public int getScaleFactor() {
	return scaleFactor;
    }

    public void setScaleFactor(int scaleFactor) {
	this.scaleFactor = scaleFactor;
	setChanged();
	notifyObservers();
    }
    
}
