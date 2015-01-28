/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ole
 */
public class LegOrder {

    public static enum LegPosition {
        BR,
        BL,
        FR,
        FL
    }

    private List<LegPosition> order = null;

    public LegOrder(List<LegPosition> order) {
        if (order == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        if (!containsEachPositionOnce(order)) {
            throw new IllegalArgumentException("List must contain all 4 different LegPosition but was " + order.toString());
        }
        this.order = order;
    }

    public LegOrder(LegPosition first, LegPosition second, LegPosition third, LegPosition fourth) {
        this(Arrays.asList(first, second, third, fourth));
    }

    public List<LegPosition> getOrder() {
        return Collections.unmodifiableList(order);
    }

    private boolean containsEachPositionOnce(List<LegPosition> order) {
        boolean eachPositionOnce = true;

        eachPositionOnce &= order.size() == LegPosition.values().length;

        for (LegPosition position : LegPosition.values()) {
            eachPositionOnce &= order.contains(position);
        }

        return eachPositionOnce;
    }

}
