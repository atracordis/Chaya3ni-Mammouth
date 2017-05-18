/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Employe;
import Entities.Filiale;
import Entities.Societe;
import Tools.Conn;
import Tools.NamedParameterStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class ServSociete implements Interfaces.ISociete 
{
    Conn cnx;

    public ServSociete() 
    {

        this.cnx = Conn.getInstance();
    }

    @Override
    public void AjouterSociete(Societe s) 
    {
        int idc=0;
        
        try {
            String req = "INSERT INTO company (name,telephone,email,logo,address1,address2,codePostal,idrepresentant) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);

            ps.setString(1, s.getName());
            ps.setString(2, s.getTelephone());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getLogo());
            ps.setString(5, s.getAdresse1());
            ps.setString(6, s.getAdresse2());
            ps.setDouble(7, s.getCodepostal());
            ps.setInt (8,s.getIdrepresentant());
            ps.executeUpdate();
            
            /*if (s.getListeFilliale()!=null)
            {
                String req2 = "select * from company WHERE name=?";
                PreparedStatement ps2 = cnx.getConnection().prepareStatement(req2);
                ps2.setString(1, s.getName());
                ResultSet result = ps2.executeQuery();
                
                while (result.next())
                {
                    idc =result.getInt("id");
                }
                
                for (Filiale f : s.getListeFilliale())
                {
                    f.setIdcompany(idc);
                    String req1 = "INSERT INTO filiale (idCompany,address1,address2,codePostal,latitude,longitude) VALUES(?,?,?,?,?,?)";
                    PreparedStatement ps1 = cnx.getConnection().prepareStatement(req1);

                    ps1.setInt(1, f.getIdcompany());
                    ps1.setString(2, f.getAdresse1());
                    ps1.setString(3, f.getAdresse2());
                    ps1.setDouble(4, f.getCodepostal());
                    ps1.setDouble(5, f.getLatitude());
                    ps1.setDouble(6, f.getLongitude());
                    ps1.setDouble(6, f.getCodepostal());
                    ps1.executeUpdate();
                    System.out.println("Filliale ajouté!");
                    for (Employe e : f.getListeEmploye())
                    {
                        String column = "(name, surname, gender, dateBirth, email, username, pass, telephone, address1, address2, codePostal, photo, dateInscription, compteActif, newsletter, type, clearanceLevel, idCompany, latitude, longitude,secretQuestion,secretAnswer,idFiliale)";
        String values = " values (:name, :surname, :gender, :dateBirth, :email, :username, :pass, :telephone, :address1, :address2, :codePostal, :photo, :dateInscription, :compteActif, :newsletter, :type, :clearanceLevel, :idCompany, :latitude, :longitude,:secretQuestion,:secretAnswer,:idFiliale)";
        String req3 = "INSERT INTO users " + column + values;
        try {
           NamedParameterStatement st = new NamedParameterStatement(cnx.getConnection(), req3);
            System.out.println(e.toString());
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
           
            st.setString("latitude", e.getLatitude());
            st.setString("longitude", e.getLongitude());
            st.setString("secretQuestion", e.getSecretQuestion());
            st.setString("secretAnswer", e.getSecretAnswer());
            st.setInt("idFiliale", e.getIdFiliale());
            st.executeUpdate();
            
            System.out.println("Insertion réussie");
        } catch (SQLException ex) {
            System.err.println(ex);
        }
                    }
                }
            }*/
            System.out.println("Societé ajouté!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ServSociete.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
        }
    }
    @Override
    public Societe ChercherSociete(String nom)
    {
        try 
        {
            Societe s= new Societe();
            String req2 = "select * from company WHERE name=?";
            PreparedStatement ps2 = cnx.getConnection().prepareStatement(req2);
            ps2.setString(1, nom);
            ResultSet result;
            result = null;
            try
            {
                result = ps2.executeQuery();
            }
            catch (SQLException ex)
            {
                Logger.getLogger(ServSociete.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            while (result.next())
            {
                s.setIds(result.getInt("id"));
                s.setName(result.getString("name"));
                s.setTelephone(result.getString("telephone"));
                s.setEmail(result.getString("telephone"));
                s.setLogo(result.getString("logo"));
                s.setAdresse1(result.getString("address1"));
                s.setAdresse2(result.getString("address2"));
                s.setCodepostal(result.getDouble("codePostal"));
                s.setIdrepresentant (result.getInt("idrepresentant"));
            }
            return s;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServSociete.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void SupprimerSociete(Societe s) 
    {
        try {
       String req="delete from company where id=?";
       
       PreparedStatement ps = cnx.getConnection().prepareStatement(req);

            ps.setInt(1, s.getIds());
            ps.executeUpdate();
           
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ServSociete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @Override
    public void ModifierSociete(Societe s) 
    {
      try {
                String req = "UPDATE company set name=?,telephone=?,email=?,logo=?,address1=?,address2=?,codePostal=?,idrepresentant=? where id=?";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);

            ps.setString(1, s.getName());
            ps.setString(2, s.getTelephone());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getLogo());
            ps.setString(5, s.getAdresse1());
            ps.setString(6, s.getAdresse2());
            ps.setDouble(7, s.getCodepostal());
             ps.setInt   (8, s.getIdrepresentant());
            ps.setInt   (9, s.getIds());
            ps.executeUpdate();   
      }
      catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ServSociete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void AfficherSociete() 
    {
        try {
            String req = "select * from company";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
                int id= rs.getInt ("id");
                String nom=rs.getString("name");
                String telephone=rs.getString("telephone");
                String email=rs.getString ("email");
                String logo=rs.getString ("logo");
                String adress1=rs.getString ("address1");
                String adress2=rs.getString ("address2");
                Double codepostal=rs.getDouble("codePostal");
                int idr=rs.getInt("idrepresentant");
                System.out.println(nom);
                System.out.println(telephone);
                System.out.println(email);
                System.out.println(logo);
                System.out.println(adress1);
                System.out.println(adress2);
                System.out.println(codepostal);
                System.out.println(idr);
            }
            }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ServSociete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
public Societe ChercherSocieteParid( int id)
    {
        try 
        {
            Societe s= new Societe();
            String req2 = "select * from company WHERE idrepresentant=?";
            PreparedStatement ps2 = cnx.getConnection().prepareStatement(req2);
            ps2.setInt(1, id);
            ResultSet result;
            result = null;
            try
            {
                result = ps2.executeQuery();
            }
            catch (SQLException ex)
            {
                Logger.getLogger(ServSociete.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            while (result.next())
            {
                s.setIds(result.getInt("id"));
                s.setName(result.getString("name"));
                s.setTelephone(result.getString("telephone"));
                s.setEmail(result.getString("email"));
                s.setLogo(result.getString("logo"));
                s.setAdresse1(result.getString("address1"));
                s.setAdresse2(result.getString("address2"));
                s.setCodepostal(result.getDouble("codePostal"));
                s.setIdrepresentant (result.getInt("idrepresentant"));
                System.out.println(s.toString());
            }
            return s;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServSociete.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
