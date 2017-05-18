/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.RideEmployee;
import Entities.User;
import static Entities.User.getCurrentId;
import Interfaces.InterfaceRideEmployee;
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
 * @author Deathscythvi
 */
public class ServiceRideEmployee implements InterfaceRideEmployee {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    Conn cnx;

    public ServiceRideEmployee() {

        this.cnx = Conn.getInstance();
    }

    @Override
    public void ajouterRideEmployee(RideEmployee Ed) {
        try {
            String req = "INSERT INTO ridedriver (price,citySource,placeSource,longSource,latSource,cityDestination,placeDestination,longDestination,latDestination,dateTimeSource,nbrPlaces,confortVoiture,marqueVoiture,modeleVoiture,handicap,animal,haveAnimal,haveLuggage,luggageMass,music,musicTaste,smoking,allowSmoking,idDriver, frequency, frequencyUnit, idFiliale) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);

            ps.setDouble(1, Ed.getPrice());
            ps.setString(2, Ed.getCitySource());
            ps.setString(3, Ed.getPlaceSource());
            ps.setDouble(4, Ed.getLongSource());
            ps.setDouble(5, Ed.getLatSource());
            ps.setString(6, Ed.getCityDestination());
            ps.setString(7, Ed.getPlaceDestination());
            ps.setDouble(8, Ed.getLongDestination());
            ps.setDouble(9, Ed.getLatDestination());
            ps.setDate(10, new java.sql.Date((Ed.getDateTimeSource()).getTime()));
            ps.setInt(11, Ed.getNbrPlaces());
            ps.setString(12, Ed.getConfortVoiture());
            ps.setString(13, Ed.getMarqueVoiture());
            ps.setString(14, Ed.getModeleVoiture());
            ps.setByte(15, Ed.getHandicap());
            ps.setByte(16, Ed.getAnimal());
            ps.setByte(17, Ed.getHaveanimal());
            ps.setByte(18, Ed.getHaveluggage());
            ps.setFloat(19, Ed.getLuggageMass());
            ps.setByte(20, Ed.getMusic());
            ps.setString(21, Ed.getMusicTaste());
            ps.setByte(22, Ed.getSmoking());
            ps.setByte(23, Ed.getAllowsmoking());
            ps.setInt(24, Integer.parseInt(User.getCurrentId()));
            ps.setInt(25, Ed.getFrequency());
            ps.setString(26, Ed.getFrequencyUnit());
            ps.setInt(27, Ed.getIdFiliale());
            ps.executeUpdate();
            System.out.println("ride ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ServiceRideEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ModifierRideEmployee(RideEmployee Ed) {
        try {
            String req = "UPDATE ridedriver SET price=? ,citySource=? ,placeSource=? ,longSource=? ,latSource=? ,cityDestination=? ,placeDestination=? ,longDestination=? ,latDestination=? ,dateTimeSource=?, nbrPlaces=? ,confortVoiture=? ,marqueVoiture=?, modeleVoiture=?,handicap=? ,animal=?, haveAnimal=?, haveLuggage=?, luggageMass=? ,music=? ,musicTaste=?, smoking=? ,allowSmoking=?, idDriver=?,frequency=?,frequencyUnit=?,idFiliale=? WHERE id = ?";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);
            ps.setDouble(1, Ed.getPrice());
            ps.setString(2, Ed.getCitySource());
            ps.setString(3, Ed.getPlaceSource());
            ps.setDouble(4, Ed.getLongSource());
            ps.setDouble(5, Ed.getLatSource());
            ps.setString(6, Ed.getCityDestination());
            ps.setString(7, Ed.getPlaceDestination());
            ps.setDouble(8, Ed.getLongDestination());
            ps.setDouble(9, Ed.getLatDestination());
            ps.setDate(10, new java.sql.Date((Ed.getDateTimeSource()).getTime()));
            ps.setInt(11, Ed.getNbrPlaces());
            ps.setString(12, Ed.getConfortVoiture());
            ps.setString(13, Ed.getMarqueVoiture());
            ps.setString(14, Ed.getModeleVoiture());
            ps.setByte(15, Ed.getHandicap());
            ps.setByte(16, Ed.getAnimal());
            ps.setByte(17, Ed.getHaveanimal());
            ps.setByte(18, Ed.getHaveluggage());
            ps.setFloat(19, Ed.getLuggageMass());
            ps.setByte(20, Ed.getMusic());
            ps.setString(21, Ed.getMusicTaste());
            ps.setByte(22, Ed.getSmoking());
            ps.setByte(23, Ed.getAllowsmoking());
            ps.setInt(24, Integer.parseInt(User.getCurrentId()));
            ps.setInt(25, Ed.getFrequency());
            ps.setString(26, Ed.getFrequencyUnit());
            ps.setInt(27, Ed.getIdFiliale());
            ps.setInt(28, Ed.getId());

            ps.executeUpdate();
            System.out.println("ride modifié");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ServiceRideEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void DeleteRideEmployee(RideEmployee Ed) {
        try {
            String req = "delete from ridedriver where id=?";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);

            ps.setInt(1, Ed.getId());
            ps.executeUpdate();
            System.out.println("ride deleted");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ServiceRideEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void AffichertousRideEmployee() {
        try {
            String req = "select * from ridedriver";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                double price = rs.getDouble("price");
                String citySource = rs.getString("citySource");
                String placeSource = rs.getString("placeSource");
                Double longSource = rs.getDouble("longSource");
                Double latSource = rs.getDouble("latSource");
                String cityDestination = rs.getString("cityDestination");
                String placeDestination = rs.getString("placeDestination");
                Double longDestination = rs.getDouble("longDestination");
                Double latDestination = rs.getDouble("latDestination");

                String dateTimeSource = rs.getString("dateTimeSource");
                int nbrPlaces = rs.getInt("nbrPlaces");
                String confortVoiture = rs.getString("confortVoiture");
                String marqueVoiture = rs.getString("marqueVoiture");
                String modeleVoiture = rs.getString("modeleVoiture");
                byte handicap = rs.getByte("handicap");
                byte animal = rs.getByte("animal");
                byte haveanimal = rs.getByte("haveanimal");
                byte haveluggage = rs.getByte("haveluggage");
                float luggageMass = rs.getFloat("luggageMass");
                byte music = rs.getByte("music");
                String musicTaste = rs.getString("musicTaste");
                byte smoking = rs.getByte("smoking");
                byte allowsmoking = rs.getByte("allowsmoking");
                int idDriver = rs.getInt("idDriver");
                int frequency = rs.getInt("frequency");
                String frequencyUnit = rs.getString("frequencyUnit");
                int IdFiliale = rs.getInt("IdFiliale");
                System.out.println("ID : " + id);
                System.out.println("Name : " + price);
                System.out.println("citySource : " + citySource);
                System.out.println("placeSource : " + placeSource);
                System.out.println("longSource : " + longSource);
                System.out.println("latSource : " + latSource);
                System.out.println("cityDestination : " + cityDestination);
                System.out.println("placeDestination : " + placeDestination);
                System.out.println("longDestination : " + longDestination);
                System.out.println("latDestination : " + latDestination);
                System.out.println("dateTimeSource : " + dateTimeSource);
                System.out.println("nbrPlaces : " + nbrPlaces);
                System.out.println("confortVoiture : " + confortVoiture);
                System.out.println("marqueVoiture : " + marqueVoiture);
                System.out.println("modeleVoiture : " + modeleVoiture);
                System.out.println("handicap : " + handicap);
                System.out.println("animal : " + animal);
                System.out.println("haveanimal : " + haveanimal);
                System.out.println("haveluggage : " + haveluggage);
                System.out.println("luggageMass : " + luggageMass);
                System.out.println("music : " + music);
                System.out.println("musicTaste : " + musicTaste);
                System.out.println("smoking : " + smoking);
                System.out.println("allowsmoking : " + allowsmoking);
                System.out.println("idDriver : " + idDriver);
                System.out.println("frequency : " + frequency);
                System.out.println("frequencyUnit : " + frequencyUnit);
                System.out.println("IdFiliale : " + IdFiliale);
                System.out.println();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ServiceRideEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<RideEmployee> RechercherMesDrivernotall() {
        String req = "SELECT id,price,citySource,placeSource,cityDestination,placeDestination,dateTimeSource FROM `ridedriver` WHERE `idDriver`=? ";
        PreparedStatement ps;

        List<RideEmployee> rec = new ArrayList<>();
        try {
            ps = cnx.getConnection().prepareStatement(req);
            ps.setInt(1, Integer.parseInt(getCurrentId()));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RideEmployee r = new RideEmployee(rs.getInt("id"), rs.getDouble("price"), rs.getString("citySource"), rs.getString("placeSource"), rs.getString("cityDestination"), rs.getString("placeDestination"), rs.getDate("dateTimeSource"));
                rec.add(r);
            }

        } catch (SQLException ex) {
            System.out.println("oups");
        }
        return rec;
    }

    public RideEmployee rechercheRideEmployeeId(int i) {
        RideEmployee r = new RideEmployee();
        String req = "SELECT * FROM `ridedriver` WHERE `idDriver`=? AND `id`=? ";
        PreparedStatement ps;

        try {
            ps = cnx.getConnection().prepareStatement(req);
            ps.setInt(1, Integer.parseInt(getCurrentId()));
            ps.setInt(2, i);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                r = new RideEmployee(rs.getInt("id"), rs.getDouble("price"), rs.getString("citySource"), rs.getString("placeSource"), rs.getDouble("longSource"), rs.getDouble("latSource"), rs.getString("cityDestination"), rs.getString("placeDestination"), rs.getDouble("longDestination"), rs.getDouble("latDestination"), rs.getDate("dateTimeSource"), rs.getInt("nbrPlaces"), rs.getString("confortVoiture"), rs.getString("marqueVoiture"), rs.getString("modeleVoiture"), rs.getByte("handicap"), rs.getByte("animal"), rs.getByte("haveanimal"), rs.getByte("haveluggage"), rs.getFloat("luggageMass"), rs.getByte("music"), rs.getString("musicTaste"), rs.getByte("smoking"), rs.getByte("allowsmoking"), rs.getInt("idDriver"), rs.getInt("frequency"), rs.getString("frequencyUnit"), rs.getInt("IdFiliale"));

            }

        } catch (SQLException ex) {
            System.out.println("oups");
        }

        return r;
    }

}
