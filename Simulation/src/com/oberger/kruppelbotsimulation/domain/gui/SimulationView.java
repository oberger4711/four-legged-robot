/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.gui;

import com.oberger.kruppelbotsimulation.domain.simulation.Simulation;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 *
 * @author oberger
 */
public class SimulationView extends JComponent {
    
    private Simulation simulation;
    private float t;

    @Override
    protected void paintComponent(Graphics g) {
	if (isOpaque()) {
	    g.setColor(getBackground());
	}
	else {
	    g.setColor(Color.white);
	}
	g.fillRect(0, 0, getWidth(), getHeight());
	
	// Draw test rectangle.
	Graphics2D g2d = (Graphics2D)g.create();
	g2d.setColor(Color.black);
	g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	g2d.dispose();
    }

    public void setSimulation(Simulation simulation) {
	this.simulation = simulation;
	// TODO: check t
	// TODO: redraw
    }

    public void setT(float t) {
	this.t = t;
	// TODO: redraw
    }
    
}
