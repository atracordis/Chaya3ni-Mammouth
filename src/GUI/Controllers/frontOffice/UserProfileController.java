/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.frontOffice;

import Entities.Preferences;
import Entities.User;
import Services.UserService;
import Tools.NumberTextField;
import java.util.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import GUI.Interfaces.Main;
import GUI.Interfaces.Main;
import Services.PreferencesService;
import Tools.OtherTools;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.print.PrinterJob;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 *
 * @author Admin
 */
public class UserProfileController extends AnchorPane {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private Hyperlink deactivateAccount;
    @FXML
    private Label phone;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label name;
    @FXML
    private Label surname;
    @FXML
    private Label gender;
    @FXML
    private ImageView imgVw;
    @FXML
    private Label dateBirth;
    @FXML
    private Label email;
    @FXML
    private Label emailLabel;
    @FXML
    private Label address1;
    @FXML
    private Label address1Label;
    @FXML
    private Label address2;
    @FXML
    private Label address2Label;
    @FXML
    private Label postalCode;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label photo;
    @FXML
    private Hyperlink logout;
    @FXML
    private Button update;
    @FXML
    private Hyperlink updatePreferences;
    @FXML
    private Hyperlink searchUser;

    private Main application;

    public void setApp(Main application) {
        this.application = application;

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws MalformedURLException {

        phone.setVisible(false);
        phoneLabel.setVisible(false);
        email.setVisible(false);
        emailLabel.setVisible(false);

        postalCode.setVisible(false);
        postalCodeLabel.setVisible(false);
        address1.setVisible(false);
        address1Label.setVisible(false);
        address2.setVisible(false);
        address2Label.setVisible(false);

        PreferencesService prefser = new PreferencesService();
        User currentUser = prefser.selectPreferencesId(User.getSelectedId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.ENGLISH);

        name.setText(currentUser.getName());
        surname.setText(currentUser.getSurname());
        String sex = currentUser.getGender();
        if (sex.equals("homme")) {
            sex = "Male";
        } else {
            sex = "Female";
        }
        gender.setText(sex);
        dateBirth.setText(currentUser.getDateBirth());

        if (currentUser.getPreferences().getEmail().equals("allow")) {
            email.setText(currentUser.getEmail());
            email.setVisible(true);
            emailLabel.setVisible(true);
        }
        if (currentUser.getPreferences().getTelephone().equals("allow")) {
            phone.setText(currentUser.getTelephone());
            phone.setVisible(true);
            phoneLabel.setVisible(true);
        }
        if (currentUser.getPreferences().getAddress().equals("allow")) {
            address1.setText(currentUser.getAddress1());
            address2.setText(currentUser.getAddress2());
            postalCode.setText(currentUser.getCodePostal());
            address1.setVisible(true);
            address2.setVisible(true);
            postalCode.setVisible(true);
            address1Label.setVisible(true);
            address2Label.setVisible(true);
            postalCodeLabel.setVisible(true);
        }

        Image img = new Image("file:///C:/wamp/www/PIDEV/pics/" + currentUser.getPhoto());
        imgVw.setImage(img);

    }

    public void processLogout(ActionEvent event) {
        if (application == null) {
            return;
        }

        application.userLogout();
    }

    public void processUpdatePreferences(ActionEvent event) {
        if (application == null) {
            return;
        }
        application.userUpdatePreferences();
    }

    public void processSearchUser(ActionEvent event) {

        if (application == null) {
            return;
        }

        application.userDisplay();
    }

}
