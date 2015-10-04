/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.simulation.legpolyfunctions;

import com.oberger.kruppelbotsimulation.domain.simulation.LegOrder;
import com.oberger.kruppelbotsimulation.domain.simulation.LegPosition;
import com.oberger.kruppelbotsimulation.function.Interpolator;
import com.oberger.kruppelbotsimulation.function.LinearInterpolator;
import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 *
 * @author oberger
 */
public class LegPolyFunctionFactoryTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private LegPolyFunctionFactory createTestee(Interpolator interpolator, List<ConcatPart> parts) {
	return new LegPolyFunctionFactory(interpolator, parts);
    }

    @Test
    public void constructor_OnPassInterpolatorNull_ThrowsException() {
	exception.expect(IllegalArgumentException.class);

	LegPolyFunctionFactory testee = createTestee(null, new ArrayList<>());
    }

    @Test
    public void constructor_OnPassListNull_ThrowsException() {
	exception.expect(IllegalArgumentException.class);

	LegPolyFunctionFactory testee = createTestee(Mockito.mock(Interpolator.class), null);
    }

    @Test
    public void create_OnCall_ShiftsFunctionForLegOrderOffset() {
	List<IReadOnlyVector2> polygons = Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 0));
	ConcatPart part = new ConcatPart(new PartialPolyFunction(polygons), EManipulatable.DYNAMIC, EBalanceMode.CRITICAL);
	LegPolyFunctionFactory testee = createTestee(new LinearInterpolator(), Arrays.asList(part));

	ILegPolyFunctions functions = testee.create(new LegOrder(LegPosition.BR, LegPosition.BL, LegPosition.FR, LegPosition.FL));
	Assert.assertEquals(1, functions.getLegFunctionBR().getValue(1), 0.00001f);
	Assert.assertEquals(1, functions.getLegFunctionBL().getValue(0.5f), 0.00001f);
	Assert.assertEquals(1, functions.getLegFunctionFR().getValue(2f), 0.00001f);
	Assert.assertEquals(1, functions.getLegFunctionFL().getValue(1.5f), 0.00001f);
    }

}
