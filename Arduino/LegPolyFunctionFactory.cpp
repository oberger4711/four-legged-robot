#include "/home/oberger/Projects/Kruppelbot/Arduino/LegPolyFunctionFactory.h"

WrappedPolyFunction* LegPolyFunctionFactory::createBr()
{
	Vector2_t* polygons = new Vector2_t[11];
	
	polygons[0].x = 0;
	polygons[0].y = 30;
	
	polygons[1].x = 287;
	polygons[1].y = 47;
	
	polygons[2].x = 574;
	polygons[2].y = 64;
	
	polygons[3].x = 1262;
	polygons[3].y = 81;
	
	polygons[4].x = 1549;
	polygons[4].y = 98;
	
	polygons[5].x = 1836;
	polygons[5].y = 115;
	
	polygons[6].x = 2124;
	polygons[6].y = 132;
	
	polygons[7].x = 2571;
	polygons[7].y = 150;
	
	polygons[8].x = 2714;
	polygons[8].y = 110;
	
	polygons[9].x = 2857;
	polygons[9].y = 69;
	
	polygons[10].x = 3000;
	polygons[10].y = 30;
	
	return new WrappedPolyFunction(polygons, 11, 3000, 0);
}

WrappedPolyFunction* LegPolyFunctionFactory::createBl()
{
	Vector2_t* polygons = new Vector2_t[11];
	
	polygons[0].x = 0;
	polygons[0].y = 30;
	
	polygons[1].x = 287;
	polygons[1].y = 47;
	
	polygons[2].x = 714;
	polygons[2].y = 64;
	
	polygons[3].x = 1262;
	polygons[3].y = 81;
	
	polygons[4].x = 1549;
	polygons[4].y = 98;
	
	polygons[5].x = 1836;
	polygons[5].y = 115;
	
	polygons[6].x = 2124;
	polygons[6].y = 132;
	
	polygons[7].x = 2571;
	polygons[7].y = 150;
	
	polygons[8].x = 2714;
	polygons[8].y = 110;
	
	polygons[9].x = 2857;
	polygons[9].y = 69;
	
	polygons[10].x = 3000;
	polygons[10].y = 30;
	
	return new WrappedPolyFunction(polygons, 11, 3000, 1500);
}

WrappedPolyFunction* LegPolyFunctionFactory::createFr()
{
	Vector2_t* polygons = new Vector2_t[11];
	
	polygons[0].x = 0;
	polygons[0].y = 30;
	
	polygons[1].x = 377;
	polygons[1].y = 47;
	
	polygons[2].x = 734;
	polygons[2].y = 64;
	
	polygons[3].x = 1022;
	polygons[3].y = 81;
	
	polygons[4].x = 1309;
	polygons[4].y = 98;
	
	polygons[5].x = 1996;
	polygons[5].y = 115;
	
	polygons[6].x = 2284;
	polygons[6].y = 132;
	
	polygons[7].x = 2571;
	polygons[7].y = 150;
	
	polygons[8].x = 2714;
	polygons[8].y = 110;
	
	polygons[9].x = 2857;
	polygons[9].y = 69;
	
	polygons[10].x = 3000;
	polygons[10].y = 30;
	
	return new WrappedPolyFunction(polygons, 11, 3000, 2250);
}

WrappedPolyFunction* LegPolyFunctionFactory::createFl()
{
	Vector2_t* polygons = new Vector2_t[11];
	
	polygons[0].x = 0;
	polygons[0].y = 30;
	
	polygons[1].x = 447;
	polygons[1].y = 47;
	
	polygons[2].x = 734;
	polygons[2].y = 64;
	
	polygons[3].x = 1022;
	polygons[3].y = 81;
	
	polygons[4].x = 1309;
	polygons[4].y = 98;
	
	polygons[5].x = 1836;
	polygons[5].y = 115;
	
	polygons[6].x = 2284;
	polygons[6].y = 132;
	
	polygons[7].x = 2571;
	polygons[7].y = 150;
	
	polygons[8].x = 2714;
	polygons[8].y = 110;
	
	polygons[9].x = 2857;
	polygons[9].y = 69;
	
	polygons[10].x = 3000;
	polygons[10].y = 30;
	
	return new WrappedPolyFunction(polygons, 11, 3000, 750);
}
