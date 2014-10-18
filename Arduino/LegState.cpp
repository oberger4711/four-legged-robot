#include "LegState.h"

LegState::LegState() : LegState(ANGLE_X_LAY_DOWN, ANGLE_Y_STAND)
{
    
}
LegState::LegState(unsigned int rotationX, unsigned int rotationY) : rotationX(rotationX), rotationY(rotationY)
{
    
}

LegState::~LegState()
{

}

unsigned int LegState::getRotationX()
{
    return rotationX;
}
void LegState::setRotationX(const unsigned int rotationX)
{
    this->rotationX = rotationX;
}

unsigned int LegState::getRotationY()
{
    return rotationY;
}
void LegState::setRotationY(const unsigned int rotationY)
{
    this->rotationY;
}
