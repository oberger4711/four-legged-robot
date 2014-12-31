/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator.Model;
import com.oberger.kruppelbotsimulation.mvc_model.function.Interpolator;
import com.oberger.kruppelbotsimulation.mvc_model.function.LinearInterpolator;
import com.oberger.kruppelbotsimulation.mvc_model.function.PolyFunction;
import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.util.Arrays;
import java.util.LinkedList;
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
    public final static List<LegOrder.LegPosition> LEG_ORDER = Arrays.asList(LegOrder.LegPosition.BR, LegOrder.LegPosition.FL, LegOrder.LegPosition.BL, LegOrder.LegPosition.FR);

    public WalkState createInitWalkState() {
        OrderedLegMapping legMapping = createInitOrderedLegMapping();
        Interpolator interpolator = createInterpolator();
        PolyFunction legFunctionForward = createInitLegFunctionForward(interpolator);
        PolyFunction legFunctionBackward = createInitLegFunctionForward(interpolator);
        Model model = createInitModel();

        WalkState created = new WalkState(legMapping, legFunctionForward, legFunctionBackward, model);
        
        return created;
    }

    private Interpolator createInterpolator() {
        return new LinearInterpolator();
    }

    private PolyFunction createInitLegFunctionForward(Interpolator interpolator) {
        Vector2 forwardStart = new Vector2(0, ANGLE_Y_STAND_IN_DEGREES - (STEP_SIZE_Y_IN_DEGREES / 2));
        Vector2 forwardEnd = new Vector2(PERIOD_IN_S - REPOSITION_TIME_IN_S, ANGLE_Y_STAND_IN_DEGREES + (STEP_SIZE_Y_IN_DEGREES / 2));

        List<IReadOnlyVector2> forwardPolygons = new LinkedList<>(interpolator.getPolygons(forwardStart, forwardEnd, NUMBER_OF_POLYGONS_FORWARD));

        PolyFunction created = new PolyFunction(interpolator, forwardPolygons);

        return created;
    }

    private PolyFunction createInitLegFunctionBackward(Interpolator interpolator) {
        Vector2 backwardStart = new Vector2(PERIOD_IN_S - REPOSITION_TIME_IN_S, ANGLE_Y_STAND_IN_DEGREES + (STEP_SIZE_Y_IN_DEGREES / 2));
        Vector2 backwardEnd = new Vector2(PERIOD_IN_S, ANGLE_Y_STAND_IN_DEGREES - (STEP_SIZE_Y_IN_DEGREES / 2));

        List<IReadOnlyVector2> backwardPolygons = new LinkedList<>(interpolator.getPolygons(backwardStart, backwardEnd, NUMBER_OF_POLYGONS_BACKWARD));
        
        PolyFunction created = new PolyFunction(interpolator, backwardPolygons);

        return created;
    }
    
    private OrderedLegMapping createInitOrderedLegMapping() {
        return new OrderedLegMapping(new LegOrder(LEG_ORDER), PERIOD_IN_S);
    }
    
    private Model createInitModel() {
        return new Model();
    }
    
}
