/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.frontOffice;

import Entities.FullRideAnimal;
import Entities.RideAnimals;
import Entities.RidePassenger;
import Services.ServiceGoalToReach;
import Services.ServiceRideAnimals;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author elloumiaymen
 */
public class AffichageInfosController implements Initializable {

    @FXML
    private TableView<FullRideAnimal> tableview;
    @FXML
    private TextField search;
    @FXML
    private Button delete;
    private final ObservableList<RideAnimals> data = FXCollections.observableArrayList();

    private ObservableList<FullRideAnimal> rideData = FXCollections.observableArrayList();

    private static int idanimall;
    private static int passid;
    private static int gtrid;
    public static FullRideAnimal animaltoEdit = new FullRideAnimal();
    @FXML
    private Button modifierbutt;
    @FXML
    private Button closeButton;
    @FXML
    private Button stat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TableColumn nameAnimalcol = new TableColumn("Name");
        nameAnimalcol.setCellValueFactory(
                new PropertyValueFactory<>("nameAnimal"));

        TableColumn speciesAnimalcol = new TableColumn(" Animal Species");
        speciesAnimalcol.setCellValueFactory(
                new PropertyValueFactory<FullRideAnimal, String>("speciesAnimal"));

        TableColumn luggageMasscol = new TableColumn("special Needs");
        luggageMasscol.setCellValueFactory(
                new PropertyValueFactory<FullRideAnimal, String>("specialNeedsAnimal"));

        TableColumn specialNeedscol = new TableColumn("Place Source");
        specialNeedscol.setCellValueFactory(
                new PropertyValueFactory<>("placeSource"));

        TableColumn Placedest = new TableColumn("Place Destination");
        Placedest.setCellValueFactory(
                new PropertyValueFactory<FullRideAnimal, String>("placeDestination"));

        TableColumn surname = new TableColumn("Surname GTR");
        surname.setCellValueFactory(
                new PropertyValueFactory<FullRideAnimal, String>("suernamegtr"));

        TableColumn name = new TableColumn("Name GTR");
        name.setCellValueFactory(
                new PropertyValueFactory<FullRideAnimal, String>("namestr"));

        TableColumn phone = new TableColumn("phone number GTR");
        phone.setCellValueFactory(
                new PropertyValueFactory<FullRideAnimal, String>("numberstr"));
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(
                new PropertyValueFactory<FullRideAnimal, String>("id"));
        tableview.getColumns().addAll(nameAnimalcol, speciesAnimalcol, luggageMasscol, specialNeedscol, Placedest, surname, name, phone);
        //PopulateTable();
        ServiceGoalToReach us = new ServiceGoalToReach();
        List<FullRideAnimal> fullinfos = us.selectAll();
        rideData.addAll(0, fullinfos);
        tableview.setItems(rideData);

    }

    public void PopulateTable() {
        ServiceRideAnimals RideService = new ServiceRideAnimals();

        try {
            ResultSet list = RideService.afficher();
            while (list.next()) {
                data.add(new RideAnimals(list.getInt(1), list.getString(2), list.getString(3), list.getInt(4), list.getFloat(5), list.getFloat(6), list.getString(7)));
                //   tableview.setItems(data);
                idanimall = list.getInt(9);
                passid = list.getInt(10);
                gtrid = list.getInt(11);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RideAnimalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void refreshtable() {
        ServiceGoalToReach us = new ServiceGoalToReach();
        List<FullRideAnimal> fullinfos = us.selectAll();
        rideData.addAll(0, fullinfos);
        tableview.setItems(rideData);
    }

    @FXML
    public void deleteButtonClicked() {
        FullRideAnimal animal = tableview.getSelectionModel().selectedItemProperty().getValue();
        ServiceRideAnimals sa = new ServiceRideAnimals();
        //RidePassenger ride = listedestrajets.getSelectionModel().getSelectedItem();
        System.out.println(animal.getId());
        idanimall = animal.getId();
        sa.DeleteAnimals(idanimall);
        tableview.getItems().clear();
        refreshtable();
    }
    @FXML
    public void ModifierButtonClicked(ActionEvent event) throws IOException {
        FullRideAnimal animal = tableview.getSelectionModel().selectedItemProperty().getValue();
         animaltoEdit = animal;
         idanimall = animal.getId();
         passid = animal.getIdridpass();
         gtrid = animal.getIdgtr();
         System.out.println(" PASSENGER ID "+passid+"GTR ID"+ gtrid);
         goTo(event);
       
    }

    @FXML
    public void searchAnimal() throws SQLException {
        tableview.getItems().clear();
        ServiceGoalToReach us = new ServiceGoalToReach();
        List<FullRideAnimal> fullinfos = us.selectAllsearch(search.getText());
        rideData.addAll(0, fullinfos);
        tableview.setItems(rideData);

    }

    private void goTo(ActionEvent event) throws IOException {
 Parent blah = FXMLLoader.load(getClass().getClassLoader().getResource("/GUI/Interfaces/ModifierFXML.fxml"));
            Scene scene = new Scene(blah);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();

    }
    
    @FXML
    private void goToStat(ActionEvent event) throws IOException {
            Parent blah = FXMLLoader.load(getClass().getClassLoader().getResource("/GUI/Interfaces/StatAnimals.fxml"));
            Scene scene = new Scene(blah);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
    }

    @FXML
    private void exit(ActionEvent event) {
          Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
    }
}
