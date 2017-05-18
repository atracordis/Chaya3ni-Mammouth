/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.frontOffice;

import Entities.FullRideAnimal;
import Services.ServiceGoalToReach;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author elloumiaymen
 */
public class ModifierFXMLController implements Initializable {

    @FXML
    private TextField NomAnimal;
    @FXML
    private TextField specialNeeds;
    @FXML
    private TextField placeSourceU;
    @FXML
    private TextField surnomGtrU;
    @FXML
    private TextField nameGtrU;
    @FXML
    private TextField notesGtrU;
    @FXML
    private ComboBox<String> comboU;
    @FXML
    private TextField placeDestinationU;
    
    ObservableList<String> listAnimals = FXCollections.observableArrayList("Chien", "Chat", "Mouton", "Oiseau", "Vache");


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboU.setItems(listAnimals);

        NomAnimal.setText(AffichageInfosController.animaltoEdit.getNameAnimal());
        specialNeeds.setText(AffichageInfosController.animaltoEdit.getSpecialNeedsAnimal());
        placeSourceU.setText(AffichageInfosController.animaltoEdit.getPlaceSource());
        placeDestinationU.setText(AffichageInfosController.animaltoEdit.getPlaceDestination());
        surnomGtrU.setText(AffichageInfosController.animaltoEdit.getSuernamegtr());
        nameGtrU.setText(AffichageInfosController.animaltoEdit.getNamestr());
        notesGtrU.setText(AffichageInfosController.animaltoEdit.getNumberstr());
        comboU.setValue(AffichageInfosController.animaltoEdit.getSpeciesAnimal());
     
        
    }
    public void buttonUpdate(ActionEvent event) throws SQLException, IOException
    {
       FullRideAnimal animal = new FullRideAnimal(NomAnimal.getText(), comboU.getValue(), specialNeeds.getText(), placeSourceU.getText(), placeDestinationU.getText(), surnomGtrU.getText(), nameGtrU.getText(), notesGtrU.getText());
        System.out.println(AffichageInfosController.animaltoEdit.getIdridpass());
          ServiceGoalToReach  sa = new ServiceGoalToReach();
          
        sa.modifieratPassager(animal, AffichageInfosController.animaltoEdit.getIdridpass());
        sa.modifieratAnimal(animal, AffichageInfosController.animaltoEdit.getId());
        sa.modifieratGTR(animal, AffichageInfosController.animaltoEdit.getIdgtr());
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification des informations");
            alert.setHeaderText(null);
            alert.setContentText("Vos infos ont été modifiés avec succées ");
            goTo(event);
    }
    private void goTo(ActionEvent event) throws IOException {
            Parent blah = FXMLLoader.load(getClass().getClassLoader().getResource("/GUI/Interfaces/AffichageInfos.fxml"));
            Scene scene = new Scene(blah);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();

    }
}
