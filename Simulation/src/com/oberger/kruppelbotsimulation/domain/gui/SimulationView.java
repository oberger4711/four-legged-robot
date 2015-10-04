/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.gui;

import com.oberger.kruppelbotsimulation.domain.simulation.Simulation;
import com.oberger.kruppelbotsimulation.model.IParentSimObject;
import com.oberger.kruppelbotsimulation.model.ISimObjectVisitor;
import com.oberger.kruppelbotsimulation.model.SimJoint;
import com.oberger.kruppelbotsimulation.model.SimMass;
import com.oberger.kruppelbotsimulation.model.SimObject;
import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JComponent;

/**
 *
 * @author oberger
 */
public class SimulationView extends JComponent {
    
    private Simulation simulationOrNull;
    private float t;
    private float zoomFactor;
    
    public SimulationView() {
	t = 0;
	zoomFactor = 10;
    }

    @Override
    protected void paintComponent(Graphics g) {
	if (isOpaque()) {
	    g.setColor(getBackground());
	}
	else {
	    g.setColor(Color.white);
	}
	g.fillRect(0, 0, getWidth(), getHeight());
	
	// Draw border.
	Graphics2D g2d = (Graphics2D)g.create();
	g2d.setColor(Color.black);
	g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	
	if (simulationOrNull != null) {
	    simulationOrNull.getModel().getRoot().accept(new ISimObjectVisitor() {

		@Override
		public void visit(SimJoint joint) {
		    visitGeneral(joint);
		    // Visit the chids recursively.
		    List<SimObject> childs = joint.getChilds();
		    for (SimObject child : childs) {
			child.accept(this);
		    }
		}

		@Override
		public void visit(SimMass mass) {
		    visitGeneral(mass);
		}
		
		private void visitGeneral(SimObject object) {
		    // Draw SimJoint.
		    Vector2 canvasPosition = mapGlobalPositionToCanvas(object.getGlobalPosition());
		    // Connection to parent if any.
		    IParentSimObject parentOrNull = object.getParentOrNull();
		    if (parentOrNull != null) {
			IReadOnlyVector2 parentCanvasPosition = mapGlobalPositionToCanvas(parentOrNull.getGlobalPosition());
			g2d.drawLine((int)parentCanvasPosition.getX() , (int)parentCanvasPosition.getY(), (int)canvasPosition.getX(), (int)canvasPosition.getY());
		    }
		}
	    });
	}
	g2d.dispose();
    }

    private Vector2 mapGlobalPositionToCanvas(IReadOnlyVector2 globalPosition) {
	return new Vector2((getWidth() / 2f) + globalPosition.getX() * zoomFactor, (getHeight() / 2f) + globalPosition.getY() * zoomFactor);
    }
    
    public void setSimulation(Simulation simulation) {
	this.simulationOrNull = simulation;
	// TODO: check t
	revalidate();
	repaint();
    }

    public void setT(float t) {
	this.t = t;
	// TODO: redraw
    }
    
}
