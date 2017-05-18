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
public class Ride {

    private double price;
    private String citySource;
    private String placeSource;
    private double longSource;
    private double latSource;
    private String cityDestination;
    private String placeDestination;
    private double longDestination;
    private double latDestination;
    private Date dateTimeSource;
    private byte haveluggage;
    private float luggageMass;
    
    public Ride(){
        
    }

    public Ride(double price, String citySource, String placeSource, double longSource, double latSource, String cityDestination, String placeDestination, double longDestination, double latDestination, Date dateTimeSource, byte haveluggage, float luggageMass) {

        this.price = price;
        this.citySource = citySource;
        this.placeSource = placeSource;
        this.longSource = longSource;
        this.latSource = latSource;
        this.cityDestination = cityDestination;
        this.placeDestination = placeDestination;
        this.longDestination = longDestination;
        this.latDestination = latDestination;
        this.dateTimeSource = dateTimeSource;
        this.haveluggage = haveluggage;
        this.luggageMass = luggageMass;
    }

    public Ride(double price, String citySource, String placeSource, String cityDestination, String placeDestination, Date dateTimeSource) {
             
        
        this.price = price;
        this.citySource = citySource;
        this.placeSource = placeSource;
        this.cityDestination = cityDestination;
        this.placeDestination = placeDestination;
        this.dateTimeSource = dateTimeSource;

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public void setLatDestination(int latDestination) {
        this.latDestination = latDestination;
    }

    public Date getDateTimeSource() {
        return dateTimeSource;
    }

    public void setDateTimeSource(Date dateTimeSource) {
        this.dateTimeSource = dateTimeSource;
    }

    public byte getHaveluggage() {
        return haveluggage;
    }

    public void setHaveluggage(byte haveluggage) {
        this.haveluggage = haveluggage;
    }

    public float getLuggageMass() {
        return luggageMass;
    }

    public void setLuggageMass(float luggageMass) {
        this.luggageMass = luggageMass;
    }

}
