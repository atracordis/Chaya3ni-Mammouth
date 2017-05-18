/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Admin
 */
public class Admin extends User {
    private String AdminLevel;
    public Admin(){};
    public Admin(String AdminLevel, String name, String surname, String gender, String dateBirth, String email, String username, String pass, String telephone, String address1, String address2, String codePostal, String photo, String dateInscription, String newsletter, String idCompany, String latitude, String longitude, String secretQuestion, String secretAnswer) {
        super(name, surname, gender, dateBirth, email, username, pass, telephone, address1, address2, codePostal, photo, dateInscription, newsletter, idCompany, latitude, longitude, secretQuestion, secretAnswer);
        this.AdminLevel = AdminLevel;
        this.type = "Admin";

    }

    public Admin(String AdminLevel, String id, String name, String surname, String gender, String dateBirth, String email, String username, String pass, String telephone, String address1, String address2, String codePostal, String photo, String dateInscription, String newsletter, String idCompany, String latitude, String longitude, String secretQuestion, String secretAnswer) {
        super(id, name, surname, gender, dateBirth, email, username, pass, telephone, address1, address2, codePostal, photo, dateInscription, newsletter, idCompany, latitude, longitude, secretQuestion, secretAnswer);
        this.AdminLevel = AdminLevel;
    }

    
    
    public String getAdminLevel() {
        return AdminLevel;
    }

    public void setAdminLevel(String AdminLevel) {
        this.AdminLevel = AdminLevel;
    }

    @Override
    public String toString() {
        return "Admin{" + "AdminLevel=" + AdminLevel +super.toString()+ '}';
    }
    
    
    
}
