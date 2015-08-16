#ifndef LegPolyFunctionFactory_h
#define LegPolyFunctionFactory_h

#include "WrappedPolyFunction.h"

class LegPolyFunctionFactory
{
public:
	WrappedPolyFunction* createBr();
	WrappedPolyFunction* createBl();
	WrappedPolyFunction* createFr();
	WrappedPolyFunction* createFl();
};

#endif
