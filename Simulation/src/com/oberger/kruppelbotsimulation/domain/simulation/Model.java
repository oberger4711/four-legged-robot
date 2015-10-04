package com.oberger.kruppelbotsimulation.domain.simulation;

import com.oberger.kruppelbotsimulation.model.SimJoint;
import com.oberger.kruppelbotsimulation.model.SimMass;
import com.oberger.kruppelbotsimulation.util.Rotation;
import com.oberger.kruppelbotsimulation.util.Vector2;
import com.oberger.kruppelbotsimulation.util.Weight;
import java.io.Serializable;

public class Model implements Serializable {

    private final static float PLATFORM_WIDTH = 2;
    private final static float PLATFORM_HEIGHT = 4;
    private final static float LEG_LENGTH = 1f;
    private final static float LEG_WEIGHT = 0.5f;

    private final SimJoint root;
    private final SimJoint servoBR;
    private final SimMass legBR;
    private final SimJoint servoBL;
    private final SimMass legBL;
    private final SimJoint servoFR;
    private final SimMass legFR;
    private final SimJoint servoFL;
    private final SimMass legFL;

    public Model() {
	root = new SimJoint(new Vector2(0, 0), new Rotation(0, true));

	servoBR = new SimJoint(new Vector2(PLATFORM_WIDTH / 2, -PLATFORM_HEIGHT / 2f), new Rotation(0, true));
	legBR = new SimMass(new Vector2(0, LEG_LENGTH), new Weight(LEG_WEIGHT));
	servoBR.addChild(legBR);
	root.addChild(servoBR);

	servoBL = new SimJoint(new Vector2(-PLATFORM_WIDTH / 2, -PLATFORM_HEIGHT / 2f), new Rotation(0, true));
	legBL = new SimMass(new Vector2(0, LEG_LENGTH), new Weight(LEG_WEIGHT));
	servoBL.addChild(legBL);
	root.addChild(servoBL);

	servoFR = new SimJoint(new Vector2(PLATFORM_WIDTH / 2, PLATFORM_HEIGHT / 2f), new Rotation(0, true));
	legFR = new SimMass(new Vector2(0, LEG_LENGTH), new Weight(LEG_WEIGHT));
	servoFR.addChild(legFR);
	root.addChild(servoFR);

	servoFL = new SimJoint(new Vector2(-PLATFORM_WIDTH / 2, PLATFORM_HEIGHT / 2f), new Rotation(0, true));
	legFL = new SimMass(new Vector2(0, LEG_LENGTH), new Weight(LEG_WEIGHT));
	servoFL.addChild(legFL);
	root.addChild(servoFL);
    }

    public SimJoint getRoot() {
	return root;
    }

    public SimJoint getServoBL() {
	return servoBL;
    }

    public SimJoint getServoBR() {
	return servoBR;
    }

    public SimJoint getServoFL() {
	return servoFL;
    }

    public SimJoint getServoFR() {
	return servoFR;
    }

}
