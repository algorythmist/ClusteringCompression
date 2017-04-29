package com.tecacet.intellijence.clustering;

import java.util.List;

public interface Cluster<T> extends Iterable<T> {

	void add(T point);

	boolean isEmpty();

	int size();

	boolean contains(T point);

	List<T> getMembers();

}