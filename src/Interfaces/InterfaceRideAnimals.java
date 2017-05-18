/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.RideAnimals;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Deathscythvi
 */
public interface InterfaceRideAnimals {

    public void ajouterRideAnimals(RideAnimals ra);

    public void ModifierRideAnimals(RideAnimals ra, int id);

    public void DeleteRideAnimals(RideAnimals ra);
    
    public void AffichertousRideAnimals();
   
    public ResultSet afficher()throws SQLException;

}
