/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Reviews;
import java.util.List;

/**
 *
 * @author SALMA
 */
public interface InterfaceReviews {
    
    public void  AjouterReviews(Reviews R);
     public void ModifierReviews(Reviews R );
     public void DeleteReviews(Reviews R);
     public List AfficherReviews();
  
}
