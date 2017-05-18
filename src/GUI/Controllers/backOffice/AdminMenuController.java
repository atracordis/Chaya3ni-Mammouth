/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.backOffice;

import Entities.User;
import GUI.Interfaces.Main;
import Services.UserService;
import animation.FadeInLeftTransition;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AdminMenuController extends AnchorPane {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
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
    private Button statsButton;
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
    private Text statsLabel;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        LBnomuser.setText(User.getCurrentName() + " " + User.getCurrentSurname());
        new FadeInLeftTransition(myAccountButton).play();
        new FadeInLeftTransition(searchUsersButton).play();
        new FadeInLeftTransition(rideButton).play();
        new FadeInLeftTransition(complaintsButton).play();
        new FadeInLeftTransition(LBnomuser).play();
        new FadeInLeftTransition(lbTitre).play();
        new FadeInLeftTransition(LBimguser).play();
        new FadeInLeftTransition(myBookingsButton).play();
        new FadeInLeftTransition(statsLabel).play();
        new FadeInLeftTransition(statsButton).play();

        lblClose.setOnMouseClicked((MouseEvent event) -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public void setApp(Main application) {
        this.application = application;
        UserService u = new UserService();

        User user = u.getUserLogin(User.getCurrentUsername(), User.getCurrentPassword());
        //  Image img = new Image(file.toURI().toURL().toExternalForm());

        Image img = new Image("file:///C:/wamp/www/PIDEV/pics/" + user.getPhoto());
        LBimguser.setImage(img);

    }

    @FXML
    private void processMyBookingsButton(ActionEvent event) throws IOException {
        application.gotoAffichReviewsAdmin();
    }

    @FXML
    private void processMyAccountButton(ActionEvent event) throws IOException {
        application.gotoAdminInscription();
    }

    @FXML
    private void processSearchUsersButton(ActionEvent event) throws IOException {
        application.gotoAdminUserDisplay();
    }

    @FXML
    private void processComplaintsButton(ActionEvent event) throws IOException {
        application.gotoAffichComplaints();
    }

    @FXML
    private void processRideButton(ActionEvent event) throws IOException {
        // tunisiashopping.Tunisiashopping.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/advertising.fxml"))));
           application.gotoMenuRides();
    }

    @FXML
    private void processStatsButton(ActionEvent event) throws IOException {
        application.gotoStatsMenu();
    }

    public void processLogout(ActionEvent event) {
        if (application == null) {
            return;
        }

        application.userLogout();
    }
    
    public void processContinueAsUser(ActionEvent event) {
        if (application == null) {
            return;
        }

        application.gotoMenu();
    }

}
