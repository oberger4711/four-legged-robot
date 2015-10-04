/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.manipulators;

import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.PartialPolyFunction;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.util.Arrays;
import java.util.Collections;
import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author oberger
 */
public class PartialPolyFunctionGradientConstraintTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    public PartialPolyFunctionGradientConstraint createTestee(float maxAbsGradient) {
	return new PartialPolyFunctionGradientConstraint(maxAbsGradient);
    }
    
    @Test
    public void constructor_OnPassNegativeAbsGradient_ThrowsException() {
	exception.expect(IllegalArgumentException.class);
	
	createTestee(-1);
    }
    
    @Test
    public void check_OnPassFunctionWithTwoPolygonsWithoutGradient_ReturnsTrue() {
	PartialPolyFunctionGradientConstraint testee = createTestee(1);
	
	boolean passed = testee.check(new PartialPolyFunction(new Vector2(0, 0), Collections.EMPTY_LIST, new Vector2(1, 0)));
	
	Assert.assertTrue(passed);
    }
    
    @Test
    public void check_OnPassFunctionWithThreePolygonsWithoutGradient_ReturnsTrue() {
	PartialPolyFunctionGradientConstraint testee = createTestee(1);
	
	boolean passed = testee.check(new PartialPolyFunction(new Vector2(0, 0), Arrays.asList(new Vector2(1, 0)), new Vector2(2, 0)));
	
	Assert.assertTrue(passed);
    }
    
    @Test
    public void check_OnPassFunctionWithTwoPolygonsAndGradientHigherThanAllowed_ReturnsFalse() {
	PartialPolyFunctionGradientConstraint testee = createTestee(1);
	
	boolean passed = testee.check(new PartialPolyFunction(new Vector2(0, 0), Collections.EMPTY_LIST, new Vector2(1, 2)));
	
	Assert.assertFalse(passed);
    }
    
    @Test
    public void check_OnPassFunctionWithTwoPolygonsAndNegativeGradientHigherThanAllowed_ReturnsFalse() {
	PartialPolyFunctionGradientConstraint testee = createTestee(1);
	
	boolean passed = testee.check(new PartialPolyFunction(new Vector2(0, 0), Collections.EMPTY_LIST, new Vector2(1, -2)));
	
	Assert.assertFalse(passed);
    }
    
    @Test
    public void check_OnPassFunctionWithThreePolygonsAndGradientHigherThanAllowedBetweenFirstTwo_ReturnsFalse() {
	PartialPolyFunctionGradientConstraint testee = createTestee(1);
	
	boolean passed = testee.check(new PartialPolyFunction(new Vector2(0, 0), Arrays.asList(new Vector2(1, 2)), new Vector2(2, 2)));
	
	Assert.assertFalse(passed);
    }
    
    @Test
    public void check_OnPassFunctionWithThreePolygonsAndGradientHigherThanAllowedBetweenLastTwo_ReturnsFalse() {
	PartialPolyFunctionGradientConstraint testee = createTestee(1);
	
	boolean passed = testee.check(new PartialPolyFunction(new Vector2(0, 0), Arrays.asList(new Vector2(1, 0)), new Vector2(2, 2)));
	
	Assert.assertFalse(passed);
    }
    
}
