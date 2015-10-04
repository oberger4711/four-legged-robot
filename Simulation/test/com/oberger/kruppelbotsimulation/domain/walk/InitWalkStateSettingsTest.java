/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.walk;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author ole
 */
public class InitWalkStateSettingsTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void constructor_OnPassLegOrderNull_ThrowsIllegalArgumentException() {
	exception.expect(IllegalArgumentException.class);

	new InitWalkStateSettings(0f, 0f, 0f, 0f, 0, 0, null, 0, 0f);
    }

}
