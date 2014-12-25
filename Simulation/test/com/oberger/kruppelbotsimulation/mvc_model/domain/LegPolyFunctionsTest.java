/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain;

import com.oberger.kruppelbotsimulation.mvc_model.function.IPolyFunction;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 *
 * @author ole
 */
public class LegPolyFunctionsTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private LegPolyFunctions createLegPolyFunctions(IPolyFunction br, IPolyFunction bl, IPolyFunction fr, IPolyFunction fl) {
        return new LegPolyFunctions(br, bl, fr, fl);
    }
    
    private IPolyFunction createDummyPolyFunction() {
        return Mockito.mock(IPolyFunction.class);
    }
    
    @Test
    public void constructor_OnPassFunctionBRNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        createLegPolyFunctions(null, createDummyPolyFunction(), createDummyPolyFunction(), createDummyPolyFunction());
    }
    
    @Test
    public void constructor_OnPassFunctionBLNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        createLegPolyFunctions(createDummyPolyFunction(), null, createDummyPolyFunction(), createDummyPolyFunction());
    }
    
    @Test
    public void constructor_OnPassFunctionFRNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        createLegPolyFunctions(createDummyPolyFunction(), createDummyPolyFunction(), null, createDummyPolyFunction());
    }
    
    @Test
    public void constructor_OnPassFunctionFLNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        createLegPolyFunctions(createDummyPolyFunction(), createDummyPolyFunction(), createDummyPolyFunction(), null);
    }
    
}
