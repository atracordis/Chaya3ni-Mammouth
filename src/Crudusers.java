/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Tools.*;
import Entities.*;
import Services.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author mdaghi
 */
public class Crudusers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
      
   /*     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();

        PreferencesService pref = new PreferencesService();
        Preferences Pref = new Preferences("803", "allow", "deny", "deny", "0", "0", "allow", "folk", "deny", "deny", "deny", "deny", "Tres confortable", "454 TDI", "Volvo");
        String name="Bejaoui", surname="Helmi", gender="homme", dateBirth="1000-12-31", email="helmi.bejaoui1@esprit.tn", username="helmi1", password="22441120", telephone="50441120", address1="", address2="", codePostal="2010", photo="", dateInscription=dtf.format(localDate), compteActif="1", newsletter="0", type="0", clearanceLevel="2", idCompany="0", latitude="0", longitude="0", secretQuestion="", secretAnswer="";       
        User u = new User( name,  surname,  gender,  dateBirth,  email,  username,  password,  telephone,  address1,  address2,  codePostal,  photo,  dateInscription,  newsletter,  idCompany,  latitude,  longitude,  secretQuestion,  secretAnswer);
        u.setPreferences(Pref);*/
        UserService userservice = new UserService();
  //      userservice.searchUser("w").forEach(System.out::println);
        System.out.println(userservice.existUser("atracordis", "username"));


        /*
   //   userservice.insertUser(u);
        pref.updatePreferences(Pref, "803");
        MessageService s = new MessageService();
        s.searchMessage("2", "803", 10, "lorem");

        
        
        */
        
        
      /*  UserService us = new UserService();
        System.out.println(us.exist("wajd.meskini@esprit.tn", "email"));
        
        
       ResultSet list=us.search("wa");
       list.next();
       System.out.println(list.getString(1));  
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        String name="Bejaoui", surname="Helmi", gender="homme", dateBirth="1000-12-31", email="helmi.bejaoui@esprit.tn", username="helmi", password="22441120", telephone="50441120", address1="", address2="", codePostal="2010", photo="", dateInscription=dtf.format(localDate), compteActif="1", newsletter="0", type="0", clearanceLevel="2", idCompany="0", latitude="0", longitude="0", secretQuestion="", secretAnswer="";       
        User u = new User( name,  surname,  gender,  dateBirth,  email,  username,  password,  telephone,  address1,  address2,  codePostal,  photo,  dateInscription,  newsletter,  idCompany,  latitude,  longitude,  secretQuestion,  secretAnswer);
        UserService userservice = new UserService();
        userservice.insertUser(u);
        
        /*
        users u = new users();
        u.insertUserType2();
        // TODO code application logic here
      /*   url="jdbc:mysql://localhost:3306/pidev";
        String login="root";
        String pwd="";
        try{
            
        Connection cnx = DriverManager.getConnection(url, login , pwd);
        System.out.println("connection Etablie");
     
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();

        
       String columns = "(name, surname, gender, dateBirth, email, username, password, telephone, address1, address2, codePostal, photo, dateInscription, compteActif, newsletter, type, clearanceLevel, idCompany, latitude, longitude)";
       String name="Meskini", surname="Wajd", gender="homme", dateBirth="1000-12-31", email="wajd.meskini@esprit.tn", username="atracordis", password="22441120", telephone="50441120", address1="", address2="", codePostal="2010", photo="", dateInscription=dtf.format(localDate), compteActif="1", newsletter="0", type="0", clearanceLevel="2", idCompany="0", latitude="0", longitude="0";
       String values =  "values ('"+name+"','"+surname+"','"+gender+"','"+dateBirth+"','"+email+"','"+username+"','"+password+"','"+telephone+"','"+address1+"','"+address2+"','"+codePostal+"','"+photo+"','"+dateInscription+"','"+compteActif+"','"+newsletter+"','"+type+"','"+clearanceLevel+"','"+idCompany+"','"+latitude+"','"+longitude+"') "; 
       String query = "INSERT INTO users "+columns+values;
       Statement st=cnx.createStatement();
       st.executeUpdate(query);
       ResultSet list=st.executeQuery("select * from users order by id desc");
       list.next();
       String idUser=list.getString(1), email2="deny", telephone2="deny", address12="deny", address22="deny", codePostal2="deny", latitude2="0", longitude2="0", music="allow", musicTaste="none", smoking="deny", allowSmoking="deny", animal="deny", haveAnimal="deny", confortVoiture="none", modeleVoiture="none", marqueVoiture="none";

       String columns2 = "(idUser, email, telephone, address1, address2, codePostal, latitude, longitude, music, musicTaste, smoking, allowSmoking, animal, haveAnimal, confortVoiture, modeleVoiture, marqueVoiture)";
       String values2 = "values ('"+idUser+"','"+email2+"','"+telephone2+"','"+address12+"','"+address22+"','"+codePostal2+"','"+latitude2+"','"+longitude2+"','"+music+"','"+musicTaste+"','"+smoking+"','"+allowSmoking+"','"+animal+"','"+haveAnimal+"','"+confortVoiture+"','"+modeleVoiture+"','"+marqueVoiture+"')";
       String query2 = "INSERT INTO preferences "+columns2+values2;
       st.executeUpdate(query2);   
       
        Statement st=cnx.createStatement();
        st.executeUpdate(requete);
        System.out.println("Personne ajoutée");*/
        ///execute update pour insert update delete
        /*String requete ="INSERT INTO personne (nom,prenom) values (?,?)";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1,"Mannoubi");
        pst.setString(2,"3antar");
        pst.executeUpdate();*/
        /*String requete ="DELETE FROM personne WHERE id = ?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1,2);
        pst.executeUpdate();*/
        /*String requete ="UPDATE personne set nom =? WHERE id = ?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1,"seif");
        pst.setInt(2,1);
        pst.executeUpdate();
        
        String requete ="SELECT * from person ";
        Statement st = cnx.createStatement();
        ResultSet res = st.executeQuery(requete);
        while (res.next())
        {
          System.out.println("--------------------------");
          System.out.println("Le nom est :"+res.getString(2));
          System.out.println("Le prenom est :"+res.getString(3));
          System.out.println("Le id est :"+res.getInt(1));
        }
        
        
        
        System.out.println(" Personne supprimé");
        }}
        catch (SQLException ex)
        {
            System.err.println("Erreur de connection");
            ex.printStackTrace();
        }*/
    
    }
    
} 
