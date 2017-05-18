/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Preferences;
import Entities.User;
import java.sql.ResultSet;
import java.util.*;

/**
 *
 * @author Admin
 */
public interface InterfacePreferences {

    public void insertPreferences(Preferences p, String id);

    public void updatePreferences(Preferences p, String id);

    public void deletePreferences(String id);
    
    public User selectPreferencesId(String id) ;


    public Map<User, Preferences> selectPreferences();

    public User selectPreferences(String username, String pass);

    //     public Map<User,Preferences> searchPreferences(String input);
}
