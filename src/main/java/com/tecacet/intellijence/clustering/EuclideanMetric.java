package com.tecacet.intellijence.clustering;


/**
 * Standard Euclidean distance in finite dimensional vector space
 */
public class EuclideanMetric implements Metric<double[]> {
    /**
     * Euclidean distance squared to avoid the square root calculation
     */
    public double distance(double[] x, double[] y) {
        double d = 0.0;
        for (int i = 0; i < x.length; i++) {
            d += (x[i] - y[i]) * (x[i] - y[i]);
        }
        return d;
    }
}
