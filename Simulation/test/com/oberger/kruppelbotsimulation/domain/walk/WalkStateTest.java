/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.walk;

import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.Model;
import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.LegPolyFunctions;
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
public class WalkStateTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private WalkState createWalkState(LegPolyFunctions legFunctions, Model model) {
	return new WalkState(legFunctions, model);
    }

    private LegPolyFunctions createLegFunctions() {
	return Mockito.mock(LegPolyFunctions.class);
    }

    private Model createDummyModel() {
	return Mockito.mock(Model.class);
    }

    @Test
    public void constructor_OnPassLegFunctionsNull_ThrowsIllegalArgumentException() {
	exception.expect(IllegalArgumentException.class);

	createWalkState(null, createDummyModel());
    }

    @Test
    public void constructor_OnPassModelNull_ThrowsIllegalArgumentException() {
	exception.expect(IllegalArgumentException.class);

	createWalkState(createLegFunctions(), null);
    }

}
