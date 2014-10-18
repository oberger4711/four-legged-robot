#ifndef LegState_h
#define LegState_h

const static unsigned int ANGLE_X_LAY_DOWN = 120;
const static unsigned int ANGLE_X_REPOSITION = 105;
const static unsigned int ANGLE_X_STAND = 90;

const static unsigned int ANGLE_Y_STAND = 90;

class LegState
{
private:
    unsigned int rotationX;
    unsigned int rotationY;
    
public:
    LegState();
    LegState(unsigned int rotationX, unsigned int rotationY);
    ~LegState();
    
    unsigned int getRotationX();
    void setRotationX(const unsigned int rotationX);
    unsigned int getRotationY();
    void setRotationY(const unsigned int rotationY);
};

#endif
