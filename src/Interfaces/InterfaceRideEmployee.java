/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.RideEmployee;
import java.util.List;

/**
 *
 * @author Deathscythvi
 */
public interface InterfaceRideEmployee {
    
   public void ajouterRideEmployee(RideEmployee Ed);

    public void ModifierRideEmployee(RideEmployee Ed);

    public void DeleteRideEmployee(RideEmployee Ed);
    
    public void AffichertousRideEmployee();
     public List<RideEmployee> RechercherMesDrivernotall();
      public RideEmployee rechercheRideEmployeeId(int i);
    
}
