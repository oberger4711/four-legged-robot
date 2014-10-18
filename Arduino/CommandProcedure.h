#ifndef CommandProcedure_h
#define CommandProcedure_h

#include "Command.h"

class CommandProcedure : public Command
{
private:
	Command** commands;
	int commandsLength;
	int currentIndex;

protected:
	void beforeExecution(PeripheryContainer& peripheryContainer);
	void onUpdate(unsigned int execTimeElapsedInMs, PeripheryContainer& periphery);

public:
	CommandProcedure(PeripheryContainer& periphery, Command** commands, int commandsLength);
	~CommandProcedure();

};

#endif