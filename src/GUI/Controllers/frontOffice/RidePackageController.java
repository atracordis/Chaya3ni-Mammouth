/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.frontOffice;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import Services.RidePackageService;
import Entities.RidePackage;
import Entities.RideDriver;
import Entities.RidePassenger;
import GUI.Interfaces.Main;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class RidePackageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<RideDriver> listedestrajets;
    @FXML
    private TableView<RidePackage> listedesmarchandises;
    @FXML
    private TextField citySource;
    @FXML
    private TextField cityDestination;
    @FXML
    private TextField typePackage;
    @FXML
    private ComboBox size;
    @FXML
    private TextField quantity;
    @FXML
    private TextField poids;
    @FXML
    private TextArea description;
    @FXML
    private TabPane tableau;
    @FXML
    private Tab AjoutModif;

    private int onChangeModif = 0;
    private int idtrajet = 0;
    private int idPackage;
    private final ObservableList<RideDriver> data = FXCollections.observableArrayList();
    private final ObservableList<RidePackage> data2 = FXCollections.observableArrayList();

    private void AffichageListeTrajet() {
        RidePackageService RidePackage = new RidePackageService();
        try {
            ResultSet list = RidePackage.afficherTrajetConducteur();
            while (list.next()) {
                data.add(new RideDriver(list.getInt(1), 0, list.getString(3), list.getString(4), list.getString(7), list.getString(8), null));
            }
            listedestrajets.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(RidePackageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void AffichageMarhandise() {
        Button modifier = new Button("Modifier");
        Button Supprimer = new Button("Supprimer");
        RidePackageService RidePackage = new RidePackageService();
        try {
            ResultSet list = RidePackage.afficherMarchandise();
            while (list.next()) {
                RidePackage p = new RidePackage(list.getInt(1), list.getInt(2), list.getString(3), list.getString(4), list.getString(5), list.getFloat(7), list.getInt(6), list.getInt(8), modifier, Supprimer);
                data2.add(p);
                System.out.println(p);
            }
            listedesmarchandises.setItems(data2);
        } catch (SQLException ex) {
            Logger.getLogger(RidePackageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void processLogout(ActionEvent event) throws IOException {
     
        Main.getApp().gotoSpecialMenu();

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        size.getItems().addAll("Petite", "Moyenne", "Grande");
        poids.setText("0");
        Pattern intPattern = Pattern.compile("-?\\d*");
        UnaryOperator<TextFormatter.Change> filter = change -> {
            if (intPattern.matcher(change.getControlNewText()).matches()) {
                return change;
            }
            return null;
        };
        TextFormatter textFormatter = new TextFormatter(filter);
        TextFormatter textFormatter2 = new TextFormatter(filter);
        poids.setTextFormatter(textFormatter);
        quantity.setTextFormatter(textFormatter2);
        TableColumn typePackage = new TableColumn("Type de marchandise");
        typePackage.setCellValueFactory(
                new PropertyValueFactory<RideDriver, String>("typePackage"));

        TableColumn sizePackage = new TableColumn("Taille de marchandise");
        sizePackage.setCellValueFactory(
                new PropertyValueFactory<RideDriver, String>("size"));

        TableColumn quantityPackage = new TableColumn("Quantite de marchandise");
        quantityPackage.setCellValueFactory(
                new PropertyValueFactory<RideDriver, String>("qunatity"));

        TableColumn poidsPackage = new TableColumn("Poids de marchandise");
        poidsPackage.setCellValueFactory(
                new PropertyValueFactory<RideDriver, Float>("poids"));

        TableColumn citySourceCol = new TableColumn("Ville de départ");
        citySourceCol.setMinWidth(175);
        citySourceCol.setCellValueFactory(
                new PropertyValueFactory<RideDriver, String>("citySource"));

        TableColumn placeSourceCol = new TableColumn("point d'embarquement");
        placeSourceCol.setMinWidth(175);
        placeSourceCol.setCellValueFactory(
                new PropertyValueFactory<RideDriver, String>("placeSource"));

        TableColumn cityDestinationCol = new TableColumn("Ville de destination");
        cityDestinationCol.setMinWidth(175);
        cityDestinationCol.setCellValueFactory(
                new PropertyValueFactory<RideDriver, String>("cityDestination"));

        TableColumn placeDestinationCol = new TableColumn("point d'embarquement");
        placeDestinationCol.setMinWidth(175);
        placeDestinationCol.setCellValueFactory(
                new PropertyValueFactory<RideDriver, String>("placeDestination"));

        TableColumn Modif = new TableColumn("Modifier");
        Modif.setCellFactory(new Callback<TableColumn.CellDataFeatures<RidePackage, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<RidePackage, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        Modif.setCellFactory(new Callback<TableColumn<RidePackage, Boolean>, TableCell<RidePackage, Boolean>>() {

            @Override
            public TableCell<RidePackage, Boolean> call(TableColumn<RidePackage, Boolean> p) {
                return new RidePackageController.ButtonCellModif();
            }

        });

        TableColumn Supp = new TableColumn("Supprimer");
        Supp.setCellFactory(
                new Callback<TableColumn.CellDataFeatures<RidePackage, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<RidePackage, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        Supp.setCellFactory(
                new Callback<TableColumn<RidePackage, Boolean>, TableCell<RidePackage, Boolean>>() {

            @Override
            public TableCell<RidePackage, Boolean> call(TableColumn<RidePackage, Boolean> p) {
                return new RidePackageController.ButtonCellSupp();
            }

        });

        listedestrajets.getColumns().addAll(citySourceCol, placeSourceCol, cityDestinationCol, placeDestinationCol);
        listedesmarchandises.getColumns().addAll(typePackage, sizePackage, quantityPackage, poidsPackage, Modif, Supp);
        AffichageListeTrajet();
        AffichageMarhandise();
    }

    @FXML
    private void onSelectedItem() {
        RideDriver ride = listedestrajets.getSelectionModel().selectedItemProperty().getValue();
        citySource.setText(ride.getCitySource());
        cityDestination.setText(ride.getCityDestination());
        idtrajet = ride.getId();
        System.out.println(idtrajet);
    }

    @FXML
    private void insertPackage() {
        if (onChangeModif == 0) {
            if (typePackage.getText().length() == 0 || quantity.getText().length() == 0 || description.getText().length() == 0) {
                Alert alertW = new Alert(Alert.AlertType.WARNING);
                alertW.setTitle("Ajout d'une marchandise");
                alertW.setHeaderText(null);
                alertW.setContentText("Un ou des champs sont invalides");
                alertW.showAndWait();
            } else if (!typePackage.equals("") && !quantity.equals("") && !poids.equals("") && !description.equals("")) {
                RidePackage p = new RidePackage(idtrajet, description.getText(), (String) size.getValue(), typePackage.getText(), Float.parseFloat(poids.getText()), Integer.parseInt(quantity.getText()), 5);
                RidePackageService Service = new RidePackageService();
                    Service.insertPackage(p);
                Alert alertI = new Alert(Alert.AlertType.INFORMATION);
                alertI.setTitle("Ajout d'une marchandise");
                alertI.setHeaderText(null);
                alertI.setContentText("Une marchandise a été ajouté");
                alertI.showAndWait();
                listedesmarchandises.getItems().clear();
                AffichageMarhandise();
                typePackage.clear();
                size.setValue("");
                poids.clear();
                quantity.clear();
                description.clear();
            }
        } else if (onChangeModif == 1) {
            if (typePackage.getText().length() == 0 || quantity.getText().length() == 0 || description.getText().length() == 0) {
                Alert alertW = new Alert(Alert.AlertType.WARNING);
                alertW.setTitle("Ajout d'une marchandise");
                alertW.setHeaderText(null);
                alertW.setContentText("Un ou des champs sont invalides");
                alertW.showAndWait();
            } else if (!typePackage.equals("") && !quantity.equals("") && !poids.equals("") && !description.equals("")) {
                RidePackage p = new RidePackage(idPackage, idtrajet, description.getText(), (String) size.getValue(), typePackage.getText(), Float.parseFloat(poids.getText()), Integer.parseInt(quantity.getText()), 5);
                RidePackageService Service = new RidePackageService();
                    Service.updatePackage(p);
                Alert alertI = new Alert(Alert.AlertType.INFORMATION);
                alertI.setTitle("Modification d'une marchandise");
                alertI.setHeaderText(null);
                alertI.setContentText("Une marchandise a été modifié");
                alertI.showAndWait();
                onChangeModif = 0;
                listedesmarchandises.getItems().clear();
                AffichageMarhandise();
                typePackage.clear();
                size.setValue("");
                poids.clear();
                quantity.clear();
                description.clear();
            }
        }
    }

    private class ButtonCellModif extends TableCell<RidePackage, Boolean> {

        final Button cellButton = new Button("Modifier");

        public ButtonCellModif() {
            cellButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    RidePackage ride = listedesmarchandises.getSelectionModel().selectedItemProperty().getValue();
                    RidePackageService service = new RidePackageService();
                    try {
                        ResultSet list = service.getCitySourceDestination(ride.getIdtrajet());
                        while (list.next()) {
                            if (list.getInt(1) == ride.getId()) {
                                idPackage = ride.getId();
                                citySource.setText(list.getString(8));
                                cityDestination.setText(list.getString(9));
                                typePackage.setText(list.getString(6));
                                size.setValue(list.getString(4));
                                quantity.setText(String.valueOf(list.getInt(5)));
                                poids.setText(String.valueOf(list.getFloat(7)));
                                description.setText(list.getString(3));
                                idtrajet = list.getInt(2);
                                onChangeModif = 1;
                                tableau.getSelectionModel().select(AjoutModif);
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(RidePackageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }

    private class ButtonCellSupp extends TableCell<RidePackage, Boolean> {

        final Button cellButton = new Button("Supprimer");

        public ButtonCellSupp() {

            cellButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    RidePackage ride = listedesmarchandises.getSelectionModel().selectedItemProperty().getValue();
                    RidePackageService service = new RidePackageService();
                        service.deletePackage(ride);
                        Alert alertI = new Alert(Alert.AlertType.INFORMATION);
                        alertI.setTitle("Suppression d'une marchandise");
                        alertI.setHeaderText(null);
                        alertI.setContentText("Une marchandise a été supprimé");
                        alertI.showAndWait();
                    listedesmarchandises.getItems().clear();
                    AffichageMarhandise();
                }
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }
}
