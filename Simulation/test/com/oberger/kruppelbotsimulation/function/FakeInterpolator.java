/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.function;

import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;

/**
 *
 * @author ole
 */
public class FakeInterpolator extends Interpolator {

    private float interpolateValue;

    public FakeInterpolator(float interpolateValue) {
	this.interpolateValue = interpolateValue;
    }

    @Override
    protected float interpolateY(IReadOnlyVector2 polygon1, IReadOnlyVector2 polygon2, float x) {
	return interpolateValue;
    }

    public void setInterpolateValue(float returnValue) {
	this.interpolateValue = returnValue;
    }

}
