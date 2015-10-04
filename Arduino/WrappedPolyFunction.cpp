#include "WrappedPolyFunction.h"

WrappedPolyFunction::WrappedPolyFunction(Vector2_t* polygons, const int polygonsLength, const unsigned int period, const unsigned int offsetX) : polygons(polygons), polygonsLength(polygonsLength), period(period), offsetX(offsetX)
{
}

WrappedPolyFunction::~WrappedPolyFunction()
{
	delete[] polygons;
}

int WrappedPolyFunction::getY(const int x)
{
	int mappedX = wrapOntoPeriod(x);
	int i;
	int interpolatedValue = 0;
	
	for (i = 0; i < polygonsLength - 1; i++)
	{
		Vector2_t p1 = polygons[i];
		Vector2_t p2 = polygons[i + 1];

		if (p1.x == mappedX)
		{
			interpolatedValue = p1.y;
		}
		else if (p2.x == mappedX)
		{
			interpolatedValue = p2.y;
		}
		else if (p1.x < mappedX && mappedX < p2.x)
		{
			interpolatedValue = interpolate(p1.x, p1.y, p2.x, p2.y, mappedX);
		}
	}

	return interpolatedValue;
}

int WrappedPolyFunction::wrapOntoPeriod(int x)
{
	int mappedX = x + offsetX;

	while (mappedX < 0)
	{
		mappedX += period;
	}
	mappedX %= period;

	return mappedX;
}

unsigned int WrappedPolyFunction::getPeriod()
{
	return period;
}

unsigned int WrappedPolyFunction::getOffsetX()
{
	return offsetX;
}
