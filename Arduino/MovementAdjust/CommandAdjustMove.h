#ifndef CommandAdjustMove_h
#define CommandAdjustMove_h

#include "Command.h"
#include "CommandMove.h"
#include "LegState.h"
#include "Movement.h"
#include "MovementAdjustStance.h"

class CommandAdjustMove : public Command
{
private:
    MovementAdjustStance* mvAdjustStance;
    CommandMove* cmdAdjustStance;
    CommandMove* cmdMove;

protected:
	void beforeExecution(PeripheryContainer& periphery);
	void onUpdate(unsigned int execTimeElapsedInMs, PeripheryContainer& periphery);

public:
    CommandAdjustMove(PeripheryContainer& periphery, Movement* const movement);
    ~CommandAdjustMove();
};

#endif
