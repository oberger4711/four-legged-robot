#ifndef MovementWalkForward_h
#define MovementWalkForward_h

#include "Movement.h"
#include "Utils.h"

class MovementWalkForward : public Movement
{
private:
    static const unsigned int PERIOD_IN_MS = 3000;//3500;
    static const unsigned int REPOSITION_TIME_IN_MS = PERIOD_IN_MS / 7;
    static const unsigned int STEP_SIZE_Y_IN_DEGREES = 120;

    unsigned int rotationX(const unsigned int execTimeElapsedInMs, const unsigned int indexFeet);
    unsigned int rotationY(const unsigned int execTimeElapsedInMs, const unsigned int indexFeet);

public:
    MovementWalkForward();
    ~MovementWalkForward();
    
    LegState getLegStateBr(const unsigned int execTimeElapsedInMs);
    LegState getLegStateBl(const unsigned int execTimeElapsedInMs);
    LegState getLegStateFl(const unsigned int execTimeElapsedInMs);
    LegState getLegStateFr(const unsigned int execTimeElapsedInMs);

    unsigned int getDurationInMs();
};

#endif
