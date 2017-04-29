package com.tecacet.intellijence.clustering;


/** 
 * Computes the center of a cluster
 *
 * @param <T>
 */
public interface CenterExtractor<T> {

	T computeCenter(Cluster<T> cluster);
	
}
