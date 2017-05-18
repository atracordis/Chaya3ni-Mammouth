/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.User;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Admin
 */
public interface InterfaceUser {

    public void insertUser(User u);

    public boolean existpassword(String id);

    public void updateUser(User u);

    public ResultSet selectUserResultSet();

    public void deleteUser(User u);

    public User getUserFromUserName(String username);

    public List<User> selectUser();

    public List<String> getEmails();

    public boolean existUser(String id, String row);

    public ResultSet selectCountPerMonth();

    public ResultSet selectAnimalYear();

    public ResultSet selectCountPerYear();

    public ResultSet selectSmokers();

    public List<User> searchUser(String input);

    public User getUserLogin(String username, String password);

    public int getUserLoginAlternate(String username, String password);

    public void deactivateUser(User u);

}
