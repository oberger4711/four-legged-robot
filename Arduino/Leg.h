#ifndef Leg_h
#define Leg_h

#include "Servo.h"
#include "LegState.h"

class Leg
{
private:
	Servo* servoX;
	Servo* servoY;
	LegState legState;
	
	void applyLegState();

public:
	Leg(Servo* servoX, Servo* servoY);
	void setRotationX(const int16_t rotationInDegrees);
	void setRotationY(const int16_t rotationInDegrees);
	void setLegState(LegState legState);
	LegState getCurrentLegState();
	~Leg();
};

#endif
