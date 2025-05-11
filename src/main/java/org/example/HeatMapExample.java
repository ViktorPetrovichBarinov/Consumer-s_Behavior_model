package org.example;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.GrayPaintScale;
import org.jfree.chart.renderer.PaintScale;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;

public class HeatMapExample extends ApplicationFrame {
    public HeatMapExample(String title, int[][] data) {
        super(title);
        JPanel chartPanel = createChartPanel(data);
        chartPanel.setPreferredSize(new Dimension(600, 600));
        setContentPane(chartPanel);
    }

    private JPanel createChartPanel(int[][] data) {
        DefaultXYZDataset dataset = new DefaultXYZDataset();

        int rows = data.length;
        int cols = data[0].length;
        int total = rows * cols;

        double[] x = new double[total];
        double[] y = new double[total];
        double[] z = new double[total];

        int idx = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                x[idx] = i;
                y[idx] = j;
                z[idx] = data[i][j];
                idx++;
            }
        }

        dataset.addSeries("HeatMap", new double[][]{x, y, z});

        XYBlockRenderer renderer = new XYBlockRenderer();
        renderer.setBlockWidth(1.0);
        renderer.setBlockHeight(1.0);

        PaintScale paintScale = new GrayPaintScale(findMin(z), findMax(z));
        renderer.setPaintScale(paintScale);

        NumberAxis xAxis = new NumberAxis("X");
        NumberAxis yAxis = new NumberAxis("Y");
        yAxis.setInverted(true);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setBackgroundPaint(Color.white);

        JFreeChart chart = new JFreeChart("Heatmap", JFreeChart.DEFAULT_TITLE_FONT, plot, false);

        return new ChartPanel(chart);
    }

    private double findMin(double[] data) {
        double min = Double.MAX_VALUE;
        for (double d : data) {
            if (d < min) min = d;
        }
        return min;
    }

    private double findMax(double[] data) {
        double max = Double.MIN_VALUE;
        for (double d : data) {
            if (d > max) max = d;
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] data = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        HeatMapExample example = new HeatMapExample("Heatmap Example", data);
        example.pack();
        example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        example.setVisible(true);
    }
}
