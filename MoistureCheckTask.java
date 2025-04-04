package org.example;

import org.firmata4j.Pin;

import java.util.TimerTask;

public class MoistureCheckTask extends TimerTask {
    private final Pin moistureSensor;
    private final PumpControl pump;
    private final DisplayDataOnOLED display;
    private final DataCollection data;
    private final MoistureCalculator calc;

    public MoistureCheckTask(Pin moistureSensor, PumpControl pump, DisplayDataOnOLED display, DataCollection data, MoistureCalculator calc) {
        this.moistureSensor = moistureSensor;
        this.pump = pump;
        this.display = display;
        this.data = data;
        this.calc = calc;
    }

    @Override
    public void run() {
        try {
            double raw = moistureSensor.getValue();
            double percent = calc.convertToPercentage(raw);

            data.addMoistureData(raw);
            pump.decidePump(percent);
            display.displayMoisture(percent, pump.isPumpOn(), pump.isManualMode());
        } catch (Exception e) {
            System.out.println("Sensor error: " + e.getMessage());
        }
    }
}
