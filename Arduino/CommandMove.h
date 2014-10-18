#ifndef CommandMove_h
#define CommandMove_h

#include "Command.h"
#include "LegState.h"
#include "Movement.h"

class CommandMove : public Command
{
private:
    Movement* movement;

protected:
	void beforeExecution(PeripheryContainer& periphery);
	void onUpdate(unsigned int execTimeElapsedInMs, PeripheryContainer& periphery);

public:
    CommandMove(PeripheryContainer& periphery, Movement* const movement);
    ~CommandMove();
};

#endif
