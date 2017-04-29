package com.tecacet.intellijence.clustering;


public class MeanCenterExtractor implements CenterExtractor<double[]> {

	public double[] computeCenter(Cluster<double[]> cluster) {
		if (cluster.isEmpty()) {
			throw new IllegalArgumentException("Cluster cannot be empty.");
		}
		double[] v = cluster.getMembers().get(0);
		double[] mean = new double[v.length];
		for (int i = 0; i < cluster.size(); i++) {
			v = cluster.getMembers().get(i);
			for (int j = 0; j < mean.length; j++) {
				mean[j] += v[j] / cluster.size();
			}
		}
		return mean;
	}

}
