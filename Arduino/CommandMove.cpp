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

	Serial.print(periphery.legBR->getCurrentLegState().getRotationY(), DEC);
	Serial.print("--");
	Serial.print(periphery.legBL->getCurrentLegState().getRotationY(), DEC);
	Serial.print("--");
	Serial.print(periphery.legFL->getCurrentLegState().getRotationY(), DEC);
	Serial.print("--");
	Serial.print(periphery.legFR->getCurrentLegState().getRotationY(), DEC);
	Serial.println();
    }
    else
    {
        finishExecution();
    }
}
