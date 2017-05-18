/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.RideDriver;
import java.util.List;

/**
 *
 * @author Deathscythvi
 */
public interface InterfaceRideDriver {

    public void ajouterRideDriver(RideDriver rd);

    public void ModifierRideDriver(RideDriver rd);

    public void DeleteRideDriver(RideDriver rd);
    
    public void AffichertousRideDriver();
    public List<RideDriver> RechercherMesDrive();
      public List<RideDriver> RechercherMesDrivernotall();
      public RideDriver rechercheRideDriverId(int i);
       public List<RideDriver> RechercherMesDriverauto(String c);
    
    

}
