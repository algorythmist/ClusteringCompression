package com.tecacet.intellijence.clustering;

import static org.junit.Assert.*;

import org.junit.Test;

public class EuclideanMetricTest {

	@Test
	public void testDistance() {
		Metric<double[]> metric = new EuclideanMetric();
		double d= metric.distance(new double[] {2., 1.}, new double[] {1. , 3.});
		assertEquals(5, d, 0.001);
	}

}
