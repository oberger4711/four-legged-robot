#ifndef Movement_h
#define Movement_h

#include "LegState.h"

class Movement
{
public:
    virtual ~Movement();
    virtual LegState getLegStateBr(const unsigned int execTimeElapsedInMs) = 0;
    virtual LegState getLegStateBl(const unsigned int execTimeElapsedInMs) = 0;
    virtual LegState getLegStateFr(const unsigned int execTimeElapsedInMs) = 0;
    virtual LegState getLegStateFl(const unsigned int execTimeElapsedInMs) = 0;

    virtual unsigned int getDurationInMs() = 0;
};

#endif
