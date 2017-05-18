/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class Message {

    String idSource, idDestination, content, dateTime, id;

    public Message(String idSource, String idDestination, String content, String dateTime) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate localDate = LocalDate.now();
        this.idSource = idSource;
        this.idDestination = idDestination;
        this.content = content;
        this.dateTime = dtf.format(localDate);
    }

    public Message() {

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idSource);
        hash = 37 * hash + Objects.hashCode(this.idDestination);
        hash = 37 * hash + Objects.hashCode(this.content);
        hash = 37 * hash + Objects.hashCode(this.dateTime);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Message other = (Message) obj;
        if (!Objects.equals(this.idSource, other.idSource)) {
            return false;
        }
        if (!Objects.equals(this.idDestination, other.idDestination)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.dateTime, other.dateTime)) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSource() {
        return idSource;
    }

    public void setIdSource(String idSource) {
        this.idSource = idSource;
    }

    public String getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(String idDestination) {
        this.idDestination = idDestination;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

}
