/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.backOffice;

import GUI.Interfaces.*;
import GUI.Interfaces.Main;
import Services.UserService;
import Tools.Conn;
import Tools.Show;
import java.net.URL;
import java.sql.PreparedStatement;
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
public class RideDriverStatsController extends AnchorPane {
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
 public void buildData() {

        Conn cnx = Conn.getInstance();
        cnx.getConnection();

        data = FXCollections.observableArrayList();

        try {

            //SQL FOR SELECTING NATIONALITY OF CUSTOMER
            String SQL = "SELECT COUNT(id),citySource FROM ridedriver GROUP BY citySource";
            PreparedStatement ps;
            ps = cnx.getConnection().prepareStatement(SQL);
           

            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Show.me(rs.getString(2));
                Show.me(rs.getString(1));
                data.add(new PieChart.Data(rs.getString(2),rs.getDouble(1)));
            }

        } catch (Exception e) {

            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());

            return;

        }

    }
    public void initialize() {
        buildData();
        pieChart.getData().addAll(data);
    }    


    }
    

