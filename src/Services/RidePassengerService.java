/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.*;
import java.util.*;
import Entities.*;
import Tools.*;
import Interfaces.RidePassengerInterface;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class RidePassengerService implements RidePassengerInterface {

    Conn cnx;

    public RidePassengerService() {
        this.cnx = Conn.getInstance();
    }

    @Override
    public void insertRidePassenger(RidePassenger p) {
        String requete = "INSERT INTO ridepassenger (price, citySource, placeSource, longSource, latSource, cityDestination, placeDestination, longDestination,latDestination, dateTimeRangeSource, dateTimeRangeDestination, handicap, animal, haveAnimal, haveLuggage, luggageMass, music, musicTaste, smoking, allowSmoking, idPassenger, type)"
                + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst;
        try {
            pst = cnx.getConnection().prepareStatement(requete);
            pst.setLong(1, p.getPrice());
            pst.setString(2, p.getCitySource());
            pst.setString(3, p.getPlaceSource());
            pst.setDouble(4, p.getLongSource());
            pst.setDouble(5, p.getLatSource());
            pst.setString(6, p.getCityDestination());
            pst.setString(7, p.getPlaceDestination());
            pst.setDouble(8, p.getLongDestination());
            pst.setDouble(9, p.getLatDestination());
            pst.setString(10, p.getDateTimeRangeSource());
            pst.setString(11, p.getDateTimeRangeDestination());
            pst.setInt(12, p.getHandicap());
            pst.setInt(13, p.getAnimal());
            pst.setInt(14, p.getHaveAnimal());
            pst.setInt(15, p.getHaveLuggage());
            pst.setFloat(16, p.getLuggageMass());
            pst.setInt(17, p.getMusic());
            pst.setString(18, p.getMusicTaste());
            pst.setInt(19, p.getSmoking());
            pst.setInt(20, p.getAllowSmoking());
            pst.setInt(21, 6);
            pst.setInt(22, 0);
            pst.executeUpdate();
            System.out.println("Trajet ajouté");
        } catch (SQLException ex) {
            Logger.getLogger(RidePassengerService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimerPassager(int id) {
        String requete = "DELETE FROM ridepassenger WHERE id = ?";
        PreparedStatement pst;
        try {
            pst = cnx.getConnection().prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RidePassengerService.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Trajet supprimé");
    }

    @Override
    public void modifierPassager(RidePassenger p, int id) {
        String requete = "UPDATE ridepassenger SET price= ?, citySource= ?, placeSource= ?, longSource= ?, latSource= ?, cityDestination= ?, placeDestination= ?, longDestination= ?,latDestination= ?, dateTimeRangeSource= ?, dateTimeRangeDestination= ?, handicap= ?, animal= ?, haveAnimal= ?, haveLuggage= ?, luggageMass= ?, music= ?, musicTaste= ?, smoking= ?, allowSmoking= ? WHERE id = ?";
        PreparedStatement pst;
        try {
            pst = cnx.getConnection().prepareStatement(requete);
            pst.setLong(1, p.getPrice());
            pst.setString(2, p.getCitySource());
            pst.setString(3, p.getPlaceSource());
            pst.setDouble(4, p.getLongSource());
            pst.setDouble(5, p.getLatSource());
            pst.setString(6, p.getCityDestination());
            pst.setString(7, p.getPlaceDestination());
            pst.setDouble(8, p.getLongDestination());
            pst.setDouble(9, p.getLatDestination());
            pst.setString(10, p.getDateTimeRangeSource());
            pst.setString(11, p.getDateTimeRangeDestination());
            pst.setInt(12, p.getHandicap());
            pst.setInt(13, p.getAnimal());
            pst.setInt(14, p.getHaveAnimal());
            pst.setInt(15, p.getHaveLuggage());
            pst.setFloat(16, p.getLuggageMass());
            pst.setInt(17, p.getMusic());
            pst.setString(18, p.getMusicTaste());
            pst.setInt(19, p.getSmoking());
            pst.setInt(20, p.getAllowSmoking());
            pst.setInt(21, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RidePassengerService.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Trajet modifier");
    }

    public ResultSet afficherTrajet () throws SQLException {
        String requete = "SELECT * FROM ridepassenger" ;
        Connection pnx = cnx.getConnection() ;
        Statement st = pnx.createStatement();
        return st.executeQuery(requete);
    }
    @Override
    public ResultSet afficherTrajet(int idPassenger) {
        String requete = "SELECT * FROM ridepassenger WHERE idPassenger = ?";
        PreparedStatement pst;
        ResultSet list = null;
        try {
            pst = cnx.getConnection().prepareStatement(requete);
            pst.setInt(1, idPassenger);
            list = pst.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(RidePassengerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ResultSet afficherUnTrajet(int id) {
        String requete = "SELECT * FROM ridepassenger WHERE id= ?";
        PreparedStatement pst;
        ResultSet list = null;
        try {
            pst = cnx.getConnection().prepareStatement(requete);
            pst.setInt(1, id);
            list = pst.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(RidePassengerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ResultSet rechercherparville(String rech) {
        String requete = "SELECT * FROM ridepassenger WHERE citySource LIKE ?";
        PreparedStatement pst;
        ResultSet list = null;
        try {
            pst = cnx.getConnection().prepareStatement(requete);
            pst.setString(1, "%" + rech + "%");
            list = pst.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(RidePassengerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ResultSet chartSpecies() {
        String requete = "SELECT COUNT(id), citySource FROM ridepassenger GROUP BY citySource";
        Statement st;
        ResultSet list = null;
        try {
            st = cnx.getConnection().createStatement();
            list = st.executeQuery(requete);
        } catch (SQLException ex) {
            Logger.getLogger(RidePassengerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
