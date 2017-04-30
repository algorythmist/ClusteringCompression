package com.tecacet.intellijence.clustering;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class GaussianExperimentClustering {

	private static final String[] NAMES = { "Red", "Green", "Blue" };

	public static void main(String[] args) {
		GaussianExperiment experiment = new GaussianExperiment(3, 2.0);

		Random randomGenerator = new Random();

		int samples = 1000;
		List<String> labels = new ArrayList<>(samples);
		List<double[]> data = new ArrayList<>(samples);

		for (int i = 0; i < samples; i++) {
			int c = randomGenerator.nextInt(3);
			labels.add(NAMES[c]);
			double[] point = experiment.nextSample(c);
			data.add(point);
		}

		EfficientClusterer<double[]> clusterer = new EfficientClusterer<>(new EuclideanMetric(),
				new MeanCenterExtractor());
		Clustering<double[]> clustering = clusterer.cluster(data, 3);
		int[] memberships = clustering.getMemberships();
		for (int cluster = 0; cluster < 3; cluster++) {
			String report = reportCounts(cluster, getRGB(cluster, memberships, labels));
			System.out.println(report);
		}

	}

	private static Map<String, Integer> getRGB(int cluster, int[] memberships, List<String> labels) {
		Map<String, Integer> counts = new TreeMap<>();
		for (int i = 0; i < labels.size(); i++) {
			if (memberships[i] == cluster) {
				String label = labels.get(i);
				Integer count = counts.get(label);
				if (count == null) {
					count = 0;
				}
				counts.put(label, count + 1);
			}
		}
		return counts;
	}

	public static String reportCounts(int cluster, Map<String, Integer> counts) {
		return "Cluster " + cluster + " contains " + counts.entrySet().stream()
				.map(e -> e.getValue() + " counts of " + e.getKey()).collect(Collectors.joining(","));

	}

}
