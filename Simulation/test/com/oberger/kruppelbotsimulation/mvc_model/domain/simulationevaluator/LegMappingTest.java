/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator;

import com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator.LegMapping;
import com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator.LegPolyFunctions;
import com.oberger.kruppelbotsimulation.mvc_model.function.IPolyFunction;
import static org.junit.Assert.assertTrue;
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
public class LegMappingTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private LegMapping createDummyLegMapping() {
        return new LegMapping() {

            @Override
            protected IPolyFunction getLegFunctionBl(IPolyFunction originalFunction) {
                return null;
            }

            @Override
            protected IPolyFunction getLegFunctionBr(IPolyFunction originalFunction) {
                return null;
            }

            @Override
            protected IPolyFunction getLegFunctionFl(IPolyFunction originalFunction) {
                return null;
            }

            @Override
            protected IPolyFunction getLegFunctionFr(IPolyFunction originalFunction) {
                return null;
            }
        };
    }

    private IPolyFunction createDummyFunction() {
        return Mockito.mock(IPolyFunction.class);
    }
    
    @Test
    public void getLegFunctions_OnPassNull_ThrowsIllegalArgumentException() {
        LegMapping dummy = createDummyLegMapping();
        
        exception.expect(IllegalArgumentException.class);
        
        dummy.getLegFunctions(null);
    }

    @Test
    public void getLegFunctions_OnCall_CreatesLegFunctionsFromTemplateMethods() {
        IPolyFunction bl = createDummyFunction();
        IPolyFunction br = createDummyFunction();
        IPolyFunction fl = createDummyFunction();
        IPolyFunction fr = createDummyFunction();

        LegMapping testee = new LegMapping() {

            @Override
            protected IPolyFunction getLegFunctionBl(IPolyFunction originalFunction) {
                return bl;
            }

            @Override
            protected IPolyFunction getLegFunctionBr(IPolyFunction originalFunction) {
                return br;
            }

            @Override
            protected IPolyFunction getLegFunctionFl(IPolyFunction originalFunction) {
                return fl;
            }

            @Override
            protected IPolyFunction getLegFunctionFr(IPolyFunction originalFunction) {
                return fr;
            }
        };
        
        LegPolyFunctions returnedLegPolyFunctions = testee.getLegFunctions(createDummyFunction());
        
        assertTrue(returnedLegPolyFunctions.getLegFunctionBL() == bl);
        assertTrue(returnedLegPolyFunctions.getLegFunctionBR() == br);
        assertTrue(returnedLegPolyFunctions.getLegFunctionFL() == fl);
        assertTrue(returnedLegPolyFunctions.getLegFunctionFR() == fr);
    }

}
