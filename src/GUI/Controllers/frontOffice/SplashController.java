/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.frontOffice;

import GUI.Interfaces.Main;
import Tools.*;
import animation.FadeInLeftTransition;
import animation.FadeInRightTransition;
import animation.FadeInTransition;
import animation.FadeInUpTransition;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Herudi
 */
public class SplashController extends AnchorPane {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    private Main application;
    @FXML
    private Text lblWelcome;
    @FXML
    private Text lblRudy;
    @FXML
    private VBox vboxBottom;
    @FXML
    private Label lblClose;
    @FXML
    private Text Labelmessage;
    @FXML
    private ImageView imgLoading;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    public void closeSplash() {
        Platform.exit();
        System.exit(0);
    }

    public void setApp(Main application){
        this.application = application;
    }
    
    public void initialize() {
        longStart();
        lblClose.setOnMouseClicked((MouseEvent event) -> {
            closeSplash();
        });
        // TODO
    }

    void longStart()  {
        Conn connection = Conn.getInstance();
        new FadeInLeftTransition(lblWelcome).play();
        new FadeInRightTransition(lblRudy).play();
        new FadeInTransition(vboxBottom).play();
        new FadeInTransition(Labelmessage).play();
        PauseTransition delay = new PauseTransition(Duration.seconds(6));
        PauseTransition delay2 = new PauseTransition(Duration.seconds(4));
        if (connection == null) {
            delay2.setOnFinished(event -> Labelmessage.setText("Connection failed"));
        } else {
            delay2.setOnFinished(event -> Labelmessage.setText("Connection successful"));
            delay.setOnFinished(event -> {
                application.gotoLogin();
                
            });

        }

        delay.play();
        delay2.play();
    }

    public void messageInformation(String msg) {
        Labelmessage.setText(msg);
    }

}
