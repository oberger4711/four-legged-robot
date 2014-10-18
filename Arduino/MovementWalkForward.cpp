#include "MovementWalkForward.h"

MovementWalkForward::MovementWalkForward()
{

}

MovementWalkForward::~MovementWalkForward()
{

}

LegState MovementWalkForward::getLegStateBr(const unsigned int execTimeElapsedInMs)
{
    return LegState(rotationX(execTimeElapsedInMs, 0), rotationY(execTimeElapsedInMs, 0));
}

LegState MovementWalkForward::getLegStateFl(const unsigned int execTimeElapsedInMs)
{
    return LegState(rotationX(execTimeElapsedInMs, 1), rotationY(execTimeElapsedInMs, 1));
}

LegState MovementWalkForward::getLegStateBl(const unsigned int execTimeElapsedInMs)
{
    return LegState(rotationX(execTimeElapsedInMs, 2), rotationY(execTimeElapsedInMs, 2));
}

LegState MovementWalkForward::getLegStateFr(const unsigned int execTimeElapsedInMs)
{
    return LegState(rotationX(execTimeElapsedInMs, 3), rotationY(execTimeElapsedInMs, 3));
}

unsigned int MovementWalkForward::getDurationInMs()
{
    return PERIOD_IN_MS;
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

unsigned int MovementWalkForward::rotationY(const unsigned int execTimeElapsedInMs, const unsigned int legIndex)
{
    unsigned int rotationY;
    unsigned int mappedExecTimeInMs = (execTimeElapsedInMs + legIndex * (PERIOD_IN_MS / 4)) % PERIOD_IN_MS;
    if (mappedExecTimeInMs < PERIOD_IN_MS - REPOSITION_TIME_IN_MS)
    {
        rotationY = interpolate(0, ANGLE_Y_STAND - STEP_SIZE_Y_IN_DEGREES / 2, PERIOD_IN_MS - REPOSITION_TIME_IN_MS, ANGLE_Y_STAND + STEP_SIZE_Y_IN_DEGREES / 2, mappedExecTimeInMs);
    }
    else
    {
        rotationY = interpolate(PERIOD_IN_MS - REPOSITION_TIME_IN_MS, ANGLE_Y_STAND + STEP_SIZE_Y_IN_DEGREES / 2, PERIOD_IN_MS, ANGLE_Y_STAND - STEP_SIZE_Y_IN_DEGREES / 2, mappedExecTimeInMs);
    }
    
    return rotationY;
}
