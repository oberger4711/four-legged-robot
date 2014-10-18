#ifndef MovementStandUp_h
#define MovementStandUp_h

#include "Movement.h"
#include "Leg.h"
#include "LegState.h"
#include "Utils.h"

class MovementStandUp : public Movement
{
private:
    static const unsigned int PERIOD_IN_MS = 1500;

    unsigned int rotationX(const unsigned int execTimeElapsedInMs);
    unsigned int rotationY(const unsigned int execTimeElapsedInMs);

public:
    MovementStandUp();
    ~MovementStandUp();
    
    LegState getLegStateBr(const unsigned int execTimeElapsedInMs);
    LegState getLegStateBl(const unsigned int execTimeElapsedInMs);
    LegState getLegStateFl(const unsigned int execTimeElapsedInMs);
    LegState getLegStateFr(const unsigned int execTimeElapsedInMs);

    unsigned int getDurationInMs();
};

#endif
