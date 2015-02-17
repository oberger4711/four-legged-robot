/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator;

import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.LegPosition;
import com.oberger.kruppelbotsimulation.mvc_model.function.Interpolator;
import com.oberger.kruppelbotsimulation.mvc_model.function.PolyFunction;
import com.oberger.kruppelbotsimulation.mvc_model.function.WrappedPolyFunction;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 *
 * @author ole
 */
public class OrderedLegPolyFunctionsTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    // TODO Use PolyFunction instead.
    private OrderedLegPolyFunctions createOrderedLegFunctions(PolyFunction originalFunction, LegOrder order, float periodInS) {
        return new OrderedLegPolyFunctions(originalFunction, order, periodInS);
    }

    private LegOrder createDummyLegOrder() {
        return new LegOrder(LegPosition.BR, LegPosition.BL, LegPosition.FR, LegPosition.FL);
    }

    private PolyFunction createDummyFunction() {
        return Mockito.mock(PolyFunction.class);
    }

    @Test
    public void constructor_OnPassOriginalFunctionNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        createOrderedLegFunctions(null, createDummyLegOrder(), 12f);
    }
    
    @Test
    public void constructor_OnPassLegOrderNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createOrderedLegFunctions(createDummyFunction(), null, 12f);
    }

    @Test
    public void getLegFunctions_OnCall_ReturnsLegFunctionBrWrappedWithCorrectParameters() {
        LegOrder order = new LegOrder(LegPosition.BR, LegPosition.BL, LegPosition.FR, LegPosition.FL);
        OrderedLegPolyFunctions testee = createOrderedLegFunctions(createDummyFunction(), order, 1.0f);

        WrappedPolyFunction wrappedDecorator = (WrappedPolyFunction) testee.getLegFunctionBR();
        assertEquals(1.0f, wrappedDecorator.getPeriod(), 0.0001f);
        assertEquals(0f, wrappedDecorator.getOffsetX(), 0.0001f);
    }

    @Test
    public void getLegFunctions_OnCall_ReturnsLegFunctionBlWrappedWithCorrectParameters() {
        LegOrder order = new LegOrder(LegPosition.BR, LegPosition.BL, LegPosition.FR, LegPosition.FL);
        OrderedLegPolyFunctions testee = createOrderedLegFunctions(createDummyFunction(), order, 1.0f);

        WrappedPolyFunction wrappedDecorator = (WrappedPolyFunction) testee.getLegFunctionBL();
        assertEquals(1.0f, wrappedDecorator.getPeriod(), 0.0001f);
        assertEquals(0.25f, wrappedDecorator.getOffsetX(), 0.0001f);
    }

    @Test
    public void getLegFunctions_OnCall_ReturnsLegFunctionFrWrappedWithCorrectParameters() {
        LegOrder order = new LegOrder(LegPosition.BR, LegPosition.BL, LegPosition.FR, LegPosition.FL);
        OrderedLegPolyFunctions testee = createOrderedLegFunctions(createDummyFunction(), order, 1.0f);

        WrappedPolyFunction wrappedDecorator = (WrappedPolyFunction) testee.getLegFunctionFR();
        assertEquals(1.0f, wrappedDecorator.getPeriod(), 0.0001f);
        assertEquals(0.5f, wrappedDecorator.getOffsetX(), 0.0001f);
    }

    @Test
    public void getLegFunctions_OnCall_ReturnsLegFunctionFlWrappedWithCorrectParameters() {
        LegOrder order = new LegOrder(LegPosition.BR, LegPosition.BL, LegPosition.FR, LegPosition.FL);
        OrderedLegPolyFunctions testee = createOrderedLegFunctions(createDummyFunction(), order, 1.0f);

        WrappedPolyFunction wrappedDecorator = (WrappedPolyFunction) testee.getLegFunctionFL();
        assertEquals(1.0f, wrappedDecorator.getPeriod(), 0.0001f);
        assertEquals(0.75f, wrappedDecorator.getOffsetX(), 0.0001f);
    }

}
