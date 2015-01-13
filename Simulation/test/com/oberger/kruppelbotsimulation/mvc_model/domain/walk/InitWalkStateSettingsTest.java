/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.domain.walk.InitWalkStateSettings;
import java.util.Properties;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
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

        new InitWalkStateSettings(0f, 0f, 0f, 0f, 0, 0, null);
    }

}
