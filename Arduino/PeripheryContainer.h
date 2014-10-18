#ifndef PeripheryContainer_h
#define PeripheryContainer_h

#include "Arduino.h"
#include <Wire.h>
#include <Adafruit_PWMServoDriver.h>

#include "Leg.h"

class PeripheryContainer
{
public:
	Leg* legBR;
	Leg* legBL;
	Leg* legFR;
	Leg* legFL;
};

#endif