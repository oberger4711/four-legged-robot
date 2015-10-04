/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.localsearch.manipulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 *
 * @author oberger
 */
public class ConstrainedManipulatorTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();

    private ConstrainedManipulator createTestee(IManipulator decoratee, List<IConstraint> constraints) {
	return new ConstrainedManipulator(decoratee, constraints);
    }
    
    private ConstrainedManipulator<Integer> createNeighbourTestee(List<IConstraint<Integer>> constraints) {
	return new ConstrainedManipulator<>((Integer i) -> Arrays.asList(i - 1, i + 1), constraints);
    }
    
    @Test
    public void constructor_OnPassDecorateeNull_ThrowsException() {
	exception.expect(IllegalArgumentException.class);
	
	createTestee(null, new ArrayList<>());
    }
    
    @Test
    public void constructor_OnPassConstraintsNull_ThrowsException() {
	exception.expect(IllegalArgumentException.class);
	
	createTestee(Mockito.mock(IManipulator.class), null);
    }
    
    @Test
    public void createNeighbours_WithoutConstraints_ReturnsNeighboursFromDecoratee() {
	ConstrainedManipulator<Integer> testee = createNeighbourTestee(Collections.EMPTY_LIST);
	
	List<Integer> passedNeighbours = testee.createNeighbours(2);
	
	Assert.assertThat(passedNeighbours, Matchers.is(Matchers.equalTo(Arrays.asList(1, 3))));
    }
    
    @Test
    public void createNeighbours_WithBothPassingConstraint_ReturnsNeighboursFromDecoratee() {
	ConstrainedManipulator<Integer> testee = createNeighbourTestee(Arrays.asList(i -> i > 0));
	
	List<Integer> passedNeighbours = testee.createNeighbours(2);
	
	Assert.assertThat(passedNeighbours, Matchers.is(Matchers.equalTo(Arrays.asList(1, 3))));
    }
    
    @Test
    public void createNeighbours_WithOnePassingConstraint_ReturnsPassingNeighboursFromDecoratee() {
	ConstrainedManipulator<Integer> testee = createNeighbourTestee(Arrays.asList(i -> i > 0));
	
	List<Integer> passedNeighbours = testee.createNeighbours(1);
	
	Assert.assertThat(passedNeighbours, Matchers.is(Matchers.equalTo(Arrays.asList(2))));
    }
    
}
