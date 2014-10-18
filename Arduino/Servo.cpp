#include "Servo.h"

Servo::Servo(Adafruit_PWMServoDriver* const servoShield,
	const uint16_t servoIndex,
	const bool counterClockwise,
	const int16_t rotationOffset) : servoShield(servoShield), servoIndex(servoIndex), counterClockwise(counterClockwise), rotationOffset(rotationOffset)
{
}

Servo::~Servo()
{
}

void Servo::setRotationOffset(const int16_t rotationOffset)
{
	this->rotationOffset = rotationOffset;
}

void Servo::setRotation(const uint16_t rotation)
{
	uint16_t pulseLength;
	uint16_t transformed = rotation;

	transformed += rotationOffset;
	if (!counterClockwise)
	{
		transformed = DEGREEMAX - transformed;
	}
	float percent = transformed / (float)DEGREEMAX;

	pulseLength = SERVOMIN + (SERVOMAX - SERVOMIN) * percent;
	if (pulseLength < SERVOMIN)
	{
		pulseLength = SERVOMIN;
	}
	else if (pulseLength > SERVOMAX)
	{
		pulseLength = SERVOMAX;
	}

	servoShield->setPWM(servoIndex, 0, pulseLength);
}
