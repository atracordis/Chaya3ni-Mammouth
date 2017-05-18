/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Employe;
import Entities.Filiale;
import Tools.Conn;
import Tools.NamedParameterStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author asus
 */
public class ServEmploye implements Interfaces.IEmploye{
Conn cnx;

    public ServEmploye() 
    {

        this.cnx = Conn.getInstance();
    }   
    @Override
    public void AjouterEmploye(Employe e) 
    {
        String column = "(name, surname, gender, dateBirth, email, username, pass, telephone, address1, address2, codePostal, photo, dateInscription, compteActif, newsletter, type, clearanceLevel, idCompany, latitude, longitude,secretQuestion,secretAnswer,idFiliale)";
        String values = " values (:name, :surname, :gender, :dateBirth, :email, :username, :pass, :telephone, :address1, :address2, :codePostal, :photo, :dateInscription, :compteActif, :newsletter, :type, :clearanceLevel, :idCompany, :latitude, :longitude,:secretQuestion,:secretAnswer,:idFiliale)";
        String req = "INSERT INTO users " + column + values;
        try {
           NamedParameterStatement st = new NamedParameterStatement(cnx.getConnection(), req);

            st.setString("name", e.getName());
            st.setString("surname", e.getSurname());
            st.setString("gender", e.getGender());
            st.setString("dateBirth", e.getDateBirth());
            st.setString("email", e.getEmail());
            st.setString("username", e.getUsername());
            st.setString("pass", e.getPassword());
            st.setString("telephone", e.getTelephone());
            st.setString("address1", e.getAddress1());
            st.setString("address2", e.getAddress2());
            st.setString("codePostal", e.getCodePostal());
            st.setString("photo", e.getPhoto());
            st.setString("dateInscription", e.getDateInscription());
            st.setString("compteActif", e.getCompteActif());
            st.setString("newsletter", e.getNewsletter());
            st.setString("type", e.getType());
            st.setString("clearanceLevel", e.getClearanceLevel());
            st.setInt("idCompany", e.getIdcompany());
            st.setString("compteActif", e.getCompteActif());
            st.setString("latitude", e.getLatitude());
            st.setString("longitude", e.getLongitude());
            st.setString("secretQuestion", e.getSecretQuestion());
            st.setString("secretAnswer", e.getSecretAnswer());
            st.setInt("idFiliale", e.getIdFiliale());
            st.executeUpdate();
            
            System.out.println("Insertion réussie");
        } catch (SQLException ex) {
            System.err.println("Erreur d'ajout");
            ex.printStackTrace();
        }
    }

    @Override
    public void SupprimerEmploye(Employe e) {
       String query = "delete from users where (username=:a and pass=:b)";
        try {
            NamedParameterStatement st = new NamedParameterStatement(cnx.getConnection(), query);
            st.setString("a", e.getUsername());
            st.setString("b", e.getPassword());
            System.out.println(st.toString());
            st.executeUpdate();
            System.err.println("Suppression réussie");

          //  preferencesservice.deletePreferences(User.getCurrentId());
           // System.out.println("Suppression préférences réussie");
        } catch (SQLException ex) {
            System.err.println("Erreur de suppression");
            
        }
    }

    @Override
    public Employe ChercherEmploye(String input) 
    {
         String query = "select * from users where (username like ?)  and compteActif=1";
        
        PreparedStatement st = null;
        try {
            st = cnx.getConnection().prepareStatement(query);
            st.setString(1, "%" + input+ "%");
            
            Employe e = null;
            ResultSet res = st.executeQuery();
            while (res.next()) {
            
                e = new Employe(res.getInt(24),res.getInt(19),res.getString(2), res.getString(3), res.getString(4), 
                        res.getString(5), res.getString(6),
                        res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getString(11),
                        res.getString(12), res.getString(13), res.getString(14), res.getString(15), res.getString(16),               
                        res.getString(20), res.getString(21), res.getString(22), res.getString(23));
                
            }
            System.err.println("Récupération réussie");
            return e;
        } 
        catch (SQLException ex) {
            System.err.println("Erreur de récupération");
    }
        return null;
    }
    @Override
    public List<Employe> ChercherEmployeFiliale(int idf) 
    {
        List<Employe> liste = new ArrayList <>();
         String query = "select * from users where idFiliale=?  and compteActif=1";
        
        PreparedStatement st = null;
        try {
            st = cnx.getConnection().prepareStatement(query);
            st.setInt (1,idf);
            
            Employe e = null;
            ResultSet res = st.executeQuery();
            while (res.next()) 
            {
            
                e = new Employe(res.getInt(24),res.getInt(19),res.getString(2), res.getString(3), res.getString(4), 
                        res.getString(5), res.getString(6),
                        res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getString(11),
                        res.getString(12), res.getString(13), res.getString(14), res.getString(15), res.getString(16),               
                        res.getString(20), res.getString(21), res.getString(22), res.getString(23));
                liste.add(e);
            }
            
            return liste;
        } 
        catch (SQLException ex) {
            System.err.println("Erreur de récupération");
    }
        return null;
    }
   
    @Override
    public void ModifierEmploye(Employe e) {
       String column = "name=:name, surname=:surname, gender=:gender, dateBirth=:dateBirth, email=:email, username=:username, pass=:pass, telephone=:telephone, address1=:address1, address2=:address2, codePostal=:codePostal, photo=:photo, dateInscription=:dateInscription, compteActif=:compteActif, newsletter=:newsletter, type=:type, clearanceLevel=:clearanceLevel, idCompany=:idCompany, latitude=:latitude, longitude=:longitude,secretQuestion=:secretQuestion, secretAnswer=:secretAnswer,idFiliale=:idFiliale ";;
        
        String query = "update users set " + column + "where id=:id";

        try {
            NamedParameterStatement st = new NamedParameterStatement(cnx.getConnection(), query);

            st.setString("name", e.getName());
            st.setString("surname", e.getSurname());
            st.setString("gender", e.getGender());
            st.setString("dateBirth", e.getDateBirth());
            st.setString("email", e.getEmail());
            st.setString("username", e.getUsername());
            st.setString("pass", e.getPassword());
            st.setString("telephone", e.getTelephone());
            st.setString("address1", e.getAddress1());
            st.setString("address2", e.getAddress2());
            st.setString("codePostal", e.getCodePostal());
            st.setString("photo", e.getPhoto());
            st.setString("dateInscription", e.getDateInscription());
            st.setString("compteActif", e.getCompteActif());
            st.setString("newsletter", e.getNewsletter());
            st.setString("type", e.getType());
            st.setString("clearanceLevel", e.getClearanceLevel());
            st.setInt("idCompany", e.getIdcompany());
            st.setString("compteActif", e.getCompteActif());
            st.setString("latitude", e.getLatitude());
            st.setString("longitude", e.getLongitude());
            st.setString("secretQuestion", e.getSecretQuestion());
            st.setString("secretAnswer", e.getSecretAnswer());
            st.setInt("idFiliale", e.getIdFiliale());
            st.setString("id", Employe.getCurrentId());
            System.out.println(st.getStatement().toString());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erreur de mise à jour");
        }
    }

    @Override
     
   public List<Employe> AfficherEmployeSociete (int id)
    {
        List<Employe> liste = new ArrayList <>();
         String query = "select * from users where idCompany=?  and compteActif=1";
        
        PreparedStatement st = null;
        try {
            st = cnx.getConnection().prepareStatement(query);
            st.setInt (1,id);
            
            Employe e = null;
            ResultSet res = st.executeQuery();
            while (res.next()) 
            {
            
                e = new Employe(res.getInt(24),res.getInt(19),res.getString(2), res.getString(3), res.getString(4), 
                        res.getString(5), res.getString(6),
                        res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getString(11),
                        res.getString(12), res.getString(13), res.getString(14), res.getString(15), res.getString(16),               
                        res.getString(20), res.getString(21), res.getString(22), res.getString(23));
                liste.add(e);
            }
            
            return liste;
        } 
        catch (SQLException ex) {
            System.err.println("Erreur de récupération");
    }
        return null;
    }
    public ResultSet selectCountParFiliale(int idc) {
        String query = "SELECT COUNT(id), idFiliale FROM users WHERE idCompany=? GROUP BY idFiliale";
        PreparedStatement st = null;
        try {
            st = cnx.getConnection().prepareStatement(query);
            st.setInt (1,idc);
            ResultSet res = st.executeQuery();
            return res;
        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return null;
    }
}
