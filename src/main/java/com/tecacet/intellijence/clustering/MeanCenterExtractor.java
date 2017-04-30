package com.tecacet.intellijence.clustering;

import java.util.ArrayList;
import java.util.List;

public class MeanCenterExtractor implements CenterExtractor<double[]> {
	
	
	@Override
	public List<double[]> computeCenters(List<double[]> dataPoints, int[] memberships, int clusters) {
		double[][] sums = new double[clusters][];
		int[] sizes = new int[clusters];
		for (int i = 0; i < dataPoints.size(); i++) {
			int cluster = memberships[i];
			sizes[cluster]++;
			double[] datum = dataPoints.get(i); 
			if (sums[cluster] == null) {
				sums[cluster] = new double[datum.length];
			}
			addTo(sums[cluster], datum);

		}
		List<double[]> means = new ArrayList<>(clusters);
		for (int c = 0; c < clusters; c++) {
			divide(sums[c], sizes[c]);
			means.add(sums[c]);
		}
		return means;
	}

	private static void addTo(double[] v1, double[] v2) {
		for (int j = 0; j < v1.length; j++) {
			v1[j] += v2[j];
		}
	}
	
	private static void divide(double[] v1, double a) {
		for (int j = 0; j < v1.length; j++) {
			v1[j] /= a;
		}
	}
}
