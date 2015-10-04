/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.walk;

import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.ISimulationState;
import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.LegPosition;
import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.Simulation;
import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.SimulationEvaluator;
import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.SimulationEvaluatorParameters;
import com.oberger.kruppelbotsimulation.localsearch.evaluator.IEvaluator;
import com.oberger.kruppelbotsimulation.localsearch.evaluator.WeightedEvaluator;
import com.oberger.kruppelbotsimulation.localsearch.evaluator.WeightedEvaluatorGroup;
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
	return new WalkStateEvaluatorAdapter(createSimulationEvaluator());
    }

    private IEvaluator<Simulation> createSimulationEvaluator() {

	return new SimulationEvaluator(createSimulationEvaluatorParameters(), createSimulationStateEvaluator());
    }

    private SimulationEvaluatorParameters createSimulationEvaluatorParameters() {
	return new SimulationEvaluatorParameters(initSettings.periodInS, SAMPLES_PER_PERIOD);
    }

    private IEvaluator<ISimulationState> createSimulationStateEvaluator() {
	List<WeightedEvaluator<ISimulationState>> evaluators = new ArrayList<>();
	evaluators.add(new WeightedEvaluator<>(new SimulationStateBalancePointEvaluator(LegPosition.BR), 1));
	evaluators.add(new WeightedEvaluator<>(new SimulationStateBalancePointEvaluator(LegPosition.BL), 1));
	evaluators.add(new WeightedEvaluator<>(new SimulationStateBalancePointEvaluator(LegPosition.FR), 1));
	evaluators.add(new WeightedEvaluator<>(new SimulationStateBalancePointEvaluator(LegPosition.FL), 1));

	return new WeightedEvaluatorGroup<>(evaluators);
    }

}
