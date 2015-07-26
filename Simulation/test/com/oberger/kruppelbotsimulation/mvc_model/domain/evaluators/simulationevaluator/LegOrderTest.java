/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author ole
 */
public class LegOrderTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private LegOrder createLegOrder(List<LegPosition> order) {
        return new LegOrder(order);
    }

    @Test
    public void constructor_OnPassLegOrderNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createLegOrder(null);
    }

    @Test
    public void constructor_OnPassLegOrderEmpty_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createLegOrder(Collections.emptyList());
    }
    
    @Test
    public void constructor_OnPassLegOrderWithDuplicates_ThrowsIllegalArgumentException() {
        List<LegPosition> legOrderWithDuplicates = Arrays.asList(LegPosition.BL, LegPosition.BL, LegPosition.BR, LegPosition.FR);

        exception.expect(IllegalArgumentException.class);

        createLegOrder(legOrderWithDuplicates);
    }
    
    @Test
    public void constructor_OnPassLegOrderWithNull_ThrowsIllegalArgumentException() {
        List<LegPosition> legOrderWithDuplicates = Arrays.asList(null, LegPosition.BL, LegPosition.BR, LegPosition.FR);

        exception.expect(IllegalArgumentException.class);

        createLegOrder(legOrderWithDuplicates);
    }

}
