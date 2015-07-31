/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions;

import com.oberger.kruppelbotsimulation.function.Interpolator;
import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 *
 * @author oberger
 */
public class ConcatPolyFunctionTests {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    public ConcatPolyFunction createTestee(Interpolator interpolator, List<ConcatPart> parts) {
	return new ConcatPolyFunction(interpolator, parts, 10);
    }

    public Interpolator createDummyInterpolator() {
	return Mockito.mock(Interpolator.class);
    }

    @Test
    public void constructor_OnPassInterpolatorNull_ThrowsIllegalArgumentException() {
	exception.expect(IllegalArgumentException.class);

	createTestee(null, new ArrayList<>());
    }

    @Test
    public void constructor_OnPassNull_ThrowsIllegalArgumentException() {
	exception.expect(IllegalArgumentException.class);

	createTestee(createDummyInterpolator(), null);
    }

    @Test
    public void constructor_OnPassNotMatchingParts_ThrowsIllegalArgumentException() {
	Vector2 first1 = new Vector2(0, 0);
	List<IReadOnlyVector2> inner1 = Arrays.asList(new Vector2(1, 1), new Vector2(2, 2));
	Vector2 last1 = new Vector2(3, 3);
	ConcatPart part1 = new ConcatPart(new PartialPolyFunction(first1, inner1, last1), EManipulatable.FIXED, EBalanceMode.IRRELEVANT);

	Vector2 first2 = new Vector2(4, 4);
	List<IReadOnlyVector2> inner2 = Arrays.asList(new Vector2(5, 5), new Vector2(6, 6));
	Vector2 last2 = new Vector2(7, 7);
	ConcatPart part2 = new ConcatPart(new PartialPolyFunction(first2, inner2, last2), EManipulatable.FIXED, EBalanceMode.IRRELEVANT);

	exception.expect(IllegalArgumentException.class);

	ConcatPolyFunction testee = createTestee(createDummyInterpolator(), Arrays.asList(part1, part2));
    }

    @Test
    public void getPolygons_OnCall_ReturnsListWithFirstInnerAndLast() {
	Vector2 first1 = new Vector2(0, 0);
	List<IReadOnlyVector2> inner1 = Arrays.asList(new Vector2(1, 1), new Vector2(2, 2));
	Vector2 last1 = new Vector2(3, 3);
	ConcatPart part1 = new ConcatPart(new PartialPolyFunction(first1, inner1, last1), EManipulatable.FIXED, EBalanceMode.IRRELEVANT);

	Vector2 first2 = new Vector2(3, 3);
	List<IReadOnlyVector2> inner2 = Arrays.asList(new Vector2(4, 4), new Vector2(5, 5));
	Vector2 last2 = new Vector2(6, 6);
	ConcatPart part2 = new ConcatPart(new PartialPolyFunction(first2, inner2, last2), EManipulatable.FIXED, EBalanceMode.IRRELEVANT);

	ConcatPolyFunction testee = createTestee(createDummyInterpolator(), Arrays.asList(part1, part2));

	Assert.assertThat(testee.getPolygons(), is(equalTo(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 2), new Vector2(3, 3), new Vector2(4, 4), new Vector2(5, 5), new Vector2(6, 6)))));
    }

    @Test
    public void getPolygons_WithOnlyOnePart_ReturnsPartPolygons() {
	Vector2 first1 = new Vector2(0, 0);
	List<IReadOnlyVector2> inner1 = Arrays.asList(new Vector2(1, 1), new Vector2(2, 2));
	Vector2 last1 = new Vector2(3, 3);
	ConcatPart part1 = new ConcatPart(new PartialPolyFunction(first1, inner1, last1), EManipulatable.FIXED, EBalanceMode.IRRELEVANT);

	ConcatPolyFunction testee = createTestee(createDummyInterpolator(), Arrays.asList(part1));

	Assert.assertThat(testee.getPolygons(), is(equalTo(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 2), new Vector2(3, 3)))));
    }

    @Test
    public void getPart_WithOnlyOnePart_ReturnsPart() {
	Vector2 first1 = new Vector2(0, 0);
	List<IReadOnlyVector2> inner1 = Arrays.asList(new Vector2(1, 1), new Vector2(2, 2));
	Vector2 last1 = new Vector2(3, 3);
	ConcatPart part1 = new ConcatPart(new PartialPolyFunction(first1, inner1, last1), EManipulatable.FIXED, EBalanceMode.IRRELEVANT);
	
	ConcatPolyFunction testee = createTestee(createDummyInterpolator(), Arrays.asList(part1));
	
	Assert.assertThat(testee.getPart(1.5f), is(equalTo(part1)));
    }
    
    @Test
    public void getPart_WithOnlyOnePartAndWrappedX_ReturnsPart() {
	Vector2 first1 = new Vector2(0, 0);
	List<IReadOnlyVector2> inner1 = Arrays.asList(new Vector2(1, 1), new Vector2(2, 2));
	Vector2 last1 = new Vector2(3, 3);
	ConcatPart part1 = new ConcatPart(new PartialPolyFunction(first1, inner1, last1), EManipulatable.FIXED, EBalanceMode.IRRELEVANT);
	
	ConcatPolyFunction testee = createTestee(createDummyInterpolator(), Arrays.asList(part1));
	
	Assert.assertThat(testee.getPart(23f), is(equalTo(part1)));
    }

}
