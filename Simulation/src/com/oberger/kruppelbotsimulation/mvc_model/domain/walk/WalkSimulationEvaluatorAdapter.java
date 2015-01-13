/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator.Model;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator.IEvaluator;

/**
 *
 * @author ole
 */
public class WalkSimulationEvaluatorAdapter implements IEvaluator<WalkState>{
    
    private Model model = null;

    @Override
    public float getScore(WalkState innerState) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
