/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.localsearch.manipulator;

import com.oberger.kruppelbotsimulation.mvc_model.localsearch.IImmutableInnerState;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.State;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator.WeightedEvaluator;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator.WeightedEvaluatorGroup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 *
 * @author ole
 */
public class ManipulatorGroupTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private ManipulatorGroup createManipulatorGroup(List<IManipulator> manipulators) {
        return new ManipulatorGroup(manipulators);
    }
    
    private IImmutableInnerState createDummyInnerState() {
        return Mockito.mock(IImmutableInnerState.class);
    }
    
    private IManipulator createFakeManipulator(IImmutableInnerState... neighbourStates) {
        IManipulator fake = Mockito.mock(IManipulator.class);
        
        Mockito.doReturn(Arrays.asList(neighbourStates)).when(fake).createNeighbours(Mockito.any());
        
        return fake;
    }
    
    @Test
    public void constructor_OnPassNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        ManipulatorGroup testee = createManipulatorGroup(null);
    }
    
    @Test
    public void constructor_OnPassEmptyList_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        ManipulatorGroup testee = createManipulatorGroup(Collections.emptyList());
    }
    
    @Test
    public void constructor_OnCall_CopiesEvaluatorList() {
        List<IManipulator> fakeWeightedEvaluators = new ArrayList<>(Arrays.asList(createFakeManipulator(createDummyInnerState())));
        ManipulatorGroup testee = createManipulatorGroup(fakeWeightedEvaluators);
        
        List<IManipulator> returned = testee.getManipulators();
        
        assertFalse(returned == fakeWeightedEvaluators);
    }
    
    @Test
    public void getNeighbours_OnPassNull_ThrowsIllegalArgumentException() {
        IManipulator fakeManipulator = createFakeManipulator((IImmutableInnerState)null);
        ManipulatorGroup testee = createManipulatorGroup(Arrays.asList(fakeManipulator));
        
        exception.expect(IllegalArgumentException.class);
        
        testee.createNeighbours(null);
    }
    
    @Test
    public void getNeighbours_WithManipulators_ReturnsUnitedListed() {
        IImmutableInnerState fakeNeighbourInnerState1 = createDummyInnerState();
        IImmutableInnerState fakeNeighbourInnerState2 = createDummyInnerState();
        IImmutableInnerState fakeNeighbourInnerState3 = createDummyInnerState();
        IManipulator fakeManipulator1 = createFakeManipulator(fakeNeighbourInnerState1);
        IManipulator fakeManipulator2 = createFakeManipulator(fakeNeighbourInnerState2, fakeNeighbourInnerState3);
        List<IManipulator> manipulators = Arrays.asList(fakeManipulator1, fakeManipulator2);
        ManipulatorGroup testee = createManipulatorGroup(manipulators);

        List<IImmutableInnerState> resultNeighbours = testee.createNeighbours(createDummyInnerState());

        assertTrue(resultNeighbours.stream().anyMatch(s -> s == fakeNeighbourInnerState1));
        assertTrue(resultNeighbours.stream().anyMatch(s -> s == fakeNeighbourInnerState2));
        assertTrue(resultNeighbours.stream().anyMatch(s -> s == fakeNeighbourInnerState3));
    }

    @Test
    public void getManipulators_OnCall_ReturnsUnmodifiableList() {
        List<IManipulator> fakeWeightedEvaluators = new ArrayList<>(Arrays.asList(createFakeManipulator(createDummyInnerState())));
        ManipulatorGroup testee = createManipulatorGroup(fakeWeightedEvaluators);
        
        List<IManipulator> returned = testee.getManipulators();
        
        exception.expect(UnsupportedOperationException.class);
        
        returned.clear();
    }
    
}
