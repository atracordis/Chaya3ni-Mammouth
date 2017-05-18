/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.RideAnimals;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Tools.Conn;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Deathscythvi
 */
public class ServiceRideAnimals implements Interfaces.InterfaceRideAnimals {

    Conn cnx;

    public ServiceRideAnimals() {

        this.cnx = Conn.getInstance();
    }

    @Override
    public void ajouterRideAnimals(RideAnimals da) {
        try {
            String req = "INSERT INTO rideanimal (nameAnimal,speciesAnimal,luggageMass,animalMass,specialNeeds, photo) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);

            ps.setString(1, da.getNameAnimal());
            ps.setString(2, da.getSpeciesAnimal());
            ps.setFloat(3, da.getLuggageMass());
            ps.setFloat(4, da.getAnimalMass());
            ps.setString(5, da.getSpecialNeeds());
            ps.setString(6, da.getPhoto());
            ps.executeUpdate();
            System.out.println("ride animals ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ServiceRideAnimals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // SECOND BY ID AS FK
    public void ajouterRideAnimalss(int id, RideAnimals da) {
        try {
            String req = "INSERT INTO rideanimal (id,nameAnimal,speciesAnimal,luggageMass,animalMass,specialNeeds, photo) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);

            ps.setInt(1, id);
            ps.setString(2, da.getNameAnimal());
            ps.setString(3, da.getSpeciesAnimal());
            ps.setFloat(4, da.getLuggageMass());
            ps.setFloat(5, da.getAnimalMass());
            ps.setString(6, da.getSpecialNeeds());
            ps.setString(7, da.getPhoto());
            ps.executeUpdate();
            System.out.println("ride animals ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ServiceRideAnimals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ModifierRideAnimals(RideAnimals da, int id) {
        try {
            String req = "UPDATE rideanimal SET  nameAnimal=?, speciesAnimal=?, luggageMass=? ,animalMass=? ,specialNeeds=? WHERE id = ?";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);

            ps.setString(1, da.getNameAnimal());
            ps.setString(2, da.getSpeciesAnimal());
            ps.setFloat(3, da.getLuggageMass());
            ps.setFloat(4, da.getAnimalMass());
            ps.setString(5, da.getSpecialNeeds());
            ps.setInt(6, id);

            ps.executeUpdate();
            System.out.println("ride Animals modifié");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ServiceRideAnimals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void DeleteRideAnimals(RideAnimals da) {
        try {
            String req = "delete from rideanimal where id=?";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);

            ps.setInt(1, da.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ServiceRideAnimals.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void AffichertousRideAnimals() {
        try {
            String req = "select * from rideanimal";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String nameAnimal = rs.getString("nameAnimal");
                String speciesAnimal = rs.getString("speciesAnimal");
                int haveLuggage = rs.getInt("haveLuggage");
                float luggageMass = rs.getFloat("luggageMass");
                float animalMass = rs.getFloat("animalMass");
                String specialNeeds = rs.getString("specialNeeds");

                System.out.println("Animal Name : " + nameAnimal);
                System.out.println("Animal Specie : " + speciesAnimal);
                System.out.println("Have luggage : " + haveLuggage);
                System.out.println("Luggage Mass : " + luggageMass);
                System.out.println("Animal Mass : " + animalMass);
                System.out.println("Special Needs : " + specialNeeds);

                System.out.println();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ServiceRideAnimals.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet afficher() throws SQLException {
        String req = "Select * from rideanimal";
        Statement st = cnx.getConnection().createStatement();
        return st.executeQuery(req);

    }
    public ResultSet afficherAnimal() throws SQLException {
        String req = "Select * from rideanimal WHERE id";
        Statement st = cnx.getConnection().createStatement();
        return st.executeQuery(req);

    }

    public ResultSet affichersearch(String nameTosearch) throws SQLException {
        String req = "SELECT * from `rideanimal` WHERE nameAnimal LIKE ?";
        PreparedStatement ps = cnx.getConnection().prepareStatement(req);
        ps.setString(1, "%" + nameTosearch + "%");

        return ps.executeQuery();

    }
    public ResultSet chartSpecies () throws SQLException {
        String req = " SELECT COUNT(id),speciesAnimal FROM rideanimal GROUP BY speciesAnimal"; 
        Statement st = cnx.getConnection().createStatement() ;
        return st.executeQuery(req) ;
    }
    
    // ajout dans RideAnimals comme clé primaire étrangére
    public ResultSet lastIdRide () throws SQLException {
        String requete = "SELECT id FROM ridepassenger ORDER BY id DESC LIMIT 1" ;
        Statement st = cnx.getConnection().createStatement() ;
        return st.executeQuery(requete) ;
        
    }
     public void ajouterphotoAnimals(RideAnimals da, int id) {
        try {
            String req = "INSERT INTO rideanimal (photo) VALUES(?) WHERE id = ?";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);

            ps.setString(1, da.getNameAnimal());
            ps.setInt(2, id);         

            ps.executeUpdate();
            System.out.println("photo animal ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ServiceRideAnimals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  public void DeleteAnimals(int id) {
        try {
            String req = "delete from rideanimal where id=?";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ServiceRideAnimals.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
