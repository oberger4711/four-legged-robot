package com.oberger.kruppelbotsimulation.mvc_model.domain;

import com.oberger.kruppelbotsimulation.mvc_model.function.*;

public class KruppelBotSimulation {

    private float totalElapsedTimeInS;
    private IPolyFunction functionBR;
    private IPolyFunction functionBL;
    private IPolyFunction functionFR;
    private IPolyFunction functionFL;

    /**
     *
     * @param legFunctions
     * @param model
     */
    public KruppelBotSimulation(ILegPolyFunctions legFunctions, KruppelBotModel model) {
        // TODO - implement KruppelBotSimulation.KruppelBotSimulation
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param totalElapsedTimeInS
     */
    public void simulate(float totalElapsedTimeInS) {
        // TODO - implement KruppelBotSimulation.simulate
        throw new UnsupportedOperationException();
    }

    public float getTotalElapsedTimeInS() {
        return this.totalElapsedTimeInS;
    }

}
