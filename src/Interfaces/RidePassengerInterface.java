/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import Entities.RidePassenger ;
import java.sql.ResultSet;

/**
 *
 * @author Asus
 */
public interface RidePassengerInterface {
    void insertRidePassenger(RidePassenger p) ;
    void supprimerPassager (int id);
    void modifierPassager (RidePassenger p, int id);
    ResultSet afficherTrajet (int idPassenger) ;
    ResultSet afficherUnTrajet (int id);
    ResultSet rechercherparville (String rech);
    ResultSet chartSpecies () ;
}
