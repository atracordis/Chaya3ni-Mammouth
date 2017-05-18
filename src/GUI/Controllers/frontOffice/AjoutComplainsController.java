/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.frontOffice;

import Entities.Complaints;
import Entities.Reviews;
import Entities.User;

import Services.ServiceComplaints;
import Services.ServiceReviews;
import Tools.Conn;
import Tools.OtherTools;
import com.jfoenix.controls.JFXTextField;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import static tray.notification.NotificationType.ERROR;
//import static jdk.nashorn.tools.Shell.SUCCESS;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author SALMA
 */
public class AjoutComplainsController implements Initializable {

    @FXML
    private Label rec;
    @FXML
    private TextField type;
    @FXML
    private TextArea contenu;
    @FXML
    private DatePicker date;
    @FXML
    private Button send;
    @FXML
    private Button add;
    private String reclamation;
    private Date dte;
    private String sujet;
    private String typ;
    private int user;
    @FXML
    private ImageView imgVw;
    private Image image;
    @FXML
    private JFXTextField fileName1;
    private String path ; 
    @FXML
    private Button exit;
    @FXML
    private AnchorPane affich;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML

    private void sendbu(ActionEvent event) {
        
        
      
        Conn cnx = Conn.getInstance();
        cnx.getConnection();

        dte = java.sql.Date.valueOf(date.getValue());
        user = (Integer.parseInt("2"));

        sujet = contenu.getText();
        typ = type.getText();
        path =fileName1.getText(); 
         if(typ.equals("")||sujet.equals("")){
              TrayNotification tray = new TrayNotification("ERROR", "you must write a title and a subject",ERROR);
        tray.showAndWait(); } else {
        ServiceComplaints srd = new ServiceComplaints();
        Complaints dr = new Complaints(user,sujet, typ, dte, path);
        srd.AjouterComplaints(dr);
//        System.out.println(dr);
//          System.out.println(user);
 
TrayNotification tray = new TrayNotification("SUCCES", "your Complaint was sent with success ",SUCCESS);
        tray.showAndWait();
        
    }}
    private FileChooser fileChooser;

    private File file;

    @FXML
    private void addbu(ActionEvent event) {

ServiceReviews re = new ServiceReviews(); 
        FileChooser fileChooser = new FileChooser();
       
        FileChooser.ExtensionFilter exjpg = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter expng = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(exjpg, expng);

        File file = fileChooser.showOpenDialog(null);
        try {
             String img; 
            img = (file.toURI().toString() );
            System.out.println(img);
             fileName1.setText(img);
            BufferedImage bfimg = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bfimg, null);
        } catch (Exception e) {
        }
        
        imgVw.setImage(image);
        imgVw.setImage(image);
        imgVw.setFitHeight(150);
        imgVw.setFitWidth(150);

    }

    @FXML
    private void exitbu(ActionEvent event) throws IOException {
            Parent blah = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/Interfaces/AffichComplaintUser.fxml"));
            Scene scene = new Scene(blah);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
    }
     
}
