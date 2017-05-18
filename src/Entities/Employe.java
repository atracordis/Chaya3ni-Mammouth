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
public class Employe extends User {
   private int idFiliale;
   private int idcompany;
    public Employe(){};
    public Employe(int idFiliale,int idcompany,String name, String surname, String gender, String dateBirth, String email, 
            String username, String pass, String telephone, String address1, String address2,
            String codePostal, String photo, String dateInscription, String newsletter, String idCompany, 
            String latitude, String longitude, String secretQuestion, String secretAnswer) {
        super(name, surname, gender, dateBirth, email, username, pass, telephone, address1, address2, codePostal, photo, dateInscription, newsletter, idCompany, latitude, longitude, secretQuestion, secretAnswer);
        this.idFiliale = idFiliale;
        this.idcompany = idcompany;
        this.type = "Employee";
        this.clearanceLevel="1";
    }

    public int getIdFiliale() {
        return idFiliale;
    }

    public void setIdFiliale(int idFiliale) {
        this.idFiliale = idFiliale;
    }
    public int getIdcompany() {
        return idcompany;
    }

    public void setIdcompany(int idcompany) {
        this.idcompany = idcompany;
    }

   @Override
    public String toString() {
        return "Employe{" + "id Filiale=" + idFiliale  + "id societe=" + idcompany+ super.toString()+ '}';
    }
}
