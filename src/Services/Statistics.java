/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author SALMA
 */


import Services.ServiceReviews;
import GUI.Controllers.frontOffice.ReviewsController;
         
import Entities.Reviews;
import java.sql.*;
import Tools.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;

/**
 *

 */
public class Statistics {

    Conn cnx;
    

    public Statistics() {
        cnx = Conn.getInstance();
    }

    public ObservableList<PieChart.Data> list_Stat() {
//        

        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();

        String req = "SELECT AVG(rating), name as nom from reviews R , users U WHERE R.idUser2=U.id Group by idUser2";
       
        try {
            PreparedStatement ps;
            ps = cnx.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

               list.add(new PieChart.Data(rs.getString(2),rs.getInt(1)));
            }

            return list;

        } catch (SQLException ex) {
            Logger.getLogger(Reviews.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }


}

