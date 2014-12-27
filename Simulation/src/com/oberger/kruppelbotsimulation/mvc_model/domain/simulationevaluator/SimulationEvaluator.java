package com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator;

import com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator.*;
import java.util.List;

public class SimulationEvaluator implements IEvaluator<Simulation> {

    private SimulationEvaluatorParameters simulationParameters = null;
    private IEvaluator<ISimulationState> simulationEvaluator = null;
    
    public SimulationEvaluator(SimulationEvaluatorParameters simulationParameters, IEvaluator<ISimulationState> simulationStateEvaluators) {
        if (simulationParameters == null || simulationStateEvaluators == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        this.simulationParameters = simulationParameters;
        this.simulationEvaluator = simulationStateEvaluators;
    }

    @Override
    public float getScore(Simulation simulation) {
        if (simulation == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        float scoreSum = 0;
        List<Float> sampleTimesInS = simulationParameters.getSampleTimesInS();
        for (Float sampleTimeInS : sampleTimesInS) {
            simulation.simulate(sampleTimeInS);
            scoreSum += simulationEvaluator.getScore(simulation);
        }
        float scoreAverage = scoreSum / sampleTimesInS.size();
        
        return scoreAverage;
    }
    
}
