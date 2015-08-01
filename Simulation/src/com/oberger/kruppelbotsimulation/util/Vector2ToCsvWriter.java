/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author oberger
 */
public class Vector2ToCsvWriter {

    public void write(List<IReadOnlyVector2> vectors, String filename) {
	try (FileWriter writer = new FileWriter(filename)) {
	    writer.append("x");
	    writer.append(',');
	    writer.append("y");
	    writer.append('\n');

	    for (IReadOnlyVector2 vector : vectors) {
		writer.append(Float.toString(vector.getX()));
		writer.append(",");
		writer.append(Float.toString(vector.getY()));
		writer.append('\n');
	    }

	    //generate whatever data you want
	    writer.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
