/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Employe;
import Entities.Representant;
import Tools.Conn;
import Tools.NamedParameterStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author asus
 */
public class ServRepresentant implements Interfaces.IRepresentant{
Conn cnx;

    public ServRepresentant() 
    {

        this.cnx = Conn.getInstance();
    }  

    @Override
    public void AjouterRepresentant(Representant r) 
    {
        String column = "(name, surname, gender, dateBirth, email, username, pass, telephone, address1, address2, codePostal, photo, dateInscription, compteActif, newsletter, type, clearanceLevel, idCompany, latitude, longitude,secretQuestion,secretAnswer)";
        String values = " values (:name, :surname, :gender, :dateBirth, :email, :username, :pass, :telephone, :address1, :address2, :codePostal, :photo, :dateInscription, :compteActif, :newsletter, :type, :clearanceLevel, :idCompany, :latitude, :longitude,:secretQuestion,:secretAnswer)";
        String req = "INSERT INTO users " + column + values;
        try {
           NamedParameterStatement st = new NamedParameterStatement(cnx.getConnection(), req);

            st.setString("name", r.getName());
            st.setString("surname", r.getSurname());
            st.setString("gender", r.getGender());
            st.setString("dateBirth", r.getDateBirth());
            st.setString("email", r.getEmail());
            st.setString("username", r.getUsername());
            st.setString("pass", r.getPassword());
            st.setString("telephone", r.getTelephone());
            st.setString("address1", r.getAddress1());
            st.setString("address2", r.getAddress2());
            st.setString("codePostal", r.getCodePostal());
            st.setString("photo", r.getPhoto());
            st.setString("dateInscription", r.getDateInscription());
            st.setString("compteActif", r.getCompteActif());
            st.setString("newsletter", r.getNewsletter());
            st.setString("type", r.getType());
            st.setString("clearanceLevel", r.getClearanceLevel());
            st.setInt("idCompany", r.getIdcompany());
            st.setString("compteActif", r.getCompteActif());
            st.setString("latitude", r.getLatitude());
            st.setString("longitude", r.getLongitude());
            st.setString("secretQuestion", r.getSecretQuestion());
            st.setString("secretAnswer", r.getSecretAnswer());
            
            st.executeUpdate();
            
            System.out.println("Insertion réussie");
        } catch (SQLException ex) {
            System.err.println("Erreur d'ajout");
            ex.printStackTrace();
        }
    }

    @Override
    public void SupprimerRepresentant(Representant r) {
        String query = "delete from users where (username=:a and pass=:b)";
        try {
            NamedParameterStatement st = new NamedParameterStatement(cnx.getConnection(), query);
            st.setString("a", r.getCurrentUsername());
            st.setString("b", r.getCurrentPassword());
            System.out.println(st.toString());
            st.executeUpdate();
            System.err.println("Suppression réussie");

          
        } catch (SQLException ex) {
            System.err.println("Erreur de suppression");
            
        }
    }

    @Override
    public Representant ChercherRepresentant(String input) {
        String query = "select * from users where (username like ?)  and compteActif=1";
        
        PreparedStatement st = null;
        try {
            st = cnx.getConnection().prepareStatement(query);
            st.setString(1, "%" + input+ "%");
            
            Representant r = null;
            ResultSet res = st.executeQuery();
            while (res.next()) {
            
                r = new Representant(res.getInt(19),res.getString(2), res.getString(3), res.getString(4), 
                        res.getString(5), res.getString(6),
                        res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getString(11),
                        res.getString(12), res.getString(13), res.getString(14), res.getString(15), res.getString(16),               
                        res.getString(20), res.getString(21), res.getString(22), res.getString(23));
                
            }
            System.err.println("Récupération réussie");
            return r;
        } 
        catch (SQLException ex) {
            System.err.println("Erreur de récupération");
    }
        return null;
    }

    @Override
    public void ModifierRepresentant(Representant r) {
         String column = "name=:name, surname=:surname, gender=:gender, dateBirth=:dateBirth, email=:email, username=:username, pass=:pass, telephone=:telephone, address1=:address1, address2=:address2, codePostal=:codePostal, photo=:photo, dateInscription=:dateInscription, compteActif=:compteActif, newsletter=:newsletter, type=:type, clearanceLevel=:clearanceLevel, idCompany=:idCompany, latitude=:latitude, longitude=:longitude,secretQuestion=:secretQuestion, secretAnswer=:secretAnswer";;
        
        String query = "update users set " + column + "where id=:id";

        try {
            NamedParameterStatement st = new NamedParameterStatement(cnx.getConnection(), query);

            st.setString("name", r.getName());
            st.setString("surname", r.getSurname());
            st.setString("gender", r.getGender());
            st.setString("dateBirth", r.getDateBirth());
            st.setString("email", r.getEmail());
            st.setString("username", r.getUsername());
            st.setString("pass", r.getPassword());
            st.setString("telephone", r.getTelephone());
            st.setString("address1", r.getAddress1());
            st.setString("address2", r.getAddress2());
            st.setString("codePostal", r.getCodePostal());
            st.setString("photo", r.getPhoto());
            st.setString("dateInscription", r.getDateInscription());
            st.setString("compteActif", r.getCompteActif());
            st.setString("newsletter", r.getNewsletter());
            st.setString("type", r.getType());
            st.setString("clearanceLevel", r.getClearanceLevel());
            st.setInt("idCompany", r.getIdcompany());
            st.setString("compteActif", r.getCompteActif());
            st.setString("latitude", r.getLatitude());
            st.setString("longitude", r.getLongitude());
            st.setString("secretQuestion", r.getSecretQuestion());
            st.setString("secretAnswer", r.getSecretAnswer());
            
            st.setString("id", Representant.getCurrentId());
            System.out.println(st.getStatement().toString());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erreur de mise à jour");
        }
    }

    @Override
    public void AfficherRepresentantSociete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
