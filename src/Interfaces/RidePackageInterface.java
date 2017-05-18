/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import Entities.RidePackage ;
import java.sql.ResultSet;

/**
 *
 * @author Asus
 */
public interface RidePackageInterface {
    void insertPackage(RidePackage p) ;
    void updatePackage (RidePackage p) ;
    void deletePackage (RidePackage p) ;
    ResultSet afficherTrajetConducteur ();
    ResultSet afficherMarchandise () ;
    ResultSet getCitySourceDestination (int idtrajet) ;
}
