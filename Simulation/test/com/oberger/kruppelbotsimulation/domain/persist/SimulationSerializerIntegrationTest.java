/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.persist;

import com.oberger.kruppelbotsimulation.domain.simulation.Model;
import com.oberger.kruppelbotsimulation.domain.simulation.Simulation;
import com.oberger.kruppelbotsimulation.domain.simulation.legpolyfunctions.LegPolyFunctionFactory;
import com.oberger.kruppelbotsimulation.domain.simulation.legpolyfunctions.LegPolyFunctions;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author oberger
 */
public class SimulationSerializerIntegrationTest {
    
    @Test
    public void deserialize_Serialized_DoesNotThrowException() throws IOException, ClassNotFoundException {
	SimulationSerializer testee = new SimulationSerializer();
	Model model = Mockito.mock(Model.class);
	LegPolyFunctions functions = Mockito.mock(LegPolyFunctions.class);
	Simulation simulation = new Simulation(functions, model);
	
	String filename = "serialized_sim_int_test";
	testee.serialize(simulation, filename);
	
	Simulation deserialized = testee.deserialize(filename);
    }
    
}
