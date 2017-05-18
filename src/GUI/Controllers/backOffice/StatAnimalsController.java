/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.backOffice;

import Services.ServiceRideAnimals;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author elloumiaymen
 */
public class StatAnimalsController implements Initializable {

    @FXML
    private PieChart pieChart;
        private ObservableList datachart;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         PieChart pieChart = new PieChart();
        try {
            buildData();
        } catch (SQLException ex) {
            Logger.getLogger(StatAnimalsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.pieChart.getData().addAll(datachart);
    }    
    public void buildData() throws SQLException {
        datachart = FXCollections.observableArrayList();
        ServiceRideAnimals RideService = new ServiceRideAnimals();
        ResultSet rs = RideService.chartSpecies();
        while (rs.next()) {
            //adding data on piechart data   
            datachart.add(new PieChart.Data(rs.getString(2), rs.getInt(1)));

        }
    
}
}
