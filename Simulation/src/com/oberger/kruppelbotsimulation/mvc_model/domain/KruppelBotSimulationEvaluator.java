package com.oberger.kruppelbotsimulation.mvc_model.domain;

import com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator.*;
import java.util.List;

public class KruppelBotSimulationEvaluator implements IEvaluator<KruppelBotSimulation> {

    private KruppelBotSimulationEvaluatorParameters simulationParameters = null;
    private WeightedEvaluatorGroup<IKruppelBotSimulationState> simulationEvaluators = null;
    
    public KruppelBotSimulationEvaluator(KruppelBotSimulationEvaluatorParameters simulationParameters, WeightedEvaluatorGroup<IKruppelBotSimulationState> simulationStateEvaluators) {
        if (simulationParameters == null || simulationStateEvaluators == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        this.simulationParameters = simulationParameters;
        this.simulationEvaluators = simulationStateEvaluators;
    }

    @Override
    public float getScore(KruppelBotSimulation simulation) {
        if (simulation == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        float scoreSum = 0;
        List<Float> sampleTimesInS = simulationParameters.getSampleTimesInS();
        for (Float sampleTimeInS : sampleTimesInS) {
            simulation.simulate(sampleTimeInS);
            scoreSum += simulationEvaluators.getScore(simulation);
        }
        float scoreAverage = scoreSum / sampleTimesInS.size();
        
        return scoreAverage;
    }
    
}
