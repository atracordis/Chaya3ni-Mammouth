/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Complaints;
import Entities.User;
import Tools.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SALMA
 */
public class ServiceComplaints  implements Interfaces.InterfaceComplaints{

    
     Conn cnx ; 
     //User user = new User();
        //String us=user.getCurrentId();
    
    public ServiceComplaints(){
        cnx=Conn.getInstance();
        
    }
    @Override
    public void AjouterComplaints(Complaints C ) {
          try {
            String request = 
                    " INSERT INTO `complaints`(`iduser`, `content`, `type`, `dateTime` , `attachment` , `status`) VALUES  "
                    + " (?,?,?,?,?,?)";
            PreparedStatement ps = cnx.getConnection().prepareStatement(request);
                 ps.setString(1,"2");
                      ps.setString(2,C.getContent());
                           ps.setString(3,C.getType());
     
       
  ps.setDate(4, new java.sql.Date((C.getDateTime()).getTime()));
   
         ps.setString(5,C.getAttachement());
         ps.setString(6,"Unseen");
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
    }

    @Override
    public void ModifierComplaints(Complaints C   ) {
         try {
        String req = "UPDATE `complaints` SET  `Status`=? WHERE `id`=? ";
        
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);
            ps.setString(1, C.getStatus());
            
            ps.setInt(2, C.getId());
            
             
             ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReviews.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    

    @Override
    public void DeleteComplaints(Complaints C) {
        try {
        String req = "DELETE FROM `complaints` WHERE id=? ";
        
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);
          
            ps.setInt(1, C.getId());
             ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReviews.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    @Override
    public List AfficherComplaints() {
        String req = "select * from `complaints`";
        PreparedStatement ps;
        List<Complaints> com = new ArrayList<>();
        try {
            ps = cnx.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Complaints c = new Complaints(rs.getInt("id"),rs.getString("content"),rs.getString("type"),rs.getDate("dateTime"),rs.getString("attachment"), rs.getString("Status"));
                com.add(c);
                
            }

        } catch (SQLException ex) {
            System.out.println("affich comp");
        }
        return com;
    }
    }

   