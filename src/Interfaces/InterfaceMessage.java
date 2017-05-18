/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Message;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public interface InterfaceMessage {

    public void insertMessage(Message m);

    public void purgeConversation(String id, String id2);

    public ResultSet selectMessage(String id, String id2, int limit);

    public ResultSet searchMessage(String id, String id2, int limit, String input);
}
