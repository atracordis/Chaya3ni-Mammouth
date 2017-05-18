/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Preferences;
import Entities.User;
import Interfaces.InterfacePreferences;
import Tools.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Admin
 */
public class PreferencesService implements InterfacePreferences {

    Conn cnx;

    public PreferencesService() {
        this.cnx = Conn.getInstance();
    }

    @Override
    public void insertPreferences(Preferences p, String id) {
        String column = " (idUser, email, telephone, address, latitude, longitude,music, musicTaste, smoking, allowSmoking,animal, haveAnimal,confortVoiture, modeleVoiture, marqueVoiture, telephoneVar, addressVar, newsletter,dateTime) ";
        String values = " values (:idUser, :email, :telephone, :address, :latitude, :longitude,:music, :musicTaste, :smoking, :allowSmoking,:animal, :haveAnimal,:confortVoiture, :modeleVoiture, :marqueVoiture, :telephoneVar, :addressVar, :newsletter, :dateTime) ";

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.now();

        String query = "INSERT INTO preferences " + column + values;
        try {
            NamedParameterStatement st = new NamedParameterStatement(cnx.getConnection(), query);
            st.setString("idUser", id);
            st.setString("email", p.getEmail());
            st.setString("telephone", p.getTelephone());
            st.setString("address", p.getAddress());
            st.setString("latitude", p.getLatitude());
            st.setString("longitude", p.getLongitude());
            st.setString("music", p.getMusic());
            st.setString("musicTaste", p.getMusicTaste());
            st.setString("smoking", p.getSmoking());
            st.setString("allowSmoking", p.getAllowSmoking());
            st.setString("animal", p.getAnimal());
            st.setString("haveAnimal", p.getHaveAnimal());
            st.setString("confortVoiture", p.getConfortVoiture());
            st.setString("modeleVoiture", p.getModeleVoiture());
            st.setString("marqueVoiture", p.getMarqueVoiture());
            st.setString("telephoneVar", "0");
            st.setString("addressVar", " ");
            st.setString("newsletter", "1");
            st.setString("dateTime",  dtf.format(localDate));

            st.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Registration Successfull");
            alert.showAndWait();

        } catch (SQLException ex) {
            System.err.println("Erreur d'ajout");
            ex.printStackTrace();
        }

    }

    @Override
    public void updatePreferences(Preferences p, String id) {
        String column = " email=:email, telephone=:telephone , address=:address , latitude=:latitude , longitude=:longitude,music=:music , musicTaste=:musicTaste , smoking=:smoking , allowSmoking=:allowSmoking,animal=:animal , haveAnimal=:haveAnimal,confortVoiture=:confortVoiture , modeleVoiture=:modeleVoiture , marqueVoiture=:marqueVoiture ";
        String query = "update preferences set " + column + "where idUser=:id";

        try {
            NamedParameterStatement st = new NamedParameterStatement(cnx.getConnection(), query);

            st.setString("id", id);
            st.setString("email", p.getEmail());
            st.setString("telephone", p.getTelephone());
            st.setString("address", p.getAddress());
            st.setString("latitude", p.getLatitude());
            st.setString("longitude", p.getLongitude());
            st.setString("music", p.getMusic());
            st.setString("musicTaste", p.getMusicTaste());
            st.setString("smoking", p.getSmoking());
            st.setString("allowSmoking", p.getAllowSmoking());
            st.setString("animal", p.getAnimal());
            st.setString("haveAnimal", p.getHaveAnimal());
            st.setString("confortVoiture", p.getConfortVoiture());
            st.setString("modeleVoiture", p.getModeleVoiture());
            st.setString("marqueVoiture", p.getMarqueVoiture());

            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erreur d'ajout");
            ex.printStackTrace();
        }

    }

    @Override
    public void deletePreferences(String id) {
        String query = "delete from user where idUser=:a";
        try {
            NamedParameterStatement st = new NamedParameterStatement(cnx.getConnection(), query);
            st.setString("a", id);
            System.out.println(st.toString());
            st.executeUpdate();
            System.err.println("Suppression réussie");
        } catch (SQLException ex) {
            System.err.println("Erreur de suppression");
            ex.printStackTrace();
        }
    }

    @Override
    public Map<User, Preferences> selectPreferences() {
        Map<User, Preferences> preferences = new HashMap<>();
        String query = "select  user.id as id, user.name as name, user.surname as surname, user.gender as gender"
                + ", user.dateBirth as dateBirth, user.email as email, user.username as username,"
                + "user.password as pass, user.telephone as telephone, user.address1 as address1,"
                + "user.address2 as address2, user.codePostal as codePostal, user.photo as photo,"
                + "user.dateInscription as user.dateInscription, user.newsletter as user.newsletter,"
                + "user.idCompany as idCompany, user.latitude as latitude, user.longitude as longitude, "
                + "user.secretQuestion as secretQuestion, user.secretAnswer as secretAnswer, "
                + "preferences.email as prefmail, preferences.telephone as phone, preferences.address as address"
                + "preferences.latitude as lat, preferences.longitude as long, preferences.music as music, preferences.musicTaste "
                + "as musicTaste, preferences.smoking as smoking, preferences.allowSmoking as allowSmoking, preferences.animal"
                + "as animal, preferences.haveAnimal as haveAnimal, preferences.confortVoiture as confortVoiture"
                + ", preferences.modeleVoiture as modele, preferences.marqueVoiture as marque"
                + " from user inner join preferences on preferences.idUser = user.id where user.compteActif=1 order by user.name asc";
        PreparedStatement st = null;
        try {
            st = cnx.getConnection().prepareStatement(query);

            ResultSet res = st.executeQuery();
            while (res.next()) {

                User u = new User(res.getString("id"), res.getString("name"), res.getString("surname"), res.getString("gender"), res.getString("dateBirth"),
                        res.getString("email"), res.getString("username"), res.getString("pass"), res.getString("telephone"), res.getString("address1"),
                        res.getString("address2"), res.getString("codePostal"), res.getString("photo"), res.getString("dateInscription"), res.getString("newsletter"),
                        res.getString("idCompany"), res.getString("latitude"), res.getString("longitude"), res.getString("secretQuestion"), res.getString("secretAnswer"));

                Preferences p = new Preferences(res.getString("id"), res.getString("prefmail"), res.getString("phone"),
                        res.getString("address"), res.getString("lat"), res.getString("long"), res.getString("music"),
                        res.getString("musicTaste"), res.getString("smoking"), res.getString("allowSmoking"), res.getString("animal"),
                        res.getString("haveAnimal"), res.getString("confortVoiture"), res.getString("modele"), res.getString("marque"));
                preferences.put(u, p);
            }
            System.err.println("Récupération réussie");
            return preferences;

        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return null;

    }

    @Override
    public User selectPreferences(String username, String pass) {

        String query1 = "select id as id, name as name, surname as surname, gender as gender"
                + ", dateBirth as dateBirth, email as email, username as username,"
                + "password as pass, telephone as telephone, address1 as address1,"
                + "address2 as address2, codePostal as codePostal, photo as photo,"
                + "dateInscription as dateInscription, newsletter as newsletter,"
                + "idCompany as idCompany, latitude as latitude, longitude as longitude, "
                + "secretQuestion as secretQuestion, secretAnswer as secretAnswer"
                + "  from user  where username=:username  ";
        try {
            NamedParameterStatement st = new NamedParameterStatement(Conn.getInstance().getConnection(), query1);
            st.setString("username", User.getCurrentUsername());
            ResultSet res = st.executeQuery();
            System.err.println("Récupération réussie");
            res.next();
            User u = new User(res.getString("id"), res.getString("name"), res.getString("surname"), res.getString("gender"), res.getString("dateBirth"),
                    res.getString("email"), res.getString("username"), res.getString("pass"), res.getString("telephone"), res.getString("address1"),
                    res.getString("address2"), res.getString("codePostal"), res.getString("photo"), res.getString("dateInscription"), res.getString("newsletter"),
                    res.getString("idCompany"), res.getString("latitude"), res.getString("longitude"), res.getString("secretQuestion"), res.getString("secretAnswer"));
            String query2 = "select * from preferences where idUser=:id";
            NamedParameterStatement st2 = new NamedParameterStatement(Conn.getInstance().getConnection(), query2);
            st2.setString("id", User.getCurrentId());
            ResultSet res2 = st2.executeQuery();
            res2.next();

            Preferences p = new Preferences(res2.getString("idUser"), res2.getString("email"), res2.getString("telephone"),
                    res2.getString("address"), res2.getString("latitude"), res2.getString("longitude"),
                    res2.getString("music"), res2.getString("musicTaste"), res2.getString("smoking"),
                    res2.getString("allowSmoking"), res2.getString("animal"), res2.getString("haveAnimal"),
                    res2.getString("confortVoiture"), res2.getString("modeleVoiture"), res2.getString("marqueVoiture"));
            System.err.println(p);
            u.setPreferences(p);

            return u;
        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public User selectPreferencesId(String id) {
        //    String query = "select user.*, preferences.* from user inner join preferences on preferences.idUser = user.id where user.compteActif=1 and user.username=:user and user.pass=:pass";
        String query1 = "select id as id, name as name, surname as surname, gender as gender"
                + ", dateBirth as dateBirth, email as email, username as username,"
                + "password as pass, telephone as telephone, address1 as address1,"
                + "address2 as address2, codePostal as codePostal, photo as photo,"
                + "dateInscription as dateInscription, newsletter as newsletter,"
                + "idCompany as idCompany, latitude as latitude, longitude as longitude, "
                + "secretQuestion as secretQuestion, secretAnswer as secretAnswer"
                + "  from user where id=:id";
        try {
            NamedParameterStatement st = new NamedParameterStatement(Conn.getInstance().getConnection(), query1);
            st.setString("id", id);
            ResultSet res = st.executeQuery();
            System.err.println("Récupération réussie");
            res.first();
            User u = new User(res.getString("id"), res.getString("name"), res.getString("surname"), res.getString("gender"), res.getString("dateBirth"),
                    res.getString("email"), res.getString("username"), res.getString("pass"), res.getString("telephone"), res.getString("address1"),
                    res.getString("address2"), res.getString("codePostal"), res.getString("photo"), res.getString("dateInscription"), res.getString("newsletter"),
                    res.getString("idCompany"), res.getString("latitude"), res.getString("longitude"), res.getString("secretQuestion"), res.getString("secretAnswer"));
            String query2 = "select * from preferences where idUser=:id";
            NamedParameterStatement st2 = new NamedParameterStatement(Conn.getInstance().getConnection(), query2);
            st2.setString("id", id);
            ResultSet res2 = st2.executeQuery();
            res2.next();
            Preferences p = new Preferences(res2.getString("idUser"), res2.getString("email"), res2.getString("telephone"),
                    res2.getString("address"), res2.getString("latitude"), res2.getString("longitude"),
                    res2.getString("music"), res2.getString("musicTaste"), res2.getString("smoking"),
                    res2.getString("allowSmoking"), res2.getString("animal"), res2.getString("haveAnimal"),
                    res2.getString("confortVoiture"), res2.getString("modeleVoiture"), res2.getString("marqueVoiture"));
            System.err.println(p);
            u.setPreferences(p);

            return u;
        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return null;
    }
}
