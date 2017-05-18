/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class Conn {

    private String pwd, login, url;
    private Connection conn;
    private static Conn instance;

    private Conn() {
        pwd = "";
        login = "root";
        url = "jdbc:mysql://localhost:3306/pidev";
        try {
            conn = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connection établie");
        } catch (SQLException ex) {
            System.err.println("Erreur de connection");
            ex.printStackTrace();
        }
    }

    public static Conn getInstance() {
        if (instance == null) {
            instance = new Conn();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }

}
