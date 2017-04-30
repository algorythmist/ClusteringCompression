package com.tecacet.intellijence.clustering;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class MeanCenterExtractorTest {

	@Test
	public void testComputeCenters() {
		CenterExtractor<double[]> meanCenterExtractor = new MeanCenterExtractor();
		int clusters = 2;
		List<double[]> dataPoints = Arrays.asList(new double[] { 1., 0. }, new double[] { 5., 5. },
				new double[] { 0., 1.0 }, new double[] { 10., 5.0 });
		int[] memberships = new int[] { 1, 0, 1, 0 };
		List<double[]> centers = meanCenterExtractor.computeCenters(dataPoints, memberships, clusters);
		double[] center1 = centers.get(0);
		double[] center2 = centers.get(1);
		assertEquals("[7.5, 5.0]", Arrays.toString(center1));
		assertEquals("[0.5, 0.5]", Arrays.toString(center2));

	}

}
