/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.backOffice;

import GUI.Interfaces.Main;
import Services.UserService;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.DrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class LineChartController extends AnchorPane {
    private ObservableList data;
    private Main application;
    @FXML
    private SwingNode swingNode;
    
    /**
     * Initializes the controller class.
     * @param application
     */
    public void setApp(Main application) {
        this.application = application;

    }

    public void initialize() {
        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
       swingNode.setContent(chartPanel);
    }

    private CategoryDataset createDataset() {
        
        try {
            // row keys...
            final String series1 = "Year subscriptions of animal owners ";
            final String series2 = "Yearly subscriptions";
            
            // column keys...
            
            // create the dataset...
            final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            UserService us = new UserService();
            ResultSet res1 = us.selectAnimalYear();
            ResultSet res2 = us.selectCountPerYear();

            
            while (res1.next()){
            dataset.addValue((Number)res1.getDouble(2), series1, res1.getString(1));}
            
            
            while (res2.next()){
            dataset.addValue((Number)res2.getDouble(1), series2, res2.getString(2));}
            
            return dataset;
        } catch (SQLException ex) {
            Logger.getLogger(LineChartController.class.getName()).log(Level.SEVERE, null, ex);
        }
     return null;   
    }

    /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return a chart.
     */    
    private JFreeChart createChart(final CategoryDataset dataset) {

        final JFreeChart chart = ChartFactory.createLineChart(
            "Subscriptions vs subscriptions of animal owners",      // chart title
            "Years",                   // domain axis label
            "Subscriptions",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

//        final StandardLegend legend = (StandardLegend) chart.getLegend();
  //      legend.setDisplaySeriesShapes(true);

        final Shape[] shapes = new Shape[3];
        int[] xpoints;
        int[] ypoints;

        // right-pointing triangle
        xpoints = new int[] {-3, 3, -3};
        ypoints = new int[] {-3, 0, 3};
        shapes[0] = new Polygon(xpoints, ypoints, 3);

        // vertical rectangle
        shapes[1] = new Rectangle2D.Double(-2, -3, 3, 6);

        // left-pointing triangle
        xpoints = new int[] {-3, 3, 3};
        ypoints = new int[] {0, -3, 3};
        shapes[2] = new Polygon(xpoints, ypoints, 3);

        final DrawingSupplier supplier = new DefaultDrawingSupplier(
            DefaultDrawingSupplier.DEFAULT_PAINT_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
            shapes
        );
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setDrawingSupplier(supplier);

        chart.setBackgroundPaint(Color.yellow);

        // set the stroke for each series...
        plot.getRenderer().setSeriesStroke(
            0, 
            new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 
                1.0f, new float[] {10.0f, 6.0f}, 0.0f
            )
        );
        plot.getRenderer().setSeriesStroke(
            1, 
            new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {6.0f, 6.0f}, 0.0f
            )
        );
        plot.getRenderer().setSeriesStroke(
            2, 
            new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {2.0f, 6.0f}, 0.0f
            )
        );

        // customise the renderer...
        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
//        renderer.setDrawShapes(true);
        renderer.setItemLabelsVisible(true);
  //      renderer.setLabelGenerator(new StandardCategoryLabelGenerator());

        // customise the range axis...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(false);
        rangeAxis.setUpperMargin(0.12);

        return chart;
        
    }

    @FXML
    private void processBack(ActionEvent event) {
        application.gotoUserStatMenu();
    }

    @FXML
    private void processPrint(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();
 if(job != null){
   job.showPrintDialog(application.getStage()); // Window must be your main Stage
   job.printPage(swingNode);
   job.endJob();
 }
    }
    
    
}
