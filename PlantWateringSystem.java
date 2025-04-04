package org.example;

import org.firmata4j.PinEventListener;
import org.firmata4j.Pin;

public class PlantWateringSystem implements PinEventListener {
    private final PumpControl pump;
    private final int buttonPin;

    public PlantWateringSystem(PumpControl pump, int buttonPin) {
        this.pump = pump;
        this.buttonPin = buttonPin;
    }

    @Override
    public void onValueChange(Pin pin) {
        if (pin.getIndex() == buttonPin) {
            if (pin.getValue() == 1) {
                pump.toggleManualMode();
            }
        }
    }

    @Override
    public void onModeChange(Pin pin, Pin.Mode mode) {}
}
