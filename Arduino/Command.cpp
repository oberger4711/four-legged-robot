#include "Command.h"

Command::Command(PeripheryContainer& periphery) : periphery(periphery), complete(true)
{
}

Command::~Command() 
{
}

void Command::finishExecution()
{
	complete = true;
}

void Command::prepareExecution()
{
	complete = false;
	execTimeStartInMs = millis();

	beforeExecution(periphery);
}

void Command::update()
{
	unsigned int execTimeElapsedInMs = millis() - execTimeStartInMs;

	onUpdate(execTimeElapsedInMs, periphery);
}

bool Command::isComplete()
{
	return complete;
}
