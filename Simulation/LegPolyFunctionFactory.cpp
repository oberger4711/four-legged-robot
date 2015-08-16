#include "LegPolyFunctionFactory.h"

LegPolyFunctionFactory::getPolyFunctionBr()
{
	Vector2_t* polygons = new Vector2_t[11];
	
	polygons[0].x = 0;
	polygons[0].y = 150;
	
	polygons[1].x = 0;
	polygons[1].y = 132;
	
	polygons[2].x = 0;
	polygons[2].y = 115;
	
	polygons[3].x = 1;
	polygons[3].y = 98;
	
	polygons[4].x = 1;
	polygons[4].y = 81;
	
	polygons[5].x = 1;
	polygons[5].y = 64;
	
	polygons[6].x = 1;
	polygons[6].y = 47;
	
	polygons[7].x = 2;
	polygons[7].y = 30;
	
	polygons[8].x = 2;
	polygons[8].y = 70;
	
	polygons[9].x = 2;
	polygons[9].y = 110;
	
	polygons[10].x = 3;
	polygons[10].y = 150;
	
	return new WrappedPolyFunction(polygons, 11, 3000, 0);
}
LegPolyFunctionFactory::getPolyFunctionBl()
{
	Vector2_t* polygons = new Vector2_t[11];
	
	polygons[0].x = 0;
	polygons[0].y = 150;
	
	polygons[1].x = 0;
	polygons[1].y = 132;
	
	polygons[2].x = 0;
	polygons[2].y = 115;
	
	polygons[3].x = 1;
	polygons[3].y = 98;
	
	polygons[4].x = 1;
	polygons[4].y = 81;
	
	polygons[5].x = 1;
	polygons[5].y = 64;
	
	polygons[6].x = 2;
	polygons[6].y = 47;
	
	polygons[7].x = 2;
	polygons[7].y = 30;
	
	polygons[8].x = 2;
	polygons[8].y = 70;
	
	polygons[9].x = 2;
	polygons[9].y = 110;
	
	polygons[10].x = 3;
	polygons[10].y = 150;
	
	return new WrappedPolyFunction(polygons, 11, 3000, 1500);
}
LegPolyFunctionFactory::getPolyFunctionFr()
{
	Vector2_t* polygons = new Vector2_t[11];
	
	polygons[0].x = 0;
	polygons[0].y = 150;
	
	polygons[1].x = 0;
	polygons[1].y = 132;
	
	polygons[2].x = 0;
	polygons[2].y = 115;
	
	polygons[3].x = 1;
	polygons[3].y = 98;
	
	polygons[4].x = 1;
	polygons[4].y = 81;
	
	polygons[5].x = 1;
	polygons[5].y = 64;
	
	polygons[6].x = 1;
	polygons[6].y = 47;
	
	polygons[7].x = 2;
	polygons[7].y = 30;
	
	polygons[8].x = 2;
	polygons[8].y = 70;
	
	polygons[9].x = 2;
	polygons[9].y = 110;
	
	polygons[10].x = 3;
	polygons[10].y = 150;
	
	return new WrappedPolyFunction(polygons, 11, 3000, 2250);
}
LegPolyFunctionFactory::getPolyFunctionFl()
{
	Vector2_t* polygons = new Vector2_t[11];
	
	polygons[0].x = 0;
	polygons[0].y = 150;
	
	polygons[1].x = 0;
	polygons[1].y = 132;
	
	polygons[2].x = 0;
	polygons[2].y = 115;
	
	polygons[3].x = 1;
	polygons[3].y = 98;
	
	polygons[4].x = 1;
	polygons[4].y = 81;
	
	polygons[5].x = 1;
	polygons[5].y = 64;
	
	polygons[6].x = 2;
	polygons[6].y = 47;
	
	polygons[7].x = 2;
	polygons[7].y = 30;
	
	polygons[8].x = 2;
	polygons[8].y = 70;
	
	polygons[9].x = 2;
	polygons[9].y = 110;
	
	polygons[10].x = 3;
	polygons[10].y = 150;
	
	return new WrappedPolyFunction(polygons, 11, 3000, 750);
}
