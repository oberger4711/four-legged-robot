/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.domain.walk.LegOrder.LegPosition;
import com.oberger.kruppelbotsimulation.mvc_model.function.IPolyFunction;
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
public class OrderedLegMappingTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private OrderedLegMapping createOrderedLegMapping(LegOrder order, float periodInS) {
        return new OrderedLegMapping(order, periodInS);
    }

    private LegOrder createDummyLegOrder() {
        return new LegOrder(LegPosition.BR, LegPosition.BL, LegPosition.FR, LegPosition.FL);
    }

    private IPolyFunction createDummyFunction() {
        return Mockito.mock(IPolyFunction.class);
    }
    
    @Test
    public void constructor_OnPassLegOrderNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        createOrderedLegMapping(null, 12f);
    }

    @Test
    public void getLegFunctions_OnCall_ReturnsLegFunctionBrWrappedWithCorrectParameters() {
        LegOrder order = new LegOrder(LegPosition.BR, LegPosition.BL, LegPosition.FR, LegPosition.FL);
        OrderedLegMapping testee = createOrderedLegMapping(order, 1.0f);

        WrappedPolyFunction wrappedDecorator = (WrappedPolyFunction) (testee.getLegFunctionBr(createDummyFunction()));
        assertEquals(1.0f, wrappedDecorator.getPeriod(), 0.0001f);
        assertEquals(0f, wrappedDecorator.getOffsetX(), 0.0001f);
    }

    @Test
    public void getLegFunctions_OnCall_ReturnsLegFunctionBlWrappedWithCorrectParameters() {
        LegOrder order = new LegOrder(LegPosition.BR, LegPosition.BL, LegPosition.FR, LegPosition.FL);
        OrderedLegMapping testee = createOrderedLegMapping(order, 1.0f);

        WrappedPolyFunction wrappedDecorator = (WrappedPolyFunction) (testee.getLegFunctionBl(createDummyFunction()));
        assertEquals(1.0f, wrappedDecorator.getPeriod(), 0.0001f);
        assertEquals(0.25f, wrappedDecorator.getOffsetX(), 0.0001f);
    }

    @Test
    public void getLegFunctions_OnCall_ReturnsLegFunctionFrWrappedWithCorrectParameters() {
        LegOrder order = new LegOrder(LegPosition.BR, LegPosition.BL, LegPosition.FR, LegPosition.FL);
        OrderedLegMapping testee = createOrderedLegMapping(order, 1.0f);

        WrappedPolyFunction wrappedDecorator = (WrappedPolyFunction) (testee.getLegFunctionFr(createDummyFunction()));
        assertEquals(1.0f, wrappedDecorator.getPeriod(), 0.0001f);
        assertEquals(0.5f, wrappedDecorator.getOffsetX(), 0.0001f);
    }

    @Test
    public void getLegFunctions_OnCall_ReturnsLegFunctionFlWrappedWithCorrectParameters() {
        LegOrder order = new LegOrder(LegPosition.BR, LegPosition.BL, LegPosition.FR, LegPosition.FL);
        OrderedLegMapping testee = createOrderedLegMapping(order, 1.0f);

        WrappedPolyFunction wrappedDecorator = (WrappedPolyFunction) (testee.getLegFunctionFl(createDummyFunction()));
        assertEquals(1.0f, wrappedDecorator.getPeriod(), 0.0001f);
        assertEquals(0.75f, wrappedDecorator.getOffsetX(), 0.0001f);
    }

}
