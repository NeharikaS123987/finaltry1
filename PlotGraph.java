package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class PlotGraph {
    private final DataCollection data;
    private final MoistureCalculator calculator;
    private final XYSeries series;
    private int x = 0;

    public PlotGraph(DataCollection data, MoistureCalculator calculator) {
        this.data = data;
        this.calculator = calculator;
        this.series = new XYSeries("Moisture %");
        setupGraph();
    }

    private void setupGraph() {
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Moisture Over Time", "Time (s)", "Moisture %", dataset);

        JFrame frame = new JFrame("Live Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new ChartPanel(chart));
        frame.setVisible(true);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                double latest = calculator.convertToPercentage(data.getLatestValue());
                series.add(x++, latest);
            }
        }, 0, 1000);
    }
}
