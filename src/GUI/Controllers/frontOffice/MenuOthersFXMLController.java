/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.frontOffice;

import Entities.User;
import GUI.Interfaces.Main;
import Services.UserService;
import animation.FadeInLeftTransition;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class MenuOthersFXMLController implements Initializable {

    private Main application;
    @FXML
    private ImageView LBimguser;
    @FXML
    private Label lblClose;
    @FXML
    private Button searchUsersButton;
    @FXML
    private Button myAccountButton;
    @FXML
    private Button rideButton;
    @FXML
    private Button complaintsButton;
    @FXML
    private Button myBookingsButton;
    @FXML
    private Text lblWelcome11;
    @FXML
    private Text lblWelcome111;
    @FXML
    private Text lblWelcome112;
    @FXML
    private Text lblWelcome113;
    @FXML
    private Label lbTitre;
    @FXML
    private Label LBnomuser;
    @FXML
    private Hyperlink Logout;
    @FXML
    private Text lblWelcome12;
    @FXML
    private Button covoiturageAnimal;
    @FXML
    private Text covoiturageAnimalLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LBnomuser.setText(User.getCurrentName() + " " + User.getCurrentSurname());
        new FadeInLeftTransition(myAccountButton).play();
        new FadeInLeftTransition(searchUsersButton).play();
        new FadeInLeftTransition(rideButton).play();
        new FadeInLeftTransition(complaintsButton).play();
        new FadeInLeftTransition(LBnomuser).play();
        new FadeInLeftTransition(lbTitre).play();
        new FadeInLeftTransition(LBimguser).play();
        new FadeInLeftTransition(myBookingsButton).play();
        new FadeInLeftTransition(covoiturageAnimalLabel).play();
        new FadeInLeftTransition(covoiturageAnimal).play();
        setApp();
        lblClose.setOnMouseClicked((MouseEvent event) -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public void setApp() {
        this.application = Main.getApp();
        UserService u = new UserService();
        User user = u.getUserLogin(User.getCurrentUsername(), User.getCurrentPassword());
        //  Image img = new Image(file.toURI().toURL().toExternalForm());

        Image img = new Image("file:///C:/wamp/www/PIDEV/pics/" + user.getPhoto());
        LBimguser.setImage(img);
    }

    @FXML
    private void processMyBookingsButton(ActionEvent event) throws IOException {
        Main.getApp().gotoAffichReviews();
    }

    @FXML
    private void processMyAccountButton(ActionEvent event) throws IOException {
        Main.getApp().gotoUpdate();
    }

    @FXML
    private void processSearchUsersButton(ActionEvent event) throws IOException {
        Main.getApp().gotoUserDisplay();
    }

    @FXML
    private void processComplaintsButton(ActionEvent event) throws IOException {
        Main.getApp().gotoAffichComplaintsUser();
    }

    @FXML
    private void processRideButton(ActionEvent event) throws IOException {
        Main.getApp().gotoMenuRides();
    }

    @FXML
    public void processLogout(ActionEvent event) {
        if (application == null) {
            return;
        }
        Main.getApp().userLogout();
    }

    @FXML
    private void processcovoiturageAnimal(ActionEvent event) {
        try {
            //     application.gotoCovoiturageAnimal(); 
            Main.getApp().gotoCovoiturageAnimal();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
