/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.function;

import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 *
 * @author ole
 */
public class WrappedPolyFunctionTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private WrappedPolyFunction createWrappedPolyFunction(IPolyFunction decorated, float period, float offsetX) {
        return new WrappedPolyFunction(decorated, period, offsetX);
    }
    
    private IPolyFunction createFakePolyFunction(List<IReadOnlyVector2> polygons) {
        IPolyFunction created = Mockito.mock(IPolyFunction.class);
        
        Mockito.doReturn(polygons).when(created).getPolygons();
        
        return created;
    }
    
    @Test
    public void constructor_OnPassDecoratedNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        WrappedPolyFunction testee = createWrappedPolyFunction(null, 1, 0);
    }
    
    @Test
    public void constructor_OnPassPeriodSmallerThanZero_ThrowsIllegalArgumentException() {
        IPolyFunction fakePolyFunction = createFakePolyFunction(Collections.emptyList());
        
        exception.expect(IllegalArgumentException.class);
        
        WrappedPolyFunction testee = createWrappedPolyFunction(fakePolyFunction, -1, 0);
    }
    
    @Test
    public void getPolygons_OnCall_ReturnsDecoratedPolygons() {
        List<IReadOnlyVector2> decoratedPolygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 2)));
        IPolyFunction fakePolyFunction = createFakePolyFunction(decoratedPolygons);
        WrappedPolyFunction testee = createWrappedPolyFunction(fakePolyFunction, 2, 1);
        
        List<IReadOnlyVector2> returned = testee.getPolygons();
        
        assertTrue(returned == decoratedPolygons);
    }
    
    @Test
    public void getValue_XInsideFirstPeriod_ReturnsFunctionValueOfX() {
        List<IReadOnlyVector2> decoratedPolygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 2)));
        IPolyFunction fakePolyFunction = createFakePolyFunction(decoratedPolygons);
        Mockito.doReturn(2f).when(fakePolyFunction).getValue(1);
        WrappedPolyFunction testee = createWrappedPolyFunction(fakePolyFunction, 2, 0);
        
        float returned = testee.getValue(1);
        
        assertEquals(2, returned, 0.0001);
    }
    
    @Test
    public void getValue_XAfterFirstPeriod_ReturnsFunctionValueOfMappedX() {
        List<IReadOnlyVector2> decoratedPolygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 2)));
        IPolyFunction fakePolyFunction = createFakePolyFunction(decoratedPolygons);
        Mockito.doReturn(2f).when(fakePolyFunction).getValue(1f);
        WrappedPolyFunction testee = createWrappedPolyFunction(fakePolyFunction, 2, 0);
        
        float returned = testee.getValue(3);
        
        assertEquals(2, returned, 0.0001);
    }
    
    
    @Test
    public void getValue_XBeforeFirstPeriod_ReturnsFunctionValueOfMappedX() {
        List<IReadOnlyVector2> decoratedPolygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 2)));
        IPolyFunction fakePolyFunction = createFakePolyFunction(decoratedPolygons);
        Mockito.doReturn(2f).when(fakePolyFunction).getValue(1f);
        WrappedPolyFunction testee = createWrappedPolyFunction(fakePolyFunction, 2, 0);
        
        float returned = testee.getValue(-1);
        
        assertEquals(2, returned, 0.0001);
    }
    
    @Test
    public void getValue_XWithOffsetInsideFirstPeriod_ReturnsFunctionValueOfXPlusOffset() {
        List<IReadOnlyVector2> decoratedPolygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 2)));
        IPolyFunction fakePolyFunction = createFakePolyFunction(decoratedPolygons);
        Mockito.doReturn(2f).when(fakePolyFunction).getValue(1f);
        WrappedPolyFunction testee = createWrappedPolyFunction(fakePolyFunction, 2, 0.5f);
        
        float returned = testee.getValue(0.5f);
        
        assertEquals(2, returned, 0.0001);
    }
    
    @Test
    public void getValue_XWithOffsetAfterFirstPeriod_ReturnsFunctionValueOfMappedXPlusOffset() {
        List<IReadOnlyVector2> decoratedPolygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 2)));
        IPolyFunction fakePolyFunction = createFakePolyFunction(decoratedPolygons);
        Mockito.doReturn(2f).when(fakePolyFunction).getValue(1f);
        WrappedPolyFunction testee = createWrappedPolyFunction(fakePolyFunction, 2, 2f);
        
        float returned = testee.getValue(1f);
        
        assertEquals(2, returned, 0.0001);
    }
    
    @Test
    public void getValue_XWithOffsetBeforeFirstPeriod_ReturnsFunctionValueOfMappedXPlusOffset() {
        List<IReadOnlyVector2> decoratedPolygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 2)));
        IPolyFunction fakePolyFunction = createFakePolyFunction(decoratedPolygons);
        Mockito.doReturn(2f).when(fakePolyFunction).getValue(1f);
        WrappedPolyFunction testee = createWrappedPolyFunction(fakePolyFunction, 2, -2f);
        
        float returned = testee.getValue(1f);
        
        assertEquals(2, returned, 0.0001);
    }
    
}
