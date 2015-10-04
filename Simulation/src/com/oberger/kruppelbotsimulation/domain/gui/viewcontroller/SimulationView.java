/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.gui.viewcontroller;

import com.oberger.kruppelbotsimulation.domain.gui.model.MvcModel;
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
import java.awt.Stroke;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComponent;

/**
 *
 * @author oberger
 */
public class SimulationView extends JComponent implements Observer {
    
    private final static int RADIUS_BALANCE_POINT = 7;
    
    private MvcModel model;
    
    public SimulationView() {
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
	
	if (model != null && model.getSimulationOrNull() != null) {
	    SimJoint root = model.getSimulationOrNull().getModel().getRoot();
	    root.accept(new ISimObjectVisitor() {

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
		    g2d.setColor(Color.black);
		    IParentSimObject parentOrNull = object.getParentOrNull();
		    if (parentOrNull != null) {
			IReadOnlyVector2 parentCanvasPosition = mapGlobalPositionToCanvas(parentOrNull.getGlobalPosition());
			g2d.drawLine((int)parentCanvasPosition.getX() , (int)parentCanvasPosition.getY(), (int)canvasPosition.getX(), (int)canvasPosition.getY());
		    }
		}
	    });
	    // Draw balance point.
	    g2d.setColor(Color.red);
	    Vector2 canvasBalancePoint = mapGlobalPositionToCanvas(root.getGlobalBalancePoint().getPosition());
	    g2d.fillOval((int)canvasBalancePoint.getX() - RADIUS_BALANCE_POINT / 2, (int)canvasBalancePoint.getY() - RADIUS_BALANCE_POINT / 2, RADIUS_BALANCE_POINT, RADIUS_BALANCE_POINT);
	}
	g2d.dispose();
    }

    private Vector2 mapGlobalPositionToCanvas(IReadOnlyVector2 globalPosition) {
	return new Vector2((getWidth() / 2f) + globalPosition.getX() * model.getScaleFactor(), (getHeight() / 2f) + globalPosition.getY() * model.getScaleFactor());
    }

    @Override
    public void update(Observable o, Object o1) {
	revalidate();
	repaint();
    }

    public void setModel(MvcModel model) {
	if (this.model != null) {
	    this.model.deleteObserver(this);
	}
	this.model = model;
	model.addObserver(this);
	update(model, null);
    }
    
}
