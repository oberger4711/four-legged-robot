/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions;

import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.Assert;
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

    private PartialPolyFunction createTestee(List<IReadOnlyVector2> list) {
	return new PartialPolyFunction(list);
    }

    @Test
    public void constructor_OnPassFirstNull_ThrowsException() {
	exception.expect(IllegalArgumentException.class);

	createTestee(null, Arrays.asList(new Vector2(1, 2)), new Vector2(2, 2));
    }

    @Test
    public void constructor_OnPassInnerNull_ThrowsException() {
	exception.expect(IllegalArgumentException.class);

	createTestee(new Vector2(0, 0), null, new Vector2(2, 2));
    }

    @Test
    public void constructor_OnPassLastNull_ThrowsException() {
	exception.expect(IllegalArgumentException.class);

	createTestee(new Vector2(0, 0), Arrays.asList(new Vector2(1, 2)), null);
    }

    @Test
    public void constructor_OnPassList_SetsPartsCorrectly() {
	PartialPolyFunction testee = createTestee(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 2)));
	Assert.assertThat(testee.getFirst(), is(equalTo(new Vector2(0, 0))));
	Assert.assertThat(testee.getInner(), is(equalTo(Arrays.asList(new Vector2(1, 1)))));
	Assert.assertThat(testee.getLast(), is(equalTo(Arrays.asList(new Vector2(2, 2)))));
    }

}
