package org.example;

public class DisplayDataOnOLED {
    public void displayMoisture(double percent, boolean pumpOn, boolean manual) {
        System.out.println("========== OLED DISPLAY ==========");
        System.out.printf("Moisture: %.1f%%\n", percent);
        System.out.println("Pump: " + (pumpOn ? "ON" : "OFF"));
        System.out.println("Mode: " + (manual ? "MANUAL" : "AUTO"));
        System.out.println("==================================\n");
    }
}
