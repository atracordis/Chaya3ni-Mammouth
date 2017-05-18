/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.FullRideAnimal;
import Entities.GoalToReach;
import Entities.RideAnimals;
import Tools.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elloumiaymen
 */
public class ServiceGoalToReach {

    Conn cnx;

    public ServiceGoalToReach() {
        this.cnx = Conn.getInstance();
    }

    public ResultSet lastId() throws SQLException {
        String requete = "SELECT id FROM rideanimal ORDER BY id DESC LIMIT 1";
        Statement st = cnx.getConnection().createStatement();
        return st.executeQuery(requete);

    }

    public void ajouterGoalToReach(GoalToReach da, int idAnimal) {
        try {
            String req = "INSERT INTO `goaltoreach`(`idAnimal`, `description`, `taille`, `notes`, `name`, `surname`)  VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);

            ps.setInt(1, idAnimal);
            ps.setString(2, da.getDescription());
            ps.setFloat(3, da.getTaille());
            ps.setString(4, da.getNumerodecontact());
            ps.setString(5, da.getPrenom());
            ps.setString(6, da.getNom());
            ps.executeUpdate();
            System.out.println("Goal to reach ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ServiceRideAnimals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet afficherAnimalRideinfos() throws SQLException {
        String req = "SELECT * FROM ridepassenger"
                + " INNER JOIN rideanimal ON ridepassenger.id=rideanimal.id "
                + "INNER JOIN goaltoreach ON goaltoreach.idAnimal=rideanimal.id ORDER BY rideanimal.nameAnimal";
        Statement st = cnx.getConnection().createStatement();
        return st.executeQuery(req);

    }

    public List<FullRideAnimal> selectAll() {
        String query = "SELECT a.nameAnimal,a.speciesAnimal,a.specialNeeds, p.placeSource, p.placeDestination, g.surname,g.name, g.notes, a.id, p.id, g.id FROM ridepassenger p INNER JOIN rideanimal a ON p.id=a.id INNER JOIN goaltoreach g ON g.idAnimal=a.id";
        List<FullRideAnimal> rides = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = cnx.getConnection().prepareStatement(query);

            ResultSet res = st.executeQuery();
            while (res.next()) {
                FullRideAnimal u = new FullRideAnimal(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5),
                        res.getString(6), res.getString(7), res.getString(8), res.getInt(9), res.getInt(10), res.getInt(11));
                rides.add(u);
                System.out.println(u);

            }
            System.err.println("Récupération réussie");
            return rides;
        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return null;
    }

    public List<FullRideAnimal> selectAllsearch(String nameTosearch) throws SQLException {
        String query = "SELECT a.nameAnimal,a.speciesAnimal,a.specialNeeds, p.placeSource, p.placeDestination, g.surname,g.name, g.notes, a.id, p.id, g.id FROM ridepassenger p INNER JOIN rideanimal a ON p.id=a.id INNER JOIN goaltoreach g ON g.idAnimal=a.id WHERE nameAnimal LIKE ? ";

        List<FullRideAnimal> rides = new ArrayList<>();
        PreparedStatement st = null;

        try {
            st = cnx.getConnection().prepareStatement(query);
            st.setString(1, "%" + nameTosearch + "%");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                FullRideAnimal u = new FullRideAnimal(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5),
                        res.getString(6), res.getString(7), res.getString(8), res.getInt(9), res.getInt(10), res.getInt(11));
                rides.add(u);
                System.out.println(u);

            }
            System.err.println("Récupération réussie");
            return rides;
        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return null;
    }

    public void modifieratPassager(FullRideAnimal p, int id) throws SQLException {
        String requete = "UPDATE ridepassenger SET placeSource= ?,  placeDestination= ? WHERE id = ?";
        PreparedStatement pst = cnx.getConnection().prepareStatement(requete);
        pst.setString(1, p.getPlaceSource());
        pst.setString(2, p.getPlaceDestination());
        pst.setInt(3, id);
        System.out.println("req1" + requete);
        pst.executeUpdate();
    }

    public void modifieratGTR(FullRideAnimal p, int id) {
        try {
            String requete = "UPDATE goaltoreach SET surname= ?,  name= ?, notes= ? WHERE id = ?";
            PreparedStatement pst = cnx.getConnection().prepareStatement(requete);

            pst.setString(1, p.getSuernamegtr());
            pst.setString(2, p.getNamestr());
            pst.setString(3, p.getNumberstr());
            pst.setInt(4, id);

            pst.executeUpdate();
        System.out.println("req2" + requete);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ServiceRideAnimals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifieratAnimal(FullRideAnimal p, int id) throws SQLException {
        String requete = "UPDATE rideanimal SET nameAnimal= ?,  speciesAnimal= ?, specialNeeds= ? WHERE id = ?";
        PreparedStatement pst = cnx.getConnection().prepareStatement(requete);
        pst.setString(1, p.getNameAnimal());
        pst.setString(2, p.getSpeciesAnimal());
        pst.setString(3, p.getSpecialNeedsAnimal());
        pst.setInt(4, id);
        System.out.println("rq3" + requete);
        pst.executeUpdate();
    }

}
