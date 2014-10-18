#ifndef MovementAdjustStance_h
#define MovementAdjustStance_h

#include "Movement.h"
#include "Utils.h"

class MovementAdjustStance : public Movement
{
private:
    static const unsigned int PERIOD_IN_MS = 300;
    
    LegState* startLegStateBr;
    LegState* startLegStateBl;
    LegState* startLegStateFr;
    LegState* startLegStateFl;
    
    LegState* endLegStateBr;
    LegState* endLegStateBl;
    LegState* endLegStateFr;
    LegState* endLegStateFl;
    
    unsigned int rotationY(const unsigned int execTimeElapsedInMs, const unsigned int startRotationY, const unsigned int endRotationY, const unsigned int legIndex);
    unsigned int rotationX(const unsigned int execTimeElapsedInMs, const unsigned int legIndex);
    
public:
    MovementAdjustStance(LegState startLegStateBl, LegState startLegStateBr, LegState startLegStateFl, LegState startLegStateFr, Movement* nextMovement);
    ~MovementAdjustStance();

    LegState getLegStateBr(const unsigned int execTimeElapsedInMs);
    LegState getLegStateBl(const unsigned int execTimeElapsedInMs);
    LegState getLegStateFl(const unsigned int execTimeElapsedInMs);
    LegState getLegStateFr(const unsigned int execTimeElapsedInMs);

    unsigned int getDurationInMs();
};

#endif
