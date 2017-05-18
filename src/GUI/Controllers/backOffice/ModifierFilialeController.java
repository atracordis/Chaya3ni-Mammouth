/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.backOffice;

import Services.ServFiliale;
import Tools.OtherTools;
import java.net.URL;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import GUI.Interfaces.Main;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ModifierFilialeController extends AnchorPane {
    
    private Main application;

    @FXML
    private TextField adressefmodif1;
    @FXML
    private TextField adressefmodif2;
    @FXML
    private TextField latitudemodiff;
    @FXML
    private TextField longitudemodiff;
    @FXML
    private TextField codepostalmodiff;
    @FXML
    private Button validermodif;
    @FXML
    private AnchorPane ap5;
    @FXML
    private Button returnb;

    /**
     * Initializes the controller class.
     * @param application
     */
      public void setApp(Main application) 
    {
        this.application = application;
    }
    public void initialize() 
    {
     adressefmodif1.setText(AffichageFilialeController.f.getAdresse1());
    adressefmodif2.setText(AffichageFilialeController.f.getAdresse2());
    latitudemodiff.setText(String.valueOf(AffichageFilialeController.f.getLatitude()));
    longitudemodiff.setText(String.valueOf(AffichageFilialeController.f.getLongitude()));
    codepostalmodiff.setText(String.valueOf(AffichageFilialeController.f.getCodepostal()));
    latitudemodiff.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
           if (!newValue.matches("\\d*")) {
               latitudemodiff.setText(newValue.replaceAll("[^\\d]", ""));
           }
        });
        longitudemodiff.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
           if (!newValue.matches("\\d*")) {
               longitudemodiff.setText(newValue.replaceAll("[^\\d]", ""));
           }
        });
       codepostalmodiff.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
           if (!newValue.matches("\\d*")) {
               codepostalmodiff.setText(newValue.replaceAll("[^\\d]", ""));
           }
        });
        OtherTools.ConditionText(codepostalmodiff, 4);
        OtherTools.ConditionText2 (latitudemodiff);
        OtherTools.ConditionText2 (longitudemodiff);
        
        
    }    

    @FXML
    private void ValiderModif(ActionEvent event) 
    {
        AffichageFilialeController.f.setAdresse1(adressefmodif1.getText());
        AffichageFilialeController.f.setAdresse2(adressefmodif2.getText());
        AffichageFilialeController.f.setLongitude(Double.parseDouble(latitudemodiff.getText()));
        AffichageFilialeController.f.setLatitude(Double.parseDouble(longitudemodiff.getText()));
        AffichageFilialeController.f.setCodepostal(Double.parseDouble(codepostalmodiff.getText()));
        ServFiliale sf = new ServFiliale ();
        if (AffichageFilialeController.f.getIdf()==0)
        {
            sf.AjouterFiliale(AffichageFilialeController.f);
            application.gotoAffichageFiliale();
        }
        else
        {
            sf.ModifierFiliale(AffichageFilialeController.f);
        application.gotoAffichageFiliale();
        }
        
                                
    }

    @FXML
    private void ReturnB(ActionEvent event) 
    {
        application.gotoDashBoardRepresentant();
    }
    
}
