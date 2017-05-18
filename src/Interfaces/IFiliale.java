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
import Entities.Filiale;
import java.util.List;
public interface IFiliale 
{
    public void AjouterFiliale(Filiale f);
    public void SupprimerFiliale(Filiale f);
    public Filiale ChercherFiliale(double cdp);
    public Filiale ChercherFilialeParid(int id) ;
    public void ModifierFiliale(Filiale f);
    public List<Filiale> AfficherFilialeSociete(int idc);
    
}