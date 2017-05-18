/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.frontOffice;

import Entities.RideDriver;
import GUI.Interfaces.Main;
import Services.ServiceRideDriver;
import Tools.Conn;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Deathscythvi
 */
public class AfficherMestrajetController implements Initializable {

    /**
     * Initializes the controller class.
     */
    static int x;
    public static AnchorPane rootP;
    @FXML
    private AnchorPane affichetrajet;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private TableView<RideDriver> tabletrajet;
    @FXML
    private TableColumn<RideDriver, Double> pricecolumn;
    @FXML
    private TableColumn<RideDriver, String> citysourcecolumn;
    @FXML
    private TableColumn<RideDriver, String> placesourcecolumn;
    @FXML
    private TableColumn<RideDriver, String> citydestinationcolumn;
    @FXML
    private TableColumn<RideDriver, String> placedestinationcolumn;
    private ObservableList<RideDriver> list;
    @FXML
    private TableColumn<RideDriver, Integer> idcolumn;
    @FXML
    private Button details;
    @FXML
    private TableColumn<RideDriver, Date> datetimesourcecolumn;
    @FXML
    private Button modifiertrajet;
    @FXML
    private TextField textrecherche;
    private Main application;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ServiceRideDriver srd = new ServiceRideDriver();
        Conn cnx = Conn.getInstance();
        cnx.getConnection();
        srd.RechercherMesDrivernotall();
        list = FXCollections.observableArrayList(srd.RechercherMesDrivernotall());
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        pricecolumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        citysourcecolumn.setCellValueFactory(new PropertyValueFactory<>("citySource"));
        placesourcecolumn.setCellValueFactory(new PropertyValueFactory<>("placeSource"));
        citydestinationcolumn.setCellValueFactory(new PropertyValueFactory<>("cityDestination"));
        placedestinationcolumn.setCellValueFactory(new PropertyValueFactory<>("placeDestination"));
        datetimesourcecolumn.setCellValueFactory(new PropertyValueFactory<>("dateTimeSource"));
        tabletrajet.setItems(list);
        rootP = affichetrajet;
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/GUI/Interfaces/Sidemenu.fxml"));
            drawer.setSidePane(box);

            for (Node node : box.getChildren()) {
                if (node.getId() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

                        switch (node.getId()) {
                            case "ajoutertrajet": {
                                try {
                                    
            Parent blah = FXMLLoader.load(getClass().getClassLoader().getResource("/GUI/Interfaces/MapajoutRideDriver.fxml"));
            Scene scene = new Scene(blah);
            Stage appStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
                                    
                                    
                                } catch (IOException ex) {
                                    Logger.getLogger(AjouteRideDriverController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                            break;
                        }
                    });
                }
            }
            HamburgerBackArrowBasicTransition burgerTask2 = new HamburgerBackArrowBasicTransition(hamburger);
            burgerTask2.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                burgerTask2.setRate(burgerTask2.getRate() * -1);
                burgerTask2.play();
                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }

            });
        } catch (IOException ex) {
            Logger.getLogger(AjouteRideDriverController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML

    private void details(ActionEvent event) throws IOException {
        rootP = affichetrajet;

        if (tabletrajet.getSelectionModel().getSelectedIndices().size() > 0) {

            ServiceRideDriver srd = new ServiceRideDriver();
            x = tabletrajet.getSelectionModel().getSelectedItem().getId();
            srd.DeleteRideDriver(tabletrajet.getSelectionModel().getSelectedItem());
            srd.RechercherMesDrivernotall();
            list = FXCollections.observableArrayList(srd.RechercherMesDrivernotall());
            idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            pricecolumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            citysourcecolumn.setCellValueFactory(new PropertyValueFactory<>("citySource"));
            placesourcecolumn.setCellValueFactory(new PropertyValueFactory<>("placeSource"));
            citydestinationcolumn.setCellValueFactory(new PropertyValueFactory<>("cityDestination"));
            placedestinationcolumn.setCellValueFactory(new PropertyValueFactory<>("placeDestination"));
            datetimesourcecolumn.setCellValueFactory(new PropertyValueFactory<>("dateTimeSource"));
            tabletrajet.setItems(list);

        }

    }

    @FXML
    private void modifiertrajet(ActionEvent event) throws IOException {

        ServiceRideDriver srd = new ServiceRideDriver();
        x = tabletrajet.getSelectionModel().getSelectedItem().getId();
        System.out.println(x);
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Interfaces/MapmodifierRideDriver.fxml"));
        affichetrajet.getChildren().setAll(pane);

    }

    @FXML
    private void recherche(ActionEvent event) {

        ServiceRideDriver srd = new ServiceRideDriver();
        list = FXCollections.observableArrayList(srd.RechercherMesDriverauto(textrecherche.getText()));
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        pricecolumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        citysourcecolumn.setCellValueFactory(new PropertyValueFactory<>("citySource"));
        placesourcecolumn.setCellValueFactory(new PropertyValueFactory<>("placeSource"));
        citydestinationcolumn.setCellValueFactory(new PropertyValueFactory<>("cityDestination"));
        placedestinationcolumn.setCellValueFactory(new PropertyValueFactory<>("placeDestination"));
        datetimesourcecolumn.setCellValueFactory(new PropertyValueFactory<>("dateTimeSource"));
        tabletrajet.setItems(list);
        tabletrajet.refresh();

    }

    @FXML
    private void recherche(KeyEvent event) {

        ServiceRideDriver srd = new ServiceRideDriver();
        list = FXCollections.observableArrayList(srd.RechercherMesDriverauto(textrecherche.getText()));
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        pricecolumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        citysourcecolumn.setCellValueFactory(new PropertyValueFactory<>("citySource"));
        placesourcecolumn.setCellValueFactory(new PropertyValueFactory<>("placeSource"));
        citydestinationcolumn.setCellValueFactory(new PropertyValueFactory<>("cityDestination"));
        placedestinationcolumn.setCellValueFactory(new PropertyValueFactory<>("placeDestination"));
        datetimesourcecolumn.setCellValueFactory(new PropertyValueFactory<>("dateTimeSource"));
        tabletrajet.setItems(list);
        tabletrajet.refresh();
    }

}
