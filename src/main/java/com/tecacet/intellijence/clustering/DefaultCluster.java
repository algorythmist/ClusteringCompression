package com.tecacet.intellijence.clustering;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultCluster<T> implements Cluster<T> {

	private List<T> members = new ArrayList<T>();

	public void add(T point) {
		members.add(point);
	}

	public boolean isEmpty() {
		return members.isEmpty();
	}

	public int size() {
		return members.size();
	}

	public T get(int i) {
		return members.get(i);
	}

	public boolean contains(T point) {
		return members.contains(point);
	}

	public Iterator<T> iterator() {
		return members.listIterator();
	}

	public List<T> getMembers() {
		return members;
	}
}
