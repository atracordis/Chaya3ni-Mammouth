/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.backOffice;

import GUI.Interfaces.Main;
import Services.UserService;
import Tools.Show;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class MonthBarChartController extends AnchorPane {
    private ObservableList data;
    private Main application;
    @FXML
    private BarChart<String, Integer>  barChart;
    @FXML
    private CategoryAxis xAxis;

    /**
     * Initializes the controller class.
     * @param application
     */
    public void setApp(Main application) {
        this.application = application;

    }

    public void initialize() {
        try {
            UserService us = new UserService() ;
            data = FXCollections.observableArrayList();         
            ObservableList<String> monthNames = FXCollections.observableArrayList();

            ResultSet rs=us.selectCountPerMonth();
            String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
            System.out.println(months);
            monthNames.addAll(Arrays.asList(months));
            xAxis.setCategories(monthNames);
            
            int[] monthCounter = new int[12];
            int j=0;
            while (rs.next()){
               
            int month = rs.getInt(1);
            monthCounter[j]=month;
            j++;
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

            for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }


            

            barChart.getData().add(series);                
        } catch (SQLException ex) {
            Logger.getLogger(MonthBarChartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void processBackToMenu(ActionEvent event) {
        application.gotoUserStatMenu();
    }
    
    @FXML
    private void processPrint(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();
 if(job != null){
   job.showPrintDialog(application.getStage()); // Window must be your main Stage
   job.printPage(barChart);
   job.endJob();
 }

    }
    
}
