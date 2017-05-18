/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.control.Button;

/**
 *
 * @author Asus
 */
public class RidePackage {
    private int id ;
    private int idtrajet ;
    private String description ;
    private String size ;
    private String typePackage ;
    private float poids ;
    private int qunatity ;
    private int prix ;
    private Button modif ;
    private Button supp ;

    public RidePackage(int idtrajet, String description, String size, String typePackage, float poids, int qunatity, int prix) {
        this.idtrajet = idtrajet ;
        this.description = description;
        this.size = size;
        this.typePackage = typePackage;
        this.poids = poids;
        this.qunatity = qunatity;
        this.prix = prix ;
    }

    public RidePackage(int id, int idtrajet, String description, String size, String typePackage, float poids, int qunatity, int prix) {
        this.idtrajet = idtrajet ;
        this.id = id;
        this.description = description;
        this.size = size;
        this.typePackage = typePackage;
        this.poids = poids;
        this.qunatity = qunatity;
        this.prix = prix ;
    }

    public RidePackage(int id, int idtrajet, String description, String size, String typePackage, float poids, int qunatity, int prix, Button modif, Button supp) {
        this.id = id;
        this.idtrajet = idtrajet;
        this.description = description;
        this.size = size;
        this.typePackage = typePackage;
        this.poids = poids;
        this.qunatity = qunatity;
        this.prix = prix;
        this.modif = modif;
        this.supp = supp;
    }

    @Override
    public String toString() {
        return "RidePackage{" + "id=" + id + ", idtrajet=" + idtrajet + ", description=" + description + ", size=" + size + ", typePackage=" + typePackage + ", poids=" + poids + ", qunatity=" + qunatity + ", prix=" + prix + ", modif=" + modif + ", supp=" + supp + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTypePackage() {
        return typePackage;
    }

    public void setTypePackage(String typePackage) {
        this.typePackage = typePackage;
    }

    public int getQunatity() {
        return qunatity;
    }

    public void setQunatity(int qunatity) {
        this.qunatity = qunatity;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getIdtrajet() {
        return idtrajet;
    }

    public void setIdtrajet(int idtrajet) {
        this.idtrajet = idtrajet;
    }
    
}
