package com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator;

import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.EBalanceMode;
import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.ILegPolyFunctions;

public interface ISimulationState {

    public float getTotalElapsedTimeInS();
    public Model getModel();
    public ILegPolyFunctions getLegFunctions();
    public EBalanceMode getBalanceMode(LegPosition position);

}
