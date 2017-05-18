/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Admin;
import Interfaces.InterfaceAdmin;
import Tools.NamedParameterStatement;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Admin
 */
public class AdminService extends UserService implements InterfaceAdmin {

    public AdminService() {
        super();
    }

    @Override
    public void insertAdmin(Admin u) {
        String column = "(name, surname, gender, dateBirth, email, username, pass, telephone, address1, address2, codePostal, photo, dateInscription, compteActif, newsletter, type, clearanceLevel, idCompany, latitude, longitude,secretQuestion,secretAnswer)";
        String values = " values (:name, :surname, :gender, :dateBirth, :email, :username, :pass, :telephone, :address1, :address2, :codePostal, :photo, :dateInscription, :compteActif, :newsletter, :type, :clearanceLevel, :idCompany, :latitude, :longitude,:secretQuestion,:secretAnswer)";
        String query = "INSERT INTO user " + column + values;
        try {
            NamedParameterStatement st = new NamedParameterStatement(cnx.getConnection(), query);

            st.setString("name", u.getName());
            st.setString("surname", u.getSurname());
            st.setString("gender", u.getGender());
            st.setString("dateBirth", u.getDateBirth());
            st.setString("email", u.getEmail());
            st.setString("username", u.getUsername());
            st.setString("pass", u.getPassword());
            st.setString("telephone", u.getTelephone());
            st.setString("address1", u.getAddress1());
            st.setString("address2", u.getAddress2());
            st.setString("codePostal", u.getCodePostal());
            st.setString("photo", u.getPhoto());
            st.setString("dateInscription", u.getDateInscription());
            st.setString("compteActif", u.getCompteActif());
            st.setString("newsletter", u.getNewsletter());
            st.setString("type", u.getType());
            st.setString("clearanceLevel", u.getAdminLevel());
            st.setString("idCompany", u.getIdCompany());
            st.setString("compteActif", u.getCompteActif());
            st.setString("latitude", u.getLatitude());
            st.setString("longitude", u.getLongitude());
            st.setString("secretQuestion", u.getSecretQuestion());
            st.setString("secretAnswer", u.getSecretAnswer());

            st.executeUpdate();
            Statement selectStatement = cnx.getConnection().createStatement();
            ResultSet list = selectStatement.executeQuery("select * from user order by id desc");
            list.next();
            preferencesservice.insertPreferences(u.getPreferences(), list.getString(1));
            System.out.println("Insertion réussie");
        } catch (SQLException ex) {
            System.err.println("Erreur d'ajout");
            ex.printStackTrace();
        }

    }

    @Override
    public void updateAdmin(Admin u) {
        String column = "name=:name, surname=:surname, gender=:gender, dateBirth=:dateBirth, email=:email, username=:username, pass=:pass, telephone=:telephone, address1=:address1, address2=:address2, codePostal=:codePostal, photo=:photo, dateInscription=:dateInscription, compteActif=:compteActif, newsletter=:newsletter, type=:type, clearanceLevel=:clearanceLevel, idCompany=:idCompany, latitude=:latitude, longitude=:longitude,secretQuestion=:secretQuestion,secretAnswer=:secretAnswer";
        String query = "update user set " + column + "where id=:id";
        try {
            NamedParameterStatement st = new NamedParameterStatement(cnx.getConnection(), query);

            st.setString("name", u.getName());
            st.setString("surname", u.getSurname());
            st.setString("gender", u.getGender());
            st.setString("dateBirth", u.getDateBirth());
            st.setString("email", u.getEmail());
            st.setString("username", u.getUsername());
            st.setString("pass", u.getPassword());
            st.setString("telephone", u.getTelephone());
            st.setString("address1", u.getAddress1());
            st.setString("address2", u.getAddress2());
            st.setString("codePostal", u.getCodePostal());
            st.setString("photo", u.getPhoto());
            st.setString("dateInscription", u.getDateInscription());
            st.setString("compteActif", u.getCompteActif());
            st.setString("newsletter", u.getNewsletter());
            st.setString("type", u.getType());
            st.setString("clearanceLevel", u.getAdminLevel());
            st.setString("idCompany", u.getIdCompany());
            st.setString("compteActif", u.getCompteActif());
            st.setString("latitude", u.getLatitude());
            st.setString("longitude", u.getLongitude());
            st.setString("secretQuestion", u.getSecretQuestion());
            st.setString("secretAnswer", u.getSecretAnswer());
            st.setString("id", u.getCurrentId());

            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erreur de mise à jour");
            ex.printStackTrace();
        }    }

    @Override
    public void deleteAdmin(Admin u) {
        String query = "delete from user where id=? and type='Admin'";
        try {
            PreparedStatement st = null;
            st.setString(1, u.getCurrentId());
            System.out.println(st.toString());
            st.executeUpdate();
            System.err.println("Suppression réussie");

            preferencesservice.deletePreferences(u.getCurrentId());
            System.out.println("Suppression préférences réussie");
        } catch (SQLException ex) {
            System.err.println("Erreur de suppression");
            ex.printStackTrace();
        }
    }

    @Override
    public List<Admin> selectAdmin() {
        String query = "select * from user where compteActif=1 and type='Admin'";
        List<Admin> admins = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = cnx.getConnection().prepareStatement(query);

            ResultSet res = st.executeQuery();
            while (res.next()) {
                Admin u = new Admin(res.getString(18),res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), 
                        res.getString(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10), 
                        res.getString(11), res.getString(12), res.getString(13), res.getString(14), res.getString(16), 
                        res.getString(19), res.getString(20), res.getString(21), res.getString(22), res.getString(23));                admins.add(u);
                System.out.println(u.getId());
            }
            System.err.println("Récupération réussie");
            return admins;
        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return null;    }

    @Override
    public boolean existAdmin(String id, String row) {
        String query = "select " + row + " from user where " + row + "='" + id + "' and type='Admin'";
        System.out.println(query);
        try {
            Statement st = cnx.getConnection().createStatement();
            ResultSet res = st.executeQuery(query);
            System.err.println("Récupération réussie");
            return res.first();
        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Admin> searchAdmin(String input) {
        String query = "select * from user where (name like ? or surname like ?)  and compteActif=1 and type='Admin'";
        List<Admin> admins = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = cnx.getConnection().prepareStatement(query);
            st.setString(1, "%" + input + "%");
            st.setString(2, "%" + input + "%");

            ResultSet res = st.executeQuery();
            while (res.next()) {
                Admin u = new Admin(res.getString(18),res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), 
                        res.getString(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10), 
                        res.getString(11), res.getString(12), res.getString(13), res.getString(14), res.getString(16), 
                        res.getString(19), res.getString(20), res.getString(21), res.getString(22), res.getString(23));                admins.add(u);
            }
            System.err.println("Récupération réussie");
            return admins;
        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Admin getAdminLogin(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Admin getAdminLoginAlternate(String username, String password) {
        String query = "select * from user where (username= ? )  and compteActif=1 and type='Admin'";
        List<Admin> admins = new ArrayList<>();
        Admin u = new Admin();
        PreparedStatement st = null;
        try {
            st = cnx.getConnection().prepareStatement(query);
            st.setString(1, username);

            ResultSet res = st.executeQuery();
            while (res.next()) {
                 u = new Admin(res.getString(18),res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), 
                        res.getString(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10), 
                        res.getString(11), res.getString(12), res.getString(13), res.getString(14), res.getString(16), 
                        res.getString(19), res.getString(20), res.getString(21), res.getString(22), res.getString(23));    
            }
            admins.add(u);
            System.err.println("Récupération réussie");
            if (u == null) {
                return null;
            } else if (u.getPass() == password) {
                return u;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return null;
    }

}
