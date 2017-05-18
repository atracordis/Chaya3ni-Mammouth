/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.backOffice;

import Entities.Complaints;
import Entities.sendMailComfirmation;
import static GUI.Controllers.frontOffice.AfficherReviewsController.x;
import GUI.Interfaces.Main;
import Services.ServiceComplaints;
import Services.ServiceReviews;
import Tools.Conn;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author SALMA
 */
public class AffichComplaintsController implements Initializable {

    @FXML
    private TableView<Complaints> view;
    @FXML
    private TableColumn<Complaints , String> con;
    @FXML
    private TableColumn<Complaints , String> ty;
    @FXML
    private TableColumn<Complaints , Date> date;
    @FXML
    private TableColumn<Complaints , String> atta;
    @FXML
    private TableColumn<Complaints, String> sta;
ObservableList<Complaints>listRe;
    @FXML
    private Button complaint;
    public static int x ; 
    @FXML
    private AnchorPane affich;
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
    private void viewComplaint(ActionEvent event) throws IOException, MessagingException {
       if (view.getSelectionModel().getSelectedIndices().size() > 0) {

            Conn cnx = Conn.getInstance();
            cnx.getConnection();
          ServiceComplaints srd= new ServiceComplaints();
       
            x=view.getSelectionModel().getSelectedItem().getId();
           int y=view.getSelectionModel().getSelectedIndex();
           
            System.out.println(y);
            Complaints C= new Complaints(x);
  
            C.setStatus("Verifier ");
    
           srd.ModifierComplaints(C);
            System.out.println(C.getStatus());
             //System.out.println(C.getId());
              System.out.println(x);
             
       
          

        view.setItems(listRe);
         
     
            Parent blah = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/Interfaces/AffichComplaints.fxml"));
            Scene scene = new Scene(blah);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
     sendMailComfirmation s = new sendMailComfirmation("rihab.chermiti@esprit.tn", "Dear Sir "
             + "your Complaint was treated , and put into consideration"
             + "Best Regards "
             + "Chaya3ni Team ");
            
    }
          
    }

    @FXML
    public void processLogout(ActionEvent event) throws IOException {
     
        Main.getApp().gotoTheOtherSpecialMenu();

    }
    
    
}
