/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;


/**
 *
 * @author asus

 */
import Entities.Societe;
public interface ISociete  
{
    public void AjouterSociete(Societe s);
    public void SupprimerSociete(Societe s);
    public Societe ChercherSocieteParid( int id);
    public Societe ChercherSociete(String nom);
    public void ModifierSociete(Societe s);
    public void AfficherSociete();
    
}
