package com.tecacet.intellijence.clustering;

import java.util.Arrays;
import java.util.List;

public class Clustering<T> {

	private final List<T> centers;
	private final int[] memberships;

	public Clustering(List<T> centers, int[] memberships) {
		super();
		this.centers = centers;
		this.memberships = memberships;
	}

	public List<T> getCenters() {
		return centers;
	}

	public int[] getMemberships() {
		return memberships;
	}

	public long getMembershipCount(int cluster) {
		return Arrays.stream(memberships).filter(i -> i==cluster).count();
	}
	
}
