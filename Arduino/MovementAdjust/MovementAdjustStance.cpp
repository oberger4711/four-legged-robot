#include "MovementAdjustStance.h"

MovementAdjustStance::MovementAdjustStance(LegState startLegStateBl, LegState startLegStateBr, LegState startLegStateFl, LegState startLegStateFr, Movement* nextMovement)
{
    this->startLegStateBl = new LegState(startLegStateBl);
    this->startLegStateBr = new LegState(startLegStateBr);
    this->startLegStateFl = new LegState(startLegStateFl);
    this->startLegStateFr = new LegState(startLegStateFr);

    endLegStateBl = new LegState(nextMovement->getLegStateBl(0));
    endLegStateBr = new LegState(nextMovement->getLegStateBr(0));
    endLegStateFl = new LegState(nextMovement->getLegStateFl(0));
    endLegStateFr = new LegState(nextMovement->getLegStateFr(0));
}

MovementAdjustStance::~MovementAdjustStance()
{
    delete startLegStateBl;
    delete startLegStateBr;
    delete startLegStateFl;
    delete startLegStateFr;
    
    delete endLegStateBl;
    delete endLegStateBr;
    delete endLegStateFl;
    delete endLegStateFr;
}

LegState MovementAdjustStance::getLegStateBr(const unsigned int execTimeElapsedInMs)
{
    unsigned int legIndex = 0;
    return LegState(rotationX(execTimeElapsedInMs, legIndex), rotationY(execTimeElapsedInMs, startLegStateBr->getRotationY(), endLegStateBr->getRotationY(), legIndex));
}
LegState MovementAdjustStance::getLegStateFl(const unsigned int execTimeElapsedInMs)
{
    unsigned int legIndex = 1;
    return LegState(rotationX(execTimeElapsedInMs, legIndex), rotationY(execTimeElapsedInMs, startLegStateFl->getRotationY(), endLegStateFl->getRotationY(), legIndex));
}
LegState MovementAdjustStance::getLegStateBl(const unsigned int execTimeElapsedInMs)
{
    unsigned int legIndex = 2;
    return LegState(rotationX(execTimeElapsedInMs, legIndex), rotationY(execTimeElapsedInMs, startLegStateBl->getRotationY(), endLegStateBl->getRotationY(), legIndex));
}
LegState MovementAdjustStance::getLegStateFr(const unsigned int execTimeElapsedInMs)
{
    unsigned int legIndex = 3;
    return LegState(rotationX(execTimeElapsedInMs, legIndex), rotationY(execTimeElapsedInMs, startLegStateFr->getRotationY(), endLegStateFr->getRotationY(), legIndex));
}

unsigned int MovementAdjustStance::rotationY(const unsigned int execTimeElapsedInMs, const unsigned int startRotationY, const unsigned int endRotationY, const unsigned int legIndex)
{
    unsigned int rotationY;
    unsigned int mappedExecTimeElapsedInMs = execTimeElapsedInMs + legIndex * (PERIOD_IN_MS / 4);
    
    if (mappedExecTimeElapsedInMs < 3 * PERIOD_IN_MS / 4)
    {
        rotationY = startRotationY;
    }
    else
    {
        rotationY = interpolate(3 * PERIOD_IN_MS / 4, startRotationY, PERIOD_IN_MS, endRotationY, mappedExecTimeElapsedInMs);
    }
    
    return rotationY;
}

unsigned int MovementAdjustStance::rotationX(const unsigned int execTimeElapsedInMs, const unsigned int legIndex)
{
    unsigned int rotationX;
    
    unsigned int mappedExecTimeElapsedInMs = execTimeElapsedInMs + legIndex * (PERIOD_IN_MS / 4);
    
    if (mappedExecTimeElapsedInMs < 3 * PERIOD_IN_MS / 4)
    {
        rotationX = ANGLE_X_STAND;
    }
    else
    {
        rotationX = interpolateSquare(3 * PERIOD_IN_MS / 4, ANGLE_X_STAND, 7 * PERIOD_IN_MS / 8, ANGLE_X_REPOSITION, mappedExecTimeElapsedInMs);
    }
    
    return rotationX;
}

unsigned int MovementAdjustStance::getDurationInMs()
{
    return PERIOD_IN_MS;
}
