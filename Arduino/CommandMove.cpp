#include "CommandMove.h"

CommandMove::CommandMove(PeripheryContainer& periphery, Movement* const movement) : Command(periphery), movement(movement)
{
}

CommandMove::~CommandMove()
{
    
}

void CommandMove::beforeExecution(PeripheryContainer& periphery)
{

}

void CommandMove::onUpdate(unsigned int execTimeElapsedInMs, PeripheryContainer& periphery)
{
    if (execTimeElapsedInMs < movement->getDurationInMs())
    {
        periphery.legBR->setLegState(movement->getLegStateBr(execTimeElapsedInMs));
        periphery.legBL->setLegState(movement->getLegStateBl(execTimeElapsedInMs));
        periphery.legFR->setLegState(movement->getLegStateFr(execTimeElapsedInMs));
        periphery.legFL->setLegState(movement->getLegStateFl(execTimeElapsedInMs));
    }
    else
    {
        finishExecution();
    }
}
