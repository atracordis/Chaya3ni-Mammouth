/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Representant;

/**
 *
 * @author asus
 */
public interface IRepresentant {
    public void AjouterRepresentant(Representant r);
    public void SupprimerRepresentant(Representant r);
    public Representant ChercherRepresentant(String input);
    public void ModifierRepresentant(Representant r);
    public void AfficherRepresentantSociete(int id);
    
}
