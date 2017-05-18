/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.*;
import java.util.Date ;
import java.util.Objects;
import javafx.scene.control.Button;

/**
 *
 * @author Asus
 */
public class RidePassenger {
    private int id ;
    private String citySource;
    private String placeSource;
    private double longSource ;
    private double latSource;
    private String cityDestination;
    private String placeDestination;
    private double longDestination ;
    private double latDestination;
    private String dateTimeRangeSource ;
    private String dateTimeRangeDestination ;
    private int handicap ;
    private int animal;
    private int haveAnimal ;
    private int haveLuggage;
    private float luggageMass ;
    private int music ;
    private String musicTaste ;
    private int smoking ;
    private int allowSmoking ;
    private int idPassager ;
    private int type ;
    private long price ;
    private Button modifer ;
    private Button Supprimr ;
    
    public RidePassenger(){}

    public RidePassenger(String citySource, String placeSource, double longSource, double latSource, String cityDestination, String placeDestination, double longDestination, double latDestination, String dateTimeRangeSource, String dateTimeRangeDestination, int handicap, int animal, int haveAnimal, int haveLuggage, float luggageMass, int music, String musicTaste, int smoking, int allowSmoking, int idPassager, int type, long price) {
        this.citySource = citySource;
        this.placeSource = placeSource;
        this.longSource = longSource;
        this.latSource = latSource;
        this.cityDestination = cityDestination;
        this.placeDestination = placeDestination;
        this.longDestination = longDestination;
        this.latDestination = latDestination;
        this.dateTimeRangeSource = dateTimeRangeSource;
        this.dateTimeRangeDestination = dateTimeRangeDestination;
        this.handicap = handicap;
        this.animal = animal;
        this.haveAnimal = haveAnimal;
        this.haveLuggage = haveLuggage;
        this.luggageMass = luggageMass;
        this.music = music;
        this.musicTaste = musicTaste;
        this.smoking = smoking;
        this.allowSmoking = allowSmoking;
        this.idPassager = idPassager;
        this.type = type;
        this.price = price;
    }

    public RidePassenger(int id, String citySource, String placeSource, double longSource, double latSource, String cityDestination, String placeDestination, double longDestination, double latDestination, String dateTimeRangeSource, String dateTimeRangeDestination, int handicap, int animal, int haveAnimal, int haveLuggage, float luggageMass, int music, String musicTaste, int smoking, int allowSmoking, int idPassager, int type, long price, Button modifier, Button supprimer) {
        this.id = id;
        this.citySource = citySource;
        this.placeSource = placeSource;
        this.longSource = longSource;
        this.latSource = latSource;
        this.cityDestination = cityDestination;
        this.placeDestination = placeDestination;
        this.longDestination = longDestination;
        this.latDestination = latDestination;
        this.dateTimeRangeSource = dateTimeRangeSource;
        this.dateTimeRangeDestination = dateTimeRangeDestination;
        this.handicap = handicap;
        this.animal = animal;
        this.haveAnimal = haveAnimal;
        this.haveLuggage = haveLuggage;
        this.luggageMass = luggageMass;
        this.music = music;
        this.musicTaste = musicTaste;
        this.smoking = smoking;
        this.allowSmoking = allowSmoking;
        this.idPassager = idPassager;
        this.type = type;
        this.price = price;
        this.modifer = modifier ;
        this.Supprimr = supprimer ;
    }

    @Override
    public String toString() {
        return "RidePassenger{" + "id=" + id + ", citySource=" + citySource + ", placeSource=" + placeSource + ", longSource=" + longSource + ", latSource=" + latSource + ", cityDestination=" + cityDestination + ", placeDestination=" + placeDestination + ", longDestination=" + longDestination + ", latDestination=" + latDestination + ", dateTimeRangeSource=" + dateTimeRangeSource + ", dateTimeRangeDestination=" + dateTimeRangeDestination + ", handicap=" + handicap + ", animal=" + animal + ", haveAnimal=" + haveAnimal + ", haveLuggage=" + haveLuggage + ", luggageMass=" + luggageMass + ", music=" + music + ", musicTaste=" + musicTaste + ", smoking=" + smoking + ", allowSmoking=" + allowSmoking + ", idPassager=" + idPassager + ", type=" + type + ", price=" + price + ", modifer=" + modifer + ", Supprimr=" + Supprimr + '}';
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public String getCitySource() {
        return citySource;
    }

    public void setCitySource(String citySource) {
        this.citySource = citySource;
    }

    public String getPlaceSource() {
        return placeSource;
    }

    public void setPlaceSource(String placeSource) {
        this.placeSource = placeSource;
    }

    public double getLongSource() {
        return longSource;
    }

    public void setLongSource(double longSource) {
        this.longSource = longSource;
    }

    public double getLatSource() {
        return latSource;
    }

    public void setLatSource(double latSource) {
        this.latSource = latSource;
    }

    public String getCityDestination() {
        return cityDestination;
    }

    public void setCityDestination(String cityDestination) {
        this.cityDestination = cityDestination;
    }

    public String getPlaceDestination() {
        return placeDestination;
    }

    public void setPlaceDestination(String placeDestination) {
        this.placeDestination = placeDestination;
    }

    public double getLongDestination() {
        return longDestination;
    }

    public void setLongDestination(double longDestination) {
        this.longDestination = longDestination;
    }

    public double getLatDestination() {
        return latDestination;
    }

    public void setLatDestination(double latDestination) {
        this.latDestination = latDestination;
    }

    public String getDateTimeRangeSource() {
        return dateTimeRangeSource;
    }

    public void setDateTimeRangeSource(String dateTimeRangeSource) {
        this.dateTimeRangeSource = dateTimeRangeSource;
    }

    public String getDateTimeRangeDestination() {
        return dateTimeRangeDestination;
    }

    public void setDateTimeRangeDestination(String dateTimeRangeDestination) {
        this.dateTimeRangeDestination = dateTimeRangeDestination;
    }

    public int getHandicap() {
        return handicap;
    }

    public void setHandicap(int handicap) {
        this.handicap = handicap;
    }

    public int getAnimal() {
        return animal;
    }

    public void setAnimal(int animal) {
        this.animal = animal;
    }

    public int getHaveAnimal() {
        return haveAnimal;
    }

    public void setHaveAnimal(int haveAnimal) {
        this.haveAnimal = haveAnimal;
    }

    public int getHaveLuggage() {
        return haveLuggage;
    }

    public void setHaveLuggage(int haveLuggage) {
        this.haveLuggage = haveLuggage;
    }

    public float getLuggageMass() {
        return luggageMass;
    }

    public void setLuggageMass(float luggageMass) {
        this.luggageMass = luggageMass;
    }

    public int getMusic() {
        return music;
    }

    public void setMusic(int music) {
        this.music = music;
    }

    public String getMusicTaste() {
        return musicTaste;
    }

    public void setMusicTaste(String musicTaste) {
        this.musicTaste = musicTaste;
    }

    public int getSmoking() {
        return smoking;
    }

    public void setSmoking(int smoking) {
        this.smoking = smoking;
    }

    public int getAllowSmoking() {
        return allowSmoking;
    }

    public void setAllowSmoking(int allowSmoking) {
        this.allowSmoking = allowSmoking;
    }

    public int getIdPassager() {
        return idPassager;
    }

    public void setIdPassager(int idPassager) {
        this.idPassager = idPassager;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Button getModifer() {
        return modifer;
    }

    public void setModifer(Button modifer) {
        this.modifer = modifer;
    }

    public Button getSupprimr() {
        return Supprimr;
    }

    public void setSupprimr(Button Supprimr) {
        this.Supprimr = Supprimr;
    }
    
}
