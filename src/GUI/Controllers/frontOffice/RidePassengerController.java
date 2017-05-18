/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.frontOffice;

import Entities.RidePassenger;
import GUI.Interfaces.Main;
import Interfaces.Controller;
import Services.RidePassengerService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
//import java.sql.Date;
import java.time.LocalDate;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import Services.*;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.TravelModes;
import com.lynden.gmapsfx.service.elevation.ElevationResult;
import com.lynden.gmapsfx.service.elevation.ElevationServiceCallback;
import com.lynden.gmapsfx.service.elevation.ElevationStatus;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.lynden.gmapsfx.service.geocoding.GeocodingServiceCallback;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import jdk.nashorn.internal.objects.NativeArray;

/**
 *
 * @author
 */
public class RidePassengerController implements Initializable, MapComponentInitializedListener,
        ElevationServiceCallback, GeocodingServiceCallback, DirectionsServiceCallback {

    @FXML
    private TextField citySource;
    @FXML
    private TextField placeSource;
    @FXML
    private TextField longSource;
    @FXML
    private TextField latSource;
    @FXML
    private TextField cityDestination;
    @FXML
    private TextField placeDestination;
    @FXML
    private TextField longDestination;
    @FXML
    private TextField latDestination;
    @FXML
    private CheckBox Animale;
    @FXML
    private CheckBox compagnie;
    @FXML
    private CheckBox Baggage;
    @FXML
    private Label poidsLabelle;
    @FXML
    private TextField poids;
    @FXML
    private CheckBox handicap;
    @FXML
    private CheckBox Musique;
    @FXML
    private Label genreMusiqueLabelle;
    @FXML
    private TextField genreMusique;
    @FXML
    private CheckBox fumeur;
    @FXML
    private CheckBox accepterFumeur;
    @FXML
    private Button button;
    @FXML
    private TableView<RidePassenger> listedestrajets;
    @FXML
    private TextField idTrajetSupp;
    @FXML
    private TabPane tableau;
    @FXML
    private Tab AjoutModif;
    @FXML
    private Tab Liste;
    @FXML
    private TextField recherche;
    @FXML
    private PieChart pieChart;
    @FXML
    WebView browser;

    private GeocodingService geocodingService;

    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;
    protected GoogleMapView mapComponent;
    protected GoogleMap map;
    protected DirectionsPane directions;

    private ObservableList dataChart;
    private int onChangeModif = 0;
    private int id = 0;
    private final ObservableList<RidePassenger> data = FXCollections.observableArrayList();
    @FXML
    private GridPane gridoption;
    private Main application;
    @FXML
    private Button abc;
    @FXML
    private Button addGM;

    @FXML
    public void goTo(javafx.event.ActionEvent event) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getClassLoader().getResource("/GUI/Interfaces/Affichage.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    public void setApp(Main application) {
        this.application = application;
    }

    @FXML
    public void processLogout(ActionEvent event) throws IOException {
        Main.getApp().gotoSpecialMenu();
    }
    @FXML
    private void insertTrajetAction(ActionEvent event) {
        if (onChangeModif == 0) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date1 = new Date();
            System.out.println(dateFormat.format(date1));
            int animalChek = 0;
            int compagnieCheck = 0;
            int BaggageCheck = 0;
            int handicapCheck = 0;
            int MusiqueCheck = 0;
            int FumeurCheck = 0;
            int AccepterFumeurcheck = 0;
            Float poids2 = 0.0f;

            if (Animale.isSelected()) {
                animalChek = 1;
            }
            if (compagnie.isSelected()) {
                compagnieCheck = 1;
            }
            if (Baggage.isSelected()) {
                BaggageCheck = 1;
            }
            if (handicap.isSelected()) {
                handicapCheck = 1;
            }
            if (Musique.isSelected()) {
                MusiqueCheck = 1;
            }
            if (fumeur.isSelected()) {
                FumeurCheck = 1;
            }
            if (accepterFumeur.isSelected()) {
                AccepterFumeurcheck = 1;
            }
            if (poids.getText() != "") {
                poids2 = Float.parseFloat(poids.getText());
            }

            if (!citySource.getText().equals("") && !placeSource.getText().equals("") && !cityDestination.getText().equals("") && !placeDestination.getText().equals("")) {
                RidePassenger a = new RidePassenger(citySource.getText(), placeSource.getText(), Integer.parseInt(longSource.getText()), Integer.parseInt(latSource.getText()), cityDestination.getText(), placeDestination.getText(), Integer.parseInt(longDestination.getText()), Integer.parseInt(latDestination.getText()), date1.toString(), date1.toString(), handicapCheck, animalChek, compagnieCheck, BaggageCheck, Float.parseFloat(poids.getText()), MusiqueCheck, genreMusique.getText(), FumeurCheck, AccepterFumeurcheck, 0, 0, 5l);
                System.out.println(citySource.getText());
                System.out.println(placeSource.getText());
                System.out.println(cityDestination.getText());
                RidePassengerService pc = new RidePassengerService();
                pc.insertRidePassenger(a);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Ajout d'un trajet");
                alert.setHeaderText(null);
                alert.setContentText("Un trajet pour votre animale a été ajouté, Veuillez Continuer vers l'ajout d'Animale");
                alert.showAndWait();
                /*
                citySource.clear();
                placeSource.clear();
                longSource.setText("0");
                latSource.setText("0");
                cityDestination.clear();
                placeDestination.clear();
                longDestination.setText("0");
                latDestination.setText("0");
                handicap.setSelected(false);
                Animale.setSelected(false);
                compagnie.setSelected(false);
                Baggage.setSelected(false);
                poids.setText("0.0");
                poids.setVisible(false);
                poidsLabelle.setVisible(false);
                Musique.setSelected(false);
                genreMusique.clear();
                genreMusique.setVisible(false);
                genreMusiqueLabelle.setVisible(false);
                fumeur.setSelected(false);
                accepterFumeur.setSelected(false);
                listedestrajets.getItems().clear(); */
                AfficherListeTrajetAction();
            } else if (citySource.getText().equals("") && placeSource.getText().equals("") && cityDestination.getText().equals("") && placeDestination.getText().equals("")) {
                System.out.println("Erreur");
                Alert alertW = new Alert(AlertType.WARNING);
                alertW.setTitle("Ajout d'un trajet");
                alertW.setHeaderText(null);
                alertW.setContentText("Un ou des champs sont invalides");
                alertW.showAndWait();
            }
        } else if (onChangeModif == 1) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date1 = new Date();
            System.out.println(dateFormat.format(date1));
            int animalChek = 0;
            int compagnieCheck = 0;
            int BaggageCheck = 0;
            int handicapCheck = 0;
            int MusiqueCheck = 0;
            int FumeurCheck = 0;
            int AccepterFumeurcheck = 0;
            Float poids2U = 0.0f;
            if (Animale.isSelected()) {
                animalChek = 1;
            }
            if (compagnie.isSelected()) {
                compagnieCheck = 1;
            }
            if (Baggage.isSelected()) {
                BaggageCheck = 1;
            }
            if (handicap.isSelected()) {
                handicapCheck = 1;
            }
            if (Musique.isSelected()) {
                MusiqueCheck = 1;
            }
            if (fumeur.isSelected()) {
                FumeurCheck = 1;
            }
            if (accepterFumeur.isSelected()) {
                AccepterFumeurcheck = 1;
            }
            if (poids.getText() != "") {
                poids2U = Float.parseFloat(poids.getText());
            }
            if (!citySource.getText().equals("") && !placeSource.getText().equals("") && !cityDestination.getText().equals("") && !placeDestination.getText().equals("")) {
                RidePassenger a = new RidePassenger(citySource.getText(), placeSource.getText(), Integer.parseInt(longSource.getText()), Integer.parseInt(latSource.getText()), cityDestination.getText(), placeDestination.getText(), Integer.parseInt(longDestination.getText()), Integer.parseInt(latDestination.getText()), date1.toString(), date1.toString(), handicapCheck, animalChek, compagnieCheck, BaggageCheck, poids2U, MusiqueCheck, genreMusique.getText(), FumeurCheck, AccepterFumeurcheck, 0, 0, 5l);
                System.out.println(citySource.getText());
                System.out.println(placeSource.getText());
                System.out.println(cityDestination.getText());
                RidePassengerService pc = new RidePassengerService();
                pc.modifierPassager(a, id);
                citySource.clear();
                placeSource.clear();
                longSource.setText("0");
                latSource.setText("0");
                cityDestination.clear();
                placeDestination.clear();
                longDestination.setText("0");
                latDestination.setText("0");
                handicap.setSelected(false);
                Animale.setSelected(false);
                compagnie.setSelected(false);
                Baggage.setSelected(false);
                poids.setText("0.0");
                poids.setVisible(false);
                poidsLabelle.setVisible(false);
                Musique.setSelected(false);
                genreMusique.clear();
                genreMusique.setVisible(false);
                genreMusiqueLabelle.setVisible(false);
                fumeur.setSelected(false);
                accepterFumeur.setSelected(false);
                onChangeModif = 0;
                listedestrajets.getItems().clear();
                AfficherListeTrajetAction();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Modification d'un trajet");
                alert.setHeaderText(null);
                alert.setContentText("Un trajet a été modifié");
                alert.showAndWait();
            } else if (citySource.getText().equals("") && placeSource.getText().equals("") && cityDestination.getText().equals("") && placeDestination.getText().equals("")) {
                System.out.println("Erreur");
                Alert alertW = new Alert(AlertType.WARNING);
                alertW.setTitle("Ajout d'un trajet");
                alertW.setHeaderText(null);
                alertW.setContentText("Un ou des champs sont invalides");
                alertW.showAndWait();
            }
        }
    }

    private void AfficherListeTrajetAction() {
        RidePassengerService RideService = new RidePassengerService();
        Button modifier = new Button("Modifier");
        Button Supprimer = new Button("Supprimer");
        try {
            ResultSet list = RideService.afficherTrajet();
            while (list.next()) {
                data.add(new RidePassenger(list.getInt(1), list.getString(3), list.getString(4), 0, 0, list.getString(7), list.getString(8), 0, 0, list.getDate(11).toString(), list.getDate(12).toString(), 0, 0, 0, 0, 0, 0, list.getString(19), 0, 0, 0, 0, 0, modifier, Supprimer));
            }
            listedestrajets.setItems(data);

        } catch (SQLException ex) {
            Logger.getLogger(RidePassengerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GoogleMaps() {
        /*WebEngine webEngine = browser.getEngine();
            String Source = citySource.getText();
            String Destination = cityDestination.getText() ;
            String url = "ttps://www.google.tn/maps/dir/";
            for (int i = 0; i < Source.length(); i++) {
                if (Source.charAt(i) != ' ') {
                    url += Source.charAt(i);
                } else {
                    url += "+";
                }
                System.out.println(url);
            }
            for (int j = 0; j < Destination.length(); j++) {
                if (Destination.charAt(j) != ' ') {
                    url += Destination.charAt(j);
                } else {
                    url += "+";
                }
            }
            webEngine.load(url);*/
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        poidsLabelle.setVisible(false);
        poids.setVisible(false);
        genreMusiqueLabelle.setVisible(false);
        genreMusique.setVisible(false);
        TableColumn IDCol = new TableColumn("ID");
        poids.setText("0.0");
        longSource.setText("0");
        latSource.setText("0");
        longDestination.setText("0");
        latDestination.setText("0");
        gridoption.setVisible(false);
        WebEngine webEngine = browser.getEngine();
        webEngine.load("https://www.google.tn/maps/");
        //citySourceCol.setMinWidth(100);
        IDCol.setCellValueFactory(
                new PropertyValueFactory<RidePassenger, String>("id"));

        TableColumn citySourceCol = new TableColumn("Ville de départ");
        citySourceCol.setMinWidth(100);
        citySourceCol.setCellValueFactory(
                new PropertyValueFactory<RidePassenger, String>("citySource"));

        TableColumn placeSourceCol = new TableColumn("point d'embarquement");
        placeSourceCol.setMinWidth(100);
        placeSourceCol.setCellValueFactory(
                new PropertyValueFactory<RidePassenger, String>("placeSource"));

        TableColumn cityDestinationCol = new TableColumn("Ville de destination");
        cityDestinationCol.setMinWidth(100);
        cityDestinationCol.setCellValueFactory(
                new PropertyValueFactory<RidePassenger, String>("cityDestination"));

        TableColumn placeDestinationCol = new TableColumn("point d'embarquement");
        placeDestinationCol.setMinWidth(100);
        placeDestinationCol.setCellValueFactory(
                new PropertyValueFactory<RidePassenger, String>("placeDestination"));

        TableColumn Modif = new TableColumn("Modifier");
        Modif.setCellFactory(
                new Callback<TableColumn.CellDataFeatures<RidePassenger, Boolean>, ObservableValue<Boolean>>() {
                    
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<RidePassenger, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        Modif.setCellFactory(
                new Callback<TableColumn<RidePassenger, Boolean>, TableCell<RidePassenger, Boolean>>() {

            @Override
            public TableCell<RidePassenger, Boolean> call(TableColumn<RidePassenger, Boolean> p) {
                return new ButtonCellModif();
            }

        });

        TableColumn Supp = new TableColumn("Supprimer");
        Supp.setCellFactory(
                new Callback<TableColumn.CellDataFeatures<RidePassenger, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<RidePassenger, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        Supp.setCellFactory(
                new Callback<TableColumn<RidePassenger, Boolean>, TableCell<RidePassenger, Boolean>>() {

            @Override
            public TableCell<RidePassenger, Boolean> call(TableColumn<RidePassenger, Boolean> p) {
                return new ButtonCellSupp();
            }

        });

        listedestrajets.getColumns().addAll(IDCol, citySourceCol, placeSourceCol, cityDestinationCol, placeDestinationCol, Modif, Supp);

        AfficherListeTrajetAction();
        PieChart pieChart = new PieChart();
        try {
            buildData();
        } catch (SQLException ex) {
            Logger.getLogger(RidePassengerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.pieChart.getData().addAll(dataChart);
    }

    @Override
    public void elevationsReceived(ElevationResult[] ers, ElevationStatus es) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void geocodedResultsReceived(GeocodingResult[] grs, GeocoderStatus gs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class ButtonCellModif extends TableCell<RidePassenger, Boolean> {

        final Button cellButton = new Button("Modifier");

        ButtonCellModif() {

            cellButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    System.out.println("looool");
                    RidePassenger ride = listedestrajets.getSelectionModel().selectedItemProperty().getValue();
                    System.out.println(ride.getId());
                    idTrajetSupp.setText(Integer.toString(ride.getId()));
                    tableau.getSelectionModel().select(AjoutModif);
                    RidePassengerService RideService = new RidePassengerService();
                    try {
                        System.out.println("xD");
                        id = ride.getId();
                        ResultSet list = RideService.afficherUnTrajet(ride.getId());
                        while (list.next()) {
                            System.out.println("looool");
                            System.out.println(list.getString(3));
                            citySource.setText(list.getString(3));
                            placeSource.setText(list.getString(4));
                            longSource.setText(list.getString(5));
                            latSource.setText(list.getString(6));
                            cityDestination.setText(list.getString(7));
                            placeDestination.setText(list.getString(8));
                            longDestination.setText(list.getString(9));
                            latDestination.setText(list.getString(10));
                            if (list.getInt(13) == 1) {
                                handicap.setSelected(true);
                            } else {
                                handicap.setSelected(false);
                            }
                            if (list.getInt(14) == 1) {
                                Animale.setSelected(true);
                            } else {
                                Animale.setSelected(false);
                            }
                            if (list.getInt(15) == 1) {
                                compagnie.setSelected(true);
                            } else {
                                compagnie.setSelected(false);
                            }
                            if (list.getInt(16) == 1) {
                                Animale.setSelected(true);
                                poidsLabelle.setVisible(true);
                                poids.setVisible(true);
                                poids.setText(Integer.toString(list.getInt(17)));
                            } else {
                                Animale.setSelected(false);
                                poidsLabelle.setVisible(false);
                                poids.setVisible(false);
                            }
                            if (list.getInt(18) == 1) {
                                Musique.setSelected(true);
                                genreMusiqueLabelle.setVisible(true);
                                genreMusique.setVisible(true);
                                genreMusique.setText(list.getString(19));
                            } else {
                                Musique.setSelected(false);
                            }
                            if (list.getInt(20) == 1) {
                                fumeur.setSelected(true);
                            } else {
                                fumeur.setSelected(false);
                            }
                            if (list.getInt(21) == 1) {
                                accepterFumeur.setSelected(true);
                            } else {
                                accepterFumeur.setSelected(false);
                            }
                            onChangeModif = 1;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(RidePassengerController.class.getName()).log(Level.SEVERE, null, ex);
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

    class ButtonCellSupp extends TableCell<RidePassenger, Boolean> {

        final Button cellButton = new Button("Supprimer");

        ButtonCellSupp() {

            cellButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    // do something when button clicked
                    //...
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

    @FXML
    private void OnSelectedItem() {
        RidePassenger ride = listedestrajets.getSelectionModel().selectedItemProperty().getValue();
        //RidePassenger ride = listedestrajets.getSelectionModel().getSelectedItem();
        System.out.println(ride.getId());
        idTrajetSupp.setText(Integer.toString(ride.getId()));
    }

    @FXML
    private void SupprimerTrajet() {
        RidePassengerService Service = new RidePassengerService();
        Service.supprimerPassager(Integer.parseInt(idTrajetSupp.getText()));
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Suppression d'un trajet");
        alert.setHeaderText(null);
        alert.setContentText("Un trajet a été supprimé");
        listedestrajets.getItems().clear();
        AfficherListeTrajetAction();
    }

    @FXML
    private void AffichageInstantanne() {
        listedestrajets.getItems().clear();
        RidePassengerService RideService = new RidePassengerService();
        Button modifier = new Button("Modifier");
        Button Supprimer = new Button("Supprimer");
        try {
            ResultSet list = RideService.rechercherparville(recherche.getText());
            while (list.next()) {
                System.out.println(list.getString(3));
                data.add(new RidePassenger(list.getInt(1), list.getString(3), list.getString(4), 0, 0, list.getString(7), list.getString(8), 0, 0, list.getDate(11).toString(), list.getDate(12).toString(), 0, 0, 0, 0, 0, 0, list.getString(19), 0, 0, 0, 0, 0, modifier, Supprimer));
            }
            listedestrajets.setItems(data);

        } catch (SQLException ex) {
            Logger.getLogger(RidePassengerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void BaggageSelected() {
        if (Baggage.isSelected()) {
            poidsLabelle.setVisible(true);
            poids.setVisible(true);
        } else {
            poidsLabelle.setVisible(false);
            poids.setVisible(false);
        }
    }

    @FXML
    private void GenreMusiqueSelected() {
        if (Musique.isSelected()) {
            genreMusiqueLabelle.setVisible(true);
            genreMusique.setVisible(true);
        } else {
            genreMusiqueLabelle.setVisible(false);
            genreMusique.setVisible(false);
        }
    }

    private void buildData() throws SQLException {
        dataChart = FXCollections.observableArrayList();
        RidePassengerService ride = new RidePassengerService();
        ResultSet list = ride.chartSpecies();
        while (list.next()) {
            dataChart.add(new PieChart.Data(list.getString(2), list.getInt(1)));
        }
    }

    // GOOGLE MAPS STARTS HERE // 
    @FXML
    private void iteneraire() {
        final Stage stage = new Stage();
        mapComponent = new GoogleMapView();
        mapComponent.addMapInializedListener(this);
        mapComponent.setDisableDoubleClick(true);
        mapComponent.getWebview().getEngine().setOnAlert((WebEvent<String> event) -> {
            //   System.out.println("Event event: " + event);
        });
        BorderPane bp = new BorderPane();
        bp.setCenter(mapComponent);

        Scene scene = new Scene(bp);
        stage.setScene(scene);
        stage.show();
    }

    public void mapInitialized() {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("Calling showDirections from Java");
                Platform.runLater(() -> mapComponent.getMap().hideDirectionsPane());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        t.start();
        //Once the map has been loaded by the Webview, initialize the map details.
        LatLong center = new LatLong(47.606189, -122.335842);
        mapComponent.addMapReadyListener(() -> {
            // This call will fail unless the map is completely ready.
            //checkCenter(center);
        });

        MapOptions options = new MapOptions();
        options.center(center)
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(9);

        map = mapComponent.createMap(options);

        map.setHeading(123.2);
        System.out.println("1");
        //GoogleMap map = mapComponent.createMap(options);
        directionsService = new DirectionsService();
        System.out.println("2");
        directionsPane = mapComponent.getDirec();
        directionsService = new DirectionsService();
        directionsPane = mapComponent.getDirec();
        DirectionsRequest request = new DirectionsRequest(citySource.getText(), cityDestination.getText(), TravelModes.DRIVING);

        directionsService.getRoute(request, this, new DirectionsRenderer(true, mapComponent.getMap(), directionsPane));
//        System.out.println("Heading is: " + map.getHeading() );

        //map.showDirectionPane();
    }

}
