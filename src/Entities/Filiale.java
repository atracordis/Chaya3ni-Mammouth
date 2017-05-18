/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class Filiale 
{
    private int idf;
    private int idcompany;
    private String adresse1;
    private String adresse2;
    private double codepostal;
    private double latitude;
    private double longitude;
    private List <Employe> listeEmploye;

    public Filiale(int idcompany, String adresse1, String adresse2, double codepostal, double latitude, double longitude) 
    {
        
        this.idcompany = idcompany;
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.codepostal = codepostal;
        this.latitude = latitude;
        this.longitude = longitude;
        this.listeEmploye= new ArrayList <>();
    }

    public Filiale() {
    }
    public int getIdf() {
        return idf;
    }

    public void setIdf(int id) {
        this.idf = id;
    }
    public int getIdcompany() {
        return idcompany;
    }

    public void setIdcompany(int idcompany) {
        this.idcompany = idcompany;
    }

    public String getAdresse1() {
        return adresse1;
    }

    public void setAdresse1(String adresse1) {
        this.adresse1 = adresse1;
    }

    public String getAdresse2() {
        return adresse2;
    }

    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }

    public double getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(double codepostal) {
        this.codepostal = codepostal;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public List<Employe> getListeEmploye() {
        return listeEmploye;
    }

    public void setListeFilliale(List<Employe> listeEmploye) {
        this.listeEmploye = listeEmploye;
    }
    @Override
    public String toString() {
        return "Filiale{" + "idfiliale= " + idf  + "idcompany=" + idcompany + ", adresse1=" + adresse1 + ", adresse2=" + adresse2 + ", codepostal=" + codepostal + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }
    public void AjouterEmploye (Employe e) 
    {
        listeEmploye.add (e);
    }
}
