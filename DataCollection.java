package org.example;

import java.util.ArrayList;
import java.util.List;

public class DataCollection {
    private static final List<Double> moistureData = new ArrayList<>();

    public void addMoistureData(double value) {
        moistureData.add(value);
    }

    public List<Double> getMoistureData() {
        return moistureData;
    }

    public double getLatestValue() {
        return moistureData.isEmpty() ? 0 : moistureData.get(moistureData.size() - 1);
    }
}
