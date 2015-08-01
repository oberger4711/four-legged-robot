package com.oberger.kruppelbotsimulation.domain.walk;

import com.oberger.kruppelbotsimulation.localsearch.HillClimbing;
import com.oberger.kruppelbotsimulation.localsearch.LocalSearchAlgorithm;
import com.oberger.kruppelbotsimulation.localsearch.State;
import com.oberger.kruppelbotsimulation.localsearch.evaluator.IEvaluator;
import com.oberger.kruppelbotsimulation.localsearch.exitcriterium.LocalMaximumExitCriterium;
import com.oberger.kruppelbotsimulation.localsearch.manipulator.IManipulator;
import com.oberger.kruppelbotsimulation.localsearch.manipulator.ManipulatorGroup;
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
		is.close();
	    } catch (IOException e) {
	    }
	}
	InitWalkStateSettings settings = new InitWalkStateSettingsParser().parseProperties(properties);
	WalkState initInnerState = new InitWalkStateFactory(settings).createInitWalkState();
	IEvaluator<WalkState> evaluator = new EvaluatorFactory(settings).createEvaluator();
	IManipulator<WalkState> manipulator = new ManipulatorFactory(settings).createManipulator();
	State<WalkState> initState = new State<>(initInnerState, evaluator, manipulator);
	LocalSearchAlgorithm<WalkState> localSearchAlgo = new HillClimbing<>();
	
	State<WalkState> finalState = localSearchAlgo.run(initState, new LocalMaximumExitCriterium());

	System.out.println("Done.");
    }

}
