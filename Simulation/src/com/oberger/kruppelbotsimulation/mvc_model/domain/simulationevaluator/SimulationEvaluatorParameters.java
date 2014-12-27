package com.oberger.kruppelbotsimulation.mvc_model.domain;

import java.util.ArrayList;
import java.util.List;

public class SimulationEvaluatorParameters {

    private final List<Float> sampleTimesInS;

    public SimulationEvaluatorParameters(List<Float> sampleTimesInS) {
        if (sampleTimesInS == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        if (sampleTimesInS.isEmpty()) {
            throw new IllegalArgumentException(new NullPointerException("List must not be empty."));
        }
        this.sampleTimesInS = new ArrayList<>(sampleTimesInS);
    }
    
    public SimulationEvaluatorParameters(float periodInS, int samplesPerPeriod) {
        if (periodInS <= 0) {
            throw new IllegalArgumentException("Simulation period must be higher than 0 but was " + periodInS);
        }
        if (samplesPerPeriod <= 0) {
            throw new IllegalArgumentException("Simulation samples per period must be higher than 0 but was " + samplesPerPeriod);
        }
        sampleTimesInS = new ArrayList<>(samplesPerPeriod);
        sampleTimesInS.add(0f);
        for (int i = 1; i <= samplesPerPeriod - 1; i++) {
            float sampleTimeInS = periodInS * ((float)i / (samplesPerPeriod - 1));
            sampleTimesInS.add(sampleTimeInS);
        }
    }
    
    public List<Float> getSampleTimesInS() {
        return sampleTimesInS;
    }
    
}
