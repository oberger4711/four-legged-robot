/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator.ISimulationState;
import com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator.Simulation;
import com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator.SimulationEvaluator;
import com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator.SimulationEvaluatorParameters;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator.IEvaluator;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator.WeightedEvaluator;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator.WeightedEvaluatorGroup;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ole
 */
public class EvaluatorFactory {
    
    private final static int SAMPLES_PER_PERIOD = 40;
    
    private InitWalkStateSettings initSettings = null;
    
    public EvaluatorFactory(InitWalkStateSettings initSettings) {
        this.initSettings = initSettings;
    }
    
    public IEvaluator<WalkState> createEvaluator() {
        List<WeightedEvaluator<WalkState>> weightedEvaluators = new ArrayList<>();
        
        weightedEvaluators.add(new WeightedEvaluator<>(createRepositionBalancePointEvaluator(), 0.5f));
        
        WeightedEvaluatorGroup<WalkState> evaluator = new WeightedEvaluatorGroup<>(weightedEvaluators);
        
        return evaluator;
    }
    
    private IEvaluator<WalkState> createRepositionBalancePointEvaluator() {
        // TODO:
        return new WalkSimulationEvaluatorAdapter(createSimulationEvaluator());
    }
    
    private IEvaluator<Simulation> createSimulationEvaluator() {
        
        return new SimulationEvaluator(createSimulationEvaluatorParameters(), createSimulationStateEvaluator());
    }
    
    private SimulationEvaluatorParameters createSimulationEvaluatorParameters() {
        return new SimulationEvaluatorParameters(initSettings.periodInS, SAMPLES_PER_PERIOD);
    }
    
    private IEvaluator<ISimulationState> createSimulationStateEvaluator() {
        return null; // TODO
    }
    
}
