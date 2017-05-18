/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.frontOffice;


import Entities.GoalToReach;
import Entities.RideAnimals;
import Services.ServiceRideAnimals;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import Services.ServiceGoalToReach;
import Tools.OtherTools;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author elloumiaymen
 */
public class AffichageController implements Initializable {

    @FXML
    private TextField SpecialNeeds;
    @FXML
    private TextField massLuggage;
    @FXML
    private ComboBox<String> combo;
    ObservableList<String> listAnimals = FXCollections.observableArrayList("Chien", "Chat", "Mouton", "Oiseau", "Vache");

    @FXML
    private TextField massAnimal;
    @FXML
    private TextField nameAnimal;
    @FXML
    private TextField nomGoalToReach;
    @FXML
    private TextField prenomGoalToReach;
    @FXML
    private TextField tailleGoalToReach;
    @FXML
    private TextField descriptionGoalToReach;
    @FXML
    private TextField numeroGoalToReach;
    @FXML
    private Button ContinuerButton;
    @FXML
    private Button uploadphotoAnimal;
    @FXML
    private Button uploadphotoGTR;
    @FXML
    private Label photoname;
    @FXML
    private Label photopath;
    @FXML
    private ImageView imgVw;

    public static RideAnimals animal = new RideAnimals();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combo.setItems(listAnimals);
        photopath.setVisible(false);
        photoname.setVisible(false);

    }

    @FXML
    public void processAddingAnimal(ActionEvent event) throws IOException, SQLException {
        int ida = 0;
        int id = 0;
       // RideAnimals Animals = new RideAnimals(nameAnimal.getText(), combo.getValue(), Float.parseFloat(massAnimal.getText()), Float.parseFloat(massLuggage.getText()), SpecialNeeds.getText());
        ServiceRideAnimals ra = new ServiceRideAnimals();
        File destFile = new File ("/Applications/MAMP/htdocs/temp"+animal.getRegpic().getName());
         OtherTools.copyFile(animal.getRegpic(), destFile);
        destFile.renameTo(new File ("/Applications/MAMP/htdocs/temp"+animal.getNameAnimal()+animal.getPhoto()));
        File destFile2 = new File ("/Applications/MAMP/htdocs/photo"+animal.getNameAnimal()+animal.getPhoto());
        animal.setPhoto(animal.getNameAnimal()+animal.getPhoto());
        File destFile3 = new File ("/Applications/MAMP/htdocs/temp"+animal.getPhoto());
        OtherTools.copyFile(destFile3, destFile2);
        animal.setRegpic(destFile2);
        System.out.println(animal.getRegpic().getAbsolutePath());
        destFile.delete();
       //Animals.setRegpic(animal.getRegpic());
        //Animals.setPhoto(animal.getPhoto());
       // ra.ajouterRideAnimals(Animals);
        //ra.ajouterphotoAnimals(Animals, Animals.getId());
      RideAnimals  Animals = new RideAnimals(nameAnimal.getText(), combo.getValue(), Float.parseFloat(massAnimal.getText()), Float.parseFloat(massLuggage.getText()), SpecialNeeds.getText(), animal.getRegpic(), animal.getPhoto());
      GoalToReach goal = new GoalToReach(nomGoalToReach.getText(), prenomGoalToReach.getText(), descriptionGoalToReach.getText(), Float.parseFloat(tailleGoalToReach.getText()), numeroGoalToReach.getText());
     // Select lastid of rides to  attribute to RideAnimals
      ResultSet lista = ra.lastIdRide();
       while(lista.next())
       {
            ida = lista.getInt(1);
       }
       ra.ajouterRideAnimalss(ida, Animals);
      
      ServiceGoalToReach gtr = new ServiceGoalToReach();
        try {
            ResultSet list = gtr.lastId();
            while (list.next()) {
                id = list.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AffichageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(id);
           gtr.ajouterGoalToReach(goal, id);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ajout d'un trajet");
                    alert.setHeaderText(null);
                    alert.setContentText("Votre demande est actuellement en ligne");
                    alert.showAndWait();
                    goTo(event);
    }
    
    
    

    @FXML
    public void processUpload(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter exjpg = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter exjpg2 = new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg");
        FileChooser.ExtensionFilter expng = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(exjpg, exjpg2, expng);
        fileChooser.setTitle("Choose an image File");

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            if (file.length() < 6000000) {
                System.out.print("Condition ok");

                Image img = new Image(file.toURI().toString());
                imgVw.setImage(img);
                imgVw.setFitHeight(150);
                imgVw.setFitWidth(150);
                animal.setRegpic(file);
                System.err.println("*********************");
                System.out.println(animal.getRegpic().getAbsolutePath());
                System.err.println("*********************");
                photopath.setText("/Applications/MAMP/htdocs/photo" + file.getName());
                photoname.setText(file.getName());
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Permiss");
                alert.setHeaderText("Permission denied");
                alert.setContentText("Your Image file is too big to upload \nplease choise another image");
            }
        }

    }

    @FXML
    private void goTo(ActionEvent event) throws IOException {
            Parent blah = FXMLLoader.load(getClass().getClassLoader().getResource("/GUI/Interfaces/AffichageInfos.fxml"));
            Scene scene = new Scene(blah);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
   }
    
    }
    


