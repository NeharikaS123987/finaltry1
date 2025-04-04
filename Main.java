package org.example;

import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;

import java.util.Timer;

public class Main {
    private static final String PORT = "/dev/tty.usbmodemXXXX"; // Update to your actual port (e.g., COM3 or /dev/tty.usbmodem14101)

    public static void main(String[] args) throws Exception {
        IODevice device = new FirmataDevice(PORT);
        device.start();
        device.ensureInitializationIsDone();

        DisplayDataOnOLED display = new DisplayDataOnOLED();

        Pin moistureSensor = device.getPin(14); // A0
        moistureSensor.setMode(Pin.Mode.ANALOG);

        Pin pumpPin = device.getPin(2); // D2
        pumpPin.setMode(Pin.Mode.OUTPUT);

        Pin buttonPin = device.getPin(6); // D6
        buttonPin.setMode(Pin.Mode.INPUT);
        buttonPin.setTriggerValue(0); // falling edge (button press)

        PumpControl pump = new PumpControl(pumpPin);
        DataCollection data = new DataCollection();
        MoistureCalculator calc = new MoistureCalculator();
        new PlotGraph(data, calc);

        // Use the PlantWateringSystem as the button listener (it works with your firmata4j)
        buttonPin.addEventListener(new PlantWateringSystem(pump, buttonPin.getIndex()));

        // Timer to check soil and operate pump
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MoistureCheckTask(moistureSensor, pump, display, data, calc), 0, 1000);
    }
}
