/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author SALMA
 */
public class Complaints  {
   private int id ;
   private String content ; 
   private String type ; 
   private Date dateTime ; 
  private int idUser ;
  private int idBooking;
  private String attachement ; 
  private String status ;

  

   

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Complaints(int id, String content, String type,String attachement, String status) {
        this.id = id;
        this.content = content;
        this.type = type;
        
        this.attachement = attachement;
        this.status = status;
    }

    public Complaints(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public Complaints(int id, String content, String type, Date dateTime, String attachement, String status) {
        this.id = id;
        this.content = content;
        this.type = type;
        this.dateTime = dateTime;
        this.attachement = attachement;
        this.status = status;
    }


    public Complaints(int id, String content, String type, Date dateTime, int idUser, int idBooking) {
        this.id = id;
        this.content = content;
        this.type = type;
        this.dateTime = dateTime;
        this.idUser = idUser;
        this.idBooking = idBooking;
   
    }

    public String getAttachement() {
        return attachement;
    }

    public void setAttachement(String attachement) {
        this.attachement = attachement;
    }

   

  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    public Complaints(String content, String type, Date dateTime, String attachement) {
        this.content = content;
        this.type = type;
        this.dateTime = dateTime;
        this.attachement = attachement;
    }

   
   


    public Complaints() {
    }

    public Complaints(String content, String type, Date dateTime, int idUser, int idBooking, String attachement) {
        this.content = content;
        this.type = type;
        this.dateTime = dateTime;
        this.idUser = idUser;
        this.idBooking = idBooking;
        
    }

    public Complaints(String content, String type, Date dateTime ) {
        this.content = content;
        this.type = type;
        this.dateTime = dateTime;
     
    }

    public Complaints(int id, String content, String type, Date dateTime, String attachement) {
        this.id = id;
        this.content = content;
        this.type = type;
        this.dateTime = dateTime;
    
    }

    public Complaints(String content, String type, Date dateTime, int idUser) {
        this.content = content;
        this.type = type;
        this.dateTime = dateTime;
        this.idUser = idUser;
    }

    public Complaints(String content, String type, Date dateTime, String attachement, String status) {
        this.content = content;
        this.type = type;
        this.dateTime = dateTime;
        this.attachement = attachement;
        this.status = status;
    }

    public Complaints(int id) {
        this.id = id;
    }

  

  

  
  

   
   
   
   
    
}
