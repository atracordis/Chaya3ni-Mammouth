/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Complaints;
import java.util.List;

/**
 *
 * @author SALMA
 */
public interface InterfaceComplaints {
    
    
        
    public void  AjouterComplaints(Complaints C );
     public void ModifierComplaints(Complaints C );
     public void DeleteComplaints(Complaints C);
     public List AfficherComplaints();
}
