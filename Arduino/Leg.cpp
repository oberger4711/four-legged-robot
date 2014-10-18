#include "Leg.h"

Leg::Leg(Servo* servoX, Servo* servoY) : servoX(servoX), servoY(servoY)
{

}

Leg::~Leg()
{
	delete servoX;
	delete servoY;
}

void Leg::applyLegState()
{
    servoX->setRotation(legState.getRotationX());
    servoY->setRotation(legState.getRotationY());
}

void Leg::setRotationX(const int16_t rotationInDegrees)
{
    legState.setRotationX(rotationInDegrees);
    applyLegState();
}

void Leg::setRotationY(const int16_t rotationInDegrees)
{
    legState.setRotationY(rotationInDegrees);
    applyLegState();
}

void Leg::setLegState(LegState legState)
{
    this->legState = LegState(legState);
    applyLegState();
}

LegState Leg::getCurrentLegState()
{
    return legState;
}

