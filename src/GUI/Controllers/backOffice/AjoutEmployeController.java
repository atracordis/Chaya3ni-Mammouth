/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.backOffice;

import Entities.Employe;
import Entities.Preferences;
import Entities.Societe;
import static GUI.Controllers.backOffice.AjoutSocieteController.ptr;
import Tools.OtherTools;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import GUI.Interfaces.Main;
/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjoutEmployeController extends AnchorPane {
    
    private Main application;

    @FXML
    private Font x1;
    @FXML
    private Insets x3;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private CheckBox newsletter;
    @FXML
    private Font x2;
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
    private Label photopath;
    @FXML
    private Label photoname;
    @FXML
    private Label success;

    
    @FXML
    private Button ajoutemployeliste;
    @FXML
    private AnchorPane ap2;
    @FXML
    private Button endemploye;
     public void setApp(Main application) 
    {
        this.application = application;

    }
    
    public void initialize() 
    {
        
        
        
        List <Employe> liste=  new ArrayList <>();
        AjoutFilialeController.f.setListeFilliale(liste);
        
        Pattern ptr = Pattern.compile("(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)");
	message.setVisible(false);
        photopath.setVisible(false);
        photoname.setVisible(false);
        email.addEventFilter(KeyEvent.KEY_TYPED, (Event arg) -> {
            KeyEvent arg0 = (KeyEvent) arg;

            if (!(ptr.matcher(email.getText()).matches())) {
                email.setStyle("-fx-background-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
           //     arg0.consume();
            } else {
                email.setStyle("-fx-background-color: rgb(255, 255, 255, 0.3), linear-gradient(rgb(0, 0, 0, 0.5), rgb(0, 0, 0, 0.8) 50%),rgb(218, 226, 224);");
            }
        });
        name.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]")) {
                name.setText(newValue.replaceAll("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]", ""));
            }
        }
    });
         surname.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]")) {
                surname.setText(newValue.replaceAll("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]", ""));
            }
        }
    });
     
    phone.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                phone.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
    });

    
    postalCode.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                postalCode.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
    });    
    OtherTools.ConditionText(phone, 8);
    OtherTools.ConditionText(postalCode,4);
    OtherTools.ConditionText2(name);
    OtherTools.ConditionText2(surname);
    OtherTools.ConditionText2(address1);
    OtherTools.ConditionText2(address2);
    
    }   
      @FXML
    private void processUpload(ActionEvent event) throws IOException
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
      private void AjoutEmployeListe(ActionEvent event) throws IOException 
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
        if (notEmpty==true)
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
        int idF= 0;
        
        int idC= AjoutFilialeController.f.getIdcompany();
        
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
        
        AjoutFilialeController.f.AjouterEmploye(e);
        

        name.setText("");
        surname.setText("");
        
        
       
        email.setText("");
        
        phone.setText("");
        photoname.setText("");
        
        address1.setText("");
        address2.setText("");
        postalCode.setText("");
       
        
        
        
          
        }

     
    }

    @FXML
    private void EndEmploye (ActionEvent event) throws IOException 
    {
        AjoutFilialeController.s.AjouterFiliale(AjoutFilialeController.f);
        application.gotoAjouterFiliale();
    }

  

    
}

    

