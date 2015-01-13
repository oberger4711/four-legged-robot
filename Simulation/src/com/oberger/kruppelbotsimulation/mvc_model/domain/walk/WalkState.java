/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator.Model;
import com.oberger.kruppelbotsimulation.mvc_model.function.IPolyFunction;
import com.oberger.kruppelbotsimulation.mvc_model.function.LinearInterpolator;
import com.oberger.kruppelbotsimulation.mvc_model.function.PolyFunction;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.IImmutableInnerState;
import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ole
 */
public class WalkState implements IImmutableInnerState {

    private OrderedLegMapping legMapping = null;
    private IPolyFunction legFunction = null;
    private List<IReadOnlyVector2> polygonsForward = null;
    private List<IReadOnlyVector2> polygonsBackward = null;
    private Model model = null;

    public WalkState(OrderedLegMapping legMapping, List<IReadOnlyVector2> polygonsBackward, List<IReadOnlyVector2> polygonsForward, Model model) {
        if (legMapping == null || polygonsForward == null || polygonsBackward == null || model == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        this.legMapping = legMapping;
        this.polygonsForward = polygonsForward;
        this.polygonsBackward = polygonsBackward;
        this.model = model;
        this.legFunction = new PolyFunction(new LinearInterpolator(), unitePolygons(polygonsBackward, polygonsForward));
    }
    
    private List<IReadOnlyVector2> unitePolygons(List<IReadOnlyVector2> backward, List<IReadOnlyVector2> forward) {
        List<IReadOnlyVector2> unionPolygons = new LinkedList<>();
        
        unionPolygons.addAll(backward);
        unionPolygons.addAll(forward);
        
        return unionPolygons;
    }
    
    public OrderedLegMapping getLegMapping() {
        return legMapping;
    }

    public List<IReadOnlyVector2> getPolygonsForward() {
        return polygonsForward;
    }

    public List<IReadOnlyVector2> getPolygonsBackward() {
        return polygonsBackward;
    }

    public IPolyFunction getLegFunction() {
        return legFunction;
    }
    
    public Model getModel() {
        return model;
    }

}
