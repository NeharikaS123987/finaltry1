package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class UnitTest {
    @Test
    public void testMoistureConversion() {
        MoistureCalculator calc = new MoistureCalculator();
        assertEquals(0.0, calc.convertToPercentage(740), 0.01);
        assertEquals(100.0, calc.convertToPercentage(495), 0.01);
        assertTrue(calc.convertToPercentage(600) > 50);
    }

    @Test
    public void testDataCollection() {
        DataCollection data = new DataCollection();
        data.addMoistureData(600);
        data.addMoistureData(650);
        assertEquals(2, data.getMoistureData().size());
    }

    @Test
    public void testManualMode() throws Exception {
        PumpControl pump = new PumpControl(null);
        assertFalse(pump.isManualMode());
        pump.toggleManualMode();
        assertTrue(pump.isManualMode());
    }
}
