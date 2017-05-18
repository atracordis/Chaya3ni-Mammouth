/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.backOffice;

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
import Services.PreferencesService;
import Services.UserService;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;

import java.util.Properties;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
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
public class EmailController extends AnchorPane {
    
    @FXML
    private TextField user;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextArea address;
    @FXML
    private Hyperlink logout;
    @FXML 
    private Button update;
    @FXML 
    private Label success;
    
    
    
    private Main application;
    @FXML
    private Font x1;
    @FXML
    private Label userLabel;
    @FXML
    private Insets x3;
    @FXML
    private Label emailLabel;
    @FXML
    private Button Button;
    
    public void setApp(Main application){
        this.application = application;
        PreferencesService prefser = new PreferencesService();

        System.out.println(User.pickedNewsletter());
        if (!(User.pickedNewsletter()))
        {
        User currentUser = prefser.selectPreferencesId(User.getSelectedId());

        user.setText(currentUser.getName() + " "+currentUser.getSurname());
        email.setText(currentUser.getCurrentEmail());
        success.setOpacity(0);
            emailLabel.setVisible(true);
            userLabel.setVisible(true);
            email.setVisible(true);
            user.setVisible(true);}
        
        if (User.pickedNewsletter())
        {
            emailLabel.setVisible(false);
            userLabel.setVisible(false);
            email.setVisible(false);
            user.setVisible(false);
        }
        else
        {
            emailLabel.setVisible(true);
            userLabel.setVisible(true);
            email.setVisible(true);
            user.setVisible(true);
        }        
        
    }

    void initialize() {
    }
    
    @FXML
    public void processLogout(ActionEvent event) {
        if (application == null){
            return;
        }
        
        application.userLogout();
    }
    
    @FXML
    public void processUpdate(ActionEvent event) {
        if (application == null){
            animateMessage();
            return;
        }
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
					return new PasswordAuthentication("wajd.meskini@esprit.tn","Na7nafibledna3edna");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("wajd.meskini@esprit.tn"));
			message.setSubject(phone.getText());
			message.setText(address.getText());

                  if (!(User.pickedNewsletter()))
                  {
                        message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email.getText()));

			Transport.send(message);

			System.out.println("Done");        
                        TrayNotification tray = new TrayNotification("Success", "Email Sent", Notifications.SUCCESS);
                        tray.showAndDismiss(Duration.seconds(3));
                  }
                  else
                  {
                      UserService s = new UserService();
                      List<String> emails = s.getEmails();
                      for (String em : emails) 
                      {
                        message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(em));
			Transport.send(message);                          
                      }
                  }

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
        

        animateMessage();
    }

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(3000), success);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }
    public void processSearchUser(ActionEvent event) {

        if (application == null) {
            return;
        }

        application.gotoAdminUserDisplay();
    }

    
}
