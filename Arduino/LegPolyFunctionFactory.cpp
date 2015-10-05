#include "/home/oberger/Projects/Kruppelbot/Arduino/LegPolyFunctionFactory.h"

WrappedPolyFunction* LegPolyFunctionFactory::createBr()
{
	Vector2_t* polygons = new Vector2_t[11];
	
	polygons[0].x = 0;
	polygons[0].y = 150;
	
	polygons[1].x = 477;
	polygons[1].y = 132;
	
	polygons[2].x = 764;
	polygons[2].y = 115;
	
	polygons[3].x = 1052;
	polygons[3].y = 98;
	
	polygons[4].x = 1339;
	polygons[4].y = 81;
	
	polygons[5].x = 1996;
	polygons[5].y = 64;
	
	polygons[6].x = 2284;
	polygons[6].y = 47;
	
	polygons[7].x = 2571;
	polygons[7].y = 30;
	
	polygons[8].x = 2714;
	polygons[8].y = 70;
	
	polygons[9].x = 2857;
	polygons[9].y = 110;
	
	polygons[10].x = 3000;
	polygons[10].y = 150;
	
	return new WrappedPolyFunction(polygons, 11, 3000, 0);
}

WrappedPolyFunction* LegPolyFunctionFactory::createBl()
{
	Vector2_t* polygons = new Vector2_t[11];
	
	polygons[0].x = 0;
	polygons[0].y = 150;
	
	polygons[1].x = 527;
	polygons[1].y = 132;
	
	polygons[2].x = 814;
	polygons[2].y = 115;
	
	polygons[3].x = 1102;
	polygons[3].y = 98;
	
	polygons[4].x = 1389;
	polygons[4].y = 81;
	
	polygons[5].x = 1996;
	polygons[5].y = 64;
	
	polygons[6].x = 2284;
	polygons[6].y = 47;
	
	polygons[7].x = 2571;
	polygons[7].y = 30;
	
	polygons[8].x = 2714;
	polygons[8].y = 70;
	
	polygons[9].x = 2857;
	polygons[9].y = 110;
	
	polygons[10].x = 3000;
	polygons[10].y = 150;
	
	return new WrappedPolyFunction(polygons, 11, 3000, 1500);
}

WrappedPolyFunction* LegPolyFunctionFactory::createFr()
{
	Vector2_t* polygons = new Vector2_t[11];
	
	polygons[0].x = 0;
	polygons[0].y = 150;
	
	polygons[1].x = 287;
	polygons[1].y = 132;
	
	polygons[2].x = 574;
	polygons[2].y = 115;
	
	polygons[3].x = 1252;
	polygons[3].y = 98;
	
	polygons[4].x = 1539;
	polygons[4].y = 81;
	
	polygons[5].x = 1826;
	polygons[5].y = 64;
	
	polygons[6].x = 2114;
	polygons[6].y = 47;
	
	polygons[7].x = 2571;
	polygons[7].y = 30;
	
	polygons[8].x = 2714;
	polygons[8].y = 70;
	
	polygons[9].x = 2857;
	polygons[9].y = 110;
	
	polygons[10].x = 3000;
	polygons[10].y = 150;
	
	return new WrappedPolyFunction(polygons, 11, 3000, 2250);
}

WrappedPolyFunction* LegPolyFunctionFactory::createFl()
{
	Vector2_t* polygons = new Vector2_t[11];
	
	polygons[0].x = 0;
	polygons[0].y = 150;
	
	polygons[1].x = 287;
	polygons[1].y = 132;
	
	polygons[2].x = 574;
	polygons[2].y = 115;
	
	polygons[3].x = 1212;
	polygons[3].y = 98;
	
	polygons[4].x = 1499;
	polygons[4].y = 81;
	
	polygons[5].x = 1786;
	polygons[5].y = 64;
	
	polygons[6].x = 2074;
	polygons[6].y = 47;
	
	polygons[7].x = 2571;
	polygons[7].y = 30;
	
	polygons[8].x = 2714;
	polygons[8].y = 70;
	
	polygons[9].x = 2857;
	polygons[9].y = 110;
	
	polygons[10].x = 3000;
	polygons[10].y = 150;
	
	return new WrappedPolyFunction(polygons, 11, 3000, 750);
}
