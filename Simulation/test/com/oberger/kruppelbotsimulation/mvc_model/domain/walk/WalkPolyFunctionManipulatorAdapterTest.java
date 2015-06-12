/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.LegOrder;
import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.Model;
import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.OrderedLegPolyFunctions;
import com.oberger.kruppelbotsimulation.mvc_model.function.PolyFunction;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.manipulator.IManipulator;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
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
public class WalkPolyFunctionManipulatorAdapterTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private WalkPolyFunctionManipulatorAdapter createTestee(IManipulator<PolyFunction> adaptee) {
        return new WalkPolyFunctionManipulatorAdapter(adaptee);
    }
    
    private IManipulator<PolyFunction> createFakePolyFunctionManipulator(List<PolyFunction> neighbours) {
        IManipulator<PolyFunction> fake = Mockito.mock(IManipulator.class);
        Mockito.doReturn(neighbours).when(fake).createNeighbours(Mockito.any());
        
        return fake;
    }
    
    private PolyFunction createFakePolyFunction() {
        return Mockito.mock(PolyFunction.class);
    }
    
    private WalkState createFakeWalkState() {
        OrderedLegPolyFunctions fakeOrderedLegFunctions = Mockito.mock(OrderedLegPolyFunctions.class);
        PolyFunction fakeOriginalFunction = Mockito.mock(PolyFunction.class);
        Mockito.doReturn(fakeOriginalFunction).when(fakeOrderedLegFunctions).getOriginalFunction();
        WalkState fake = Mockito.mock(WalkState.class);
        LegOrder fakeLegOrder = Mockito.mock(LegOrder.class);
        Mockito.doReturn(fakeLegOrder).when(fakeOrderedLegFunctions).getLegOrder();
        Mockito.doReturn(12f).when(fakeOrderedLegFunctions).getPeriodInS();
        Mockito.doReturn(fakeOrderedLegFunctions).when(fake).getLegFunctions();
        Model fakeModel = Mockito.mock(Model.class);
        Mockito.doReturn(fakeModel).when(fake).getModel();
        
        return fake;
    }
    
    @Test
    public void constructor_OnPassNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        createTestee(null);
    }
    
    @Test
    public void createNeighbours_OnPassNull_ThrowsIllegalArgumentException() {
        WalkPolyFunctionManipulatorAdapter testee = createTestee(createFakePolyFunctionManipulator(Collections.emptyList()));
        
        exception.expect(IllegalArgumentException.class);
        testee.createNeighbours(null);
    }
    
    @Test
    public void createNeighbours_OnCall_ReturnsWalkStatesWithOriginalModel() {
        List<PolyFunction> neighbourPolyFunctions = Arrays.asList(createFakePolyFunction());
        IManipulator<PolyFunction> adaptee = createFakePolyFunctionManipulator(neighbourPolyFunctions);
        WalkPolyFunctionManipulatorAdapter testee = createTestee(adaptee);
        WalkState originalWalkState = createFakeWalkState();
        
        List<WalkState> neighbourWalkStates = testee.createNeighbours(originalWalkState);
        assertEquals(originalWalkState.getModel(), neighbourWalkStates.get(0).getModel());
    }
    
    @Test
    public void createNeighbours_OnCall_ReturnsWalkStatesWithLegFunctionsWithOriginalLegOrder() {
        List<PolyFunction> neighbourPolyFunctions = Arrays.asList(createFakePolyFunction());
        IManipulator<PolyFunction> adaptee = createFakePolyFunctionManipulator(neighbourPolyFunctions);
        WalkPolyFunctionManipulatorAdapter testee = createTestee(adaptee);
        WalkState originalWalkState = createFakeWalkState();
        
        List<WalkState> neighbourWalkStates = testee.createNeighbours(originalWalkState);
        assertEquals(originalWalkState.getLegFunctions().getLegOrder(), neighbourWalkStates.get(0).getLegFunctions().getLegOrder());
    }
    
    @Test
    public void createNeighbours_OnCall_ReturnsWalkStatesWithLegFunctionsWithOriginalPeriodInMs() {
        List<PolyFunction> neighbourPolyFunctions = Arrays.asList(createFakePolyFunction());
        IManipulator<PolyFunction> adaptee = createFakePolyFunctionManipulator(neighbourPolyFunctions);
        WalkPolyFunctionManipulatorAdapter testee = createTestee(adaptee);
        WalkState originalWalkState = createFakeWalkState();
        
        List<WalkState> neighbourWalkStates = testee.createNeighbours(createFakeWalkState());
        assertEquals(originalWalkState.getLegFunctions().getPeriodInS(), neighbourWalkStates.get(0).getLegFunctions().getPeriodInS(), 0f);
    }

    @Test
    public void createNeighbours_OnCall_ReturnsWalkStatesWithAdapteeNeighbours() {
        List<PolyFunction> neighbourPolyFunctions = Arrays.asList(createFakePolyFunction(), createFakePolyFunction());
        IManipulator<PolyFunction> adaptee = createFakePolyFunctionManipulator(neighbourPolyFunctions);
        WalkPolyFunctionManipulatorAdapter testee = createTestee(adaptee);
        
        List<WalkState> neighbourWalkStates = testee.createNeighbours(createFakeWalkState());
        assertEquals(neighbourPolyFunctions.get(0), neighbourWalkStates.get(0).getLegFunctions().getOriginalFunction());
        assertEquals(neighbourPolyFunctions.get(1), neighbourWalkStates.get(1).getLegFunctions().getOriginalFunction());
    }

}
