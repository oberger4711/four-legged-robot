#ifndef Utils_h
#define Utils_h

#define max(a,b) ((a)>(b)?(a):(b))

unsigned int interpolate(double xStart, double yStart, double xEnd, double yEnd, double t);
unsigned int interpolateSquare(double xPoint, double yPoint, double xVertex, double yVertex, double t);

#endif
