/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.backOffice;

import GUI.Interfaces.*;
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
public class SmokersPieChartController extends AnchorPane {
    private ObservableList data;
    private ObservableList data2;
    private Main application;
    @FXML
    private PieChart pieChart;

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
            ResultSet rs=us.selectSmokers();
            while(rs.next())
                data.add(new PieChart.Data(rs.getString(1)+" "+rs.getString(2)+"s",rs.getDouble(1)));
            pieChart.getData().addAll(data);
            
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
   job.printPage(pieChart);
   job.endJob();
 }

    }
    
}
