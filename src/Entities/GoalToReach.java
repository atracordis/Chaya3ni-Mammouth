/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author elloumiaymen
 */
public class GoalToReach {
        private String nom;
        private String prenom;
        private String description;
        private float taille;
        private String numerodecontact;

    public GoalToReach(String nom, String prenom, String description, float taille, String numerodecontact) {
        this.nom = nom;
        this.prenom = prenom;
        this.description = description;
        this.taille = taille;
        this.numerodecontact = numerodecontact;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getTaille() {
        return taille;
    }

    public void setTaille(float taille) {
        this.taille = taille;
    }

    public String getNumerodecontact() {
        return numerodecontact;
    }

    public void setNumerodecontact(String numerodecontact) {
        this.numerodecontact = numerodecontact;
    }
        
                
    
}
