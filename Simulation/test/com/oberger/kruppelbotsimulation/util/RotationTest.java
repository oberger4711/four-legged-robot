/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.util;

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
public class RotationTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private static Rotation createRotation(float rotationInDegrees, boolean counterClockwise) {
        return new Rotation(rotationInDegrees, counterClockwise);
    }
    
    @Test
    public void add_OnPassNull_ThrowsIllegalArgumentException() {
        Rotation rotation = new Rotation(0, true);
        
        exception.expect(IllegalArgumentException.class);
        
        rotation.add(null);
    }
    
    @Test
    public void add_OnPassRotation_ReturnsFirstSummand() {
        Rotation firstSummand = new Rotation(1, true);
        Rotation secondSummand = new Rotation(2, true);
        
        Rotation sum = firstSummand.add(secondSummand);
        
        assertTrue(firstSummand == sum);
    }
    
    @Test
    public void add_OnPassRotation_AddsRotation() {
        Rotation firstSummand = new Rotation(1, true);
        Rotation secondSummand = new Rotation(2, true);
        
        Rotation sum = firstSummand.add(secondSummand);
        
        assertEquals(3, sum.getRotationInDegreesCC(), 0.0001f);
    }
    
    @Test
    public void add_OnSumExceedsFullRotation_MapsSumIntoUnderFullRotation() {
        Rotation firstSummand = new Rotation(300, true);
        Rotation secondSummand = new Rotation(100, true);
        
        Rotation sum = firstSummand.add(secondSummand);
        
        assertEquals(40, sum.getRotationInDegreesCC(), 0.0001f);
    }
    
    @Test
    public void subtract_OnPassNull_ThrowsIllegalArgumentException() {
        Rotation rotation = new Rotation(0, true);
        
        exception.expect(IllegalArgumentException.class);
        
        rotation.subtract(null);
    }
    
    @Test
    public void subtract_OnPassRotation_ReturnsMinuend() {
        Rotation minuend = new Rotation(100, true);
        Rotation subtrahend = new Rotation(40, true);
        
        Rotation difference = minuend.subtract(subtrahend);
        
        assertTrue(minuend == difference);
    }
    
    @Test
    public void subtract_OnPassRotation_SubtractsRotation() {
        Rotation minuend = new Rotation(100, true);
        Rotation subtrahend = new Rotation(40, true);
        
        Rotation difference = minuend.subtract(subtrahend);
        
        assertEquals(60, difference.getRotationInDegreesCC(), 0.0001f);
    }
    
    @Test
    public void subtract_OnDifferenceUndercutsZero_MapsSumIntoPositive() {
        Rotation minuend = new Rotation(10, true);
        Rotation subtrahend = new Rotation(70, true);
        
        Rotation difference = minuend.subtract(subtrahend);
        
        assertEquals(300, difference.getRotationInDegreesCC(), 0.0001f);
    }    
    
    @Test
    public void setRotation_OnPassNegativeValueCC_MapsIntoPositive() {
        Rotation rotation = new Rotation(-45f, true);
        
        assertEquals(360 - 45, rotation.getRotationInDegreesCC(), 0.0001f);
    }
    
    @Test
    public void setRotation_OnPassNegativeValueCW_MapsIntoPositive() {
        Rotation rotation = new Rotation(-45f, false);
        
        assertEquals(360 - 45, rotation.getRotationInDegreesCW(), 0.0001f);
    }
    
    @Test
    public void setRotation_OnPassValueHigherThanFullRotationCC_MapsIntoUnderFullRotation() {
        Rotation rotation = new Rotation(370, true);
        
        assertEquals(10, rotation.getRotationInDegreesCC(), 0.0001f);
    }
    
    @Test
    public void setRotation_OnPassValueHigherThanFullRotationCW_MapsIntoUnderFullRotation() {
        Rotation rotation = new Rotation(370, false);
        
        assertEquals(10, rotation.getRotationInDegreesCW(), 0.0001f);
    }
    
    @Test
    public void getRotationInDegreesCC_AfterSetRotationCC_ReturnsCorrectValue() {
        Rotation rotation = createRotation(45, true);
        
        assertEquals(45, rotation.getRotationInDegreesCC(), 0.0001f);
    }
    
    @Test
    public void getRotationInDegreesCC_AfterSetRotationCW_ReturnsCorrectValue() {
        Rotation rotation = createRotation(45, false);
        
        assertEquals(360 - 45, rotation.getRotationInDegreesCC(), 0.0001f);
    }
    
    @Test
    public void getRotationInDegreesCW_AfterSetRotationCC_ReturnsCorrectValue() {
        Rotation rotation = createRotation(45, true);
        
        assertEquals(360 - 45, rotation.getRotationInDegreesCW(), 0.0001f);
    }
    
    @Test
    public void getRotationInDegreesCW_AfterSetRotationCW_ReturnsCorrectValue() {
        Rotation rotation = createRotation(45, false);
        
        assertEquals(45, rotation.getRotationInDegreesCW(), 0.0001f);
    }
    
    @Test
    public void equals_OnPassRotationWithSameValue_ReturnsTrue() {
        Rotation rotation1 = createRotation(45, true);
        Rotation rotation2 = createRotation(45, true);
        
        boolean areEqual = rotation1.equals(rotation2);
        
        assertTrue(areEqual);
    }
    
    @Test
    public void equals_OnPassRotationWithDifferentValue_ReturnsTrue() {
        Rotation rotation1 = createRotation(45, true);
        Rotation rotation2 = createRotation(47, true);
        
        boolean areEqual = rotation1.equals(rotation2);
        
        assertFalse(areEqual);
    }
    
}
