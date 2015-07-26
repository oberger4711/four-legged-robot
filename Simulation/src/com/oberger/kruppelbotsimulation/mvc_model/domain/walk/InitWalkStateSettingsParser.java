/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.LegOrder;
import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.LegPosition;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author ole
 */
public class InitWalkStateSettingsParser {

    public final static String KEY_PERIOD_IN_S = "period_in_s";
    public final static String KEY_REPOSITION_TIME_IN_S = "reposition_time_in_s";
    public final static String KEY_ANGLE_STAND_Y_IN_DEGREES = "angle_stand_y_in_degrees";
    public final static String KEY_STEP_SIZE_Y_IN_DEGREES = "step_size_y_in_degrees";
    public final static String KEY_NUMBER_OF_POLYGONS_FORWARD = "number_of_polygons_forward";
    public final static String KEY_NUMBER_OF_POLYGONS_BACKWARD = "number_of_polygons_backward";
    public final static String KEY_LEG_ORDER = "leg_order";

    public InitWalkStateSettingsParser() {

    }

    public InitWalkStateSettings parseProperties(Properties properties) {
        if (properties == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        try {
            return new InitWalkStateSettings(parsePeriodInS(properties), parseRepositionTimeInS(properties), parseAngleStandYInDegrees(properties), parseStepSizeYInDegrees(properties), parseNumberOfPolygonsForward(properties), parseNumberOfPolygonsBackward(properties), parseLegOrder(properties));
        }
        catch (IllegalArgumentException e) {
            throw e;
        }
    }
    
    private float parsePeriodInS(Properties properties) throws NumberFormatException {
        return Float.parseFloat(properties.getProperty(KEY_PERIOD_IN_S));
    }
    
    private float parseRepositionTimeInS(Properties properties) throws NumberFormatException {
        return Float.parseFloat(properties.getProperty(KEY_REPOSITION_TIME_IN_S));
    }
    
    private float parseAngleStandYInDegrees(Properties properties) throws NumberFormatException {
        return Float.parseFloat(properties.getProperty(KEY_ANGLE_STAND_Y_IN_DEGREES));
    }
    
    private float parseStepSizeYInDegrees(Properties properties) throws NumberFormatException {
        return Float.parseFloat(properties.getProperty(KEY_STEP_SIZE_Y_IN_DEGREES));
    }
    
    private int parseNumberOfPolygonsForward(Properties properties) throws NumberFormatException {
        return Integer.parseInt(properties.getProperty(KEY_NUMBER_OF_POLYGONS_FORWARD));
    }
    
    private int parseNumberOfPolygonsBackward(Properties properties) throws NumberFormatException {
        return Integer.parseInt(properties.getProperty(KEY_NUMBER_OF_POLYGONS_BACKWARD));
    }
    
    private LegOrder parseLegOrder(Properties properties) throws IllegalArgumentException {
        List<LegPosition> parsed = new LinkedList<>();
        
        String legOrderString = properties.getProperty(KEY_LEG_ORDER);
        String[] legPositionsStrings = legOrderString.split("-");
        for (String legPositionString : legPositionsStrings) {
            parsed.add(parseLegPosition(legPositionString));
        }
        
        return new LegOrder(parsed);
    }
    
    private LegPosition parseLegPosition(String value) {
        for (LegPosition pos : LegPosition.values()) {
            if (pos.toString().toUpperCase().equals(value.toUpperCase())) {
                return pos;
            }
        }
        throw new IllegalArgumentException("Invalid leg position. Must be one of " + Arrays.toString(LegPosition.values()) + " but was " + value + ".");
    }
    
}
