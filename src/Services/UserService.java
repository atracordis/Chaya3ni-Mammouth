/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.*;
import Interfaces.InterfaceUser;
import java.sql.*;
import Tools.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class UserService implements InterfaceUser {

    Conn cnx;
    protected PreferencesService preferencesservice;

    public UserService() {
        this.cnx = Conn.getInstance();
        preferencesservice = new PreferencesService();
    }

    /**
     *
     * @param u
     */
    @Override
    public void insertUser(User u) {

        String column = "(roles, enabled, username_canonical, name, surname, gender, dateBirth,email_canonical, email, username, password, telephone, address1, address2, codePostal, photo, dateInscription, compteActif, newsletter, type, clearanceLevel, idCompany, latitude, longitude,secretQuestion,secretAnswer)";
        String values = " values (:roles, :enabled, :username_canonical, :name, :surname, :gender, :dateBirth,:email_canonical, :email, :username, :pass, :telephone, :address1, :address2, :codePostal, :photo, :dateInscription, :compteActif, :newsletter, :type, :clearanceLevel, :idCompany, :latitude, :longitude,:secretQuestion,:secretAnswer)";
        String query = "INSERT INTO user " + column + values;
        try {
            NamedParameterStatement st = new NamedParameterStatement(cnx.getConnection(), query);
            st.setString("roles", "a:1:{i:0;s:11:\"ROLE_CLIENT\";}");
            st.setString("enabled", "1");
            st.setString("username_canonical", u.getUsername());
            st.setString("name", u.getName());
            st.setString("surname", u.getSurname());
            st.setString("gender", u.getGender());
            st.setString("dateBirth", u.getDateBirth());
            st.setString("email", u.getEmail());
            st.setString("email_canonical", u.getEmail());
            st.setString("username", u.getUsername());
            st.setString("pass", BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()));
            st.setString("telephone", u.getTelephone());
            st.setString("address1", u.getAddress1());
            st.setString("address2", u.getAddress2());
            st.setString("codePostal", u.getCodePostal());
            st.setString("photo", u.getPhoto());
            st.setString("dateInscription", u.getDateInscription());
            st.setString("compteActif", u.getCompteActif());
            st.setString("newsletter", u.getNewsletter());
            st.setString("type", u.getType());
            st.setString("clearanceLevel", u.getClearanceLevel());
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
            System.out.println("HERE *************************");
            System.out.println(list.getString(1));
            System.out.println("HERE *************************");
            System.out.println(u.getPreferences());
            System.out.println("HERE *************************");
            preferencesservice.insertPreferences(u.getPreferences(), list.getString(1));
            System.out.println("Insertion réussie");
        } catch (SQLException ex) {
            System.err.println("Erreur d'ajout");
            ex.printStackTrace();
        }
    }

    @Override
    public void updateUser(User u) {
        String column = "username_canonical=:username_canonical, name=:name, email_canonical=:email_canonical, surname=:surname, gender=:gender, dateBirth=:dateBirth, email=:email, username=:username, password=:pass, telephone=:telephone, address1=:address1, address2=:address2, codePostal=:codePostal, photo=:photo, dateInscription=:dateInscription, compteActif=:compteActif, newsletter=:newsletter, type=:type, clearanceLevel=:clearanceLevel, idCompany=:idCompany, latitude=:latitude, longitude=:longitude,secretQuestion=:secretQuestion, secretAnswer=:secretAnswer ";;

        String query = "update user set " + column + "where id=:id";

        try {
            NamedParameterStatement st = new NamedParameterStatement(cnx.getConnection(), query);

            st.setString("username_canonical", u.getUsername());
            st.setString("name", u.getName());
            st.setString("email_canonical", u.getEmail());
            st.setString("surname", u.getSurname());
            st.setString("gender", u.getGender());
            st.setString("dateBirth", u.getDateBirth());
            st.setString("email", u.getEmail());
            st.setString("username", u.getUsername());
            st.setString("pass", BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()));
            st.setString("telephone", u.getTelephone());
            st.setString("address1", u.getAddress1());
            st.setString("address2", u.getAddress2());
            st.setString("codePostal", u.getCodePostal());
            st.setString("photo", u.getPhoto());
            st.setString("dateInscription", u.getDateInscription());
            st.setString("compteActif", u.getCompteActif());
            st.setString("newsletter", u.getNewsletter());
            st.setString("type", u.getType());
            st.setString("clearanceLevel", u.getClearanceLevel());
            st.setString("idCompany", u.getIdCompany());
            st.setString("compteActif", u.getCompteActif());
            st.setString("latitude", u.getLatitude());
            st.setString("longitude", u.getLongitude());
            st.setString("secretQuestion", u.getSecretQuestion());
            st.setString("secretAnswer", u.getSecretAnswer());
            st.setString("id", User.getCurrentId());
            System.out.println(st.getStatement().toString());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erreur de mise à jour");
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User u) {
        String query = "delete from user where username=:a ";
        try {
            NamedParameterStatement st = new NamedParameterStatement(cnx.getConnection(), query);
            st.setString("a", u.getUsername());
            System.out.println(st.toString());
            st.executeUpdate();
            System.err.println("Suppression réussie");

            //  preferencesservice.deletePreferences(User.getCurrentId());
            // System.out.println("Suppression préférences réussie");
        } catch (SQLException ex) {
            System.err.println("Erreur de suppression");
            ex.printStackTrace();
        }
    }

    @Override
    public void deactivateUser(User u) {
        String query = "update user set compteActif=1 where username=?";
        try {
            PreparedStatement st = null;
            st.setString(1, User.getCurrentUsername());
            System.out.println(st.toString());
            st.executeUpdate();
            System.err.println("Suppression réussie");

            //      preferencesservice.deletePreferences(User.getCurrentId());
            //        System.out.println("Suppression préférences réussie");
        } catch (SQLException ex) {
            System.err.println("Erreur de suppression");
            ex.printStackTrace();
        }
    }

    @Override
    public User getUserLogin(String username, String password) {

        String query = "select id as id, name as name, surname as surname, gender as gender"
                + ", dateBirth as dateBirth, email as email, username as username,"
                + "password as pass, telephone as telephone, address1 as address1,"
                + "address2 as address2, codePostal as codePostal, photo as photo,"
                + "dateInscription as dateInscription, newsletter as newsletter,"
                + "idCompany as idCompany, latitude as latitude, longitude as longitude, "
                + "secretQuestion as secretQuestion, secretAnswer as secretAnswer"
                + " from user where (username=? )  ";
        User u = new User();
        System.out.println(query);
        PreparedStatement st = null;
        try {
            st = cnx.getConnection().prepareStatement(query);
            st.setString(1, username);

            ResultSet res = st.executeQuery();
            if (res.next()) {
                /*User(String id, String name, String surname, String gender, String dateBirth, 
            String email, String username, String pass, String telephone, String address1, 
            String address2, String codePostal, String photo, String dateInscription, String newsletter, 
            String idCompany, String latitude, String longitude, String secretQuestion, String secretAnswer) {*/

                u = new User(res.getString("id"), res.getString("name"), res.getString("surname"), res.getString("gender"), res.getString("dateBirth"),
                        res.getString("email"), res.getString("username"), res.getString("pass"), res.getString("telephone"), res.getString("address1"),
                        res.getString("address2"), res.getString("codePostal"), res.getString("photo"), res.getString("dateInscription"), res.getString("newsletter"),
                        res.getString("idCompany"), res.getString("latitude"), res.getString("longitude"), res.getString("secretQuestion"), res.getString("secretAnswer"));
            }

            String query2 = "select roles,type from user where username=?  and compteActif=1";
            PreparedStatement st2 = null;
            st2 = cnx.getConnection().prepareStatement(query2);
            st2.setString(1, username);
            ResultSet res2 = st2.executeQuery();
            System.out.println(u.getPass());
            if (res2.next()) {
                String k2 = res2.getString("type");
                String k = res2.getString("roles");
                System.err.println("Récupération réussie");
                if (k2 != null) {
                    if (k2.equals("admin")) {
                        User.setIsAdmin(true);
                        User.isRepresentative = false;
                    } else if (k2.equals("User")) {
                        User.setIsAdmin(false);
                        User.isRepresentative = false;
                    } else {
                        User.setIsAdmin(false);
                        User.isRepresentative = true;
                    }
                } else if (k != null) {
                    if (k.equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {
                        User.setIsAdmin(true);
                        User.isRepresentative = false;
                    } else 
                    {
                    User.setIsAdmin(false);
                        User.isRepresentative = false;
                    }
                    
                    /* 
                    
                    else  if (k.equals("a:1:{i:0;s:10:\"ROLE_USER\";}")) {
                        User.setIsAdmin(true);
                    } 
                    else 
                    */

                }
            }

            return u;
        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getEmails() {
        List<String> emails = new ArrayList<>();
        try {
            String query = "select email from user where newsletter=1 and compteActif=1";
            PreparedStatement st = null;
            st = cnx.getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                emails.add(rs.getString(1));
            }
            return emails;
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public User getUserFromUserName(String username) {

        String query = "select id as id, name as name, surname as surname, gender as gender"
                + ", dateBirth as dateBirth, email as email, username as username,"
                + "password as pass, telephone as telephone, address1 as address1,"
                + "address2 as address2, codePostal as codePostal, photo as photo,"
                + "dateInscription as dateInscription, newsletter as newsletter,"
                + "idCompany as idCompany, latitude as latitude, longitude as longitude, "
                + "secretQuestion as secretQuestion, secretAnswer as secretAnswer"
                + " from user where username= ?   and compteActif=1";
        User u = new User();
        PreparedStatement st = null;
        try {
            st = cnx.getConnection().prepareStatement(query);
            st.setString(1, username);

            ResultSet res = st.executeQuery();
            if (res.next()) {
                u = new User(res.getString("id"), res.getString("name"), res.getString("surname"), res.getString("gender"), res.getString("dateBirth"),
                        res.getString("email"), res.getString("username"), res.getString("pass"), res.getString("telephone"), res.getString("address1"),
                        res.getString("address2"), res.getString("codePostal"), res.getString("photo"), res.getString("dateInscription"), res.getString("newsletter"),
                        res.getString("idCompany"), res.getString("latitude"), res.getString("longitude"), res.getString("secretQuestion"), res.getString("secretAnswer"));
                System.err.println("Récupération réussie");
                return u;
            } else {
                return null; // find nothing
            }
        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public int getUserLoginAlternate(String username, String password) {

        String query = "select id as id, name as name, surname as surname, gender as gender"
                + ", dateBirth as dateBirth, email as email, username as username,"
                + "password as pass, telephone as telephone, address1 as address1,"
                + "address2 as address2, codePostal as codePostal, photo as photo,"
                + "dateInscription as dateInscription, newsletter as newsletter,"
                + "idCompany as idCompany, latitude as latitude, longitude as longitude, "
                + "secretQuestion as secretQuestion, secretAnswer as secretAnswer"
                + " from user where (username= ? )  and compteActif=1";
        List<User> users = new ArrayList<>();
        User u = new User();
        PreparedStatement st = null;
        try {
            st = cnx.getConnection().prepareStatement(query);
            st.setString(1, username);

            ResultSet res = st.executeQuery();
            if (res.next()) {
                u = new User(res.getString("id"), res.getString("name"), res.getString("surname"), res.getString("gender"), res.getString("dateBirth"),
                        res.getString("email"), res.getString("username"), res.getString("pass"), res.getString("telephone"), res.getString("address1"),
                        res.getString("address2"), res.getString("codePostal"), res.getString("photo"), res.getString("dateInscription"), res.getString("newsletter"),
                        res.getString("idCompany"), res.getString("latitude"), res.getString("longitude"), res.getString("secretQuestion"), res.getString("secretAnswer"));
                System.err.println("Récupération réussie");

                if (BCrypt.checkpw(password, u.getPass())) {
                    return 1; //l9ina wou jawou behi
                } else {
                    return 2;// mezel chwaya
                }
            } else {
                return 3; // find nothing
            }
        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public ResultSet selectUserResultSet() {
        String query = "select id as id, name as name, surname as surname, gender as gender"
                + ", dateBirth as dateBirth, email as email, username as username,"
                + "password as pass, telephone as telephone, address1 as address1,"
                + "address2 as address2, codePostal as codePostal, photo as photo,"
                + "dateInscription as dateInscription, newsletter as newsletter,"
                + "idCompany as idCompany, latitude as latitude, longitude as longitude, "
                + "secretQuestion as secretQuestion, secretAnswer as secretAnswer"
                + "  from user where compteActif=1";
        PreparedStatement st = null;
        try {
            st = cnx.getConnection().prepareStatement(query);

            ResultSet res = st.executeQuery();
            return res;
        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> selectUser() {
        String query = "select id as id, name as name, surname as surname, gender as gender"
                + ", dateBirth as dateBirth, email as email, username as username,"
                + "password as pass, telephone as telephone, address1 as address1,"
                + "address2 as address2, codePostal as codePostal, photo as photo,"
                + "dateInscription as dateInscription, newsletter as newsletter,"
                + "idCompany as idCompany, latitude as latitude, longitude as longitude, "
                + "secretQuestion as secretQuestion, secretAnswer as secretAnswer"
                + "  from user  where compteActif=1";
        List<User> users = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = cnx.getConnection().prepareStatement(query);

            ResultSet res = st.executeQuery();
            while (res.next()) {
                User u = new User(res.getString("id"), res.getString("name"), res.getString("surname"), res.getString("gender"), res.getString("dateBirth"),
                        res.getString("email"), res.getString("username"), res.getString("pass"), res.getString("telephone"), res.getString("address1"),
                        res.getString("address2"), res.getString("codePostal"), res.getString("photo"), res.getString("dateInscription"), res.getString("newsletter"),
                        res.getString("idCompany"), res.getString("latitude"), res.getString("longitude"), res.getString("secretQuestion"), res.getString("secretAnswer"));
                users.add(u);
                System.out.println(u.getId());
            }
            System.err.println("Récupération réussie");
            return users;
        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean existUser(String id, String row) {
        String query = "select " + row + " from user where " + row + "='" + id + "'";
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
    public boolean existpassword(String id) {
        String query = "select * from compromisedpasswords where pass='" + id + "'";
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

    /**
     *
     * @param input
     * @return
     */
    @Override
    public List<User> searchUser(String input) {
        String query = "select id as id, name as name, surname as surname, gender as gender"
                + ", dateBirth as dateBirth, email as email, username as username,"
                + "password as pass, telephone as telephone, address1 as address1,"
                + "address2 as address2, codePostal as codePostal, photo as photo,"
                + "dateInscription as dateInscription, newsletter as newsletter,"
                + "idCompany as idCompany, latitude as latitude, longitude as longitude, "
                + "secretQuestion as secretQuestion, secretAnswer as secretAnswer"
                + "  from user  where (name like ? or surname like ?)  and compteActif=1";
        List<User> users = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = cnx.getConnection().prepareStatement(query);
            st.setString(1, "%" + input + "%");
            st.setString(2, "%" + input + "%");

            ResultSet res = st.executeQuery();
            while (res.next()) {
                User u = new User(res.getString("id"), res.getString("name"), res.getString("surname"), res.getString("gender"), res.getString("dateBirth"),
                        res.getString("email"), res.getString("username"), res.getString("pass"), res.getString("telephone"), res.getString("address1"),
                        res.getString("address2"), res.getString("codePostal"), res.getString("photo"), res.getString("dateInscription"), res.getString("newsletter"),
                        res.getString("idCompany"), res.getString("latitude"), res.getString("longitude"), res.getString("secretQuestion"), res.getString("secretAnswer"));
                users.add(u);
            }
            System.err.println("Récupération réussie");
            return users;
        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet selectCountPerMonth() {
        String query = "SELECT COUNT(id), DATE_FORMAT(dateInscription, '%M') AS OrderYear FROM user GROUP BY EXTRACT(MONTH FROM dateInscription)";
        PreparedStatement st = null;
        try {
            st = cnx.getConnection().prepareStatement(query);

            ResultSet res = st.executeQuery();
            return res;
        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet selectAnimalYear() {
        String query = "SELECT DATE_FORMAT(user.dateInscription, '%y') AS OrderYear, count(preferences.idUser) as \"Allow animal\" FROM user inner join preferences on preferences.idUser=user.id where preferences.animal='allow' GROUP BY EXTRACT(YEAR FROM dateInscription), preferences.animal";
        PreparedStatement st = null;
        try {
            st = cnx.getConnection().prepareStatement(query);

            ResultSet res = st.executeQuery();
            return res;
        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet selectCountPerYear() {
        String query = "SELECT COUNT(id), DATE_FORMAT(dateInscription, '%y') AS OrderYear FROM user GROUP BY EXTRACT(YEAR FROM dateInscription)";
        PreparedStatement st = null;
        try {
            st = cnx.getConnection().prepareStatement(query);

            ResultSet res = st.executeQuery();
            return res;
        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet selectSmokers() {
        String query = "select count(idUser), if (smoking='allow','Smoker','Non smoker') as smoking from preferences group by smoking";
        PreparedStatement st = null;
        try {
            st = cnx.getConnection().prepareStatement(query);

            ResultSet res = st.executeQuery();
            return res;
        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return null;
    }
}
