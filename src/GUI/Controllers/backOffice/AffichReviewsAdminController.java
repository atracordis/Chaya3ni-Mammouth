/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.backOffice;

import Entities.Complaints;
import Entities.Reviews;
import static GUI.Controllers.frontOffice.AfficherReviewsController.x;
import GUI.Interfaces.Main;
import Services.ServiceReviews;
import Tools.Conn;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SALMA
 */
public class AffichReviewsAdminController implements Initializable {

    @FXML
    private TableColumn<Reviews, Integer> id;
    @FXML
    private TableColumn<Reviews, Integer> user;
    @FXML
    private TableColumn<Reviews, Integer> booking;
    @FXML
    private TableColumn<Reviews, Integer> ra;
    @FXML
    private TableColumn<Reviews, String> ti;
    @FXML
    private TableColumn<Reviews, String> con;
    @FXML
    private TableColumn<Reviews, Date> date;
    @FXML
    private TableView<Reviews> table;

    public static int x;
 ObservableList<Reviews> listRe;
    @FXML
    private Button delete;
    @FXML
    private Button exit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ServiceReviews Re = new ServiceReviews();
        Re.AfficherReviews();
        listRe = FXCollections.observableArrayList(Re.AfficherReviews());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        user.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        booking.setCellValueFactory(new PropertyValueFactory<>("idBooking"));
        ra.setCellValueFactory(new PropertyValueFactory<>("rating"));
        ti.setCellValueFactory(new PropertyValueFactory<>("title"));
        con.setCellValueFactory(new PropertyValueFactory<>("content"));
        date.setCellValueFactory(new PropertyValueFactory<>("dateTime"));

        table.setItems(listRe);

    }
        // TODO
      
    @FXML
    public void processLogout(ActionEvent event) throws IOException {
     
        Main.getApp().gotoTheOtherSpecialMenu();

    }

    @FXML
    private void deletebu(ActionEvent event) {
          if (table.getSelectionModel().getSelectedIndices().size() > 0) {
           

                Conn cnx = Conn.getInstance();
                cnx.getConnection();
                ServiceReviews srd = new ServiceReviews();
                x = table.getSelectionModel().getSelectedItem().getId();
                srd.DeleteReviews(table.getSelectionModel().getSelectedItem());
                srd.AfficherReviews();
                listRe = FXCollections.observableArrayList(srd.AfficherReviews());
                id.setCellValueFactory(new PropertyValueFactory<>("id"));
                user.setCellValueFactory(new PropertyValueFactory<>("idUser"));
                booking.setCellValueFactory(new PropertyValueFactory<>("idBooking"));
                ra.setCellValueFactory(new PropertyValueFactory<>("rating"));
                ti.setCellValueFactory(new PropertyValueFactory<>("title"));
                con.setCellValueFactory(new PropertyValueFactory<>("content"));
                date.setCellValueFactory(new PropertyValueFactory<>("dateTime"));

                table.setItems(listRe);
    }
          }

    @FXML
    private void exitbu(ActionEvent event) {
           // get a handle to the stage
        Stage stage = (Stage) exit.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
    
    

