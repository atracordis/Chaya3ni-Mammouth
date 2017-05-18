/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Societe 
{
    private int ids;
    private String name;
    private String telephone;
    private String email;
    private String logo;
    private String adresse1;
    private String adresse2;
    private double codepostal;
    private int idrepresentant;
    private List <Filiale> listeFilliale;
    private static File logosociete;
    
    public static File getLogosociete()
    {
        return Societe.logosociete;
    }
    public static void setLogosociete(File file)
    {
        Societe.logosociete=file;
    }

    public Societe(String name, String telephone, String email, String logo, String adresse1, String adresse2, double codepostal,int idr) {
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.logo = logo;
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.codepostal = codepostal;
        this.idrepresentant=idr;
        this.listeFilliale= new ArrayList <>();
        
    }

    public Societe() {this.listeFilliale= new ArrayList <>(); }
    
    public int getIds() {
        return ids;
    }

    public int getIdrepresentant() 
    {
        return idrepresentant;
    }
    public void setIds(int id) 
    {
        this.ids = id;
    }
    public void setIdrepresentant(int idrepresentant) {
        this.idrepresentant = idrepresentant;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public List<Filiale> getListeFilliale() {
        return listeFilliale;
    }

    public void setListeFilliale(List<Filiale> listeFilliale) {
        this.listeFilliale = listeFilliale;
    }
    
    
    @Override
    public String toString() 
    {
        return "Societe{" + "name=" + name + ", telephone=" + telephone + ", email=" + email + ", logo=" + logo + ", adresse1=" + adresse1 + ", adresse2=" + adresse2 + ", codepostal=" + codepostal + '}';
    }
    
    public void AjouterFiliale (Filiale f) 
    {
        listeFilliale.add (f);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    
}
