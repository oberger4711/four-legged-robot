/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain;

import com.oberger.kruppelbotsimulation.mvc_model.function.IPolyFunction;
import com.oberger.kruppelbotsimulation.mvc_model.model.SimJoint;
import com.oberger.kruppelbotsimulation.util.Rotation;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author ole
 */
@RunWith(MockitoJUnitRunner.class)
public class KruppelBotSimulationTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private KruppelBotSimulation createKruppelBotSimulation(ILegPolyFunctions legFunctions, KruppelBotModel model) {
        return new KruppelBotSimulation(legFunctions, model);
    }

    private KruppelBotModel createDummyKruppelBotModel() {
        KruppelBotModel dummy = Mockito.mock(KruppelBotModel.class);

        Mockito.doReturn(createDummySimJoint()).when(dummy).getRoot();
        Mockito.doReturn(createDummySimJoint()).when(dummy).getServoBL();
        Mockito.doReturn(createDummySimJoint()).when(dummy).getServoBR();
        Mockito.doReturn(createDummySimJoint()).when(dummy).getServoFL();
        Mockito.doReturn(createDummySimJoint()).when(dummy).getServoFR();

        return dummy;
    }

    private KruppelBotModel createFakeKruppelBotModel(SimJoint fakeServoBL, SimJoint fakeServoBR, SimJoint fakeServoFL, SimJoint fakeServoFR) {
        KruppelBotModel fake = Mockito.mock(KruppelBotModel.class);

        Mockito.doReturn(fakeServoBL).when(fake).getServoBL();
        Mockito.doReturn(fakeServoBR).when(fake).getServoBR();
        Mockito.doReturn(fakeServoFL).when(fake).getServoFL();
        Mockito.doReturn(fakeServoFR).when(fake).getServoFR();

        return fake;
    }

    private SimJoint createDummySimJoint() {
        return Mockito.mock(SimJoint.class);
    }

    private ILegPolyFunctions createDummyLegFunctions() {
        ILegPolyFunctions dummy = Mockito.mock(ILegPolyFunctions.class);

        Mockito.doReturn(createDummyPolyFunction()).when(dummy).getLegFunctionBL();
        Mockito.doReturn(createDummyPolyFunction()).when(dummy).getLegFunctionBR();
        Mockito.doReturn(createDummyPolyFunction()).when(dummy).getLegFunctionFL();
        Mockito.doReturn(createDummyPolyFunction()).when(dummy).getLegFunctionFR();

        return dummy;
    }

    private IPolyFunction createDummyPolyFunction() {
        IPolyFunction dummy = Mockito.mock(IPolyFunction.class);

        Mockito.doReturn(0f).when(dummy).getValue(Mockito.any(Float.class));

        return dummy;
    }

    private ILegPolyFunctions createFakeLegPolyFunctions(IPolyFunction functionBL, IPolyFunction functionBR, IPolyFunction functionFL, IPolyFunction functionFR) {
        ILegPolyFunctions fake = Mockito.mock(ILegPolyFunctions.class);

        Mockito.doReturn(functionBL).when(fake).getLegFunctionBL();
        Mockito.doReturn(functionBR).when(fake).getLegFunctionBR();
        Mockito.doReturn(functionFL).when(fake).getLegFunctionFL();
        Mockito.doReturn(functionFR).when(fake).getLegFunctionFR();

        return fake;
    }

    @Test
    public void constructor_OnPassLegFunctionsNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createKruppelBotSimulation(null, createDummyKruppelBotModel());
    }

    @Test
    public void constructor_OnPassKruppelBotModelNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createKruppelBotSimulation(createDummyLegFunctions(), null);
    }

    @Test
    public void simulate_OnCall_RotatesLegsToFunctionValues() {
        IPolyFunction fakeFunctionBL = createDummyPolyFunction();
        Mockito.doReturn(1f).when(fakeFunctionBL).getValue(0);
        IPolyFunction fakeFunctionBR = createDummyPolyFunction();
        Mockito.doReturn(2f).when(fakeFunctionBR).getValue(0);
        IPolyFunction fakeFunctionFL = createDummyPolyFunction();
        Mockito.doReturn(3f).when(fakeFunctionFL).getValue(0);
        IPolyFunction fakeFunctionFR = createDummyPolyFunction();
        Mockito.doReturn(4f).when(fakeFunctionFR).getValue(0);
        ILegPolyFunctions fakeLegFunctions = createFakeLegPolyFunctions(fakeFunctionBL, fakeFunctionBR, fakeFunctionFL, fakeFunctionFR);

        SimJoint fakeServoBL = createDummySimJoint();
        SimJoint fakeServoBR = createDummySimJoint();
        SimJoint fakeServoFL = createDummySimJoint();
        SimJoint fakeServoFR = createDummySimJoint();
        KruppelBotModel fakeModel = createFakeKruppelBotModel(fakeServoBL, fakeServoBR, fakeServoFL, fakeServoFR);

        KruppelBotSimulation testee = createKruppelBotSimulation(fakeLegFunctions, fakeModel);
        testee.simulate(0);

        Mockito.verify(fakeServoBL).setOffsetRotation(new Rotation(1f, true));
        Mockito.verify(fakeServoBR).setOffsetRotation(new Rotation(2f, true));
        Mockito.verify(fakeServoFL).setOffsetRotation(new Rotation(3f, true));
        Mockito.verify(fakeServoFR).setOffsetRotation(new Rotation(4f, true));
    }

}
