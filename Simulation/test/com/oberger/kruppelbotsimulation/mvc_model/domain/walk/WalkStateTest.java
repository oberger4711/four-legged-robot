/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator.Model;
import com.oberger.kruppelbotsimulation.mvc_model.function.PolyFunction;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author ole
 */
@RunWith(MockitoJUnitRunner.class)
public class WalkStateTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private WalkState createWalkState(OrderedLegMapping legMapping, PolyFunction legFunction, Model model) {
        return new WalkState(legMapping, legFunction, model);
    }
    
    private OrderedLegMapping createDummyLegMapping() {
        return Mockito.mock(OrderedLegMapping.class);
    }
    
    private PolyFunction createDummyFunction() {
        return Mockito.mock(PolyFunction.class);
    }
    
    private Model createDummyModel() {
        return Mockito.mock(Model.class);
    }
    
    @Test
    public void constructor_OnPassLegMappingNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        createWalkState(null, createDummyFunction(), createDummyModel());
    }
    
    @Test
    public void constructor_OnPassLegFunctionNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        createWalkState(createDummyLegMapping(), null, createDummyModel());
    }
    
    @Test
    public void constructor_OnPassModelNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        createWalkState(createDummyLegMapping(), createDummyFunction(), null);
    }
    
}
