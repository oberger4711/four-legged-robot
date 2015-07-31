/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.manipulators;

import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.ConcatPart;
import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.EBalanceMode;
import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.EManipulatable;
import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.PartialPolyFunction;
import com.oberger.kruppelbotsimulation.localsearch.manipulator.IManipulator;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.util.Arrays;
import java.util.LinkedList;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 *
 * @author oberger
 */
public class ConcatPolyFunctionManipulatorTests {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private ConcatPolyFunctionManipulator createTestee(IManipulator<PartialPolyFunction> partialManipulator) {
	return new ConcatPolyFunctionManipulator(partialManipulator);
    }
    
    @Test
    public void constructor_OnPassNull_ThrowsIllegalArgumentException() {
	exception.expect(IllegalArgumentException.class);
	
	createTestee(null);
    }
    
    @Test
    public void createNeighbours_OnPassNull_ThrowsIllegalArgumentException() {
	IManipulator<PartialPolyFunction> fakePartialManipulator = (IManipulator <PartialPolyFunction>)Mockito.mock(IManipulator.class);
	Mockito.doReturn(new LinkedList<>()).when(fakePartialManipulator).createNeighbours(Mockito.any());
	ConcatPolyFunctionManipulator testee = createTestee(fakePartialManipulator);
	
	exception.expect(IllegalArgumentException.class);
	testee.createNeighbours(null);
    }
    
    @Test
    public void createNeighbours_OnPassFunctionWithOneDynamicPart_ReturnsFunctionWithManipulatedPart() {
	PartialPolyFunction original = new PartialPolyFunction(new Vector2(0, 0), Arrays.asList(new Vector2(1, 1)), new Vector2(2, 2));
	ConcatPart originalPart = new ConcatPart(original, EManipulatable.DYNAMIC, EBalanceMode.IRRELEVANT);
	
	PartialPolyFunction neighbour = new PartialPolyFunction(new Vector2(0, 0), Arrays.asList(new Vector2(1, 2)), new Vector2(2, 2));
	IManipulator<PartialPolyFunction> fakePartialManipulator = (IManipulator <PartialPolyFunction>)Mockito.mock(IManipulator.class);
	Mockito.doReturn(Arrays.asList(neighbour)).when(fakePartialManipulator).createNeighbours(original);
	ConcatPolyFunctionManipulator testee = createTestee(fakePartialManipulator);
	
	Assert.fail();
	// testee.createNeighbours(originalPart);
    }
    
}
