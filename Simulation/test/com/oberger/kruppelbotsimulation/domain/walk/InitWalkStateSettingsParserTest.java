/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.walk;

import com.oberger.kruppelbotsimulation.domain.simulation.LegPosition;
import java.util.Arrays;
import java.util.Properties;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author ole
 */
public class InitWalkStateSettingsParserTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private InitWalkStateSettingsParser createParser() {
	return new InitWalkStateSettingsParser();
    }

    private Properties createFakeProperties(float periodInS, float repositionTimeInS, float angleStandYInDegrees, float stepSizeYInDegrees, int numberOfPolygonsForward, int numberOfPolygonsBackward, String legOrder, int polygonManipulationStep, float polygonManipulationMaxGradient) {
	Properties properties = new Properties();

	properties.setProperty(InitWalkStateSettingsParser.KEY_PERIOD_IN_S, Float.toString(periodInS));
	properties.setProperty(InitWalkStateSettingsParser.KEY_REPOSITION_TIME_IN_S, Float.toString(repositionTimeInS));
	properties.setProperty(InitWalkStateSettingsParser.KEY_ANGLE_STAND_Y_IN_DEGREES, Float.toString(angleStandYInDegrees));
	properties.setProperty(InitWalkStateSettingsParser.KEY_STEP_SIZE_Y_IN_DEGREES, Float.toString(stepSizeYInDegrees));
	properties.setProperty(InitWalkStateSettingsParser.KEY_NUMBER_OF_POLYGONS_FORWARD, Integer.toString(numberOfPolygonsForward));
	properties.setProperty(InitWalkStateSettingsParser.KEY_NUMBER_OF_POLYGONS_BACKWARD, Integer.toString(numberOfPolygonsBackward));
	properties.setProperty(InitWalkStateSettingsParser.KEY_LEG_ORDER, legOrder);
	properties.setProperty(InitWalkStateSettingsParser.KEY_POLYGON_MANIPULATION_STEP, Integer.toString(polygonManipulationStep));
	properties.setProperty(InitWalkStateSettingsParser.KEY_POLYGON_MAX_GRADIENT, Float.toString(polygonManipulationMaxGradient));

	return properties;
    }

    @Test
    public void parseProperties_OnPassNull_ThrowsIllegalArgumentException() {
	exception.expect(IllegalArgumentException.class);

	new InitWalkStateSettingsParser().parseProperties(null);
    }

    @Test
    public void fromProperties_WithLegitProperties_ParsesPeriodInS() {
	Properties fakeProperties = createFakeProperties(4, 2, 90, 120, 5, 2, "BR-FL-BL-FR", 1, 100f);
	InitWalkStateSettingsParser testee = createParser();

	InitWalkStateSettings parsed = testee.parseProperties(fakeProperties);

	assertEquals(4f, parsed.periodInS, 0.0001f);
    }

    @Test
    public void fromProperties_WithLegitProperties_ParsesRepositionTimeInS() {
	Properties fakeProperties = createFakeProperties(4, 2, 90, 120, 5, 2, "BR-FL-BL-FR", 1, 100f);
	InitWalkStateSettingsParser testee = createParser();

	InitWalkStateSettings parsed = testee.parseProperties(fakeProperties);

	assertEquals(2f, parsed.repositionTimeInS, 0.0001f);
    }

    @Test
    public void fromProperties_WithLegitProperties_ParsesAngleStandYInDegrees() {
	Properties fakeProperties = createFakeProperties(4, 2, 90, 120, 5, 2, "BR-FL-BL-FR", 1, 100f);
	InitWalkStateSettingsParser testee = createParser();

	InitWalkStateSettings parsed = testee.parseProperties(fakeProperties);

	assertEquals(90f, parsed.angleStandYInDegrees, 0.0001f);
    }

    @Test
    public void fromProperties_WithLegitProperties_ParsesStepSizeInDegrees() {
	Properties fakeProperties = createFakeProperties(4, 2, 90, 120, 5, 2, "BR-FL-BL-FR", 1, 100f);
	InitWalkStateSettingsParser testee = createParser();

	InitWalkStateSettings parsed = testee.parseProperties(fakeProperties);

	assertEquals(120f, parsed.stepSizeYInDegrees, 0.0001f);
    }

    @Test
    public void fromProperties_WithLegitProperties_ParsesNumberOfPolygonsForward() {
	Properties fakeProperties = createFakeProperties(4, 2, 90, 120, 5, 2, "BR-FL-BL-FR", 1, 100f);
	InitWalkStateSettingsParser testee = createParser();

	InitWalkStateSettings parsed = testee.parseProperties(fakeProperties);

	assertEquals(5, parsed.numberOfPolygonsForward);
    }

    @Test
    public void fromProperties_WithLegitProperties_ParsesNumberOfPolygonsBackward() {
	Properties fakeProperties = createFakeProperties(4, 2, 90, 120, 5, 2, "BR-FL-BL-FR", 1, 100f);
	InitWalkStateSettingsParser testee = createParser();

	InitWalkStateSettings parsed = testee.parseProperties(fakeProperties);

	assertEquals(2, parsed.numberOfPolygonsBackward);
    }

    @Test
    public void fromProperties_WithLegitProperties_ParsesLegOrder() {
	Properties fakeProperties = createFakeProperties(4, 2, 90, 120, 5, 2, "BR-FL-BL-FR", 1, 100f);
	InitWalkStateSettingsParser testee = createParser();

	InitWalkStateSettings parsed = testee.parseProperties(fakeProperties);

	assertEquals(Arrays.asList(LegPosition.BR, LegPosition.FL, LegPosition.BL, LegPosition.FR), parsed.legOrder.getOrder());
    }
    
    @Test
    public void fromProperties_WithLegitProperties_ParsesPolygonManipulationStep() {
	Properties fakeProperties = createFakeProperties(4, 2, 90, 120, 5, 2, "BR-FL-BL-FR", 1, 100f);
	InitWalkStateSettingsParser testee = createParser();

	InitWalkStateSettings parsed = testee.parseProperties(fakeProperties);

	assertEquals(1, parsed.polygonManipulationStep);
    }
    
    @Test
    public void fromProperties_WithLegitProperties_ParsesPolygonManipulationMaxGradient() {
	Properties fakeProperties = createFakeProperties(4, 2, 90, 120, 5, 2, "BR-FL-BL-FR", 1, 100f);
	InitWalkStateSettingsParser testee = createParser();

	InitWalkStateSettings parsed = testee.parseProperties(fakeProperties);

	assertEquals(100f, parsed.polygonManipulationMaxGradient, 0.0001);
    }

}
