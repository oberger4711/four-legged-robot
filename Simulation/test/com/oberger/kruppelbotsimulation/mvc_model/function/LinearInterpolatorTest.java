/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.function;

import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LinearInterpolatorTest {

    private LinearInterpolator createLinearInterpolator() {
        return new LinearInterpolator();
    }
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Test
    public void getValue_OnPassNullPolygons_ThrowsIllegalArgumentException() {
        LinearInterpolator testee = createLinearInterpolator();
        
        exception.expect(IllegalArgumentException.class);
        
        testee.getValue(null, 0);
    }

    @Test
    public void getValue_OnPassEmptyList_ThrowsIllegalArgumentException() {
        LinearInterpolator testee = createLinearInterpolator();
        
        exception.expect(IllegalArgumentException.class);
        
        testee.getValue(Collections.emptyList(), 0);
    }
    
    @Test
    public void getValue_OnPassUnsortedList_ThrowsIllegalArgumentException() {
        LinearInterpolator testee = createLinearInterpolator();
        List<IReadOnlyVector2> polygons = new ArrayList<>(Arrays.asList(new Vector2(1, 0), new Vector2(0, 0)));
        
        exception.expect(IllegalArgumentException.class);
        
        testee.getValue(polygons, 0.5f);
    }
    
    @Test
    public void getValue_OnPassTwoPointsWithXInsidePolygonInterval_ReturnsInterpolated() {
        LinearInterpolator testee = createLinearInterpolator();
        List<IReadOnlyVector2> polygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1)));
        
        float interpolated = testee.getValue(polygons, 0.5f);
        
        assertEquals(0.5f, interpolated, 0.0001f);
    }
    
    @Test
    public void getValue_OnPassTwoPointsWithXBeforePolygonInterval_ReturnsInterpolated() {
        LinearInterpolator testee = createLinearInterpolator();
        List<IReadOnlyVector2> polygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1)));
        
        float interpolated = testee.getValue(polygons, -0.5f);
        
        assertEquals(-0.5f, interpolated, 0.0001f);
    }
    
    @Test
    public void getValue_OnPassThreePointsWithXBetweenFirstTwoPolygons_ReturnsInterpolatedWithFirstTwoPolygons() {
        LinearInterpolator testee = createLinearInterpolator();
        List<IReadOnlyVector2> polygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 0)));
        
        float interpolated = testee.getValue(polygons, 0.5f);
        
        assertEquals(0.5f, interpolated, 0.0001f);
    }
    
    @Test
    public void getValue_OnPassThreePointsWithXBetweenLastTwoPolygons_ReturnsInterpolatedWithFirstTwoPolygons() {
        LinearInterpolator testee = createLinearInterpolator();
        List<IReadOnlyVector2> polygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 0)));
        
        float interpolated = testee.getValue(polygons, 1.5f);
        
        assertEquals(0.5f, interpolated, 0.0001f);
    }
    
    @Test
    public void getValue_OnPassThreePointsWithXAfterLastPolygonInterval_ReturnsInterpolatedWithLastTwoPolygons() {
        LinearInterpolator testee = createLinearInterpolator();
        List<IReadOnlyVector2> polygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 0)));
        
        float interpolated = testee.getValue(polygons, 2.5f);
        
        assertEquals(-0.5f, interpolated, 0.0001f);
    }

}
