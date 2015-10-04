/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.walk;

import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.ISimulationState;
import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.LegPosition;
import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.Model;
import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.EBalanceMode;
import com.oberger.kruppelbotsimulation.model.BalancePoint;
import com.oberger.kruppelbotsimulation.model.SimJoint;
import com.oberger.kruppelbotsimulation.util.Vector2;
import com.oberger.kruppelbotsimulation.util.Weight;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;
import org.mockito.Mockito;

/**
 *
 * @author ole
 */
public class SimulationStateBalancePointEvaluatorTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private SimulationStateBalancePointEvaluator createTestee(LegPosition criticalPosition) {
	return new SimulationStateBalancePointEvaluator(criticalPosition);
    }

    private Model createFakeModel(BalancePoint rootBalancePoint) {
	Model fake = Mockito.mock(Model.class);
	SimJoint fakeRoot = Mockito.mock(SimJoint.class);

	SimJoint fakeBl = createFakeSimJoint(new Vector2(-1, -1));
	Mockito.doReturn(fakeBl).when(fake).getServoBL();

	SimJoint fakeBr = createFakeSimJoint(new Vector2(1, -1));
	Mockito.doReturn(fakeBr).when(fake).getServoBR();

	SimJoint fakeFl = createFakeSimJoint(new Vector2(-1, 1));
	Mockito.doReturn(fakeFl).when(fake).getServoFL();

	SimJoint fakeFr = createFakeSimJoint(new Vector2(1, 1));
	Mockito.doReturn(fakeFr).when(fake).getServoFR();

	Mockito.doReturn(rootBalancePoint).when(fakeRoot).getGlobalBalancePoint();
	Mockito.doReturn(fakeRoot).when(fake).getRoot();

	return fake;
    }

    private SimJoint createFakeSimJoint(Vector2 globalPosition) {
	SimJoint fake = Mockito.mock(SimJoint.class);

	Mockito.doReturn(globalPosition).when(fake).getGlobalPosition();

	return fake;
    }

    private ISimulationState createFakeSimulationState(float totalElapsedTimeInMs, Model model, EBalanceMode legBalanceMode) {
	ISimulationState fake = Mockito.mock(ISimulationState.class);

	Mockito.doReturn(totalElapsedTimeInMs).when(fake).getTotalElapsedTimeInS();
	Mockito.doReturn(model).when(fake).getModel();
	Mockito.doReturn(legBalanceMode).when(fake).getBalanceMode(Matchers.any());

	return fake;
    }

    @Test
    public void constructor_OnPassCriticalPositionNull_ThrowsIllegalArgumentException() {
	exception.expect(IllegalArgumentException.class);

	createTestee(null);
    }

    @Test
    public void getSore_OnPassNull_ThrowsIllegalArgumentException() {
	SimulationStateBalancePointEvaluator testee = createTestee(LegPosition.BR);

	exception.expect(IllegalArgumentException.class);

	testee.getScore(null);
    }

    @Test
    public void getScore_NotCriticalTimeSpan_ReturnsZeroScore() {
	Model fakeModel = createFakeModel(new BalancePoint(new Vector2(-1, 1), new Weight(1f)));
	ISimulationState fakeSimulationState = createFakeSimulationState(0f, fakeModel, EBalanceMode.IRRELEVANT);

	SimulationStateBalancePointEvaluator testee = createTestee(LegPosition.BR);
	float score = testee.getScore(fakeSimulationState);

	assertEquals(0, score, 0.0001f);
    }

    @Test
    public void getScore_OnPassWellBalancedSimulationStateInCriticalTimespan_ReturnsPositiveScore() {
	Model fakeModel = createFakeModel(new BalancePoint(new Vector2(-1, 1), new Weight(1f)));
	ISimulationState fakeSimulationState = createFakeSimulationState(1.5f, fakeModel, EBalanceMode.CRITICAL);

	SimulationStateBalancePointEvaluator testee = createTestee(LegPosition.BR);
	float score = testee.getScore(fakeSimulationState);

	assertTrue(score > 0);
    }

    @Test
    public void getScore_OnPassWellBalancedSimulationStateInCriticalTimespan_ReturnsZeroScore() {
	Model fakeModel = createFakeModel(new BalancePoint(new Vector2(-1, 1), new Weight(1f)));
	ISimulationState fakeSimulationState = createFakeSimulationState(1.5f, fakeModel, EBalanceMode.CRITICAL);

	SimulationStateBalancePointEvaluator testee = createTestee(LegPosition.FL);
	float score = testee.getScore(fakeSimulationState);

	assertEquals(0, score, 0.0001f);
    }

}
