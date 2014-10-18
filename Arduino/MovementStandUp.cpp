#include "MovementStandUp.h"

MovementStandUp::MovementStandUp()
{
    
}

MovementStandUp::~MovementStandUp()
{

}


LegState MovementStandUp::getLegStateBr(const unsigned int execTimeElapsedInMs)
{
    return LegState(rotationX(execTimeElapsedInMs), rotationY(execTimeElapsedInMs));
}

LegState MovementStandUp::getLegStateBl(const unsigned int execTimeElapsedInMs)
{
    return LegState(rotationX(execTimeElapsedInMs), rotationY(execTimeElapsedInMs));
}

LegState MovementStandUp::getLegStateFr(const unsigned int execTimeElapsedInMs)
{
    return LegState(rotationX(execTimeElapsedInMs), rotationY(execTimeElapsedInMs));
}

LegState MovementStandUp::getLegStateFl(const unsigned int execTimeElapsedInMs)
{
    return LegState(rotationX(execTimeElapsedInMs), rotationY(execTimeElapsedInMs));
}

unsigned int MovementStandUp::getDurationInMs()
{
    return PERIOD_IN_MS;
}


unsigned int MovementStandUp::rotationX(const unsigned int execTimeElapsedInMs)
{
    return interpolate(0, ANGLE_X_LAY_DOWN, PERIOD_IN_MS, 90, execTimeElapsedInMs);
}

unsigned int MovementStandUp::rotationY(const unsigned int execTimeElapsedInMs)
{
    return ANGLE_Y_STAND;
}
