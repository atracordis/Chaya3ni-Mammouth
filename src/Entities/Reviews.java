/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


import java.text.SimpleDateFormat;
import static java.util.Collections.list;
import java.util.Date;
import javafx.scene.control.TableColumn;

/**
 *
 * @author SALMA
 */
public class Reviews {
    
 private int id ; 

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
 private String nom ; 
    private int rating ; 
     private int idBooking ;
      private int idUser ; 
     private String title; 
    private String content ; 
    private Date dateTime ;
    
    
    
    private String moy   ;
   private int idUser2 ; 

    public Reviews(String title) {
        this.title = title;
    }

   
    
    
    
    public String getMoy() {
        return moy;
    }

    public void setMoy(String moy) {
        this.moy = moy;
    }

    public Reviews(int id, int rating, int idBooking, int idUser, String title, String content, Date dateTime) {
        this.id = id;
        this.rating = rating;
        this.idBooking = idBooking;
        this.idUser = idUser;
        this.title = title;
        this.content = content;
        this.dateTime = dateTime;
        
       
    }

    public Reviews(int id, int rating, int idBooking, int idUser, String title, String content, Date dateTime, String moy, int idUser2) {
        this.id = id;
        this.rating = rating;
        this.idBooking = idBooking;
        this.idUser = idUser;
        this.title = title;
        this.content = content;
        this.dateTime = dateTime;
        this.moy = moy;
        this.idUser2 = idUser2;
    }

    public Reviews(int rating, int idBooking, int idUser, String title, String content, Date dateTime, int idUser2) {
        this.rating = rating;
        this.idBooking = idBooking;
        this.idUser = idUser;
        this.title = title;
        this.content = content;
        this.dateTime = dateTime;
        this.idUser2 = idUser2;
    }

   

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Reviews{" + "id=" + id + ", rating=" + rating + ", idBooking=" + idBooking + ", idUser=" + idUser + ", title=" + title + ", content=" + content + ", dateTime=" + dateTime + '}';
    }

    public Reviews(int id) {
        this.id = id;
    }

    public Reviews(int rating, int idBooking, int idUser, String title, String content, Date dateTime) {
        this.rating = rating;
        this.idBooking = idBooking;
        this.idUser = idUser;
        this.title = title;
        this.content = content;
        this.dateTime = dateTime;
    }

    public Reviews(int rating, int idBooking, String title, String content, Date dateTime) {
        this.rating = rating;
        this.idBooking = idBooking;
        this.title = title;
        this.content = content;
        this.dateTime = dateTime;
    }

    public Reviews(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Reviews(int id, int rating, String title, String content) {
        this.id = id;
        this.rating = rating;
        this.title = title;
        this.content = content;
    }

    public Reviews() {
    }

    public Reviews(int id, int idUser) {
        this.id = id;
        this.idUser = idUser;
    }

    
    public Reviews(int id, int idBooking, int idUser) {
        this.id = id;
        this.idBooking = idBooking;
        this.idUser = idUser;
    }

    public Reviews(int id, int rating, int idBooking, int idUser, String title, String content, Date dateTime, int idUser2) {
        this.id = id;
        this.rating = rating;
        this.idBooking = idBooking;
        this.idUser = idUser;
        this.title = title;
        this.content = content;
        this.dateTime = dateTime;
        this.idUser2 = idUser2;
    }

    public int getIdUser2() {
        return idUser2;
    }

    public void setIdUser2(int idUser2) {
        this.idUser2 = idUser2;
    }

    public Reviews(String nom, int rating, String title, String content, Date dateTime) {
        this.nom = nom;
        this.rating = rating;
        this.title = title;
        this.content = content;
        this.dateTime = dateTime;
    }


    

    

  

   
   

}
