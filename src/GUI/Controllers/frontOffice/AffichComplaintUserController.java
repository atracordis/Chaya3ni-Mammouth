/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.frontOffice;

import Entities.Complaints;
import GUI.Interfaces.Main;
import Services.ServiceComplaints;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SALMA
 */
public class AffichComplaintUserController implements Initializable {

    @FXML
    private AnchorPane affich;
    @FXML
    private TableView<Complaints> view;
    @FXML
    private TableColumn<Complaints, String> con;
    @FXML
    private TableColumn<Complaints, String> ty;
    @FXML
    private TableColumn<Complaints, Date> date;
    @FXML
    private TableColumn<Complaints, String> atta;
    @FXML
    private TableColumn<Complaints, String> sta;
    @FXML
    private Button complaint;
ObservableList<Complaints>listRe;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
          ServiceComplaints Re= new ServiceComplaints();
         Re.AfficherComplaints(); 
         listRe=FXCollections.observableArrayList(Re.AfficherComplaints());
         
         
             //id.setCellValueFactory(new PropertyValueFactory<>("id"));
        con.setCellValueFactory(new PropertyValueFactory<>("Content"));
        ty.setCellValueFactory(new PropertyValueFactory<>("type"));
         date.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        atta.setCellValueFactory(new PropertyValueFactory<>("attachement"));
         sta.setCellValueFactory(new PropertyValueFactory<>("Status"));
        

        view.setItems(listRe);
        // TODO
       
    }    

    @FXML
    public void processLogout(ActionEvent event) throws IOException {
     
        Main.getApp().gotoSpecialMenu();

    }
    @FXML
    private void sendCom(ActionEvent event) throws IOException {
            Parent blah = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/Interfaces/AjoutComplains.fxml"));
            Scene scene = new Scene(blah);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
    }
    
}
