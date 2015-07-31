package com.oberger.kruppelbotsimulation.model;

import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Vector2;
import com.oberger.kruppelbotsimulation.util.Weight;
import java.util.ArrayList;
import java.util.List;

public class BalancePoint {

    private Vector2 position;
    private Weight weight;

    public BalancePoint(IReadOnlyVector2 position, Weight weight) {
	if (position == null || weight == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}

	this.position = new Vector2(position);
	this.weight = weight;
    }

    public static BalancePoint mergeBalancePoints(List<BalancePoint> balancePoints) {
	if (balancePoints == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}
	if (balancePoints.isEmpty()) {
	    throw new IllegalArgumentException("Passing an empty list is not allowed.");
	}

	List<Weight> weights = new ArrayList<>();
	Vector2 weightedPositionMean = new Vector2();
	Vector2 averagePosition = new Vector2();
	for (BalancePoint balancePoint : balancePoints) {
	    weights.add(balancePoint.getWeight());
	    weightedPositionMean.add(balancePoint.getPosition().scale(balancePoint.getWeight().getValue()));
	    averagePosition.add(balancePoint.getPosition());
	}
	Weight weightSum = new Weight(weights);
	weightedPositionMean.scale(1f / weightSum.getValue());
	averagePosition.scale(1f / balancePoints.size());

	Vector2 mergedPosition = null;
	if (!weightSum.isZero()) {
	    mergedPosition = weightedPositionMean;
	} else {
	    mergedPosition = averagePosition;
	}
	BalancePoint result = new BalancePoint(mergedPosition, weightSum);

	return result;
    }

    public Vector2 getPosition() {
	return new Vector2(position);
    }

    public Weight getWeight() {
	return weight;
    }

}
