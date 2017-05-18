/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Reviews;
import Entities.User;
import static GUI.Controllers.frontOffice.UpdateReviewsController.y;
import Tools.Conn;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author SALMA
 */
public class ServiceReviews implements Interfaces.InterfaceReviews {

    Conn cnx;
    

    public ServiceReviews() {
        cnx = Conn.getInstance();
    }

    @Override
    public void AjouterReviews(Reviews R) {

        try {
            String request
                    = "INSERT INTO `reviews`(`rating`, `idBooking`, `idUser`,`idUser2`, `title` , `content`, `dateTime`) "
                    + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.getConnection().prepareStatement(request);
            ps.setInt(1, R.getRating());
            ps.setInt(2, R.getIdBooking());
            ps.setInt(3, R.getIdUser());
            ps.setInt(4, R.getIdUser2());
            ps.setString(5, R.getTitle());
             ps.setString(6, R.getContent());
            ps.setDate(7, new java.sql.Date((R.getDateTime()).getTime()));

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void ModifierReviews(Reviews R) {
        try {
            String req = "UPDATE `reviews` SET `rating`=? ,`title`=? ,`content`=? WHERE id=?";

            PreparedStatement ps = cnx.getConnection().prepareStatement(req);

            ps.setInt(1, R.getRating());

            ps.setString(2, R.getTitle());
            ps.setString(3, R.getContent());

            ps.setInt(4, R.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReviews.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void DeleteReviews(Reviews R) {

        try {
            String req = "delete from reviews where id=?";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);

            ps.setInt(1, R.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceReviews.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Reviews> AfficherReviews() {
        String req = "select * from `reviews`";
        PreparedStatement ps;
        List<Reviews> rec = new ArrayList<>();
        try {
            ps = cnx.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reviews r = new Reviews(rs.getInt("id"), rs.getInt("rating"), rs.getInt("idUser"), rs.getInt("idBooking"), rs.getString("title"), rs.getString("content"), rs.getDate("dateTime"));
                rec.add(r);

            }

        } catch (SQLException ex) {
            System.out.println("g");
        }
        return rec;
    }

    public String findbyName(int id) {

        String req = "SELECT distinct(name) FROM users U Join reviews D ON U.id = D.idUser2 Group by  ; ";
        String name = "";

        PreparedStatement ps;
        try {
            ps = cnx.getConnection().prepareStatement(req);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                name = rs.getString("name");
                System.out.println(name);
            }

        } catch (SQLException ex) {
            System.out.println("prob with find by name ");
        }
        return name;

    }

    public Reviews RecherchebyID(int i) {
        Reviews r = new Reviews();
        String req = "select * from `reviews` where id=?";
        PreparedStatement ps;
        try {
            ps = cnx.getConnection().prepareStatement(req);
            ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                r = new Reviews(rs.getInt("id"), rs.getInt("rating"), rs.getInt("idUser"), rs.getInt("idBooking"), rs.getString("title"), rs.getString("content"), rs.getDate("dateTime"));

            }

        } catch (SQLException ex) {
            System.out.println("lllllll");
        }
        return r;

    }

    public List<String> moyenne() {

    

        String req = "SELECT AVG(rating) as moyenne , name as nom from reviews R , users U WHERE R.idUser2=U.id ";
        PreparedStatement ps;
       List<String> lst= new ArrayList<>();

        try {
            ps = cnx.getConnection().prepareStatement(req);
            // ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
              //  System.out.println(rs.getString("moyenne"));
              
            rs.getString(1);
            rs.getString(2);
        lst.add(  rs.getString(1));
         lst.add(  rs.getString(2));
        
            }

        } catch (SQLException ex) {
            System.out.println("moyenne is incorrect ");
        }
       return lst; 
    }

   
    public List<Reviews> Display() {

        String req = "select name as nom , rating , title , content , dateTime from users U , reviews R where U.id=R.idUser2 group by idUser2 ; ";
        
        PreparedStatement ps;
        List<Reviews> re = new ArrayList<>();
        try {
            ps = cnx.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
            Reviews r = new Reviews(rs.getString("nom"),rs.getInt("rating"),rs.getString("title"),rs.getString("content"),rs.getDate("dateTime"));
                re.add(r);
                System.out.println(rs.getString("nom"));

            }
            

        } catch (SQLException ex) {
            System.out.println("Display wrong ");
        }
       return re;
    }
    public  ObservableList<User> DisplayCommun() throws SQLException{
      //  ServiceReviews rs = New ServiceReviews();
          List<String> lst= new ArrayList<>();
        String req = "SELECT distinct(name) as nom,users.id as id from bookings join users on ( bookings.idRidePassenger=users.id) or ( bookings.idRideDriver=users.id) where "
                + "(idRideDriver = 2 or idRidePassenger = 2)";
      
      
        PreparedStatement ps;
         ObservableList<User> re = FXCollections.observableArrayList();
           try {
            ps = cnx.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()) {
                User u = new User(rs.getString("id"), rs.getString("nom"));
               
            re.add(  u);
             
             
            }

            
        } catch (SQLException ex) {
            System.out.println("Display wrong ");
        }
            return re ; 
}
}



