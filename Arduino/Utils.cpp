int interpolate(double xStart, double yStart, double xEnd, double yEnd, double t)
{
    double a = (yEnd - yStart) / (xEnd - xStart);
    double c = yEnd - a * xEnd;

    return a * t + c;
}

int interpolateSquare(double xPoint, double yPoint, double xVertex, double yVertex, double t)
{
    double a = (yPoint - yVertex) / ((xPoint - xVertex) * (xPoint - xVertex));
    double bracket = t - xVertex;

    return a * bracket * bracket + yVertex;
}

int max(unsigned int i1, unsigned int i2)
{
    int max = i1;
    if (i2 > i1)
    {
        max = i2;
    }
    
    return max;
}
