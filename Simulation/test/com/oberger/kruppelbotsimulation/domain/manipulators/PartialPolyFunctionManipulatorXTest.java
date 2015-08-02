/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.manipulators;

import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.PartialPolyFunction;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author oberger
 */
public class PartialPolyFunctionManipulatorXTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private PartialPolyFunctionManipulatorX createTestee(float manipulationStep) {
	return new PartialPolyFunctionManipulatorX(manipulationStep);
    }

    private PartialPolyFunction createPartialPolyFunction(Vector2... polygons) {
	return new PartialPolyFunction(Arrays.asList(polygons));
    }

    @Test
    public void manipulate_OnPassNull_ThrowsIllegalArgumentException() {
	PartialPolyFunctionManipulatorX testee = createTestee(1);

	exception.expect(IllegalArgumentException.class);

	testee.createNeighbours(null);
    }

    @Test
    public void manipulate_OnCall_Steps() {
	PartialPolyFunctionManipulatorX testee = createTestee(0.5f);
	PartialPolyFunction fakeFunction = createPartialPolyFunction(new Vector2(0, 0), new Vector2(1, 0), new Vector2(2, 0), new Vector2(3, 0));

	List<PartialPolyFunction> manipulatedFunctions = testee.createNeighbours(fakeFunction);

	assertEquals(Arrays.asList(new Vector2(1.5f, 0), new Vector2(2, 0)), manipulatedFunctions.get(0).getInner());
	assertEquals(Arrays.asList(new Vector2(1, 0), new Vector2(2.5f, 0)), manipulatedFunctions.get(1).getInner());
    }
    
    @Test
    public void manipulate_WouldOvertakeNextPolygon_StepsOnlyIfNotExceeding() {
	PartialPolyFunctionManipulatorX testee = createTestee(2);
	PartialPolyFunction fakeFunction = createPartialPolyFunction(new Vector2(0, 0), new Vector2(1, 0), new Vector2(2, 0), new Vector2(5, 0));

	List<PartialPolyFunction> manipulatedFunctions = testee.createNeighbours(fakeFunction);

	assertEquals(Arrays.asList(new Vector2(1, 0), new Vector2(4, 0)), manipulatedFunctions.get(0).getInner());
    }

}
