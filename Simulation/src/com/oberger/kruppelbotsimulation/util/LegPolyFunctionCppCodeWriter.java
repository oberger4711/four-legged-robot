/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.util;

import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.LegPosition;
import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.ConcatPolyFunction;
import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.ILegPolyFunctions;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author oberger
 */
public class LegPolyFunctionCppCodeWriter {

    public void write(ILegPolyFunctions legFunctions, String filename) {
	try (FileWriter writer = new FileWriter(filename + ".cpp")) {
	    writer.append("#include \"" + filename + ".h\"\n");
	    writer.append("\n");
	    writeGetLegFunction(legFunctions, LegPosition.BR, writer);
	    writeGetLegFunction(legFunctions, LegPosition.BL, writer);
	    writeGetLegFunction(legFunctions, LegPosition.FR, writer);
	    writeGetLegFunction(legFunctions, LegPosition.FL, writer);

	    writer.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private void writeGetLegFunction(ILegPolyFunctions legFunctions, LegPosition position, FileWriter writer) throws IOException {
	writer.append("LegPolyFunctionFactory::getPolyFunction" + position.toString().substring(0, 1).toUpperCase() + position.toString().substring(1, 2).toLowerCase() + "()\n");
	writer.append("{\n");
	writeGetLegFunctionBody(legFunctions, position, writer);
	writer.append("}\n");
    }

    private void writeGetLegFunctionBody(ILegPolyFunctions legFunctions, LegPosition position, FileWriter writer) throws IOException {
	ConcatPolyFunction function = legFunctions.getLegFunction(position);
	writer.append("\tVector2_t* polygons = new Vector2_t[" + function.getPolygons().size() + "];\n");
	List<IReadOnlyVector2> polygons = function.getPolygons();
	for (int i = 0; i < polygons.size(); i++) {
	    IReadOnlyVector2 polygon = polygons.get(i);
	    writer.append("\t\n");
	    writer.append("\tpolygons[" + i + "].x = " + (int) polygon.getX() + ";\n");
	    writer.append("\tpolygons[" + i + "].y = " + (int) polygon.getY() + ";\n");
	}
	writer.append("\t\n");
	writer.append("\treturn new WrappedPolyFunction(polygons, " + polygons.size() + ", " + (int) (function.getPeriod() * 1000) + ", " + (int) (function.getOffsetX() * 1000) + ");\n");
    }

}
