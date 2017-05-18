/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Employe;
import java.util.List;

/**
 *
 * @author asus
 */
public interface IEmploye {
    public void AjouterEmploye(Employe e);
    public void SupprimerEmploye(Employe e);
    public Employe ChercherEmploye(String input);
    public void ModifierEmploye(Employe e);
    public List<Employe> AfficherEmployeSociete(int id);
    public List<Employe> ChercherEmployeFiliale(int idf);
    
}
