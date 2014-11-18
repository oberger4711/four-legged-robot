package com.oberger.kruppelbotsimulation.mvc_model.model;

import com.oberger.kruppelbotsimulation.util.*;
import java.util.List;

public class BalancePoint {
    
    private final static float ZERO_WEIGHT_THRESHOLD = 0.00001f;

    private Vector2 globalPosition;
    private Vector2 offsetPosition;
    private float globalRotation;
    private float offsetRotation;
    private float weight;
    private boolean counterClockwise;

    public BalancePoint(Vector2 offsetPosition, float offsetRotation, boolean counterClockwise, float weight) {
        if (offsetPosition == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        if (weight < ZERO_WEIGHT_THRESHOLD) {
            throw new IllegalArgumentException("Weight must be higher than " + ZERO_WEIGHT_THRESHOLD);
        }
        this.offsetPosition = new Vector2(offsetPosition);
        this.offsetRotation = offsetRotation;
        this.counterClockwise = counterClockwise;
        this.weight = weight;
        
        this.globalPosition = new Vector2(offsetPosition);
        this.globalRotation = offsetRotation;
    }

    public static BalancePoint mergeBalancePoints(List<BalancePoint> balancePoints) {
        if (balancePoints == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        if (balancePoints.isEmpty()) {
            throw new IllegalArgumentException("Passing an empty list is not allowed.");
        }
        
        Vector2 weightedOffsetPositionMean = new Vector2();
        float weightSum = 0;
        for (BalancePoint balancePoint : balancePoints) {
            weightSum += balancePoint.getWeight();
            weightedOffsetPositionMean.add(balancePoint.getOffsetPosition().scale(balancePoint.getWeight()));
        }
        weightedOffsetPositionMean.scale(1f / weightSum);
        // TODO: counterclockwise berücksichtigen, 
        BalancePoint result = new BalancePoint(weightedOffsetPositionMean, 0, true, weightSum);
        
        return result;
    }

    public void onBalancePointUpdate(BalancePoint parentBalancePoint) {
        // TODO - implement BalancePoint.onBalancePointUpdate
        throw new UnsupportedOperationException();
    }

    public Vector2 getGlobalPosition() {
        return new Vector2(this.globalPosition);
    }

    public Vector2 getOffsetPosition() {
        return new Vector2(this.offsetPosition);
    }

    public float getGlobalRotation() {
        return this.globalRotation;
    }

    public float getOffsetRotation() {
        return this.offsetRotation;
    }

    public float getWeight() {
        return this.weight;
    }

    public boolean isCounterClockwise() {
        return this.counterClockwise;
    }

}
