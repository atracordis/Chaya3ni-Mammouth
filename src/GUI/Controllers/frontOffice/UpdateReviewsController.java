/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.frontOffice;

import Entities.Reviews;
import static GUI.Controllers.frontOffice.AfficherReviewsController.x;
import Services.ServiceReviews;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.xml.ws.Service;

/**
 * FXML Controller class
 *
 * @author SALMA
 */
public class UpdateReviewsController implements Initializable {

    @FXML
    private AnchorPane Modifzone;
    @FXML
    private Button boutonmodifier;
    
    @FXML
    private TextField txtsujet2;

    @FXML
    private TextArea txtcontenu2;
    
    @FXML
    TextField txtrating;
    @FXML
    private Button Ex;

    public static int y;
    @FXML
    private AnchorPane affich;
    @FXML
    private AnchorPane zoneupdate;
    @FXML
    private AnchorPane zonesup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ServiceReviews srd = new ServiceReviews();
        Reviews r = srd.RecherchebyID(y);
        txtrating.setText("" + r.getRating());
        txtcontenu2.setText(r.getContent());
        txtsujet2.setText(r.getTitle());

    }

    @FXML
    private void update(ActionEvent event) {

        ServiceReviews re = new ServiceReviews();
         
        Reviews review = new Reviews(y,(Integer.parseInt(txtrating.getText())), txtsujet2.getText(), txtcontenu2.getText());
        System.out.println(y);
        re.ModifierReviews(review);
          AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/Interfaces/AffichReview.fxml"));
             Modifzone.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(UpdateReviewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    

    }

    @FXML
    private void exbu(ActionEvent event) throws IOException {
//        // get a handle to the stage
//        Stage stage = (Stage) Ex.getScene().getWindow();
//        // do what you have to do
//        stage.close();//        stage.close();

            Parent blah = FXMLLoader.load(getClass().getClassLoader().getResource("/GUI/Interfaces/AffichReview.fxml"));
            Scene scene = new Scene(blah);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
    }
}
