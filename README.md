# Data-Visualizer

**Unlocking Data Insights - A Custom Java Application for Large-Scale Analysis**

Data-Visualizer is a powerful Java application designed to extract meaningful insights from large and complex datasets. It provides robust tools for data cleansing, transformation, and visualization, making data analysis more accessible and actionable. By leveraging the capabilities of Apache Commons Math and JFreeChart, this application offers both statistical rigor and impactful graphical representations.

## Features

*   **Data Cleansing:** Implements robust algorithms to handle missing values, inconsistencies, and outliers in your dataset.
*   **Data Transformation:** Offers sophisticated transformation techniques to enhance data clarity and prepare it for analysis.
*   **Statistical Analysis:** Utilizes the Apache Commons Math library for rigorous statistical computations. [6]
*   **Data Visualization:** Creates clear and actionable visualizations using the JFreeChart library, including histograms, scatter plots, and line charts. [12, 19]
*   **Customizable:** The application is designed to be extensible, allowing you to add new data processing modules and visualization types.

## Installation

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/your-username/Data-Visualizer.git
    ```
2.  **Navigate to the project directory:**
    ```bash
    cd Data-Visualizer
    ```
3.  **Compile the source code:**
    This project uses Maven for dependency management. To build the project, run:
    ```bash
    mvn clean install
    ```

## Usage

To use the application, you can run the `main` method in the `com.yourusername.datavisualizer.DataVisualizerApplication` class.

Here is a basic example of how to use the application with a sample dataset:

```java
package com.yourusername.datavisualizer;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;

import java.io.File;
import java.io.IOException;

public class DataVisualizerApplication {

    public static void main(String[] args) {
        // Sample data
        double[] data = {12.5, 18.3, 11.2, 19.0, 22.1, 14.7, 15.8, 16.3, 13.9, 17.5};

        // Perform statistical analysis using Apache Commons Math
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (double value : data) {
            stats.addValue(value);
        }

        System.out.println("Mean: " + stats.getMean());
        System.out.println("Standard Deviation: " + stats.getStandardDeviation());

        // Create a histogram using JFreeChart
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Data", data, 10);

        JFreeChart histogram = ChartFactory.createHistogram(
                "Data Distribution",
                "Value",
                "Frequency",
                dataset);

        try {
            ChartUtils.saveChartAsPNG(new File("histogram.png"), histogram, 500, 300);
            System.out.println("Histogram saved as histogram.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
