package com.tecacet.intellijence.clustering;

import java.util.List;

public class EfficientClusterer<T> {
	
	private static final int DEFALT_MAX_ITERATIONS = 1000;
	private static final double DEFAULT_TOLERANCE = 0.0001;

	private final CenterSelector<T> centerSelector = new RandomCenterSelector<>();

	private final Metric<T> metric;
	private final CenterExtractor<T> centerExtractor;

	public EfficientClusterer(Metric<T> metric, CenterExtractor<T> centerExtractor) {
		super();
		this.metric = metric;
		this.centerExtractor = centerExtractor;
	}

	public Clustering<T> cluster(List<T> dataPoints, int clusters) {

		// choose initial centers
		List<T> centers = centerSelector.chooseInitialCenters(dataPoints, clusters);

		Clustering<T> clustering = new Clustering<>(centers, new int[dataPoints.size()]);
		int iter;
		for (iter = 0; iter < DEFALT_MAX_ITERATIONS; iter++) {
			clustering = clusteringStep(dataPoints, clustering);
			if (converged(centers, clustering.getCenters())) {
				System.out.println("Clustering converged " + iter);
				break;
			}
			centers = clustering.getCenters();
		}
		return clustering;
	}

	private Clustering<T> clusteringStep(List<T> dataPoints, Clustering<T> clustering) {
		// assign membership
		int[] memberships = getMemberships(dataPoints, clustering.getCenters(), clustering.getMemberships());
		int clusters = clustering.getCenters().size();
		// adjust the centers
		List<T> newCenters = centerExtractor.computeCenters(dataPoints, memberships, clusters);
		return new Clustering<>(newCenters, memberships);
	}

	private int[] getMemberships(List<T> dataPoints, List<T> centers, int[] memberships) {
														// array
		for (int i = 0; i < dataPoints.size(); i++) {
			T p = dataPoints.get(i);
			int centerIndex = findClosestCenter(p, centers);
			memberships[i] = centerIndex;
		}
		return memberships;
	}

	private int findClosestCenter(T p, List<T> centers) {
		double min = metric.distance(p, centers.get(0));
		int bestCenter = 0;
		for (int i = 1; i < centers.size(); i++) {
			T center = centers.get(i);
			double d = metric.distance(p, center);
			if (d < min) {
				bestCenter = i;
				min = d;
			}
		}
		return bestCenter;
	}

	/*
	 * condition for convergence is that the centers have not moved perceptibly
	 */
	private boolean converged(List<T> oldCenters, List<T> newCenters) {
		for (int i = 0; i < oldCenters.size(); i++) {
			T oldCenter = oldCenters.get(i);
			T newCenter = newCenters.get(i);
			double distance = metric.distance(oldCenter, newCenter);
			if (distance > DEFAULT_TOLERANCE) {
				return false;
			}
		}
		return true;
	}

}
