#ifndef Command_h
#define Command_h

#include "Arduino.h"

#include "PeripheryContainer.h"

class Command
{
private:
	PeripheryContainer& periphery;
	unsigned long execTimeStartInMs;
	bool complete;

protected:
	void finishExecution();

	virtual void beforeExecution(PeripheryContainer& peripheryContainer) = 0;
	virtual void onUpdate(unsigned int execTimeElapsedInMs, PeripheryContainer& periphery) = 0;

public:
	Command(PeripheryContainer& peripheryContainer);
	virtual ~Command();
	void prepareExecution();
	void update();
	bool isComplete();
};

#endif
