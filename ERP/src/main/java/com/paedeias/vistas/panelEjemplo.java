/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import javax.swing.JFrame;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;


/**
 * A simple demonstration application showing how to create a pie chart using 
 * data from a {@link DefaultPieDataset}.
 */
public class panelEjemplo extends JPanel {

    /**
     * Default constructor.
     *
     * @param title  the frame title.
     */
    public panelEjemplo() {
    }

    /**
     * Creates a sample dataset.
     * 
     * @return A sample dataset.
     */
    private static CategoryDataset  createDataset(double ventas, double reservaciones, double anticipos, double egresos) {
        
        final String series1 = "Tus indicadores mensuales";
        
        
        final String category1 = "Devoluciones";
        final String category2 = "Anticipos";
        final String category3 = "Reservaciones";
        final String category4 = "Ventas";        
        
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(new Double(egresos),category1,series1);
        dataset.addValue(new Double(anticipos),category2,series1);
        dataset.addValue(new Double(reservaciones),category3,series1);
        dataset.addValue(new Double(ventas),category4,series1);
        
        return dataset;        
    }
    
    /**
     * Creates a chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return A chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {
        
        JFreeChart chart = ChartFactory.createBarChart(
            "",  // chart title
            "Área",
            "Valor",
            dataset,             // data
            PlotOrientation.VERTICAL,
            true,
            false,
            false
        );
        
        

        CategoryPlot  plot = (CategoryPlot) chart.getPlot();
        plot.setNoDataMessage("No hay información disponible");
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.black);
        plot.setRangeGridlinePaint(Color.black);
        
        ((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();        
        renderer.setDrawBarOutline(false);
        
        
        renderer.setSeriesPaint(1, new Color(255,255,0));
        renderer.setSeriesPaint(2, new Color(11,70,119));
        renderer.setSeriesPaint(3, new Color(143,195,19));
        renderer.setSeriesPaint(4, new Color(255,0,0));
        
        return chart;
        
    }
    
    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     * 
     * @return A panel.
     */
    public static JPanel createDemoPanel(double ventas, double reservaciones, double anticipos, double devoluciones) {
        JFreeChart chart = createChart(createDataset(ventas, reservaciones, anticipos, devoluciones));
        ChartPanel chartp = new ChartPanel(chart);
        chartp.setSize(916,545);
        chartp.setVisible(true);
        return chartp;
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
}

