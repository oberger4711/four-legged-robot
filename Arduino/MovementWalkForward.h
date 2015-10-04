#ifndef MovementWalkForward_h
#define MovementWalkForward_h

#include "Movement.h"
#include "Utils.h"
#include "WrappedPolyFunction.h"
#include "LegPolyFunctionFactory.h"

class MovementWalkForward : public Movement
{
private:
    unsigned int PERIOD_IN_MS;
    unsigned int REPOSITION_TIME_IN_MS;
    unsigned int STEP_SIZE_Y_IN_DEGREES;

    WrappedPolyFunction* fBr;
    WrappedPolyFunction* fBl;
    WrappedPolyFunction* fFr;
    WrappedPolyFunction* fFl;

    unsigned int rotationX(const unsigned int execTimeElapsedInMs, const unsigned int indexFeet);

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
