/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.frontOffice;

import Entities.RideDriver;
import Entities.RideEmployee;
import Services.ServiceRideDriver;
import Services.ServiceRideEmployee;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Deathscythvi
 */
public class AfficherMestrajetEmployeeController implements Initializable {

    public static int y;
    @FXML
    private TableView<RideEmployee> tabletrajet;
    @FXML
    private TableColumn<RideEmployee, Integer> idcolumn;
    @FXML
    private TableColumn<RideEmployee, Double> pricecolumn;
    @FXML
    private TableColumn<RideEmployee, String> citysourcecolumn;
    @FXML
    private TableColumn<RideEmployee, String> placesourcecolumn;
    @FXML
    private TableColumn<RideEmployee, String> citydestinationcolumn;
    @FXML
    private TableColumn<RideEmployee, String> placedestinationcolumn;
    @FXML
    private TableColumn<RideEmployee, Date> datetimesourcecolumn;
    @FXML
    private Button modifiertrajet;
    @FXML
    private Button delete;
    @FXML
    private JFXDrawer drawer;
     private ObservableList<RideEmployee> list;
    @FXML
    private AnchorPane affichetrajet;
    @FXML
    private JFXHamburger hamburger;

    /**
     * Initializes the controller class.
     */
     public static AnchorPane rootP;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         rootP = affichetrajet;
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/GUI/Interfaces/Sidemenu.fxml"));
            drawer.setSidePane(box);

            for (Node node : box.getChildren()) {
                if (node.getId() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

                       if(node.getId().equals("ajoutertrajet")) {
                            
                                try {
                                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Interfaces/MapajoutRiderDriverEmployee.fxml"));
                                    affichetrajet.getChildren().setAll(pane);
                                } catch (IOException ex) {
                                    Logger.getLogger(AjouteRideDriverController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                        }else   if(node.getId().equals("affichetrajet")) {
                            
                                try {
                                    AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Interfaces/AfficherMestrajetEmployee.fxml"));
                                    affichetrajet.getChildren().setAll(pane);
                                } catch (IOException ex) {
                                    Logger.getLogger(AjouteRideDriverController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            
                           
                              
                        }

                        //ap.setBackground(new Background(new BackgroundFill(Paint.valueOf("#fc0000"),CornerRadii.EMPTY,Insets.EMPTY)));
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
        ServiceRideEmployee srd = new ServiceRideEmployee();
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

    

    @FXML
    private void modifiertrajet(ActionEvent event) throws IOException {
        ServiceRideDriver srd = new ServiceRideDriver();
            y=tabletrajet.getSelectionModel().getSelectedItem().getId();
            System.out.println(y);
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Interfaces/MapmodiferRideDriverEmployee.fxml"));
            affichetrajet.getChildren().setAll(pane);       
    }
    

    @FXML
    private void delete(ActionEvent event) {
        rootP = affichetrajet;

        if (tabletrajet.getSelectionModel().getSelectedIndices().size() > 0) {

            Conn cnx = Conn.getInstance();
            cnx.getConnection();
            ServiceRideEmployee srd = new ServiceRideEmployee();
            y=tabletrajet.getSelectionModel().getSelectedItem().getId();
            srd.DeleteRideEmployee(tabletrajet.getSelectionModel().getSelectedItem());
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
}

