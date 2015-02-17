/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.manipulators;

import com.oberger.kruppelbotsimulation.mvc_model.function.IPolyFunction;
import com.oberger.kruppelbotsimulation.mvc_model.function.Interpolator;
import com.oberger.kruppelbotsimulation.mvc_model.function.PolyFunction;
import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author ole
 */
@RunWith(MockitoJUnitRunner.class)
public class PolyFunctionManipulatorTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private PolyFunctionManipulator createTestee(int startPolygonIndex, int endPolygonIndex, float maxAbsManipulationStep, float maxAbsGradient) {
        return new PolyFunctionManipulator(startPolygonIndex, endPolygonIndex, maxAbsManipulationStep, maxAbsGradient);
    }

    private Random createFakeRandomPolygon(int offsetStep) {
        Random fakeRandomStep = Mockito.mock(Random.class);

        Mockito.doReturn(offsetStep).when(fakeRandomStep).nextInt();

        return fakeRandomStep;
    }

    private Random createFakeRandomStep(float factor) {
        Random fakeRandomStep = Mockito.mock(Random.class);

        Mockito.doReturn(factor).when(fakeRandomStep).nextFloat();

        return fakeRandomStep;
    }

    private PolyFunction createFakePolyFunction(Vector2... polygons) {
        PolyFunction fake = Mockito.mock(PolyFunction.class);

        Mockito.doReturn(Arrays.asList(polygons)).when(fake).getPolygons();
        Mockito.doReturn(Mockito.mock(Interpolator.class)).when(fake).getInterpolator();

        return fake;
    }

    @Test
    public void constructor_OnPassNegativeStartPolygonIndex_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createTestee(-1, 1, 1, 1);
    }

    @Test
    public void constructor_OnPassEndPolygonIndexLowerThanStartPolygonIndex_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createTestee(0, 1, 1, 1);
    }

    @Test
    public void constructor_OnPassNegativeMaxAbsManipulationStep_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createTestee(0, 2, -1, 1);
    }

    @Test
    public void constructor_OnPassNegativeMaxAbsGradient_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createTestee(0, 2, 1, -1);
    }

    @Test
    public void manipulate_OnPassNull_ThrowsIllegalArgumentException() {
        PolyFunctionManipulator testee = createTestee(0, 2, 1, 1);

        exception.expect(IllegalArgumentException.class);

        testee.createNeighbours(null);
    }

    @Test
    public void manipulate_OnPassPolyFunctionWithFewerPolygonsThanNeeded_ThrowsIllegalArgumentException() {
        PolyFunctionManipulator testee = createTestee(0, 2, 1, 1);
        PolyFunction fakeFunction = createFakePolyFunction(new Vector2(0, 0), new Vector2(1, 1));

        exception.expect(IllegalArgumentException.class);

        testee.createNeighbours(fakeFunction);
    }

    @Test
    public void manipulate_OnPassPolyFunctionWithMaxPositiveStepLimitedByMaxStep_StepsByMaxPositiveStep() {
        PolyFunctionManipulator testee = createTestee(0, 2, 1, 1000);
        PolyFunction fakeFunction = createFakePolyFunction(new Vector2(0, 0), new Vector2(1, 0), new Vector2(2, 0));
        testee.setRandomNextPolygon(createFakeRandomPolygon(0));
        testee.setRandomNextStep(createFakeRandomStep(1f));

        List<PolyFunction> manipulatedFunctions = testee.createNeighbours(fakeFunction);

        assertEquals(Arrays.asList((IReadOnlyVector2) new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 0)), manipulatedFunctions.get(0).getPolygons());
    }

    @Test
    public void manipulate_OnPassPolyFunctionWithMaxPositiveStepLimitedByMaxGradientFromBefore_StepsByMaxPositiveStep() {
        PolyFunctionManipulator testee = createTestee(0, 2, 1000, 1);
        PolyFunction fakeFunction = createFakePolyFunction(new Vector2(0, 0), new Vector2(1, 0), new Vector2(2, 1));
        testee.setRandomNextPolygon(createFakeRandomPolygon(0));
        testee.setRandomNextStep(createFakeRandomStep(1f));

        List<PolyFunction> manipulatedFunctions = testee.createNeighbours(fakeFunction);

        assertEquals(Arrays.asList((IReadOnlyVector2) new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 1)), manipulatedFunctions.get(0).getPolygons());
    }

    @Test
    public void manipulate_OnPassPolyFunctionWithMaxPositiveStepLimitedByMaxGradientFromAfter_StepsByMaxPositiveStep() {
        PolyFunctionManipulator testee = createTestee(0, 2, 1000, 1);
        PolyFunction fakeFunction = createFakePolyFunction(new Vector2(0, 1), new Vector2(1, 0), new Vector2(2, 0));
        testee.setRandomNextPolygon(createFakeRandomPolygon(0));
        testee.setRandomNextStep(createFakeRandomStep(1f));

        List<PolyFunction> manipulatedFunctions = testee.createNeighbours(fakeFunction);

        assertEquals(Arrays.asList((IReadOnlyVector2) new Vector2(0, 1), new Vector2(1, 1f), new Vector2(2, 0)), manipulatedFunctions.get(0).getPolygons());
    }

    @Test
    public void manipulate_OnPassPolyFunctionWithMaxNegativeStepLimitedByMaxStep_StepsByMaxPositiveStep() {
        PolyFunctionManipulator testee = createTestee(0, 2, 1, 1000);
        PolyFunction fakeFunction = createFakePolyFunction(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 0));
        testee.setRandomNextPolygon(createFakeRandomPolygon(0));
        testee.setRandomNextStep(createFakeRandomStep(0f));

        List<PolyFunction> manipulatedFunctions = testee.createNeighbours(fakeFunction);

        assertEquals(Arrays.asList((IReadOnlyVector2) new Vector2(0, 0), new Vector2(1, 0), new Vector2(2, 0)), manipulatedFunctions.get(0).getPolygons());
    }

    @Test
    public void manipulate_OnPassPolyFunctionWithMaxNegativeStepLimitedByMaxGradientFromBefore_StepsByMaxNegativeStep() {
        PolyFunctionManipulator testee = createTestee(0, 2, 1000, 1);
        PolyFunction fakeFunction = createFakePolyFunction(new Vector2(0, 1), new Vector2(1, 1), new Vector2(2, 0));
        testee.setRandomNextPolygon(createFakeRandomPolygon(0));
        testee.setRandomNextStep(createFakeRandomStep(0f));

        List<PolyFunction> manipulatedFunctions = testee.createNeighbours(fakeFunction);

        assertEquals(Arrays.asList((IReadOnlyVector2) new Vector2(0, 1), new Vector2(1, 0), new Vector2(2, 0)), manipulatedFunctions.get(0).getPolygons());
    }

    @Test
    public void manipulate_OnPassPolyFunctionWithMaxNegativeStepLimitedByMaxGradientFromAfter_StepsByMaxNegativeStep() {
        PolyFunctionManipulator testee = createTestee(0, 2, 1000, 1);
        PolyFunction fakeFunction = createFakePolyFunction(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 1));
        testee.setRandomNextPolygon(createFakeRandomPolygon(0));
        testee.setRandomNextStep(createFakeRandomStep(0f));

        List<PolyFunction> manipulatedFunctions = testee.createNeighbours(fakeFunction);

        assertEquals(Arrays.asList((IReadOnlyVector2) new Vector2(0, 0), new Vector2(1, 0), new Vector2(2, 1)), manipulatedFunctions.get(0).getPolygons());
    }
    
    @Test
    public void manipulate_OnPassPolyFunctionWithMultipleManipulatablePolygons_ManipulatesRandom() {
        PolyFunctionManipulator testee = createTestee(0, 3, 1, 1000);
        PolyFunction fakeFunction = createFakePolyFunction(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 1), new Vector2(3, 2));
        testee.setRandomNextPolygon(createFakeRandomPolygon(1));
        testee.setRandomNextStep(createFakeRandomStep(1));
        
        List<PolyFunction> manipulatedFunctions = testee.createNeighbours(fakeFunction);
        
        assertEquals(Arrays.asList((IReadOnlyVector2) new Vector2(0, 0), new Vector2(1, 2), new Vector2(2, 1), new Vector2(3, 2)), manipulatedFunctions.get(0).getPolygons());
    }

}
