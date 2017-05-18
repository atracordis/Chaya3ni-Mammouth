/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.*;
import Tools.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Interfaces.RidePackageInterface;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class RidePackageService implements RidePackageInterface {

    Conn cnx;

    public RidePackageService() {
        this.cnx = Conn.getInstance();
    }

    @Override
    public void insertPackage(RidePackage p) {
        String requete = "INSERT INTO `ridepackage`(`idtrajet`, `description`, `size`, `typePackage`, `quantity`, `poids`, `prix`) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pst;
        try {
            pst = cnx.getConnection().prepareStatement(requete);
            pst.setInt(1, p.getIdtrajet());
            pst.setString(2, p.getDescription());
            pst.setString(3, p.getSize());
            pst.setString(4, p.getTypePackage());
            pst.setInt(5, p.getQunatity());
            pst.setFloat(6, p.getPoids());
            pst.setInt(7, p.getPrix());
            System.out.println(pst.toString());
            pst.executeUpdate();
            System.out.println("Marchandise ajouté");
        } catch (SQLException ex) {
            Logger.getLogger(RidePackageService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deletePackage(RidePackage p) {
        String requete = "DELETE FROM ridepackage WHERE id_package = ?";
        PreparedStatement pst;
        try {
            pst = cnx.getConnection().prepareStatement(requete);
            pst.setInt(1, p.getId());
            pst.executeUpdate();
            System.out.println("Marchandise Supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(RidePackageService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void updatePackage(RidePackage p) {
        String requete = "UPDATE ridepackage SET idtrajet = ?, description = ?, size = ?, typePackage = ?, quantity = ?, poids = ?, prix = ? WHERE id_package = ?";
        PreparedStatement pst;
        try {
            pst = cnx.getConnection().prepareStatement(requete);
            pst.setInt(1, p.getIdtrajet());
            pst.setString(2, p.getDescription());
            pst.setString(3, p.getSize());
            pst.setString(4, p.getTypePackage());
            pst.setInt(5, p.getQunatity());
            pst.setFloat(6, p.getPoids());
            pst.setInt(7, p.getPrix());
            pst.setInt(8, p.getId());
            pst.executeUpdate();
            System.out.println("Marchandise modifié");
        } catch (SQLException ex) {
            Logger.getLogger(RidePackageService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ResultSet afficherTrajetConducteur() {
        String requete = "SELECT * FROM ridePassenger";
        Statement st;
        ResultSet list = null ;
        try {
            st = cnx.getConnection().createStatement();
             list = st.executeQuery(requete);
        } catch (SQLException ex) {
            Logger.getLogger(RidePackageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }

    @Override
    public ResultSet afficherMarchandise() {
        String requete = "SELECT * FROM ridepackage";
        Statement st;
        ResultSet list = null ;
        try {
            st = cnx.getConnection().createStatement();
            list = st.executeQuery(requete); ;
        } catch (SQLException ex) {
            Logger.getLogger(RidePackageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }

    @Override
    public ResultSet getCitySourceDestination(int idtrajet) {
        String requete = "SELECT id_package,idtrajet, description, size, quantity, typePackage,poids, citySource, cityDestination FROM ridepackage p INNER JOIN ridepassenger pe ON ? = pe.id";
        PreparedStatement pst;
        ResultSet list = null ;
        try {
            pst = cnx.getConnection().prepareStatement(requete);
            pst.setInt(1, idtrajet);
            list = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(RidePackageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }
}
