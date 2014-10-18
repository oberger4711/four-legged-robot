#ifndef Servo_h
#define Servo_h

#include "Arduino.h"
#include <Wire.h>
#include <Adafruit_PWMServoDriver.h>

#define SERVOMIN  155 // min pulse length count (out of 4096)
#define SERVOMAX  480 // max pulse length count (out of 4096)
#define DEGREEMAX 180

class Servo
{
private:
	Adafruit_PWMServoDriver* servoShield;
	bool counterClockwise;
	uint16_t servoIndex;
	int16_t rotationOffset;

public:
	Servo(Adafruit_PWMServoDriver* const servoShield, const uint16_t servoIndex, const bool counterClockwise, const int16_t rotOffset = 0);
	~Servo();

	void setRotationOffset(const int16_t degrees);
	void setRotation(const uint16_t degrees);
};

#endif