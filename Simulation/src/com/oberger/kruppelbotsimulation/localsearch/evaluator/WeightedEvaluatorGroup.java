package com.oberger.kruppelbotsimulation.localsearch.evaluator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jdk.nashorn.internal.ir.annotations.Immutable;

@Immutable
public class WeightedEvaluatorGroup<T> implements IEvaluator<T> {

    private List<WeightedEvaluator<T>> evaluators;

    public WeightedEvaluatorGroup(List<WeightedEvaluator<T>> evaluatorList) {
	if (evaluatorList == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}
	if (evaluatorList.isEmpty()) {
	    throw new IllegalArgumentException("Passing an empty list is not allowed.");
	}
	this.evaluators = new ArrayList(evaluatorList);
    }

    public List<WeightedEvaluator<T>> getEvaluators() {
	return Collections.unmodifiableList(evaluators);
    }

    @Override
    public float getScore(T innerState) {
	float weightedScore = 0;
	float weightSum = 0;

	for (WeightedEvaluator weightedEvaluator : evaluators) {
	    weightedScore += weightedEvaluator.getScore(innerState) * weightedEvaluator.getWeight();
	    weightSum += weightedEvaluator.getWeight();
	}
	weightedScore /= weightSum;

	return weightedScore;
    }

}
