/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Tools.NamedParameterStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class users {

   String name, surname, gender, dateBirth, email, username, password, telephone, address1, address2, codePostal, photo, dateInscription, compteActif, newsletter, type, clearanceLevel, idCompany, latitude, longitude;
   String values, columns;
           String url;
        String login;
        String pwd;
       Connection cnx;

    public users(String name, String surname, String gender, String dateBirth, String email, String username, String password, String telephone, String address1, String address2, String codePostal, String photo, String dateInscription, String newsletter, String idCompany, String latitude, String longitude) {
        pwd="";
        login="root";
        url="jdbc:mysql://localhost:3306/pidev";
        try{
        cnx = DriverManager.getConnection(url, login , pwd);}
        catch (SQLException ex)
        {
            System.err.println("Erreur de connection");
            ex.printStackTrace();
        }

             
        
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateBirth = dateBirth;
        this.email = email;
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.address1 = address1;
        this.address2 = address2;
        this.codePostal = codePostal;
        this.photo = photo;
        this.dateInscription = dateInscription;
        this.compteActif = "1";
        this.newsletter = newsletter;
        this.type = "1";
        this.clearanceLevel = "0";
        this.idCompany = idCompany;
        this.latitude = latitude;
        this.longitude = longitude;
        //this.values =  "values ('"+name+"','"+surname+"','"+gender+"','"+dateBirth+"','"+email+"','"+username+"','"+password+"','"+telephone+"','"+address1+"','"+address2+"','"+codePostal+"','"+photo+"','"+dateInscription+"','"+compteActif+"','"+newsletter+"','"+type+"','"+clearanceLevel+"','"+idCompany+"','"+latitude+"','"+longitude+"') "; 
        this.values =  "values (?,?,?,?,?,?,'"+password+"','"+telephone+"','"+address1+"','"+address2+"','"+codePostal+"','"+photo+"','"+dateInscription+"','"+compteActif+"','"+newsletter+"','"+type+"','"+clearanceLevel+"','"+idCompany+"','"+latitude+"','"+longitude+"') "; 

        this.columns = "(name, surname, gender, dateBirth, email, username, password, telephone, address1, address2, codePostal, photo, dateInscription, compteActif, newsletter, type, clearanceLevel, idCompany, latitude, longitude)";
    }

    users() {  
        pwd="";
        login="root";
        url="jdbc:mysql://localhost:3306/pidev";
        try{
        cnx = DriverManager.getConnection(url, login , pwd);}
        catch (SQLException ex)
        {
            System.err.println("Erreur de connection");
            ex.printStackTrace();
        }

    }
    
    public void insertUser()
    {
       String query = "INSERT INTO users "+columns+values;       
        try{
            Statement st=cnx.createStatement();
            st.executeUpdate(query);}
        catch (SQLException ex)
        {
            System.err.println("Erreur d'ajout");
            ex.printStackTrace();
        }
    }
    
    public void insertUserType2()
    {
       String query = "INSERT INTO compromisedpasswords (pass) values (:Doe)";       
        try{
            NamedParameterStatement st= new NamedParameterStatement(cnx, query);
            
            
            
            st.setString("Doe", "Doe");
            System.out.println(st.toString());
            st.executeUpdate();}
        catch (SQLException ex)
        {
            System.err.println("Erreur d'ajout");
            ex.printStackTrace();
        }
    }
   

}


