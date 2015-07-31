/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.model;

import com.oberger.kruppelbotsimulation.util.Vector2;
import com.oberger.kruppelbotsimulation.util.Weight;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author ole
 */
public class BalancePointTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private static BalancePoint createBalancePoint(Vector2 position, Weight weight) {
	return new BalancePoint(position, weight);
    }

    private static List<BalancePoint> createBalancePointList(BalancePoint... balancePoints) {
	return Arrays.asList(balancePoints);
    }

    @Test
    public void constructor_OnPassPositionNull_ThrowsIllegalArgumentException() {
	exception.expect(IllegalArgumentException.class);

	createBalancePoint(null, new Weight(1));
    }

    @Test
    public void constructor_OnCall_CopiesVectorParameter() {
	Vector2 position = new Vector2(1, 2);

	BalancePoint balancePoint = createBalancePoint(position, new Weight(1));

	assertFalse(position == balancePoint.getPosition());
    }

    @Test
    public void mergeBalancePoints_OnPassNull_ThrowsIllegalArgumentException() {
	exception.expect(IllegalArgumentException.class);

	BalancePoint.mergeBalancePoints(null);
    }

    @Test
    public void mergeBalancePoints_OnPassEmptyList_ThrowsIllegalArgumentException() {
	List<BalancePoint> balancePointList = Collections.<BalancePoint>emptyList();

	exception.expect(IllegalArgumentException.class);

	BalancePoint.mergeBalancePoints(balancePointList);
    }

    @Test
    public void mergeBalancePoints_OnPassOneBalancePoint_MergedPositionIsGivenPosition() {
	BalancePoint balancePoint = createBalancePoint(new Vector2(1, 2), new Weight(1));
	List<BalancePoint> balancePointList = createBalancePointList(balancePoint);

	BalancePoint mergedBalancePoint = BalancePoint.mergeBalancePoints(balancePointList);

	assertEquals(balancePoint.getPosition().getX(), mergedBalancePoint.getPosition().getX(), 0.001f);
    }

    @Test
    public void mergeBalancePoints_OnPassTwoBalancePointsOfSameWeight_MergedPositionIsMeanPosition() {
	BalancePoint balancePoint1 = createBalancePoint(new Vector2(0, 1), new Weight(1));
	BalancePoint balancePoint2 = createBalancePoint(new Vector2(1, 2), new Weight(1));
	List<BalancePoint> balancePointList = createBalancePointList(balancePoint1, balancePoint2);

	BalancePoint mergedBalancePoint = BalancePoint.mergeBalancePoints(balancePointList);

	assertEquals(0.5f, mergedBalancePoint.getPosition().getX(), 0.001f);
    }

    @Test
    public void mergeBalancePoints_OnPassTwoBalancePointsOfDifferentWeight_MergedWeightIsWeightSum() {
	BalancePoint balancePoint1 = createBalancePoint(new Vector2(0, 0), new Weight(1));
	BalancePoint balancePoint2 = createBalancePoint(new Vector2(1, 1), new Weight(2));
	List<BalancePoint> balancePointList = createBalancePointList(balancePoint1, balancePoint2);

	BalancePoint mergedBalancePoint = BalancePoint.mergeBalancePoints(balancePointList);

	assertEquals(3, mergedBalancePoint.getWeight().getValue(), 0.001f);
    }

    @Test
    public void mergeBalancePoints_OnPassTwoBalancePointsOfDifferentWeight_MergedPositionIsWeightedMean() {
	BalancePoint balancePoint1 = createBalancePoint(new Vector2(0, 1), new Weight(1));
	BalancePoint balancePoint2 = createBalancePoint(new Vector2(1, 2), new Weight(2));
	List<BalancePoint> balancePointList = createBalancePointList(balancePoint1, balancePoint2);

	BalancePoint mergedBalancePoint = BalancePoint.mergeBalancePoints(balancePointList);

	assertEquals(0.6666f, mergedBalancePoint.getPosition().getX(), 0.001f);
	assertEquals(1.6666f, mergedBalancePoint.getPosition().getY(), 0.001f);
    }

    @Test
    public void mergeBalancePoints_OnPassThreeBalancePointsOfDifferentWeight_MergedPositionIsWeightedMean() {
	BalancePoint balancePoint1 = createBalancePoint(new Vector2(0, 0), new Weight(1));
	BalancePoint balancePoint2 = createBalancePoint(new Vector2(1, 1), new Weight(30));
	BalancePoint balancePoint3 = createBalancePoint(new Vector2(2, 2), new Weight(1));
	List<BalancePoint> balancePointList = createBalancePointList(balancePoint1, balancePoint2, balancePoint3);

	BalancePoint mergedBalancePoint = BalancePoint.mergeBalancePoints(balancePointList);

	assertEquals(1, mergedBalancePoint.getPosition().getX(), 0.001f);
	assertEquals(1, mergedBalancePoint.getPosition().getY(), 0.001f);
    }

    @Test
    public void mergeBalancePoints_OnPassTwoBalancePointsOfWeightZero_MergedPositionIsMean() {
	BalancePoint balancePoint1 = createBalancePoint(new Vector2(0, 0), new Weight());
	BalancePoint balancePoint2 = createBalancePoint(new Vector2(1, 1), new Weight());
	List<BalancePoint> balancePointList = createBalancePointList(balancePoint1, balancePoint2);

	BalancePoint mergedBalancePoint = BalancePoint.mergeBalancePoints(balancePointList);

	assertEquals(0.5f, mergedBalancePoint.getPosition().getX(), 0.001f);
	assertEquals(0.5f, mergedBalancePoint.getPosition().getY(), 0.001f);
    }

    @Test
    public void mergeBalancePoints_OnPassTwoBalancePointsOfWeightZero_MergedWeightIsZero() {
	BalancePoint balancePoint1 = createBalancePoint(new Vector2(0, 0), new Weight());
	BalancePoint balancePoint2 = createBalancePoint(new Vector2(1, 1), new Weight());
	List<BalancePoint> balancePointList = createBalancePointList(balancePoint1, balancePoint2);

	BalancePoint mergedBalancePoint = BalancePoint.mergeBalancePoints(balancePointList);

	assertTrue(mergedBalancePoint.getWeight().isZero());
    }

}
