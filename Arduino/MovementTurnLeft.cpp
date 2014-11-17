#include "MovementTurnLeft.h"


MovementTurnLeft::MovementTurnLeft()
{
    mvWalkForward = new MovementWalkForward();
}

MovementTurnLeft::~MovementTurnLeft()
{
    delete mvWalkForward;
}

LegState MovementTurnLeft::getLegStateBr(const unsigned int execTimeElapsedInMs)
{
    LegState res = mvWalkForward->getLegStateBr(execTimeElapsedInMs);
    return res;
}

LegState MovementTurnLeft::getLegStateBl(const unsigned int execTimeElapsedInMs)
{
    LegState res = mvWalkForward->getLegStateBl(execTimeElapsedInMs);
    res.setRotationY(mirrorRotation(res.getRotationY()));
    return res;
}

LegState MovementTurnLeft::getLegStateFl(const unsigned int execTimeElapsedInMs)
{
    LegState res = mvWalkForward->getLegStateFl(execTimeElapsedInMs);
    res.setRotationY(mirrorRotation(res.getRotationY()));
    return res;
}

LegState MovementTurnLeft::getLegStateFr(const unsigned int execTimeElapsedInMs)
{
    LegState res = mvWalkForward->getLegStateFr(execTimeElapsedInMs);
    return res;
}


unsigned int MovementTurnLeft::mirrorRotation(const unsigned int rotation)
{
    return DEGREEMAX - rotation;
}

unsigned int MovementTurnLeft::getDurationInMs()
{
    return mvWalkForward->getDurationInMs();
}
