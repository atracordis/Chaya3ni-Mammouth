/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.frontOffice;

import Entities.Reviews;
import Entities.User;
import Entities.sendMailComfirmation;
import Services.ServiceReviews;
import Tools.Conn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop.Action;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javax.mail.MessagingException;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import static tray.notification.NotificationType.ERROR;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author SALMA
 */
public class ReviewsController implements Initializable {

    @FXML
    private JFXTextField ra;
    @FXML
    private JFXTextField ti;
    @FXML
    private JFXTextArea con;
    @FXML
    private DatePicker date;
    @FXML
    private Button re;

    /**
     *
     *
     * Initializes the controller class.
     */
    private int rating;
    private String title;
    private String content;
    private int idBooking;
    private int idUser;
    private Date dateTime;
    private int id;
    @FXML
    private JFXButton annuler;
    ObservableList<User> listUsers;
    ServiceReviews RE = new ServiceReviews();
    @FXML
    private ComboBox<String> names;
    @FXML
    private AnchorPane affich;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            listUsers = (ObservableList<User>) RE.DisplayCommun();
            // ObservableList obsListDev = FXCollections.observableArrayList(RE.DisplayCommun());
            ObservableList lsname = FXCollections.observableArrayList();
            for (User user : listUsers) {
                lsname.add(user.getName());
                //Controle de saisie !!!
                ra.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        if (!newValue.matches("\\d*")) {
                            ra.setText(newValue.replaceAll("[^\\d]", ""));
                        }

                    }

                });
                ti.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        if (!newValue.matches("[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]")) {
                            ti.setText(newValue.replaceAll("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]", ""));
                        }
                    }
                });
              
                
            }
            names.setItems(lsname);
        } catch (SQLException ex) {
            Logger.getLogger(ReviewsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void reviw(ActionEvent event) throws IOException, MessagingException {
        
        
            Conn cnx = Conn.getInstance();
            cnx.getConnection();
            for (User listUser : listUsers) {
                if (names.getSelectionModel().getSelectedItem().equals(listUser.getName())) {
                    id = Integer.parseInt(listUser.getId());

                }

            }
            idUser = (Integer.parseInt("2"));

            rating = (Integer.parseInt(ra.getText()));
            idBooking = (Integer.parseInt("2"));

            title = ti.getText();
            content = con.getText();
            dateTime = java.sql.Date.valueOf(date.getValue());
             
            ServiceReviews srd = new ServiceReviews();
            Reviews dr = new Reviews(rating, idBooking, idUser, title, content, dateTime, id);
            srd.AjouterReviews(dr);
            //   sendMailComfirmation s = new sendMailComfirmation("maroua.boubaker@esprit.tn", "success");
           TrayNotification tray = new TrayNotification("SUCCES", "your Review was saved   ",SUCCESS);
        tray.showAndWait();
             }
        

        @FXML
        private void undo
        (ActionEvent event) throws IOException {
//        // get a handle to the stage
//        Stage stage =(Stage)annuler.getScene().getWindow();
//        // do what you have to do
//        stage.close();
            Parent blah = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/Interfaces/AffichReview.fxml"));
            Scene scene = new Scene(blah);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        }

    }
