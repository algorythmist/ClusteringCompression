package com.tecacet.intellijence.clustering;

import java.util.ArrayList;
import java.util.List;

public class MenoidCenterExtractor<T> implements CenterExtractor<T> {

	private final double[][] distances;

	

	public MenoidCenterExtractor(double[][] distances) {
		super();
		this.distances = distances;
	}

	@Override
	public List<T> computeCenters(List<T> dataPoints, int[] memberships, int clusters) {
		List<Integer>[] byIndex = new List[clusters];
		for (int i = 0; i < dataPoints.size(); i++) {
			if (byIndex[memberships[i]] == null) {
				byIndex[memberships[i]] = new ArrayList<>();
			}
			byIndex[memberships[i]].add(i);
		}
		List<T> centers = new ArrayList<>();
		for (int i = 0; i < clusters; i++) {
			int index = computeCenter(byIndex[i]);
			centers.add(dataPoints.get(index));
		}
		return centers;
	}

	public int computeCenter(List<Integer> cluster) {
		
		double min = Double.MAX_VALUE;
		int bestCenter = 0;
		for (int i = 0; i < cluster.size(); i++) {
			int p1 = cluster.get(i);
			double distance = 0.0; // the total distance from p1
			for (int j = 0; j < cluster.size(); j++) {
				int p2 = cluster.get(j);
				distance += distances[p1][p2];
				if (distance > min) { // skip if already too big
					continue;
				}
			}
			if (distance < min) {
				min = distance;
				bestCenter = p1;
			}
		} // for each point

		return bestCenter;
	}

}
