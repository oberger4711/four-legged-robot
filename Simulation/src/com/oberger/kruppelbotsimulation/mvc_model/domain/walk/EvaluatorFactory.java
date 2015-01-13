/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

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
    
    private InitWalkStateSettings settings = null;
    
    public EvaluatorFactory(InitWalkStateSettings settings) {
        this.settings = settings;
    }
    
    public IEvaluator<WalkState> createEvaluator() {
        List<WeightedEvaluator<WalkState>> weightedEvaluators = new ArrayList<>();
        
        weightedEvaluators.add(new WeightedEvaluator<>(createRepositionBalancePointEvaluator(), 0.5f));
        
        WeightedEvaluatorGroup<WalkState> evaluator = new WeightedEvaluatorGroup<>(weightedEvaluators);
        
        return evaluator;
    }
    
    private IEvaluator<WalkState> createRepositionBalancePointEvaluator() {
        return new WalkSimulationEvaluatorAdapter();
    }
    
}
