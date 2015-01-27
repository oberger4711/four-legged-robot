package com.oberger.kruppelbotsimulation.util;

public interface IReadOnlyVector2 {

	float getX();

	float getY();
        
        float getLength();

	@Override
	boolean equals(Object obj);

	@Override
	int hashCode();

}