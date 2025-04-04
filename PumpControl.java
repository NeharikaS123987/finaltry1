package org.example;

import org.firmata4j.Pin;

public class PumpControl {
    private final Pin pumpPin;
    private boolean manualMode = false;
    private boolean pumpOn = false;

    public PumpControl(Pin pumpPin) {
        this.pumpPin = pumpPin;
    }

    public void toggleManualMode() {
        manualMode = !manualMode;
        System.out.println("Manual Mode: " + (manualMode ? "ON" : "OFF"));
    }

    public boolean isManualMode() {
        return manualMode;
    }

    public boolean isPumpOn() {
        return pumpOn;
    }

    public void decidePump(double moisturePercent) throws Exception {
        if (manualMode) {
            pumpPin.setValue(1);
            pumpOn = true;
        } else {
            if (moisturePercent < 30) {
                pumpPin.setValue(1);
                pumpOn = true;
            } else {
                pumpPin.setValue(0);
                pumpOn = false;
            }
        }
    }
}
