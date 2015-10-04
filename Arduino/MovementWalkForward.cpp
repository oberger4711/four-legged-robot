#include "MovementWalkForward.h"

MovementWalkForward::MovementWalkForward()
{
	PERIOD_IN_MS = 3000;
	REPOSITION_TIME_IN_MS = 428;

	LegPolyFunctionFactory factory;
	fBr = factory.createBr();
	fBl = factory.createBl();
	fFr = factory.createFr();
	fFl = factory.createFl();
}

MovementWalkForward::~MovementWalkForward()
{
	delete fBr;
	delete fBl;
	delete fFr;
	delete fFl;
}

LegState MovementWalkForward::getLegStateBr(const unsigned int execTimeElapsedInMs)
{
    return LegState(rotationX(execTimeElapsedInMs, 0), fBr->getY(execTimeElapsedInMs));
}

LegState MovementWalkForward::getLegStateFl(const unsigned int execTimeElapsedInMs)
{
    return LegState(rotationX(execTimeElapsedInMs, 1), fFl->getY(execTimeElapsedInMs));
}

LegState MovementWalkForward::getLegStateBl(const unsigned int execTimeElapsedInMs)
{
    return LegState(rotationX(execTimeElapsedInMs, 2), fBl->getY(execTimeElapsedInMs));
}

LegState MovementWalkForward::getLegStateFr(const unsigned int execTimeElapsedInMs)
{
    return LegState(rotationX(execTimeElapsedInMs, 3), fFr->getY(execTimeElapsedInMs));
}

unsigned int MovementWalkForward::getDurationInMs()
{
    return fBr->getPeriod();
}


unsigned int MovementWalkForward::rotationX(const unsigned int execTimeElapsedInMs, const unsigned int legIndex)
{
    unsigned int rotationX;
    unsigned int mappedExecTimeInMs = (execTimeElapsedInMs + legIndex * (PERIOD_IN_MS / 4)) % PERIOD_IN_MS;
    if (mappedExecTimeInMs < PERIOD_IN_MS - REPOSITION_TIME_IN_MS)
    {
        rotationX = ANGLE_X_STAND;
    }
    else
    {
        rotationX = interpolateSquare(PERIOD_IN_MS - REPOSITION_TIME_IN_MS, ANGLE_X_STAND, PERIOD_IN_MS - REPOSITION_TIME_IN_MS / 2, ANGLE_X_REPOSITION, mappedExecTimeInMs);
    }
    
    return rotationX;
}
