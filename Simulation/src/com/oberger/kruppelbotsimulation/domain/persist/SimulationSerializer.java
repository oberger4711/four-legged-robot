/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.persist;

import com.oberger.kruppelbotsimulation.domain.simulation.Simulation;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author oberger
 */
public class SimulationSerializer {

    public void serialize(Simulation simulation, String fileName) throws IOException {
	try (
		OutputStream file = new FileOutputStream(fileName);
		OutputStream buffer = new BufferedOutputStream(file);
		ObjectOutput output = new ObjectOutputStream(buffer);) {
	    output.writeObject(simulation);
	}
    }

    public Simulation deserialize(String fileName) throws IOException, ClassNotFoundException {
	try (
		InputStream file = new FileInputStream(fileName);
		InputStream buffer = new BufferedInputStream(file);
		ObjectInput input = new ObjectInputStream(buffer);) {
	    return (Simulation) input.readObject();
	}
    }

}
