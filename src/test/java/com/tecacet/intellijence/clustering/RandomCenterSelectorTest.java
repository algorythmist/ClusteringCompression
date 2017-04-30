package com.tecacet.intellijence.clustering;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class RandomCenterSelectorTest {

	@Test(expected = IllegalArgumentException.class)
	public void testFewerPointsThanClusters() {
		double[] p1 = new double[] { 1, 1 };
		double[] p2 = new double[] { 2, 2 };
		RandomCenterSelector<double[]> centerSelector = new RandomCenterSelector<>();
		centerSelector.chooseInitialCenters(Arrays.asList(p1, p2), 3);
	}

	@Test
	public void testSelect() {
		Random generator = new Random();
		RandomCenterSelector<double[]> centerSelector = new RandomCenterSelector<>(generator);
		List<double[]> data = new ArrayList<>();
		for (int i = 0;i<10;i++) {
			double[] point = new double[] {generator.nextGaussian(), generator.nextGaussian()};
			data.add(point);
		}
		List<double[]> centers = centerSelector.chooseInitialCenters(data, 3);
		assertEquals(3, centers.size());
		centers.forEach(p -> assertTrue(data.contains(p)));
	}
}
