package org.example;

public class MoistureCalculator {
    private final double min = 495.0;
    private final double max = 740.0;

    public double convertToPercentage(double value) {
        if (value > max) return 0;
        if (value < min) return 100;
        return ((max - value) / (max - min)) * 100.0;
    }
}
