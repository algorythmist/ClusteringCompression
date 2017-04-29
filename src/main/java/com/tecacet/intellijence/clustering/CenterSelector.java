package com.tecacet.intellijence.clustering;

import java.util.List;

/**
 * Selects cluster centers from a set of data points.
 *
 * @param <T>
 */
public interface CenterSelector<T> {

	List<T> chooseInitialCenters(List<T> dataPoints, int clusters);
}
