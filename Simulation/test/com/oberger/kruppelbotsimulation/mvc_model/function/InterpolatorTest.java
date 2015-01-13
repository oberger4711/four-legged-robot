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

/**
 *
 * @author ole
 */
@RunWith(MockitoJUnitRunner.class)
public class InterpolatorTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private FakeInterpolator createFakeInterpolator(float interpolateValue) {
        return new FakeInterpolator(interpolateValue);
    }
    
    @Test
    public void getValue_OnPassNullPolygons_ThrowsIllegalArgumentException() {
        FakeInterpolator testee = createFakeInterpolator(0.5f);
        
        exception.expect(IllegalArgumentException.class);
        
        testee.getValue(null, 0);
    }

    @Test
    public void getValue_OnPassEmptyList_ThrowsIllegalArgumentException() {
        FakeInterpolator testee = createFakeInterpolator(0.5f);
        
        exception.expect(IllegalArgumentException.class);
        
        testee.getValue(Collections.emptyList(), 0);
    }
    
    @Test
    public void getValue_OnPassUnsortedList_ThrowsIllegalArgumentException() {
        FakeInterpolator testee = createFakeInterpolator(0.5f);
        List<IReadOnlyVector2> polygons = new ArrayList<>(Arrays.asList(new Vector2(1, 0), new Vector2(0, 0)));
        
        exception.expect(IllegalArgumentException.class);
        
        testee.getValue(polygons, 0.5f);
    }
    
    @Test
    public void getValue_OnPassListWithIdenticalPolygonsOnly_ThrowsIllegalArgumentException() {
        FakeInterpolator testee = createFakeInterpolator(-1f);
        List<IReadOnlyVector2> polygons = new ArrayList<>(Arrays.asList(new Vector2(1, 1), new Vector2(1,1)));
        
        exception.expect(IllegalArgumentException.class);
        
        testee.getValue(polygons, 0f);
    }
    
    @Test
    public void getValue_OnPassTwoPointsWithXOnFirstInterval_ReturnsPolygonY() {
        FakeInterpolator testee = createFakeInterpolator(-1f);
        List<IReadOnlyVector2> polygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1)));
        
        float interpolated = testee.getValue(polygons, 0f);
        
        assertEquals(0, interpolated, 0.0001f);
    }
    
    @Test
    public void getValue_OnPassTwoPointsWithXOnSecondInterval_ReturnsPolygonY() {
        FakeInterpolator testee = createFakeInterpolator(-1f);
        List<IReadOnlyVector2> polygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1)));
        
        float interpolated = testee.getValue(polygons, 1f);
        
        assertEquals(1, interpolated, 0.0001f);
    }
    
    @Test
    public void getValue_OnPassTwoPointsWithXInsidePolygonInterval_ReturnsInterpolated() {
        FakeInterpolator testee = createFakeInterpolator(0.5f);
        List<IReadOnlyVector2> polygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1)));
        
        float interpolated = testee.getValue(polygons, 0.5f);
        
        assertEquals(0.5f, interpolated, 0.0001f);
    }
    
    @Test
    public void getValue_OnPassTwoPointsWithXBeforePolygonInterval_ReturnsInterpolated() {
        FakeInterpolator testee = createFakeInterpolator(-0.5f);
        List<IReadOnlyVector2> polygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1)));
        
        float interpolated = testee.getValue(polygons, -0.5f);
        
        assertEquals(-0.5f, interpolated, 0.0001f);
    }
    
    @Test
    public void getValue_OnPassThreePointsWithXBetweenFirstTwoPolygons_ReturnsInterpolatedWithFirstTwoPolygons() {
        FakeInterpolator testee = createFakeInterpolator(0.5f);
        List<IReadOnlyVector2> polygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 0)));
        
        float interpolated = testee.getValue(polygons, 0.5f);
        
        assertEquals(0.5f, interpolated, 0.0001f);
    }
    
    @Test
    public void getValue_OnPassThreePointsWithXBetweenLastTwoPolygons_ReturnsInterpolatedWithFirstTwoPolygons() {
        FakeInterpolator testee = createFakeInterpolator(0.5f);
        List<IReadOnlyVector2> polygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 0)));
        
        float interpolated = testee.getValue(polygons, 1.5f);
        
        assertEquals(0.5f, interpolated, 0.0001f);
    }
    
    @Test
    public void getValue_OnPassThreePointsWithXAfterLastPolygonInterval_ReturnsInterpolatedWithLastTwoPolygons() {
        FakeInterpolator testee = createFakeInterpolator(-0.5f);
        List<IReadOnlyVector2> polygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 0)));
        
        float interpolated = testee.getValue(polygons, 2.5f);
        
        assertEquals(-0.5f, interpolated, 0.0001f);
    }
    
    @Test
    public void getValue_OnPassListWithDifferentAndIdenticalPolygonsAndXOnFirstPolygon_ReturnsPolygonValue() {
        FakeInterpolator testee = createFakeInterpolator(-1f);
        List<IReadOnlyVector2> polygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(1,1)));
        
        float interpolatedY = testee.getValue(polygons, 1f);
        
        assertEquals(1f, interpolatedY, 0.0001f);
    }
    
    @Test
    public void getPolygons_OnPassStartNull_ThrowsIllegalArgumentException() {
        FakeInterpolator testee = createFakeInterpolator(0.5f);
        Vector2 end = new Vector2(0, 0);
        
        exception.expect(IllegalArgumentException.class);
        
        testee.getPolygons(null, end, 3);
    }
    
    @Test
    public void getPolygons_OnPassEndNull_ThrowsIllegalArgumentException() {
        FakeInterpolator testee = createFakeInterpolator(0.5f);
        Vector2 start = new Vector2(0, 0);
        
        exception.expect(IllegalArgumentException.class);
        
        testee.getPolygons(start, null, 3);
    }
    
    @Test
    public void getPolygons_OnPassNumberOfPolygonsOne_ThrowsIllegalArgumentException() {
        FakeInterpolator testee = createFakeInterpolator(0.5f);
        Vector2 start = new Vector2(0, 0);
        Vector2 end = new Vector2(0, 0);
        
        exception.expect(IllegalArgumentException.class);
        
        testee.getPolygons(start, end, 1);
    }
    
    @Test
    public void getPolygons_OnPassNumberOfPolygonsTwo_ReturnsStartAndEndAsList() {
        FakeInterpolator testee = createFakeInterpolator(0.5f);
        Vector2 start = new Vector2(0, 0);
        Vector2 end = new Vector2(1, 1);
        
        List<Vector2> returnedPolygons = testee.getPolygons(start, end, 2);
        
        List<Vector2> expectedPolygons = Arrays.asList(start, end);
        assertEquals(expectedPolygons, returnedPolygons);
    }
    
    @Test
    public void getPolygons_OnPassNumberOfPolygonsThree_ReturnsStartAndInterpolatedAndEndAsList() {
        FakeInterpolator testee = createFakeInterpolator(0.5f);
        Vector2 start = new Vector2(0, 0);
        Vector2 end = new Vector2(1, 1);
        
        List<Vector2> returnedPolygons = testee.getPolygons(start, end, 3);
        
        List<Vector2> expectedPolygons = Arrays.asList(start, new Vector2(0.5f, 0.5f), end);
        assertEquals(expectedPolygons, returnedPolygons);
    }
    
}
