#ifndef MovementReverse_h
#define MovementReverse_h

#include "Movement.h"

// Decorator for reversing a movement.
class MovementReverse : public Movement
{
private:
    Movement* movementToReverse;
    
    unsigned int reverseExecTimeElapsedInMs(const unsigned int execTimeElapsedInMs);

public:
    MovementReverse(Movement* movementToReverse);
    ~MovementReverse();
    
    LegState getLegStateBr(const unsigned int execTimeElapsedInMs);
    LegState getLegStateBl(const unsigned int execTimeElapsedInMs);
    LegState getLegStateFl(const unsigned int execTimeElapsedInMs);
    LegState getLegStateFr(const unsigned int execTimeElapsedInMs);
    
    unsigned int getDurationInMs();
};

#endif
