#include "CommandProcedure.h"

CommandProcedure::CommandProcedure(PeripheryContainer& periphery, Command** commands, int commandsLength) : Command(periphery), commands(commands), commandsLength(commandsLength), currentIndex(0)
{
}

CommandProcedure::~CommandProcedure()
{
	for (int i = 0; i < commandsLength; i++)
	{
		delete commands[i];
	}
}

void CommandProcedure::beforeExecution(PeripheryContainer& peripheryContainer)
{
	currentIndex = 0;
}

void CommandProcedure::onUpdate(unsigned int execTimeElapsedInMs, PeripheryContainer& periphery)
{
	if (!commands[currentIndex]->isComplete())
	{
		// Keep on updating current command
		commands[currentIndex]->update();
	}
	else
	{
		// Next command
		if (currentIndex < commandsLength)
		{
			currentIndex++;
			commands[currentIndex]->prepareExecution();
		}
		else
		{
			finishExecution();
		}
	}
}