package com.tecacet.intellijence.clustering;

import java.util.ArrayList;
import java.util.List;

/**
 * K-means clustering algorithm
 */
public class KMeansClusterer<T> {

	protected static final int DEFALT_MAX_ITERATIONS = 1000;

	protected static final double DEFAULT_TOLERANCE = 0.0001;

	/* number of clusters to maintain */
	private final int numberOfClusters;

	/* list of current clusters */
	private List<T> centers;

	private final Metric<T> metric;
	private final CenterSelector<T> centerSelector;
	private final CenterExtractor<T> centerExtractor;

	private int maxIterations;
	private double tolerance;

	public KMeansClusterer(int clusters, Metric<T> metric,
			CenterExtractor<T> extractor) {
		this(clusters, metric, extractor, new RandomCenterSelector<T>());
	}

	public KMeansClusterer(int clusters, Metric<T> metric,
			CenterExtractor<T> extractor, CenterSelector<T> selector) {
		this.numberOfClusters = clusters;
		this.centerExtractor = extractor;
		this.metric = metric;
		this.centerSelector = selector;
	}

	/**
	 * Main algorithm
	 * 
	 * @return list of centers of clusters
	 */
	public List<T> cluster(List<T> dataPoints) {
		// choose initial centers
		this.centers = centerSelector.chooseInitialCenters(dataPoints,
				numberOfClusters);

		List<T> centerList = centers;
		int iter;
		for (iter = 0; iter < DEFALT_MAX_ITERATIONS; iter++) {
			centerList = clusteringStep(dataPoints);
			if (converged(centerList)) {
				break;
			} else {
				this.centers = centerList;
			}
		}
		return centers;
	}

	/*
	 * condition for convergence is that the centers have not moved perceptibly
	 */
	protected boolean converged(List<T> centerList) {
		for (int i = 0; i < centerList.size(); i++) {
			T oldCenter = this.centers.get(i);
			T newCenter = centerList.get(i);
			double distance = metric.distance(oldCenter, newCenter);
			if (distance > tolerance) {

				return false;
			}
		}
		return true;
	}

	/**
	 * Assigns membership values to each point and computes new averages
	 * 
	 * @return a list of the new centers
	 */
	public List<T> clusteringStep(List<T> dataPoints) {
		List<Cluster<T>> memberships = getMemberships(dataPoints);

		// adjust the centers
		ArrayList<T> newCenters = new ArrayList<T>(centers.size());
		for (int i = 0; i < centers.size(); i++) {
			T center = centers.get(i);
			T newCenter;
			if (memberships.get(i).isEmpty()) {
				newCenter = center;
			} else {
				newCenter = centerExtractor.computeCenter(memberships.get(i));
			}
			newCenters.add(newCenter);
		}
		return newCenters;
	}

	public List<Cluster<T>> getMemberships(List<T> dataPoints) {
		List<Cluster<T>> memberships = new ArrayList<Cluster<T>>(
				numberOfClusters);
		for (int i = 0; i < numberOfClusters; i++) {
			Cluster<T> cluster = new DefaultCluster<T>();
			memberships.add(cluster);
		}

		for (T p : dataPoints) {
			int centerIndex = findClosestCenter(p);
			memberships.get(centerIndex).add(p);
		}
		return memberships;
	}

	public int findClosestCenter(T p) {
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

	public int getMaxIterations() {
		return maxIterations;
	}

	public void setMaxIterations(int maxIterations) {
		this.maxIterations = maxIterations;
	}

	public double getTolerance() {
		return tolerance;
	}

	public void setTolerance(double tolerance) {
		this.tolerance = tolerance;
	}

}
