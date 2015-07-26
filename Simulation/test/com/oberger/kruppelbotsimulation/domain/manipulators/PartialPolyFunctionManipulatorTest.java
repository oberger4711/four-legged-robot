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
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author ole
 */
@RunWith(MockitoJUnitRunner.class)
public class PartialPolyFunctionManipulatorTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private PartialPolyFunctionManipulator createTestee(float maxAbsManipulationStep, float maxAbsGradient) {
	return new PartialPolyFunctionManipulator(maxAbsManipulationStep, maxAbsGradient);
    }

    private PartialPolyFunction createPartialPolyFunction(Vector2... polygons) {
	return new PartialPolyFunction(Arrays.asList(polygons));
    }

    @Test
    public void constructor_OnPassNegativeMaxAbsManipulationStep_ThrowsIllegalArgumentException() {
	exception.expect(IllegalArgumentException.class);

	createTestee(-1, 1);
    }

    @Test
    public void constructor_OnPassNegativeMaxAbsGradient_ThrowsIllegalArgumentException() {
	exception.expect(IllegalArgumentException.class);

	createTestee(1, -1);
    }

    @Test
    public void manipulate_OnPassNull_ThrowsIllegalArgumentException() {
	PartialPolyFunctionManipulator testee = createTestee(1, 1);

	exception.expect(IllegalArgumentException.class);

	testee.createNeighbours(null);
    }

    @Test
    public void manipulate_OnCall_Steps() {
	PartialPolyFunctionManipulator testee = createTestee(1, 1000);
	PartialPolyFunction fakeFunction = createPartialPolyFunction(new Vector2(0, 0), new Vector2(1, 0), new Vector2(2, 0));

	List<PartialPolyFunction> manipulatedFunctions = testee.createNeighbours(fakeFunction);

	assertEquals(Arrays.asList(new Vector2(1, 1)), manipulatedFunctions.get(0).getInner());
    }

    @Test
    public void manipulate_OnPassPolyFunctionWithStepExceedingMaxGradientFromBefore_ReturnsNoNeighbours() {
	PartialPolyFunctionManipulator testee = createTestee(1000, 1);
	PartialPolyFunction fakeFunction = createPartialPolyFunction(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 1));

	List<PartialPolyFunction> manipulatedFunctions = testee.createNeighbours(fakeFunction);

	assertEquals(0, manipulatedFunctions.size());
    }

}
