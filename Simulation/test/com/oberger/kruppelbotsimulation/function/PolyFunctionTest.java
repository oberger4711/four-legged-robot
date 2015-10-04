/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.function;

import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 *
 * @author ole
 */
public class PolyFunctionTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private PolyFunction createPolyFunction(Interpolator interpolator, List<IReadOnlyVector2> polygons) {
	return new PolyFunction(interpolator, polygons);
    }

    @Test
    public void constructor_OnPassNullInterpolator_ThrowsIllegalArgumentException() {
	List<IReadOnlyVector2> polygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1)));

	exception.expect(IllegalArgumentException.class);

	PolyFunction testee = createPolyFunction(null, polygons);
    }

    @Test
    public void constructor_OnPassNullPolygons_ThrowsIllegalArgumentException() {
	Interpolator fakeInterpolator = Mockito.mock(Interpolator.class);

	exception.expect(IllegalArgumentException.class);

	PolyFunction testee = createPolyFunction(fakeInterpolator, null);
    }

    @Test
    public void constructor_OnPassPolygonsOfSizeOne_ThrowsIllegalArgumentException() {
	Interpolator fakeInterpolator = Mockito.mock(Interpolator.class);
	List<IReadOnlyVector2> polygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0)));

	exception.expect(IllegalArgumentException.class);

	createPolyFunction(fakeInterpolator, polygons);
    }

    @Test
    public void constructor_OnPassOrderInnerFalse_ThrowsException() {
	Interpolator fakeInterpolator = Mockito.mock(Interpolator.class);
	List<IReadOnlyVector2> polygons = Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(0, 2), new Vector2(3, 3));
	exception.expect(IllegalArgumentException.class);

	createPolyFunction(fakeInterpolator, polygons);
    }

    @Test
    public void getValue_OnCall_ReturnsInterpolatedValue() {
	Interpolator fakeInterpolator = Mockito.mock(Interpolator.class);
	List<IReadOnlyVector2> polygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1)));
	PolyFunction testee = createPolyFunction(fakeInterpolator, polygons);
	Mockito.doReturn(42f).when(fakeInterpolator).getValue(testee.getPolygons(), 0);

	float value = testee.getValue(0);

	assertEquals(42, value, 0.0001f);
    }

    @Test
    public void getPolygons_OnCall_ReturnsUnmodifiableList() {
	Interpolator fakeInterpolator = Mockito.mock(Interpolator.class);
	List<IReadOnlyVector2> polygons = new ArrayList<>(Arrays.asList(new Vector2(0, 0), new Vector2(1, 1)));
	PolyFunction testee = createPolyFunction(fakeInterpolator, polygons);

	List<IReadOnlyVector2> returned = testee.getPolygons();

	exception.expect(UnsupportedOperationException.class);

	returned.clear();
    }

}
