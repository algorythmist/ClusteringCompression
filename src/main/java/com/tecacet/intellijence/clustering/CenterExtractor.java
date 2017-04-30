package com.tecacet.intellijence.clustering;

import java.util.List;

public interface CenterExtractor<T> {

	List<T> computeCenters(List<T> dataPoints, int[] memberships, int clusters);

}