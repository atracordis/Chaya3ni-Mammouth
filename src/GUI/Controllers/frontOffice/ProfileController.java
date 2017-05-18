/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.frontOffice;

import Entities.User;
import java.util.*;
import java.net.URL;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import GUI.Interfaces.Main;
import GUI.Interfaces.Main;
/**
 *
 * @author Admin
 */
public class ProfileController extends AnchorPane {
    
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML
    private TextField user;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextArea address;
    @FXML
    private CheckBox subscribed;
    @FXML
    private Hyperlink logout;
    @FXML 
    private Button update;
    
    @FXML 
    private Label success;
    
    private Main application;
    
    public void setApp(Main application){
        this.application = application;
     /*
        User loggedUser = application.getLoggedUser();
        user.setText(User.getCurrentName());
        email.setText(User.getCurrentEmail());
        phone.setText(User.getCurrentSurname());
        if (User.getCurrentSecretAnswer()!= null) {
            address.setText(User.getCurrentSecretAnswer());
        }
        */
        subscribed.setSelected(true);
        success.setOpacity(0);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    }
    
    public void processLogout(ActionEvent event) {
        if (application == null){
            return;
        }
        
        application.userLogout();
    }
    
    public void processUpdate(ActionEvent event) {
        if (application == null){
            animateMessage();
            return;
        }
        User loggedUser = application.getLoggedUser();
        loggedUser.setEmail(email.getText());
        loggedUser.setTelephone(phone.getText());
        loggedUser.setNewsletter(Boolean.toString(subscribed.isSelected()));
        loggedUser.setAddress1(address.getText());
        animateMessage();
    }

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(3000), success);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }
    
}
