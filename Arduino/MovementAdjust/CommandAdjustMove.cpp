#include "CommandAdjustMove.h"

CommandAdjustMove::CommandAdjustMove(PeripheryContainer& periphery, Movement* const movement) : Command(periphery)
{
    // TODO set the startStates beforeExecution
    // TODO check if Adjustment is really necessary
    mvAdjustStance = new MovementAdjustStance(periphery.legBL->getCurrentLegState(), periphery.legBR->getCurrentLegState(), periphery.legFL->getCurrentLegState(), periphery.legFR->getCurrentLegState(), movement);
    cmdAdjustStance = new CommandMove(periphery, mvAdjustStance);
    cmdMove = new CommandMove(periphery, movement);
}

CommandAdjustMove::~CommandAdjustMove()
{
    delete cmdMove;
    if (cmdAdjustStance != NULL)
    {
        delete cmdAdjustStance;
    }
    delete mvAdjustStance;
}

void CommandAdjustMove::beforeExecution(PeripheryContainer& periphery)
{
    if (cmdAdjustStance != NULL)
    {
        cmdAdjustStance->prepareExecution();
    }
    cmdMove->prepareExecution();
}

void CommandAdjustMove::onUpdate(unsigned int execTimeElapsedInMs, PeripheryContainer& periphery)
{
    if (cmdAdjustStance != NULL && !cmdAdjustStance->isComplete())
    {
        cmdAdjustStance->update();
    }
    else if (!cmdMove->isComplete())
    {
        cmdMove->update();
    }
    else
    {
        finishExecution();
    }
}

bool CommandAdjustMove::isAdjustionNecessary()
