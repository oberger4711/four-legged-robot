/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.LegOrder;
import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.Model;
import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.legpolyfunctions.OrderedLegPolyFunctions;
import com.oberger.kruppelbotsimulation.mvc_model.function.Interpolator;
import com.oberger.kruppelbotsimulation.mvc_model.function.LinearInterpolator;
import com.oberger.kruppelbotsimulation.mvc_model.function.PolyFunction;
import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ole
 */
public class InitWalkStateFactory {

    private InitWalkStateSettings initSettings = null;

    public InitWalkStateFactory(InitWalkStateSettings initSettings) {
        this.initSettings = initSettings;
    }

    public WalkState createInitWalkState() {
        Interpolator interpolator = createInterpolator();
        OrderedLegPolyFunctions legFunctions = createInitOrderedLegFunctions(interpolator);
        List<IReadOnlyVector2> legFunctionPolygonsBackward = createInitLegFunctionPolygonsBackward(interpolator);
        List<IReadOnlyVector2> legFunctionPolygonsForward = createInitLegFunctionPolygonsForward(interpolator);

        Model model = createInitModel();

        WalkState created = new WalkState(legFunctions, model);

        return created;
    }

    private Interpolator createInterpolator() {
        return new LinearInterpolator();
    }

    private List<IReadOnlyVector2> createInitLegFunctionPolygons(Interpolator interpolator) {
        List<IReadOnlyVector2> polygons = new LinkedList<>();

        polygons.addAll(createInitLegFunctionPolygonsBackward(interpolator));
        polygons.addAll(createInitLegFunctionPolygonsForward(createInterpolator()));

        return polygons;
    }

    private List<IReadOnlyVector2> createInitLegFunctionPolygonsForward(Interpolator interpolator) {
        Vector2 forwardStart = new Vector2(0, initSettings.angleStandYInDegrees - (initSettings.stepSizeYInDegrees / 2));
        Vector2 forwardEnd = new Vector2(initSettings.periodInS - initSettings.repositionTimeInS, initSettings.angleStandYInDegrees + (initSettings.stepSizeYInDegrees / 2));

        List<IReadOnlyVector2> polygons = new LinkedList<>(interpolator.getPolygons(forwardStart, forwardEnd, initSettings.numberOfPolygonsForward));

        return polygons;
    }

    private List<IReadOnlyVector2> createInitLegFunctionPolygonsBackward(Interpolator interpolator) {
        Vector2 backwardStart = new Vector2(initSettings.periodInS - initSettings.repositionTimeInS, initSettings.angleStandYInDegrees + (initSettings.stepSizeYInDegrees / 2));
        Vector2 backwardEnd = new Vector2(initSettings.periodInS, initSettings.angleStandYInDegrees - (initSettings.stepSizeYInDegrees / 2));

        List<IReadOnlyVector2> polygons = new LinkedList<>(interpolator.getPolygons(backwardStart, backwardEnd, initSettings.numberOfPolygonsBackward));

        return polygons;
    }

    private PolyFunction createInitLegFunction(Interpolator interpolator, List<IReadOnlyVector2> polygons) {
        return new PolyFunction(interpolator, polygons);
    }

    private OrderedLegPolyFunctions createInitOrderedLegFunctions(Interpolator interpolator) {
        
        return new OrderedLegPolyFunctions(createInitLegFunction(interpolator, createInitLegFunctionPolygons(interpolator)), new LegOrder(initSettings.legOrder), initSettings.periodInS);
    }

    private Model createInitModel() {
        return new Model();
    }

}
