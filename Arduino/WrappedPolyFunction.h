#ifndef WrappedPolyFunction_h
#define WrappedPolyFunction_h

#include "Utils.h"
#include "Vector2.h"

class WrappedPolyFunction
{
private:
	Vector2_t* polygons;
	int polygonsLength;
	unsigned int period;
	unsigned int offsetX;

	int wrapOntoPeriod(int x);
public:
	WrappedPolyFunction(Vector2_t* polygons, const int polygonsLength, const unsigned int period, const unsigned int offsetX);
	int getY(const int x);
	unsigned int getPeriod();
	unsigned int getOffsetX();
	~WrappedPolyFunction();
};

#endif
