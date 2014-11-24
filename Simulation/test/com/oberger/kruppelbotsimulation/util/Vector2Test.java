/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.util;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author ole
 */
public class Vector2Test {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();

    private static Vector2 createVector2(float x, float y) {
        return new Vector2(x, y);
    }

    @Test
    public void add_OnPassNull_ThrowsIllegalArgumentsException() {
        Vector2 v1 = createVector2(0, 0);
        Vector2 v2 = null;

        exception.expect(IllegalArgumentException.class);
        v1.add(v2);
    }
    
    @Test
    public void add_OnPassOtherVector_ReturnsFirstSummand() {
        Vector2 v1 = createVector2(0, 1);
        Vector2 v2 = createVector2(2, 3);
        
        Vector2 result = v1.add(v2);
        
        assertTrue(v1 == result);
    }
    
    @Test
    public void add_OnPassOtherVector_AddsComponents() {
        Vector2 v1 = createVector2(0, 1);
        Vector2 v2 = createVector2(2, 3);
        
        Vector2 sum = v1.add(v2);
        
        assertEquals(2, sum.getX(), 0.001);
        assertEquals(4, sum.getY(), 0.001);
    }
    
    @Test
    public void subtract_OnPassNull_ThrowsIllegalArgumentException() {
        Vector2 v1 = createVector2(0, 0);
        Vector2 v2 = null;

        exception.expect(IllegalArgumentException.class);
        v1.subtract(v2);
    }
    
    @Test
    public void subtract_OnCall_ReturnsMinuend() {
        Vector2 v1 = createVector2(0, 1);
        Vector2 v2 = createVector2(2, 3);
        
        Vector2 result = v1.subtract(v2);
        
        assertTrue(v1 == result);
    }
    
    @Test
    public void subtract_OnPassOtherVector_SubtractsComponents() {
        Vector2 v1 = createVector2(0, 1);
        Vector2 v2 = createVector2(2, 4);
        
        Vector2 sum = v1.subtract(v2);
        
        assertEquals(-2, sum.getX(), 0.001);
        assertEquals(-3, sum.getY(), 0.001);
    }
    
    @Test
    public void scale_OnCall_DoesReturnSelf() {
        Vector2 v1 = createVector2(1, 2);
        
        Vector2 result = v1.scale(2);
        
        assertTrue(v1 == result);
    }

    @Test
    public void scale_OnCall_ReturnsScaledVector() {
        Vector2 v1 = createVector2(1, 2);
        
        Vector2 scaled = v1.scale(2);
        
        assertEquals(scaled.getX(), 2, 0.001);
        assertEquals(scaled.getY(), 4, 0.001);
    }
    
    @Test
    public void rotate_OnPassNull_ThrowsIllegalArgumentsException() {
        Vector2 v1 = createVector2(1, 2);
        
        exception.expect(IllegalArgumentException.class);
        
        v1.rotate(null, 0f);
    }
    
    @Test
    public void rotate_OnCall_ReturnsSelf() {
        Vector2 v1 = createVector2(1, 2);
        
        Vector2 result = v1.rotate(new Vector2(), 0f);
        
        assertTrue(v1 == result);
    }
    
    @Test
    public void rotate_OnPassDegrees180_RotatesBy180Degrees() {
        Vector2 v1 = createVector2(1, 2);
        
        Vector2 result = v1.rotate(new Vector2(), 180);
        
        assertEquals(-1, result.getX(), 0.0001);
        assertEquals(-2, result.getY(), 0.0001);
    }
    
    @Test
    public void rotate_OnPassDegrees90_RotatesBy90DegreesCC() {
        Vector2 v1 = createVector2(1, 2);
        
        Vector2 result = v1.rotate(new Vector2(), 90);
        
        assertEquals(-2, result.getX(), 0.0001);
        assertEquals(1, result.getY(), 0.0001);
    }
    
    @Test
    public void equals_OnPassVector2WithSameCoordinates_ReturnsTrue() {
        Vector2 v1 = createVector2(0.123f, 0.123f);
        Vector2 v2 = createVector2(0.123f, 0.123f);
        
        assertTrue(v1.equals(v2));
    }
    
    @Test
    public void equals_OnPassVector2WithFirstCoordinateDifferent_ReturnsFalse() {
        Vector2 v1 = createVector2(0.123f, 0.123f);
        Vector2 v2 = createVector2(0.456f, 0.123f);
        
        assertFalse(v1.equals(v2));
    }
    
    @Test
    public void equals_OnPassVector2WithSecondCoordinateDifferent_ReturnsFalse() {
        Vector2 v1 = createVector2(0.123f, 0.123f);
        Vector2 v2 = createVector2(0.123f, 0.456f);
        
        assertFalse(v1.equals(v2));
    }
}
