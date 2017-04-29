package com.tecacet.intellijence.clustering;

/**
 * A metric gives a measure of distance between two elements. A metric D must
 * satisfy the following contract:
 * 
 * D(x,y) >= 0 for all x,y D(x,y) = 0 if and only if x.equals(y) D(x,y) = D(y,x)
 * for any two points x,y (symmetry) D(x,y)+D(y,z) <= D(x,z) (triangle
 * inequality)
 * 
 * @param <T>
 *            the element in the metrizable space
 */
public interface Metric<T> {
	/** The distance between two vectors */
	double distance(T v1, T v2);
}