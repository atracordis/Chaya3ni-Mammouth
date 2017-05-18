/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Deathscythvi
 */
public class RideDriver extends Ride {

    public int id;
    private int nbrPlaces;
    private String confortVoiture;
    private String marqueVoiture;
    private String modeleVoiture;
    private byte handicap;
    private byte animal;
    private byte haveanimal;
    private byte music;
    private String musicTaste;
    private byte smoking;
    private byte allowsmoking;
    private int idDriver;
    
    public RideDriver(){
        
    }

    //constructeur avec id
    public RideDriver(int id, double price, String citySource, String placeSource, double longSource, double latSource, String cityDestination,
            String placeDestination, double longDestination, double latDestination, Date dateTimeSource, int nbrPlaces,
            String confortVoiture, String marqueVoiture, String modeleVoiture, byte handicap, byte animal, byte haveanimal,
            byte haveluggage, float luggageMass, byte music, String musicTaste, byte smoking, byte allowsmoking, int idDriver) {

        super(price, citySource, placeSource, longSource, latSource, cityDestination, placeDestination, longDestination, latDestination, dateTimeSource, haveluggage, luggageMass);

        this.id = id;
        this.nbrPlaces = nbrPlaces;
        this.confortVoiture = confortVoiture;
        this.marqueVoiture = marqueVoiture;
        this.modeleVoiture = modeleVoiture;
        this.handicap = handicap;
        this.animal = animal;
        this.haveanimal = haveanimal;
        this.music = music;
        this.musicTaste = musicTaste;
        this.smoking = smoking;
        this.allowsmoking = allowsmoking;
        this.idDriver = idDriver;

    }
    //constructeur sans id

    public RideDriver(double price, String citySource, String placeSource, double longSource, double latSource, String cityDestination,
            String placeDestination, double longDestination, double latDestination, Date dateTimeSource, int nbrPlaces,
            String confortVoiture, String marqueVoiture, String modeleVoiture, byte handicap, byte animal, byte haveanimal,
            byte haveluggage, float luggageMass, byte music, String musicTaste, byte smoking, byte allowsmoking) {

        super(price, citySource, placeSource, longSource, latSource, cityDestination, placeDestination, longDestination, latDestination, dateTimeSource, haveluggage, luggageMass);

        this.nbrPlaces = nbrPlaces;
        this.confortVoiture = confortVoiture;
        this.marqueVoiture = marqueVoiture;
        this.modeleVoiture = modeleVoiture;
        this.handicap = handicap;
        this.animal = animal;
        this.haveanimal = haveanimal;
        this.music = music;
        this.musicTaste = musicTaste;
        this.smoking = smoking;
        this.allowsmoking = allowsmoking;
        this.idDriver = idDriver;

    }

    public RideDriver(int id, double price, String citySource, String placeSource, String cityDestination, String placeDestination, Date dateTimeSource) {

        super(price, citySource, placeSource, cityDestination, placeDestination, dateTimeSource);
        this.id = id;

    }

    public int getNbrPlaces() {
        return nbrPlaces;
    }

    public void setNbrPlaces(int nbrPlaces) {
        this.nbrPlaces = nbrPlaces;
    }

    public String getConfortVoiture() {
        return confortVoiture;
    }

    public void setConfortVoiture(String confortVoiture) {
        this.confortVoiture = confortVoiture;
    }

    public String getMarqueVoiture() {
        return marqueVoiture;
    }

    public void setMarkeVoiture(String markeVoiture) {
        this.marqueVoiture = markeVoiture;
    }

    public String getModeleVoiture() {
        return modeleVoiture;
    }

    public void setModeleVoiture(String modeleVoiture) {
        this.modeleVoiture = modeleVoiture;
    }

    public byte getHandicap() {
        return handicap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHandicap(byte handicap) {
        this.handicap = handicap;
    }

    public byte getAnimal() {
        return animal;
    }

    public int getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(int idDriver) {
        this.idDriver = idDriver;
    }

    public void setAnimal(byte animal) {
        this.animal = animal;
    }

    public byte getHaveanimal() {
        return haveanimal;
    }

    public void setHaveanimal(byte haveanimal) {
        this.haveanimal = haveanimal;
    }

    public byte getMusic() {
        return music;
    }

    public void setMusic(byte music) {
        this.music = music;
    }

    public String getMusicTaste() {
        return musicTaste;
    }

    public void setMusicTaste(String musicTaste) {
        this.musicTaste = musicTaste;
    }

    public byte getSmoking() {
        return smoking;
    }

    public void setSmoking(byte smoking) {
        this.smoking = smoking;
    }

    public byte getAllowsmoking() {
        return allowsmoking;
    }

    public void setAllowsmoking(byte allowsmoking) {
        this.allowsmoking = allowsmoking;
    }

}
