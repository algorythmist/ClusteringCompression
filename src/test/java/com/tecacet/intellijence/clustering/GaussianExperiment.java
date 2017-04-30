package com.tecacet.intellijence.clustering;

import java.util.Random;

public class GaussianExperiment {

	private Random random = new Random();

	private double[][] means;
	private double sd = .5;

	public GaussianExperiment(int classes, double spacing) {
		means = new double[classes][];
		// space means
		double m = -1.0;
		for (int c = 0; c < classes; c++) {
			means[c] = new double[] { m, m };
			m += spacing;
		}
	}

	public double getSd() {
		return sd;
	}

	public void setSd(double sd) {
		this.sd = sd;
	}

	public double[] nextSample(int c) {
		double[] sample = nextGaussian(means[c], sd);
		return sample;
	}

	private double[] nextGaussian(double[] means, double sd) {
		return new double[] { means[0]+ sd*random.nextGaussian(), means[1]+sd*random.nextGaussian() };
	}
}
