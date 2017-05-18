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
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;

import java.util.Properties;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
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
public class LoginController extends AnchorPane {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML
    TextField userId;
    @FXML
    PasswordField password;
    @FXML
    Button login;
    @FXML
    Button register;
    @FXML
    Label errorMessage;

    private Main application;

    public void setApp(Main application) {
        this.application = application;

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        errorMessage.setText("");
        TrayNotification tray = new TrayNotification("Welcome", "Please login or register", Notifications.SUCCESS);
        tray.showAndDismiss(Duration.seconds(3));
    }

    public void processForgotPassword(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Choosing authentification method");
        alert.setHeaderText("Would you like to be sent an email with your password or to answer your secret question?");
        alert.setContentText("Choose your option.");

        ButtonType secret = new ButtonType("Secret question");
        ButtonType email = new ButtonType("Email");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(secret, email, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == secret) {

            UserService us = new UserService();
            User user = us.getUserFromUserName(userId.getText());
            if (user != null) {
                User.setCurrentUsername(userId.getText());
                application.gotoLoginSecretQuestion();
            } else {
                TrayNotification tray = new TrayNotification("Warning", "Unknown username", Notifications.WARNING);
                tray.showAndDismiss(Duration.seconds(3));
            }

        } else if (result.get() == email) {

            UserService us = new UserService();
            User user = us.getUserFromUserName(userId.getText());

            if (user != null) {
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class",
                        "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");

                Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("wajd.meskini@esprit.tn", "Na7nafibledna3edna");
                    }
                });

                try {

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("wajd.meskini@esprit.tn"));
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(user.getEmail()));
                    message.setSubject("Forgot your password - Chaya3ni");
                    message.setText("Hello\n Amnesia? Chaya3ni got your back!\n Here s your password: \n " + user.getSecretAnswer() + "\n Stay safe out there, and happy riding! \n Yours, Mammouth Industries.");

                    Transport.send(message);

                    System.out.println("Done");
                    TrayNotification tray = new TrayNotification("Success", "Email Sent", Notifications.SUCCESS);
                    tray.showAndDismiss(Duration.seconds(3));

                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }

            } else {
                TrayNotification tray = new TrayNotification("Warning", "Unknown username", Notifications.WARNING);
                tray.showAndDismiss(Duration.seconds(3));
            }
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

    public void processLogin(ActionEvent event) {
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            errorMessage.setText("Hello " + userId.getText());
        } else {
            UserService userservice = new UserService();
            if (userservice.getUserLoginAlternate(userId.getText(), password.getText()) == 3) {
                TrayNotification tray = new TrayNotification("Error", "Unknown username ", Notifications.ERROR);
                tray.showAndDismiss(Duration.seconds(3));
            } else if (userservice.getUserLoginAlternate(userId.getText(), password.getText()) == 2) {
                TrayNotification tray = new TrayNotification("Warning", "Password doesn't match username", Notifications.WARNING);
                tray.showAndDismiss(Duration.seconds(3));
            } else if (userservice.getUserLoginAlternate(userId.getText(), password.getText()) == 1) {
                TrayNotification tray = new TrayNotification("Welcome", "Welcome Back " + userId.getText(), Notifications.SUCCESS);
                tray.showAndDismiss(Duration.seconds(3));
                application.userLogging(userId.getText(), password.getText());
            } else {
                errorMessage.setText("Unknown user " + userId.getText() + " or wrong password");
            }
        }
    }

    public void processRegister(ActionEvent event) {
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            errorMessage.setText("Hello " + userId.getText());
        } else if (!application.userRegister()) {
            errorMessage.setText("Unknown user " + userId.getText());
        }
    }

}
