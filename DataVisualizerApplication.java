package com.yourusername.datavisualizer;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataVisualizerApplication {

    public static void main(String[] args) {
        // Load data from CSV
        List<double[]> data = loadDataFromCSV("sample-data/sample.csv");

        if (data.isEmpty()) {
            System.out.println("No data loaded.");
            return;
        }

        // Separate columns
        double[] column1 = data.stream().mapToDouble(row -> row[0]).toArray();
        double[] column2 = data.stream().mapToDouble(row -> row[1]).toArray();

        // Perform statistical analysis on the first column
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (double value : column1) {
            stats.addValue(value);
        }

        System.out.println("Statistics for the first column:");
        System.out.println("Mean: " + stats.getMean());
        System.out.println("Standard Deviation: " + stats.getStandardDeviation());

        // Create a histogram of the first column
        createHistogram(column1, "Column1_Distribution", "histogram_column1.png");

        // Create a scatter plot of both columns
        createScatterPlot(column1, column2, "Column1 vs Column2", "scatter_plot.png");
    }

    private static List<double[]> loadDataFromCSV(String filePath) {
        List<double[]> data = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                double[] row = new double[values.length];
                for (int i = 0; i < values.length; i++) {
                    row[i] = Double.parseDouble(values[i]);
                }
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static void createHistogram(double[] data, String title, String fileName) {
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Data", data, 10);

        JFreeChart histogram = ChartFactory.createHistogram(
                title,
                "Value",
                "Frequency",
                dataset);

        saveChart(histogram, fileName);
    }

    private static void createScatterPlot(double[] xData, double[] yData, String title, String fileName) {
        XYSeries series = new XYSeries("Data");
        for (int i = 0; i < xData.length; i++) {
            series.add(xData[i], yData[i]);
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);

        JFreeChart scatterPlot = ChartFactory.createScatterPlot(
                title,
                "X-Axis",
                "Y-Axis",
                dataset);

        saveChart(scatterPlot, fileName);
    }

    private static void saveChart(JFreeChart chart, String fileName) {
        try {
            ChartUtils.saveChartAsPNG(new File(fileName), chart, 500, 300);
            System.out.println("Chart saved as " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
