package com.example.hypertensionchecker.presentation.theme;

import java.util.*;
import java.sql.*;
import java.sql.DriverManager;

public class KNN {
    // Define instance variables
    private int k;
    private List<int[]> features;
    private List<Integer> labels;

    // Constructor
    public KNN(int k) {
        this.k = k;
        this.features = new ArrayList<>();
        this.labels = new ArrayList<>();
    }

    // Add a data point to the model
    public void addDataPoint(int[] feature, int label) {
        this.features.add(feature);
        this.labels.add(label);
    }

    // Calculate the Euclidean distance between two points
    private double distance(int[] x1, int[] x2) {
        double sum = 0.0;
        for (int i = 0; i < x1.length; i++) {
            sum += Math.pow(x1[i] - x2[i], 2);
        }
        return Math.sqrt(sum);
    }

    // Classify a new data point
    public int classify(int[] feature) {
        // Calculate distances to all data points
        List<Double> distances = new ArrayList<>();
        for (int[] x : this.features) {
            distances.add(distance(x, feature));
        }

        // Find the k nearest neighbors
        List<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < this.k; i++) {
            int minIndex = distances.indexOf(Collections.min(distances));
            neighbors.add(this.labels.get(minIndex));
            distances.set(minIndex, Double.POSITIVE_INFINITY);
        }

        // Return the most common class label among the neighbors
        int maxLabel = 0;
        int maxCount = 0;
        Set<Integer> uniqueLabels = new HashSet<>(neighbors);
        for (int label : uniqueLabels) {
            int count = Collections.frequency(neighbors, label);
            if (count > maxCount) {
                maxCount = count;
                maxLabel = label;
            }
        }
        return maxLabel;
    }


}
