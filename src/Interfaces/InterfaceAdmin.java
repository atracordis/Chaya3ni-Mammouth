/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Admin;
import java.util.*;

/**
 *
 * @author Admin
 */
public interface InterfaceAdmin extends InterfaceUser{

    public void insertAdmin(Admin u);

    public void updateAdmin(Admin u);

    public void deleteAdmin(Admin u);

    public List<Admin> selectAdmin();

    public boolean existAdmin(String id, String row);

    public List<Admin> searchAdmin(String input);

    public Admin getAdminLogin(String username, String password);

    public Admin getAdminLoginAlternate(String username, String password);
    
    
}
