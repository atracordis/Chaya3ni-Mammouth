/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.backOffice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import GUI.Interfaces.Main;
/**
 * FXML Controller class
 *
 * @author asus
 */
public class DashboardRepresentantController extends AnchorPane {
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
    private Button complaintsButton;
    @FXML
    private Text lblWelcome11;
    @FXML
    private Text lblWelcome12;
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
    private Button SocieteButton;
    @FXML
    private Button gotomap;

    /**
     * Initializes the controller class.
     */
    public void setApp(Main application) 
    {
        this.application = application;

    }
   
    public void initialize() {
        // TODO
    }    

    @FXML
    private void processSearchUsersButton(ActionEvent event) {
    }

    @FXML
    private void processMyAccountButton(ActionEvent event) {
    }


    @FXML
    private void processComplaintsButton(ActionEvent event) {
    }


    @FXML
    private void ProfilSociete(ActionEvent event) 
    {
        application.gotoProfilSociete();
    }

    @FXML
    private void GotoMap(ActionEvent event) 
    {
        application.gotoMap();
    }
    
}
