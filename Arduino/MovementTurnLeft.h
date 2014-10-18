#ifndef MovementTurnLeft_h
#define MovementTurnLeft_h

#include "Utils.h"
#include "Servo.h"
#include "Movement.h"
#include "MovementWalkForward.h"

class MovementTurnLeft : public Movement
{
private:    
    Movement* mvWalkForward;

    unsigned int mirrorRotation(const unsigned int rotation);

public:
    MovementTurnLeft();
    ~MovementTurnLeft();
    
    LegState getLegStateBr(const unsigned int execTimeElapsedInMs);
    LegState getLegStateBl(const unsigned int execTimeElapsedInMs);
    LegState getLegStateFl(const unsigned int execTimeElapsedInMs);
    LegState getLegStateFr(const unsigned int execTimeElapsedInMs);

    unsigned int getDurationInMs();
};

#endif
