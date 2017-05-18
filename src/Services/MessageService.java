/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Message;
import Interfaces.InterfaceMessage;
import Tools.*;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class MessageService implements InterfaceMessage {

    Conn cnx;

    public MessageService() {
        this.cnx = Conn.getInstance();
    }

    @Override
    public void insertMessage(Message m) {
        String column = " (idSource, idDestination, content, dateTime) ";
        String values = " values (:idSource, :idDestination, :content, :dateTime)";
        String query = "INSERT INTO messages " + column + values;
        try {
            NamedParameterStatement st = new NamedParameterStatement(cnx.getConnection(), query);

            st.setString("idSource", m.getIdSource());
            st.setString("idDestination", m.getIdDestination());
            st.setString("content", m.getContent());
            st.setString("dateTime", m.getDateTime());

            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erreur d'ajout");
            ex.printStackTrace();
        }

    }

    @Override
    public void purgeConversation(String id, String id2) {

        String query = "delete from messages where idSource=:idSource and idDestination=:idDestination";
        try {
            NamedParameterStatement st = new NamedParameterStatement(cnx.getConnection(), query);
            st.setString(":idSource", id);
            st.setString(":idDestination", id2);
            st.executeUpdate();
            System.err.println("Suppression réussie");
        } catch (SQLException ex) {
            System.err.println("Erreur de suppression");
            ex.printStackTrace();
        }

    }

    @Override
    public ResultSet selectMessage(String id, String id2, int limit) {

        String query = "select tablename.*, user.name, parent.name from (select * from messages where (idSource=:idSource and idDestination=:idDestination) or (idDestination=:newSource and idSource=:newDestination) order by dateTime desc limit " + Integer.toString(limit) + ") as tablename inner join users as user on user.id=tablename.idSource inner join users as parent on parent.id=tablename.idDestination order by dateTime asc";
        try {
            NamedParameterStatement st = new NamedParameterStatement(cnx.getConnection(), query);
            st.setString(":idSource", id);
            st.setString(":idDestination", id2);
            st.setString(":newSource", id2);
            st.setString(":newDestination", id);
            ResultSet res = st.executeQuery();
            System.out.println("Récupération réussie");
            return res;
        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return null;

    }

    @Override
    public ResultSet searchMessage(String id, String id2, int limit, String input) {

        String query = "select tablename.*, user.name, user.surname, parent.name, parent.surname from (select * from messages where ((idSource=:idSource and idDestination=:idDestination) or (idDestination=:newSource and idSource=:newDestination)) and (content like '%'||:value||'%' ) order by dateTime desc limit " + Integer.toString(limit) + ") as tablename inner join users as user on user.id=tablename.idSource inner join users as parent on parent.id=tablename.idDestination order by dateTime asc";
        try {
            NamedParameterStatement st = new NamedParameterStatement(cnx.getConnection(), query);
            st.setString("idSource", id);
            st.setString("idDestination", id2);
            st.setString("newSource", id2);
            st.setString("newDestination", id);
            st.setString("value", input);
            System.out.println(query);
            ResultSet res = st.executeQuery();
            System.out.println("Récupération réussie");
            return res;
        } catch (SQLException ex) {
            System.err.println("Erreur de récupération");
            ex.printStackTrace();
        }
        return null;
    }

}
