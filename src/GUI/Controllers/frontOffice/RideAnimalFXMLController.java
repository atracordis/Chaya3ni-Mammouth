/*
PI: Interfaces CRUD this week. Back office + Front office
Web: See 3A1 group for homework
GL: One diagramme séquence objet. One diagramme de classes de conception. Sprint backlog.
Réseau: Topologie du réseau * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.frontOffice;
import GUI.Interfaces.Main;

import Entities.RideAnimals;
import Services.ServiceRideAnimals;
import Tools.*;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.util.Callback;
import static javax.management.Query.value;

/**
 * FXML Controller class
 *
 * @author elloumiaymen
 */
public class RideAnimalFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox<String> combo; // Combo de type
    @FXML
    private TextField nameAnimal;
    @FXML
    private TextField massAnimal;
    @FXML
    private Button add;
    @FXML
    private TableView<RideAnimals> tableview;
    @FXML
    private Button delete;

    private Main application;

    private final ObservableList<RideAnimals> data = FXCollections.observableArrayList();

    ObservableList<String> listAnimals = FXCollections.observableArrayList("Chien", "Chat", "Mouton", "Oiseau", "Vache");

    @FXML
    private Button Update;

    @FXML
    private TextField nameAnimalU;
    @FXML
    private TextField massAnimalU;
    @FXML
    private TextField massLuggageU;
    @FXML
    private ComboBox<String> comboU;
    @FXML
    private TextField massLuggage;
    @FXML
    private TextField SpecialNeeds;
    @FXML
    private TextField SpecialNeedsU;
    @FXML
    private TextField search;
    @FXML
    private Font x1;
    @FXML
    private Insets x3;
    @FXML
    private Font x11;
    @FXML
    private Insets x31;

    private static int id = 0;
    @FXML
    private PieChart pieChart;

    private ObservableList datachart;

    public void setApp(Main application) {
        this.application = application;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TableColumn nameAnimalcol = new TableColumn("Name");
        // nameAnimalcol.setMinWidth(100);

        nameAnimalcol.setCellValueFactory(
                new PropertyValueFactory<RideAnimals, String>("nameAnimal"));

        TableColumn speciesAnimalcol = new TableColumn("Species Animal");
        speciesAnimalcol.setMinWidth(100);
        speciesAnimalcol.setCellValueFactory(
                new PropertyValueFactory<RideAnimals, String>("speciesAnimal"));

        TableColumn luggageMasscol = new TableColumn("luggageMass");
        //cityDestinationCol.setMinWidth(100);
        luggageMasscol.setCellValueFactory(
                new PropertyValueFactory<RideAnimals, Float>("luggageMass"));

        TableColumn specialNeedscol = new TableColumn("specialNeeds");
        //cityDestinationCol.setMinWidth(100);
        specialNeedscol.setCellValueFactory(
                new PropertyValueFactory<RideAnimals, Float>("specialNeeds"));

        tableview.getColumns().addAll(nameAnimalcol, speciesAnimalcol, specialNeedscol);
        combo.setItems(listAnimals);
        comboU.setItems(listAnimals);
        PopulateTable();

        //PIE CHART
        PieChart pieChart = new PieChart();
        try {
            buildData();
        } catch (SQLException ex) {
            Logger.getLogger(RideAnimalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.pieChart.getData().addAll(datachart);

    }

    /**
     *
     * @param event
     */
    @FXML
    public void processUpdate(ActionEvent event) {

        RideAnimals Animals = new RideAnimals(nameAnimal.getText(), combo.getValue(), Float.parseFloat(massAnimal.getText()), Float.parseFloat(massLuggage.getText()), SpecialNeeds.getText());
        ServiceRideAnimals ra = new ServiceRideAnimals();
        ra.ajouterRideAnimals(Animals);
        tableview.getItems().clear();
        PopulateTable();
    }

    @FXML
    public void PopulateTable() {
        ServiceRideAnimals RideService = new ServiceRideAnimals();

        try {
            ResultSet list = RideService.afficher();
            while (list.next()) {
                data.add(new RideAnimals(list.getInt(1), list.getString(2), list.getString(3), list.getInt(4), list.getFloat(5), list.getFloat(6), list.getString(7)));
                tableview.setItems(data);

            }
        } catch (SQLException ex) {
            Logger.getLogger(RideAnimalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void deleteButtonClicked() {
        RideAnimals animal = tableview.getSelectionModel().selectedItemProperty().getValue();
        ServiceRideAnimals sa = new ServiceRideAnimals();
        sa.DeleteRideAnimals(animal);
        tableview.getItems().clear();
        PopulateTable();
    }

    @FXML
    public void UpdateButtonClickd() {
        System.out.println("lool");
        RideAnimals Animals = new RideAnimals(nameAnimalU.getText(), comboU.getValue(), Float.parseFloat(massAnimalU.getText()), Float.parseFloat(massLuggageU.getText()), SpecialNeedsU.getText());
        System.out.println(id);
        ServiceRideAnimals ra = new ServiceRideAnimals();
        ra.ModifierRideAnimals(Animals, id);
        tableview.getItems().clear();
        PopulateTable();
    }

    @FXML
    public void OnSelectedItem() {
        RideAnimals ra = tableview.getSelectionModel().selectedItemProperty().getValue();
        ServiceRideAnimals RideService = new ServiceRideAnimals();
        nameAnimalU.setText(ra.getNameAnimal());
        comboU.setValue(ra.getSpeciesAnimal());
        massLuggageU.setText(Float.toString(ra.getLuggageMass()));
        massAnimalU.setText(Float.toString(ra.getAnimalMass()));
        SpecialNeedsU.setText(ra.getSpecialNeeds());
         id = ra.getId();
    }

    @FXML
    public void searchAnimal() {
        ServiceRideAnimals RideService = new ServiceRideAnimals();
        tableview.getItems().clear();
        try {
            ResultSet list = RideService.affichersearch(search.getText());
            while (list.next()) {
                data.add(new RideAnimals(list.getInt(1), list.getString(2), list.getString(3), list.getInt(4), list.getFloat(5), list.getFloat(6), list.getString(7)));
                tableview.setItems(data);

            }
        } catch (SQLException ex) {
            Logger.getLogger(RideAnimalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void buildData() throws SQLException {
        datachart = FXCollections.observableArrayList();
        ServiceRideAnimals RideService = new ServiceRideAnimals();
        ResultSet rs = RideService.chartSpecies();
        while (rs.next()) {
            //adding data on piechart data   
            datachart.add(new PieChart.Data(rs.getString(2), rs.getInt(1)));

        }

    }

}
