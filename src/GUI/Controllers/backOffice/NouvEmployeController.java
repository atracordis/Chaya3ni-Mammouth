/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.backOffice;

import Entities.Employe;
import Entities.Preferences;
import static GUI.Controllers.backOffice.AjoutSocieteController.ptr;
import Services.ServEmploye;
import Tools.OtherTools;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import GUI.Interfaces.Main;
/**
 * FXML Controller class
 *
 * @author asus
 */
public class NouvEmployeController extends AnchorPane {
    private Main application;

    @FXML
    private AnchorPane ap2;
    @FXML
    private Insets x3;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private CheckBox newsletter;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField address1;
    @FXML
    private TextField address2;
    @FXML
    private TextField postalCode;
    @FXML
    private DatePicker dateBirth;
    @FXML
    private ComboBox<?> gender;
    @FXML
    private Label message;
    @FXML
    private ImageView imgVw;
    @FXML
    private Button upload;
    @FXML
    private Button endemploye;
    @FXML
    private Label photopath;
    @FXML
    private Label photoname;
    @FXML
    private Label success;
    @FXML
    private Button returnb;
    

    /**
     * Initializes the controller class.
     */
      public void setApp(Main application) 
    {
        this.application = application;
    }
    public void initialize() 
    {
        // TODO
    }    

    @FXML
    private void processUpload(ActionEvent event) 
    {
         FileChooser fileChooser = new FileChooser();
                     FileChooser.ExtensionFilter exjpg = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                     FileChooser.ExtensionFilter exjpg2 = new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg");
                     FileChooser.ExtensionFilter expng = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                     fileChooser.getExtensionFilters().addAll(exjpg,exjpg2, expng);
                     fileChooser.setTitle("Choose an image File");

                     File file = fileChooser.showOpenDialog(null);
                     
                             if (file != null) {
            if (file.length() < 6000000) {
                System.out.print("Condition ok");
                
                   Image img = new Image(file.toURI().toString() ); 
                                imgVw.setImage(img);
                                imgVw.setFitHeight(150);
                                imgVw.setFitWidth(150);
                                Employe.setRegPic(file);
                                photopath.setText("C:\\wamp64\\www\\employe_photos\\"+file.getName());
                                photoname.setText(file.getName());
            } 
            else 
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Permiss");
                alert.setHeaderText("Permission denied");
                alert.setContentText("Your Image file is too big to upload \nplease choise another image");
            }
 
    }
    }

    @FXML
    private void EndEmploye(ActionEvent event) throws IOException 
    {
        Boolean notEmpty=true;
        String problem="";
        if (name.getText().equals("")) 
        {
            notEmpty = false;
            problem="Adresse 1 cannot be empty";
            notEmpty = false;
            problem="Name cannot be empty";
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Information");
     
            alert.setHeaderText(problem);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            String exceptionText = sw.toString();

            
            Optional<ButtonType> result = alert.showAndWait();
        }
        if (surname.getText().equals("")) 
        {
            notEmpty = false;
            problem="Latitude cannot be empty";
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Information");
     
            alert.setHeaderText(problem);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            String exceptionText = sw.toString();

            
            Optional<ButtonType> result = alert.showAndWait();
     
        }
        if (address1.getText().equals("")) 
        {
            notEmpty = false;
            problem="Longitude cannot be empty";
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Information");
     
            alert.setHeaderText(problem);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            String exceptionText = sw.toString();

            
            Optional<ButtonType> result = alert.showAndWait();
        }
        if (postalCode.getText().length()!=4) 
        {
            notEmpty = false;
            problem="Code Postal cannot contains less or more than 4 numbers";
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Information");
     
            alert.setHeaderText(problem);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            String exceptionText = sw.toString();

            
            Optional<ButtonType> result = alert.showAndWait();
        }
        if (phone.getText().length()!=8) 
        {
            notEmpty = false;
            problem="Telephone cannot contains less or more than 4 numbers";
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Information");
     
            alert.setHeaderText(problem);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            String exceptionText = sw.toString();

            
            Optional<ButtonType> result = alert.showAndWait();
        }
        if (!(ptr.matcher(email.getText()).matches())) 
        {
             notEmpty = false;
            problem="Invalid E-mail";
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Information");
     
            alert.setHeaderText(problem);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            String exceptionText = sw.toString();

            
            Optional<ButtonType> result = alert.showAndWait();
          
        } 
        if ( Employe.getRegPic()== null) 
        {
            notEmpty = false;
            problem="The picture cannot be empty";
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Information");
     
            alert.setHeaderText(problem);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            String exceptionText = sw.toString();

            
            Optional<ButtonType> result = alert.showAndWait();
        }
        if (notEmpty != false)
        {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();

        String firstName = name.getText();
        String lastName = surname.getText();
        String sex = (String) gender.getValue();
        if (sex.equals("Male")) {
            sex = "homme";
        } else {
            sex = "femme";
        }
        String birthDate = dateBirth.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String mail = email.getText();
        
        String telephone = phone.getText();
        String pic = photoname.getText();
        
        String adress1 = address1.getText();
        String adress2 = address2.getText();
        String codePostal = postalCode.getText();
        String news;
        
        
        if (newsletter.isSelected()) {
            news = "1";
        } else {
            news = "0";
        }

        Preferences Pref = new Preferences("0", "allow", "deny", "deny",
                "0", "0", "allow", "folk",
                "deny", "deny", "deny", "deny",
                "Tres confortable", "454 TDI", "Volvo");
        String pseudo = firstName + "."+lastName;
        String pass = firstName + "."+lastName;
        String questionSecret = " ";
        String answerSecret = " ";
        int idF= AffichageEmployeController.idF;
        
        int idC= AffichageEmployeController.idC;
        
        Employe e = new Employe(idF,idC,firstName, lastName, sex, birthDate, mail,
                pseudo, pass, telephone, adress1, adress2,
                codePostal, pic, dtf.format(localDate), news, "0",
                "0", "0", questionSecret, answerSecret);
        e.setPreferences(Pref);

        
        System.out.println(e);
        

   
        
        Employe.setCurrentId(e.getId());
        Employe.setCurrentUsername(e.getUsername());
        Employe.setCurrentPassword(e.getPass());
        
        File destFile = new File ("C:\\wamp64\\www\\employe_photos\\"+Employe.getRegPic().getName());
        if (!destFile.createNewFile())
        {
            System.out.println ("non cree");
        } 
        else
        {
         OtherTools.copyFile(Employe.getRegPic(), destFile);
        destFile.renameTo(new File ("C:\\wamp64\\www\\employe_photos\\"+e.getUsername()+Employe.getRegPic().getName()));
            
            e.setPhoto(destFile.getName());
            System.out.println(e);
            
            Employe.setRegPic(destFile); 
            destFile.delete();
        }
        ServEmploye se = new ServEmploye();
    se.AjouterEmploye(e);
    application.gotoAffichageEmploye();
        }
    }

    @FXML
    private void ReturnB(ActionEvent event) 
    {
                application.gotoDashBoardRepresentant();

    }
    
}
