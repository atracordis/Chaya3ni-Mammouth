/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author asus
 */
public class Representant extends User {
   
   private int idcompany;
    public Representant(){};
    public Representant(int idcompany,String name, String surname, String gender, String dateBirth, String email, 
            String username, String pass, String telephone, String address1, String address2,
            String codePostal, String photo, String dateInscription, String newsletter, String idCompany, 
            String latitude, String longitude, String secretQuestion, String secretAnswer) {
        super(name, surname, gender, dateBirth, email, username, pass, telephone, address1, address2, codePostal, photo, dateInscription, newsletter, idCompany, latitude, longitude, secretQuestion, secretAnswer);
        
        this.idcompany = idcompany;
        this.type = "Representative";
        this.clearanceLevel="2";
    }

    
    public int getIdcompany() {
        return idcompany;
    }

    public void setIdcompany(int idcompany) {
        this.idcompany = idcompany;
    }

   @Override
    public String toString() {
        return "Representant{"  + "id societe=" + idcompany+ super.toString()+ '}';
    }
}
