package com.tecacet.intellijence.clustering;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MenoidCenterExtractorTest {

	@Test
	public void testComputeCenter() {
		
		double[] p1 = new double[] { 1.0, 0.0, 0.0 };
		double[] p2 = new double[] { 1.0, 1.0, 0.0 };
		double[] p3 = new double[] { 1.0, 1.0, 1.0 };

		double[] p4 = new double[] { 2.0, 0.0, 0.0 };
		double[] p5 = new double[] { 2.0, 1.0, 0.0 };
		double[] p6 = new double[] { 3.0, 3.0, 3.0 };
		List<double[]> points = Arrays.asList(p1, p2, p3, p4, p5, p6);
		Metric<double[]> metric = new EuclideanMetric();
		DistanceMatrixCalculator<double[]> matrixCalculator = new DistanceMatrixCalculator<>(metric);
		CenterExtractor<double[]> extractor = new MenoidCenterExtractor<double[]>(matrixCalculator.computeDistances(points));
		int[] memberships = new int[] { 0, 0, 0, 1, 1, 1 };
		List<double[]> centers = extractor.computeCenters(points, memberships, 2);
		assertEquals(2, centers.size());
		assertEquals("[1.0, 1.0, 0.0]", Arrays.toString(centers.get(0)));
		assertEquals("[2.0, 1.0, 0.0]", Arrays.toString(centers.get(1)));
	}

}
