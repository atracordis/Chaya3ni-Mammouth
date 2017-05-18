/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Filiale;
import Entities.Societe;
import Tools.Conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author asus
 */
public class ServFiliale implements Interfaces.IFiliale
{
    Conn cnx;

    public ServFiliale() 
    {

        this.cnx = Conn.getInstance();
    }   

    @Override
    public void AjouterFiliale(Filiale f) 
    {
        try {
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
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ServFiliale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      @Override
    public Filiale ChercherFiliale(double cdp) 
    {
         try 
        {
            Filiale f= new Filiale();
            String req2 = "select * from filiale WHERE codePostal=?";
            PreparedStatement ps2 = cnx.getConnection().prepareStatement(req2);
            ps2.setDouble(1, cdp);
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
                f.setIdf(result.getInt("id"));
                f.setIdcompany(result.getInt("idCompany"));
                f.setAdresse1(result.getString("address1"));
                f.setAdresse2(result.getString("address2"));
                f.setLatitude(result.getDouble("latitude"));
                f.setLongitude(result.getDouble("longitude"));
                f.setCodepostal(result.getDouble("codePostal"));
            }
            return f;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServFiliale.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @Override
    public Filiale ChercherFilialeParid(int id) 
    {
         try 
        {
            Filiale f= new Filiale();
            String req2 = "select * from filiale WHERE id=?";
            PreparedStatement ps2 = cnx.getConnection().prepareStatement(req2);
            ps2.setDouble(1, id);
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
                f.setIdf(result.getInt("id"));
                f.setIdcompany(result.getInt("idCompany"));
                f.setAdresse1(result.getString("address1"));
                f.setAdresse2(result.getString("address2"));
                f.setLatitude(result.getDouble("latitude"));
                f.setLongitude(result.getDouble("longitude"));
                f.setCodepostal(result.getDouble("codePostal"));
            }
            return f;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServFiliale.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public void SupprimerFiliale(Filiale f) 
    {
        try {
       String req="delete from filiale where id=?";
       
       PreparedStatement ps = cnx.getConnection().prepareStatement(req);

            ps.setInt(1, f.getIdf());
            ps.executeUpdate();
           
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ServFiliale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public void ModifierFiliale(Filiale f) 
    {
        try {
        String req1 = "UPDATE filiale SET idCompany=?,address1=?,address2=?,codePostal=?,latitude=?,longitude=? WHERE id=?";
                    PreparedStatement ps1 = cnx.getConnection().prepareStatement(req1);
                    ps1.setInt(1,f.getIdcompany());
                    ps1.setString(2, f.getAdresse1());
                    
                    ps1.setString(3, f.getAdresse2());
                    ps1.setDouble(4, f.getCodepostal());
                    ps1.setDouble(5, f.getLatitude());
                    ps1.setDouble(6, f.getLongitude());
                    
                    ps1.setInt (7,f.getIdf());
                            
                    ps1.executeUpdate();
                    System.out.println("Filliale ajouté!");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ServFiliale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Filiale> AfficherFilialeSociete(int idc) 
    {
        try {
            List<Filiale> liste = new ArrayList <>();
            String req = "select * from filiale where idCompany=?";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);
            ps.setInt (1,idc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
                int idf= rs.getInt ("id");
                
                String adresse1=rs.getString("address1");
                String adresse2=rs.getString("address2");
                Double codepostal=rs.getDouble ("codePostal");
                Double latitude=rs.getDouble ("latitude");
                Double longitude=rs.getDouble ("longitude");
                
                Filiale f= new Filiale (idc,adresse1,adresse2,codepostal,latitude,longitude);
                f.setIdf(idf);
                liste.add(f);
            }
            return liste;
            }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ServSociete.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
