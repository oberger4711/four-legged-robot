/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.function.Interpolator;
import com.oberger.kruppelbotsimulation.mvc_model.function.LinearInterpolator;
import com.oberger.kruppelbotsimulation.mvc_model.function.PolyFunction;
import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ole
 */
public class InitWalkStateFactory {

    public final static float PERIOD_IN_S = 3.0f;
    public final static float REPOSITION_TIME_IN_S = PERIOD_IN_S / 7;
    public final static float ANGLE_Y_STAND_IN_DEGREES = 90;
    public final static float STEP_SIZE_Y_IN_DEGREES = 120;
    public final static int NUMBER_OF_POLYGONS_FORWARD = 32;
    public final static int NUMBER_OF_POLYGONS_BACKWARD = 8;
    public final static int NUMBER_OF_POLYGONS_TOTAL = NUMBER_OF_POLYGONS_FORWARD + NUMBER_OF_POLYGONS_BACKWARD;
    
    public WalkState createInitWalkState() {
        PolyFunction legFunction = createInitLegFunctionForward();
        
//        WalkState walkState = new WalkState(, legFunction, null)
        return null;
    }

    private static Interpolator createInterpolator() {
        return new LinearInterpolator();
    }

    private static PolyFunction createInitLegFunctionForward() {
        Vector2 forwardStart = new Vector2(0, ANGLE_Y_STAND_IN_DEGREES - (STEP_SIZE_Y_IN_DEGREES / 2));
        Vector2 backwardStart = new Vector2(PERIOD_IN_S - REPOSITION_TIME_IN_S, ANGLE_Y_STAND_IN_DEGREES + (STEP_SIZE_Y_IN_DEGREES / 2));
        Vector2 backwardEnd = new Vector2(PERIOD_IN_S, ANGLE_Y_STAND_IN_DEGREES - (STEP_SIZE_Y_IN_DEGREES / 2));

        Interpolator interpolator = createInterpolator();
        List<Vector2> forwardPolygons = interpolator.getPolygons(forwardStart, backwardStart, NUMBER_OF_POLYGONS_FORWARD);
        List<Vector2> backwardPolygons = interpolator.getPolygons(backwardStart, backwardEnd, NUMBER_OF_POLYGONS_BACKWARD);
        List<IReadOnlyVector2> allPolygons = new ArrayList<>(forwardPolygons);
        allPolygons.addAll(backwardPolygons);
        
        PolyFunction created = new PolyFunction(new LinearInterpolator(), allPolygons);

        return created;
    }

}
