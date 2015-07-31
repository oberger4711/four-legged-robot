/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author ole
 */
public class WeightTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private static Weight createWeight(float weight) {
	return new Weight(weight);
    }

    private static Weight createWeight() {
	return new Weight();
    }

    private static List<Weight> createWeightList(Weight... weights) {
	return Arrays.asList(weights);
    }

    @Test
    public void constructor_OnPassNoWeight_InitializesAttributes() {
	Weight zeroWeight = createWeight();

	assertEquals(0, zeroWeight.getValue(), 0.0001f);
	assertTrue(zeroWeight.isZero());
    }

    @Test
    public void constructor_OnPassWeight_InitializesAttributes() {
	Weight zeroWeight = createWeight(23);

	assertEquals(23, zeroWeight.getValue(), 0.001f);
	assertFalse(zeroWeight.isZero());
    }

    @Test
    public void constructor_OnPassWeightZero_ThrowsIllegalArgumentException() {
	exception.expect(IllegalArgumentException.class);

	createWeight(0);
    }

    @Test
    public void constructorAdd_OnPassNull_ThrowsIllegalArgumentsException() {
	exception.expect(IllegalArgumentException.class);

	new Weight(null);
    }

    @Test
    public void constructorAdd_OnPassEmptyList_ThrowsIllegalArgumentsException() {
	exception.expect(IllegalArgumentException.class);

	new Weight(Collections.<Weight>emptyList());
    }

    @Test
    public void constructorAdd_OnPassOtherWeightNoZero_AddsComponents() {
	Weight firstSummand = createWeight(1);
	Weight secondSummand = createWeight(2);

	Weight result = new Weight(createWeightList(firstSummand, secondSummand));

	assertEquals(3, result.getValue(), 0.001f);
    }

    @Test
    public void constructorAdd_OnPassOtherWeightFirstZero_ResultNotZero() {
	Weight firstSummand = createWeight();
	Weight secondSummand = createWeight(2);

	Weight result = new Weight(createWeightList(firstSummand, secondSummand));

	assertFalse(result.isZero());
	assertEquals(2, result.getValue(), 0.001f);
    }

    @Test
    public void constructorAdd_OnPassOtherWeight_BothZero_ResultIsZero() {
	Weight firstSummand = createWeight();
	Weight secondSummand = createWeight();

	Weight result = new Weight(createWeightList(firstSummand, secondSummand));

	assertTrue(result.isZero());
	assertEquals(0, result.getValue(), 0.001f);
    }

}
