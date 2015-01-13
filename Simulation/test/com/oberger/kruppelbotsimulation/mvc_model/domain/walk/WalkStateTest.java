/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.domain.simulationevaluator.Model;
import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author ole
 */
@RunWith(MockitoJUnitRunner.class)
public class WalkStateTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private WalkState createWalkState(OrderedLegMapping legMapping, List<IReadOnlyVector2> polygonsBackward, List<IReadOnlyVector2> polygonsForward, Model model) {
        return new WalkState(legMapping, polygonsBackward, polygonsForward, model);
    }

    private OrderedLegMapping createDummyLegMapping() {
        return Mockito.mock(OrderedLegMapping.class);
    }

    private List<IReadOnlyVector2> createDummyPolygonsForward() {
        return Arrays.asList(new Vector2(0, 0), new Vector2(1, 1));
    }

    private List<IReadOnlyVector2> createDummyPolygonsBackward() {
        return Arrays.asList(new Vector2(1, 1), new Vector2(2, 0));
    }

    private Model createDummyModel() {
        return Mockito.mock(Model.class);
    }

    @Test
    public void constructor_OnPassLegMappingNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createWalkState(null, createDummyPolygonsBackward(), createDummyPolygonsForward(), createDummyModel());
    }

    @Test
    public void constructor_OnPassPolygonsForwardNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createWalkState(createDummyLegMapping(), createDummyPolygonsBackward(), null, createDummyModel());
    }

    @Test
    public void constructor_OnPassPolygonsBackwardNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createWalkState(createDummyLegMapping(), null, createDummyPolygonsForward(), createDummyModel());
    }

    @Test
    public void constructor_OnPassModelNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createWalkState(createDummyLegMapping(), createDummyPolygonsBackward(), createDummyPolygonsForward(), null);
    }

    @Test
    public void getLegPolygons_OnInjectedLegitPolygons_ContainsAllPolygons() {
        List<IReadOnlyVector2> polygonsBackward = Arrays.asList(new Vector2(1, 1), new Vector2(2, 0));
        List<IReadOnlyVector2> polygonsForward = Arrays.asList(new Vector2(0, 0), new Vector2(1, 1));
        List<IReadOnlyVector2> polygonsUnited = new LinkedList<>();
        polygonsUnited.addAll(polygonsBackward);
        polygonsUnited.addAll(polygonsForward);

        WalkState testee = createWalkState(createDummyLegMapping(), polygonsBackward, polygonsForward, createDummyModel());

        assertEquals(polygonsUnited, testee.getLegFunction().getPolygons());
    }

}
