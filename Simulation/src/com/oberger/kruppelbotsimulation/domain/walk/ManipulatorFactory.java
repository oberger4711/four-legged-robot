/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.walk;

import com.oberger.kruppelbotsimulation.domain.manipulators.ConcatPolyFunctionManipulator;
import com.oberger.kruppelbotsimulation.domain.manipulators.LegPolyFunctionsManipulator;
import com.oberger.kruppelbotsimulation.domain.manipulators.PartialPolyFunctionGradientConstraint;
import com.oberger.kruppelbotsimulation.domain.manipulators.PartialPolyFunctionManipulator;
import com.oberger.kruppelbotsimulation.domain.simulation.legpolyfunctions.ConcatPolyFunction;
import com.oberger.kruppelbotsimulation.domain.simulation.legpolyfunctions.ILegPolyFunctions;
import com.oberger.kruppelbotsimulation.domain.simulation.legpolyfunctions.PartialPolyFunction;
import com.oberger.kruppelbotsimulation.localsearch.manipulator.ConstrainedManipulator;
import com.oberger.kruppelbotsimulation.localsearch.manipulator.IConstraint;
import com.oberger.kruppelbotsimulation.localsearch.manipulator.IManipulator;
import com.oberger.kruppelbotsimulation.localsearch.manipulator.ManipulatorGroup;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oberger
 */
public class ManipulatorFactory {
    
    private InitWalkStateSettings settings = null;
    
    public ManipulatorFactory(InitWalkStateSettings settings) {
	this.settings = settings;
    }
    
    public IManipulator<WalkState> createManipulator() {
	return new WalkStateManipulatorAdapter(createLegPolyFunctionsManipulator());
    }
    
    private IManipulator<ILegPolyFunctions> createLegPolyFunctionsManipulator() {
	return new LegPolyFunctionsManipulator(createConcatPolyFunctionManipulator());
    }
    
    private IManipulator<ConcatPolyFunction> createConcatPolyFunctionManipulator() {
	return new ConcatPolyFunctionManipulator(createPartialPolyFunctionManipulator());
    }
    
    private IManipulator<PartialPolyFunction> createPartialPolyFunctionManipulator() {
	List<IManipulator<PartialPolyFunction>> manipulators = new ArrayList<>();
	List<IConstraint<PartialPolyFunction>> constraints = new ArrayList<>();
	constraints.add(new PartialPolyFunctionGradientConstraint(settings.polygonManipulationMaxGradient));
	manipulators.add(new ConstrainedManipulator<>(new PartialPolyFunctionManipulator(settings.polygonManipulationStep), constraints));
	manipulators.add(new ConstrainedManipulator<>(new PartialPolyFunctionManipulator(-settings.polygonManipulationStep), constraints));
	
	return new ManipulatorGroup<>(manipulators);
    }
    
}
