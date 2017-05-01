package com.tecacet.intellijence.clustering;

import java.util.List;

public class DistanceMatrixCalculator<T> {

	private final Metric<T> metric;

	public DistanceMatrixCalculator(Metric<T> metric) {
		super();
		this.metric = metric;
	}

	public double[][] computeDistances(List<T> points) {
		int size = points.size();
		double d[][] = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = i; j < size; j++) {
				d[j][i] = d[i][j] = metric.distance(points.get(i), points.get(j));
			}
		}
		return d;
	}
}
