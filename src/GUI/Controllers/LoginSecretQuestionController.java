/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import Entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.util.Duration;
import GUI.Interfaces.Main;
import GUI.Interfaces.Main;
import Services.UserService;
import Tools.OtherTools;
import Tools.Show;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Admin
 */
public class LoginSecretQuestionController extends AnchorPane {

    @FXML
    Label userId;
    @FXML
    TextField password;
    @FXML
    Button login;
    @FXML
    Label errorMessage;

    private Main application;
    @FXML
    private Hyperlink back;

    public void setApp(Main application) {
        this.application = application;
        TrayNotification tray = new TrayNotification("Forgot password", "Please answer your question", Notifications.SUCCESS);
        tray.showAndDismiss(Duration.seconds(3));
                UserService us = new UserService();
        User user = us.getUserFromUserName(User.getCurrentUsername());
        userId.setText(user.getSecretQuestion());


    }

    void initialize() {
        errorMessage.setText("");
    }

    @FXML
    public void processLogin(ActionEvent event) {
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            errorMessage.setText("Hello " + userId.getText());
        } else {
            UserService us = new UserService();
            User user = us.getUserFromUserName(User.getCurrentUsername());

            if (password.getText().equals(user.getSecretAnswer())) {
                application.userLogging(user.getUsername(), user.getPass());
                TrayNotification tray = new TrayNotification("Welcome", "Welcome Back " + User.getCurrentName() + " " + User.getCurrentSurname(), Notifications.SUCCESS);
                tray.showAndDismiss(Duration.seconds(3));
            } else {
                TrayNotification tray = new TrayNotification("Warning", "Wrong answer ", Notifications.WARNING);
                tray.showAndDismiss(Duration.seconds(3));
            }
        }
    }

    @FXML
    private void processBackToLogin(ActionEvent event) {
        application.gotoLogin();
    }

}
