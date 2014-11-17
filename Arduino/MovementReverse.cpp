#include "MovementReverse.h"

MovementReverse::MovementReverse(Movement* movementToReverse) : movementToReverse(movementToReverse)
{

}

MovementReverse::~MovementReverse()
{

}

LegState MovementReverse::getLegStateBr(const unsigned int execTimeElapsedInMs)
{
    return movementToReverse->getLegStateBr(reverseExecTimeElapsedInMs(execTimeElapsedInMs));
}

LegState MovementReverse::getLegStateBl(const unsigned int execTimeElapsedInMs)
{
    return movementToReverse->getLegStateBl(reverseExecTimeElapsedInMs(execTimeElapsedInMs));
}

LegState MovementReverse::getLegStateFr(const unsigned int execTimeElapsedInMs)
{
    return movementToReverse->getLegStateFr(reverseExecTimeElapsedInMs(execTimeElapsedInMs));
}

LegState MovementReverse::getLegStateFl(const unsigned int execTimeElapsedInMs)
{
    return movementToReverse->getLegStateFl(reverseExecTimeElapsedInMs(execTimeElapsedInMs));
}

unsigned int MovementReverse::reverseExecTimeElapsedInMs(const unsigned int execTimeElapsedInMs)
{
    return movementToReverse->getDurationInMs() - execTimeElapsedInMs;
}

unsigned int MovementReverse::getDurationInMs()
{
    return movementToReverse->getDurationInMs();
}
