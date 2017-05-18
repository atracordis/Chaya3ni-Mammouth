/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.File;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class User {


    protected String id, name, surname, gender, dateBirth, email, username, pass, telephone, address1, address2, codePostal, photo, dateInscription, compteActif, newsletter, type, clearanceLevel, idCompany, latitude, longitude, secretQuestion, secretAnswer;
    protected static String currentId, currentUsername, currentPassword, currentName, currentSurname, currentEmail, currentSecretQuestion, currentSecretAnswer, currentPhoto, currentClearanceLevel, currentType;
    protected static File regPic;
    protected Preferences preferences;
    protected static String selectedId;
    protected static boolean isAdmin;
    public static boolean isRepresentative;
    static boolean choseNewsletter;
    
        public static boolean pickedNewsletter()
    {
        return choseNewsletter;               
    }

        public static void setpickedNewsletter(boolean x)
        {
            User.choseNewsletter=x;
        }

    public static boolean isAdmin() {
        return isAdmin;
    }

    public static void setIsAdmin(boolean isAdmin) {
        User.isAdmin = isAdmin;
    }
    

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void UserCurrent(String currentId, String currentUsername, String currentPassword, String currentName, String currentSurname, String currentEmail, String currentSecretQuestion, String currentSecretAnswer, String currentPhoto, String currentClearanceLevel, String currentType) {
        User.currentId = currentId;
        User.currentUsername = currentUsername;
        User.currentPassword = currentPassword;
        User.currentName = currentName;
        User.currentSurname = currentSurname;
        User.currentEmail = currentEmail;
        User.currentSecretQuestion = currentSecretQuestion;
        User.currentSecretAnswer = currentSecretAnswer;
        User.currentPhoto = currentPhoto;
        User.currentClearanceLevel = currentClearanceLevel;
        User.currentType = currentType;
    }
    
    
    public static File getRegPic()
    {
        return User.regPic;
    }
    public static void setRegPic(File file)
    {
        User.regPic=file;
    }

    public static String getSelectedId() {
        return selectedId;
    }

    public static void setSelectedId(String selectedId) {
        User.selectedId = selectedId;
    }
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", gender=" + gender + ", dateBirth=" + dateBirth + ", email=" + email + ", username=" + username + ", pass=" + pass + ", telephone=" + telephone + ", address1=" + address1 + ", address2=" + address2 + ", codePostal=" + codePostal + ", photo=" + photo + ", dateInscription=" + dateInscription + ", compteActif=" + compteActif + ", newsletter=" + newsletter + ", type=" + type + ", clearanceLevel=" + clearanceLevel + ", idCompany=" + idCompany + ", latitude=" + latitude + ", longitude=" + longitude + ", secretQuestion=" + secretQuestion + ", secretAnswer=" + secretAnswer + ", currentId=" + currentId + ", currentUsername=" + currentUsername + ", currentPassword=" + currentPassword + ", currentName=" + currentName + ", currentSurname=" + currentSurname + ", currentEmail=" + currentEmail + ", currentSecretQuestion=" + currentSecretQuestion + ", currentSecretAnswer=" + currentSecretAnswer + ", currentPhoto=" + currentPhoto + ", currentClearanceLevel=" + currentClearanceLevel + ", currentType=" + currentType + ", preferences=" + preferences + '}';
    }

    public User() {
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public static String getCurrentId() {
        return currentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static void setCurrentId(String currentId) {
        User.currentId = currentId;
    }

    public static String getCurrentUsername() {
        return currentUsername;
    }

    public static void setCurrentUsername(String currentUsername) {
        User.currentUsername = currentUsername;
    }

    public static String getCurrentPassword() {
        return currentPassword;
    }

    public static void setCurrentPassword(String currentPassword) {
        User.currentPassword = currentPassword;
    }

    public static String getCurrentName() {
        return currentName;
    }

    public static void setCurrentName(String currentName) {
        User.currentName = currentName;
    }

    public static String getCurrentSurname() {
        return currentSurname;
    }

    public static void setCurrentSurname(String currentSurname) {
        User.currentSurname = currentSurname;
    }

    public static String getCurrentEmail() {
        return currentEmail;
    }

    public static void setCurrentEmail(String currentEmail) {
        User.currentEmail = currentEmail;
    }

    public static String getCurrentSecretQuestion() {
        return currentSecretQuestion;
    }

    public static void setCurrentSecretQuestion(String currentSecretQuestion) {
        User.currentSecretQuestion = currentSecretQuestion;
    }

    public static String getCurrentSecretAnswer() {
        return currentSecretAnswer;
    }

    public static void setCurrentSecretAnswer(String currentSecretAnswer) {
        User.currentSecretAnswer = currentSecretAnswer;
    }

    public static String getCurrentPhoto() {
        return currentPhoto;
    }

    public static void setCurrentPhoto(String currentPhoto) {
        User.currentPhoto = currentPhoto;
    }

    public static String getCurrentClearanceLevel() {
        return currentClearanceLevel;
    }

    public static void setCurrentClearanceLevel(String currentClearanceLevel) {
        User.currentClearanceLevel = currentClearanceLevel;
    }

    public static String getCurrentType() {
        return currentType;
    }

    public static void setCurrentType(String currentType) {
        User.currentType = currentType;
    }

    public User(String name, String surname, String gender, String dateBirth, String email, 
            String username, String pass, String telephone, String address1, String address2,
            String codePostal, String photo, String dateInscription, String newsletter, String idCompany, 
            String latitude, String longitude, String secretQuestion, String secretAnswer) {

        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateBirth = dateBirth;
        this.email = email;
        this.username = username;
        this.pass = pass;
        this.telephone = telephone;
        this.address1 = address1;
        this.address2 = address2;
        this.codePostal = codePostal;
        this.photo = photo;
        this.dateInscription = dateInscription;
        this.compteActif = "1";
        this.newsletter = newsletter;
        this.type = "User";
        this.clearanceLevel = "0";
        this.idCompany = idCompany;
        this.latitude = latitude;
        this.longitude = longitude;
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
    }

    public User(String id, String name, String surname, String gender, String dateBirth, 
            String email, String username, String pass, String telephone, String address1, 
            String address2, String codePostal, String photo, String dateInscription, String newsletter, 
            String idCompany, String latitude, String longitude, String secretQuestion, String secretAnswer) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateBirth = dateBirth;
        this.email = email;
        this.username = username;
        this.pass = pass;
        this.telephone = telephone;
        this.address1 = address1;
        this.address2 = address2;
        this.codePostal = codePostal;
        this.photo = photo;
        this.dateInscription = dateInscription;
        this.compteActif = "1";
        this.newsletter = newsletter;
        this.type = "User";
        this.clearanceLevel = "0";
        this.idCompany = idCompany;
        this.latitude = latitude;
        this.longitude = longitude;
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.surname);
        hash = 37 * hash + Objects.hashCode(this.email);
        hash = 37 * hash + Objects.hashCode(this.username);
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
        final User other = (User) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.gender, other.gender)) {
            return false;
        }
        if (!Objects.equals(this.dateBirth, other.dateBirth)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.pass, other.pass)) {
            return false;
        }
        if (!Objects.equals(this.telephone, other.telephone)) {
            return false;
        }
        if (!Objects.equals(this.address1, other.address1)) {
            return false;
        }
        if (!Objects.equals(this.address2, other.address2)) {
            return false;
        }
        if (!Objects.equals(this.codePostal, other.codePostal)) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        if (!Objects.equals(this.dateInscription, other.dateInscription)) {
            return false;
        }
        if (!Objects.equals(this.compteActif, other.compteActif)) {
            return false;
        }
        if (!Objects.equals(this.newsletter, other.newsletter)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.clearanceLevel, other.clearanceLevel)) {
            return false;
        }
        if (!Objects.equals(this.idCompany, other.idCompany)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return pass;
    }

    public void setPassword(String pass) {
        this.pass = pass;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getCompteActif() {
        return compteActif;
    }

    public void setCompteActif(String compteActif) {
        this.compteActif = compteActif;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }

    public String getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(String newsletter) {
        this.newsletter = newsletter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClearanceLevel() {
        return clearanceLevel;
    }

    public void setClearanceLevel(String clearanceLevel) {
        this.clearanceLevel = clearanceLevel;
    }

    public String getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(String idCompany) {
        this.idCompany = idCompany;
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

}
