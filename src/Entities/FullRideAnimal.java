/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.control.Button;

/**
 *
 * @author elloumiaymen
 */
public class FullRideAnimal {
    private String nameAnimal;
    private String speciesAnimal;
    private String specialNeedsAnimal;
    private String placeSource;
    private String placeDestination;
    private String suernamegtr;
    private String namestr;
    private String numberstr;
    private int id;
    private int idridpass;
    private int idgtr;
    private Button modifier;
    
    

    public FullRideAnimal(String nameAnimal, String speciesAnimal, String specialNeedsAnimal, String placeSource, String placeDestination, String suernamegtr, String namestr, String numberstr) {
        this.nameAnimal = nameAnimal;
        this.speciesAnimal = speciesAnimal;
        this.specialNeedsAnimal = specialNeedsAnimal;
        this.placeSource = placeSource;
        this.placeDestination = placeDestination;
        this.suernamegtr = suernamegtr;
        this.namestr = namestr;
        this.numberstr = numberstr;
    }

    public FullRideAnimal(String nameAnimal, String speciesAnimal, String specialNeedsAnimal, String placeSource, String placeDestination, String suernamegtr, String namestr, String numberstr, int id) {
        this.nameAnimal = nameAnimal;
        this.speciesAnimal = speciesAnimal;
        this.specialNeedsAnimal = specialNeedsAnimal;
        this.placeSource = placeSource;
        this.placeDestination = placeDestination;
        this.suernamegtr = suernamegtr;
        this.namestr = namestr;
        this.numberstr = numberstr;
        this.id = id;
    }

    public FullRideAnimal(String nameAnimal, String speciesAnimal, String specialNeedsAnimal, String placeSource, String placeDestination, String suernamegtr, String namestr, String numberstr, int id, Button modifier) {
        this.nameAnimal = nameAnimal;
        this.speciesAnimal = speciesAnimal;
        this.specialNeedsAnimal = specialNeedsAnimal;
        this.placeSource = placeSource;
        this.placeDestination = placeDestination;
        this.suernamegtr = suernamegtr;
        this.namestr = namestr;
        this.numberstr = numberstr;
        this.id = id;
        this.modifier = modifier;
    }

    public FullRideAnimal(String nameAnimal, String speciesAnimal, String specialNeedsAnimal, String placeSource, String placeDestination, String suernamegtr, String namestr, String numberstr, int id, int idridpass, int idgtr) {
        this.nameAnimal = nameAnimal;
        this.speciesAnimal = speciesAnimal;
        this.specialNeedsAnimal = specialNeedsAnimal;
        this.placeSource = placeSource;
        this.placeDestination = placeDestination;
        this.suernamegtr = suernamegtr;
        this.namestr = namestr;
        this.numberstr = numberstr;
        this.id = id;
        this.idridpass = idridpass;
        this.idgtr = idgtr;
    }

    public int getIdridpass() {
        return idridpass;
    }

    public void setIdridpass(int idridpass) {
        this.idridpass = idridpass;
    }

    public int getIdgtr() {
        return idgtr;
    }

    public void setIdgtr(int idgtr) {
        this.idgtr = idgtr;
    }

    public FullRideAnimal() {
    }

    public String getNameAnimal() {
        return nameAnimal;
    }

    public void setNameAnimal(String nameAnimal) {
        this.nameAnimal = nameAnimal;
    }

    public String getSpeciesAnimal() {
        return speciesAnimal;
    }

    public void setSpeciesAnimal(String speciesAnimal) {
        this.speciesAnimal = speciesAnimal;
    }

    public String getSpecialNeedsAnimal() {
        return specialNeedsAnimal;
    }

    public void setSpecialNeedsAnimal(String specialNeedsAnimal) {
        this.specialNeedsAnimal = specialNeedsAnimal;
    }

    public String getPlaceSource() {
        return placeSource;
    }

    public void setPlaceSource(String placeSource) {
        this.placeSource = placeSource;
    }

    public String getPlaceDestination() {
        return placeDestination;
    }

    public void setPlaceDestination(String placeDestination) {
        this.placeDestination = placeDestination;
    }

    public String getSuernamegtr() {
        return suernamegtr;
    }

    public void setSuernamegtr(String suernamegtr) {
        this.suernamegtr = suernamegtr;
    }

    public String getNamestr() {
        return namestr;
    }

    public void setNamestr(String namestr) {
        this.namestr = namestr;
    }

    public String getNumberstr() {
        return numberstr;
    }

    public void setNumberstr(String numberstr) {
        this.numberstr = numberstr;
    }

    @Override
    public String toString() {
        return "FullRideAnimal{" + "nameAnimal=" + nameAnimal + ", speciesAnimal=" + speciesAnimal + ", specialNeedsAnimal=" + specialNeedsAnimal + ", placeSource=" + placeSource + ", placeDestination=" + placeDestination + ", suernamegtr=" + suernamegtr + ", namestr=" + namestr + ", numberstr=" + numberstr + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
