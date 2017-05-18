/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.frontOffice;

import Entities.Reviews;
import static GUI.Controllers.frontOffice.UpdateReviewsController.y;
import GUI.Interfaces.Main;
import Services.ServiceReviews;
import Services.UserService;
import Tools.Conn;
import com.jfoenix.controls.JFXButton;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import static java.util.Collections.list;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author SALMA
 */
public class AfficherReviewsController implements Initializable {

    private static Object getChildren() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
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
    private JFXButton annuler;
    @FXML
    private Button edit;
    @FXML
    private Button DELETE;
    @FXML
    private AnchorPane affich;
    @FXML
    private Button add;
    @FXML
    private TableColumn<Reviews, String> name;
    @FXML
    private Hyperlink Logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
UserService u = new UserService(); 
u.selectUser();
        ServiceReviews Re = new ServiceReviews();
        Re.Display();
        
        listRe = FXCollections.observableArrayList(Re.Display());
       // System.out.println(listRe.get(0).ge);
       
      //   name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        //id.setCellValueFactory(new PropertyValueFactory<>("id"));
       // user.setCellValueFactory(new PropertyValueFactory<>("idUser"));
       // booking.setCellValueFactory(new PropertyValueFactory<>("idBooking"));
       
        name.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ra.setCellValueFactory(new PropertyValueFactory<>("rating"));
        ti.setCellValueFactory(new PropertyValueFactory<>("title"));
        con.setCellValueFactory(new PropertyValueFactory<>("content"));
        date.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        
  
        table.setItems(listRe);

    }

    @FXML
    public void processLogout(ActionEvent event) throws IOException {
     
        Main.getApp().gotoSpecialMenu();

    }

    // TODO
    @FXML
    private void undo(ActionEvent event) throws IOException {
        // get a handle to the stage
     //   Stage stage = (Stage) annuler.getScene().getWindow();
        // do what you have to do
     //   stage.close();
    }

    @FXML
    private void editbu(ActionEvent event) throws IOException {
    
        ServiceReviews srd = new ServiceReviews();
           y=table.getSelectionModel().getSelectedItem().getId();
           int x=table.getSelectionModel().getSelectedIndex();
       // y = table.getSelectionModel().getSelectedItem().getId();
        System.out.println(y);
            Parent blah = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/Interfaces/UpdateReviews.fxml"));
            Scene scene = new Scene(blah);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
    }
    public static AnchorPane rootP;

    @FXML
    private void delbu(ActionEvent event) {
        if (table.getSelectionModel().getSelectedIndices().size() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation");
            alert.setContentText("Confirme DELETE ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                Conn cnx = Conn.getInstance();
                cnx.getConnection();
                ServiceReviews srd = new ServiceReviews();
                x = table.getSelectionModel().getSelectedItem().getId();
                srd.DeleteReviews(table.getSelectionModel().getSelectedItem());
                srd.AfficherReviews();
                listRe = FXCollections.observableArrayList(srd.AfficherReviews());
              //  id.setCellValueFactory(new PropertyValueFactory<>("id"));
              //  user.setCellValueFactory(new PropertyValueFactory<>("idUser"));
              //  booking.setCellValueFactory(new PropertyValueFactory<>("idBooking"));
                ra.setCellValueFactory(new PropertyValueFactory<>("rating"));
                ti.setCellValueFactory(new PropertyValueFactory<>("title"));
                con.setCellValueFactory(new PropertyValueFactory<>("content"));
                date.setCellValueFactory(new PropertyValueFactory<>("dateTime"));

                table.setItems(listRe);
            }

        }
    }

    @FXML
    private void addbu(ActionEvent event) throws IOException {
//       ReviewsController  uec  =new  ReviewsController();
//        
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("../GUI/Review.fxml"));
//       ReviewsController reviewsController = fxmlLoader.getController();

            Parent blah = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/Interfaces/Review.fxml"));
            Scene scene = new Scene(blah);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
    }

 
}
