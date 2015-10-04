package com.oberger.kruppelbotsimulation.domain.walk;

import com.oberger.kruppelbotsimulation.domain.gui.GuiWrapper;
import com.oberger.kruppelbotsimulation.localsearch.HillClimbing;
import com.oberger.kruppelbotsimulation.localsearch.LocalSearchAlgorithm;
import com.oberger.kruppelbotsimulation.localsearch.State;
import com.oberger.kruppelbotsimulation.localsearch.evaluator.IEvaluator;
import com.oberger.kruppelbotsimulation.localsearch.exitcriterium.LocalMaximumExitCriterium;
import com.oberger.kruppelbotsimulation.localsearch.manipulator.IManipulator;
import com.oberger.kruppelbotsimulation.domain.persist.LegPolyFunctionsCsvWriter;
import com.oberger.kruppelbotsimulation.domain.simulation.Simulation;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WalkOptimization implements Runnable {

    public static void main(String[] args) {
	new WalkOptimization().run();
    }

    @Override
    public void run() {
	Properties properties = new Properties();
	InputStream is = getClass().getResourceAsStream("WalkOptimizationSettings.properties");
	try {
	    properties.load(is);
	} catch (IOException e) {
	    System.out.println("Error reading Properties: " + e.getMessage());
	    return;
	} finally {
	    try {
		if (is != null) {
		    is.close();
		}
	    } catch (IOException e) {
	    }
	}
	InitWalkStateSettings settings = new InitWalkStateSettingsParser().parseProperties(properties);
	WalkState initInnerState = new InitWalkStateFactory(settings).createInitWalkState();
	IEvaluator<WalkState> evaluator = new EvaluatorFactory(settings).createEvaluator();
	IManipulator<WalkState> manipulator = new ManipulatorFactory(settings).createManipulator();
	State<WalkState> initState = new State<>(initInnerState, evaluator, manipulator);
	LocalSearchAlgorithm<WalkState> localSearchAlgo = new HillClimbing<>();
	System.out.println("Initial Score : " + initState.getScore());

	State<WalkState> finalState = localSearchAlgo.run(initState, new LocalMaximumExitCriterium());
	System.out.println("Final Score : " + finalState.getScore());
	
	System.out.println("Writing CSV File...");
	new LegPolyFunctionsCsvWriter().write(finalState.getInnerState().getLegFunctions(), "out.csv");
	
	System.out.println("Starting GUI...");
	new GuiWrapper().show(new Simulation(finalState.getInnerState().getLegFunctions(), finalState.getInnerState().getModel()));

	System.out.println("Done.");
    }

}
