package com.oberger.kruppelbotsimulation.domain.simulation;

import com.oberger.kruppelbotsimulation.domain.simulation.legpolyfunctions.EBalanceMode;
import com.oberger.kruppelbotsimulation.domain.simulation.legpolyfunctions.ILegPolyFunctions;

public interface ISimulationState {

    public float getTotalElapsedTimeInS();

    public Model getModel();

    public ILegPolyFunctions getLegFunctions();

    public EBalanceMode getBalanceMode(LegPosition position);

}
