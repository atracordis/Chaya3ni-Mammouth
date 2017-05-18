/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author Admin
 */
public class Preferences {

    String idUser, email, telephone, address, latitude, longitude, music, musicTaste, smoking, allowSmoking, animal, haveAnimal, confortVoiture, modeleVoiture, marqueVoiture;

    @Override
    public String toString() {
        return "Preferences{" + "idUser=" + idUser + ", email=" + email + ", telephone=" + telephone + ", address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + ", music=" + music + ", musicTaste=" + musicTaste + ", smoking=" + smoking + ", allowSmoking=" + allowSmoking + ", animal=" + animal + ", haveAnimal=" + haveAnimal + ", confortVoiture=" + confortVoiture + ", modeleVoiture=" + modeleVoiture + ", marqueVoiture=" + marqueVoiture + '}';
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.idUser);
        hash = 41 * hash + Objects.hashCode(this.email);
        hash = 41 * hash + Objects.hashCode(this.telephone);
        hash = 41 * hash + Objects.hashCode(this.address);
        hash = 41 * hash + Objects.hashCode(this.latitude);
        hash = 41 * hash + Objects.hashCode(this.longitude);
        hash = 41 * hash + Objects.hashCode(this.music);
        hash = 41 * hash + Objects.hashCode(this.musicTaste);
        hash = 41 * hash + Objects.hashCode(this.smoking);
        hash = 41 * hash + Objects.hashCode(this.allowSmoking);
        hash = 41 * hash + Objects.hashCode(this.animal);
        hash = 41 * hash + Objects.hashCode(this.haveAnimal);
        hash = 41 * hash + Objects.hashCode(this.confortVoiture);
        hash = 41 * hash + Objects.hashCode(this.modeleVoiture);
        hash = 41 * hash + Objects.hashCode(this.marqueVoiture);
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
        final Preferences other = (Preferences) obj;
        if (!Objects.equals(this.idUser, other.idUser)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.telephone, other.telephone)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        if (!Objects.equals(this.music, other.music)) {
            return false;
        }
        if (!Objects.equals(this.musicTaste, other.musicTaste)) {
            return false;
        }
        if (!Objects.equals(this.smoking, other.smoking)) {
            return false;
        }
        if (!Objects.equals(this.allowSmoking, other.allowSmoking)) {
            return false;
        }
        if (!Objects.equals(this.animal, other.animal)) {
            return false;
        }
        if (!Objects.equals(this.haveAnimal, other.haveAnimal)) {
            return false;
        }
        if (!Objects.equals(this.confortVoiture, other.confortVoiture)) {
            return false;
        }
        if (!Objects.equals(this.modeleVoiture, other.modeleVoiture)) {
            return false;
        }
        if (!Objects.equals(this.marqueVoiture, other.marqueVoiture)) {
            return false;
        }
        return true;
    }

    public Preferences() {

    }

    public Preferences(String idUser, String email, String telephone, String address, String latitude, String longitude, String music, String musicTaste, String smoking, String allowSmoking, String animal, String haveAnimal, String confortVoiture, String modeleVoiture, String marqueVoiture) {
        this.idUser = idUser;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.music = music;
        this.musicTaste = musicTaste;
        this.smoking = smoking;
        this.allowSmoking = allowSmoking;
        this.animal = animal;
        this.haveAnimal = haveAnimal;
        this.confortVoiture = confortVoiture;
        this.modeleVoiture = modeleVoiture;
        this.marqueVoiture = marqueVoiture;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getMusicTaste() {
        return musicTaste;
    }

    public void setMusicTaste(String musicTaste) {
        this.musicTaste = musicTaste;
    }

    public String getSmoking() {
        return smoking;
    }

    public void setSmoking(String smoking) {
        this.smoking = smoking;
    }

    public String getAllowSmoking() {
        return allowSmoking;
    }

    public void setAllowSmoking(String allowSmoking) {
        this.allowSmoking = allowSmoking;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getHaveAnimal() {
        return haveAnimal;
    }

    public void setHaveAnimal(String haveAnimal) {
        this.haveAnimal = haveAnimal;
    }

    public String getConfortVoiture() {
        return confortVoiture;
    }

    public void setConfortVoiture(String confortVoiture) {
        this.confortVoiture = confortVoiture;
    }

    public String getModeleVoiture() {
        return modeleVoiture;
    }

    public void setModeleVoiture(String modeleVoiture) {
        this.modeleVoiture = modeleVoiture;
    }

    public String getMarqueVoiture() {
        return marqueVoiture;
    }

    public void setMarqueVoiture(String marqueVoiture) {
        this.marqueVoiture = marqueVoiture;
    }

}
