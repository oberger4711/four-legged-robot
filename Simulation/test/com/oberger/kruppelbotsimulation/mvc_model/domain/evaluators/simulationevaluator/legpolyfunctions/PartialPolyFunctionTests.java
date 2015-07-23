/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.legpolyfunctions;

import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author oberger
 */
public class PartialPolyFunctionTests {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	private PartialPolyFunction createTestee(IReadOnlyVector2 first, List<IReadOnlyVector2> inner, IReadOnlyVector2 last) {
		return new PartialPolyFunction(first, inner, last);
	}
	
	@Test
	public void constructor_OnPassFirstNull_ThrowsException() {
		exception.expect(IllegalArgumentException.class);
		
		
		createTestee(null, new ArrayList<IReadOnlyVector2>(), null);
	}

}
