package com.oberger.kruppelbotsimulation.mvc_model.domain;

import com.oberger.kruppelbotsimulation.util.Rotation;

public class Simulation implements ISimulationState {

    private float totalElapsedTimeInS;
    private ILegPolyFunctions legFunctions = null;
    private Model model = null;

    public Simulation(ILegPolyFunctions legFunctions, Model model) {
        if (legFunctions == null || model == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        this.legFunctions = legFunctions;
        this.model = model;
    }

    public void simulate(float totalElapsedTimeInS) {
        this.totalElapsedTimeInS = totalElapsedTimeInS;
        
        Rotation rotationBL = new Rotation(legFunctions.getLegFunctionBL().getValue(totalElapsedTimeInS), true);
        model.getServoBL().setOffsetRotation(rotationBL);
        
        Rotation rotationBR = new Rotation(legFunctions.getLegFunctionBR().getValue(totalElapsedTimeInS), true);
        model.getServoBR().setOffsetRotation(rotationBR);
        
        Rotation rotationFL = new Rotation(legFunctions.getLegFunctionFL().getValue(totalElapsedTimeInS), true);
        model.getServoFL().setOffsetRotation(rotationFL);
        
        Rotation rotationFR = new Rotation(legFunctions.getLegFunctionFR().getValue(totalElapsedTimeInS), true);
        model.getServoFR().setOffsetRotation(rotationFR);
    }

    @Override
    public float getTotalElapsedTimeInS() {
        return this.totalElapsedTimeInS;
    }

    @Override
    public Model getModel() {
        return model;
    }
    
}
