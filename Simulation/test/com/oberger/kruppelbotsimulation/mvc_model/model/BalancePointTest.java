/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.model;

import com.oberger.kruppelbotsimulation.util.Vector2;
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

    private static BalancePoint createBalancePoint(Vector2 offsetPosition, float offsetRotation, boolean counterClockwise, float weight) {
        return new BalancePoint(offsetPosition, offsetRotation, counterClockwise, weight);
    }
    
    private static List<BalancePoint> createBalancePointList(BalancePoint... balancePoints) {
        return Arrays.asList(balancePoints);
    }
    
    @Test
    public void constructor_OnPassNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        
        createBalancePoint(null, 0, true, 0f);
    }

    @Test
    public void constructor_OnCall_CopiesVectorParameter() {
        Vector2 offsetPosition = new Vector2(1, 2);

        BalancePoint balancePoint = createBalancePoint(offsetPosition, 0, true, 0);
        offsetPosition.setX(2);

        assertFalse(Math.abs(2f - balancePoint.getOffsetPosition().getX()) < 0.001f);
        assertFalse(Math.abs(2f - balancePoint.getGlobalPosition().getX()) < 0.001f);
    }

    @Test
    public void constructor_ByDefault_GlobalPositionIsOffsetPosition() {
        BalancePoint balancePoint = createBalancePoint(new Vector2(1, 2), 0, true, 0);

        Vector2 globalPosition = balancePoint.getGlobalPosition();

        assertTrue(globalPosition.equals(new Vector2(1, 2)));
    }

    @Test
    public void constructor_ByDefault_GloblaRotationIsOffsetPosition() {
        BalancePoint balancePoint = createBalancePoint(new Vector2(1, 2), 12, true, 0);

        float globalRotation = balancePoint.getGlobalRotation();

        assertEquals(12, globalRotation, 0.001f);
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
    public void mergeBalancePoints_OnPassOneBalancePoint_MergedOffsetPositionIsGivenOffsetPosition() {
        BalancePoint balancePoint = createBalancePoint(new Vector2(1, 2), 12, true, 1);
        List<BalancePoint> balancePointList = createBalancePointList(balancePoint);

        BalancePoint mergedBalancePoint = BalancePoint.mergeBalancePoints(balancePointList);
        
        assertEquals(balancePoint.getOffsetPosition().getX(), mergedBalancePoint.getOffsetPosition().getX(), 0.001f);
        assertEquals(balancePoint.getOffsetPosition().getY(), mergedBalancePoint.getOffsetPosition().getY(), 0.001f);
    }
    
    @Test
    public void mergeBalancePoints_OnPassTwoBalancePointsOfSameWeight_MergedOffsetPositionIsMeanOffsetPosition() {
        BalancePoint balancePoint1 = createBalancePoint(new Vector2(0, 1), 0, true, 1);
        BalancePoint balancePoint2 = createBalancePoint(new Vector2(1, 2), 0, true, 1);
        List<BalancePoint> balancePointList = createBalancePointList(balancePoint1, balancePoint2);
        
        BalancePoint mergedBalancePoint = BalancePoint.mergeBalancePoints(balancePointList);
        
        assertEquals(0.5f, mergedBalancePoint.getOffsetPosition().getX(), 0.001f);
        assertEquals(1.5f, mergedBalancePoint.getOffsetPosition().getY(), 0.001f);
    }
    
    @Test
    public void mergeBalancePoints_OnPassTwoBalancePointsOfDifferentWeight_MergedWeightIsWeightSum() {
        BalancePoint balancePoint1 = createBalancePoint(new Vector2(0, 0), 0, true, 1);
        BalancePoint balancePoint2 = createBalancePoint(new Vector2(1, 1), 0, true, 2);
        List<BalancePoint> balancePointList = createBalancePointList(balancePoint1, balancePoint2);
        
        BalancePoint mergedBalancePoint = BalancePoint.mergeBalancePoints(balancePointList);
        
        assertEquals(3, mergedBalancePoint.getWeight(), 0.001f);
    }
    
}
