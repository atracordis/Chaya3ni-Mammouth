/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.backOffice;

import Entities.Societe;
import Entities.User;
import static GUI.Controllers.backOffice.AffichageEmployeController.e;
import Services.ServEmploye;
import Services.ServSociete;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import Tools.OtherTools;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import GUI.Interfaces.Main;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjoutSocieteController extends AnchorPane {

    @FXML
    private TextField noms;
    @FXML
    private TextField telephones;
    @FXML
    private TextField adress1s;
    @FXML
    private TextField adress2s;
    @FXML
    private TextField emails;
    @FXML
    private TextField codepostals;
    private Main application;
    private String nomsociete;
    private String telephonesociete;
    private String adressesociete1;
    private String adressesociete2;
    private String emailsociete;
    private String logosociete;
    private double codepostalsociete;
    private int idr;
    public static Pattern ptr = Pattern.compile("(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)");

    public static String namesociete;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button uploadlogo;
    @FXML
    private Button submitsocietes;
    @FXML
    private ImageView imgVw;
    @FXML
    private Label photopath;
    @FXML
    private Label photoname;
    
    
    /**
     * Initializes the controller class.
     */
    
    /**
     * Initializes the controller class.
     * @param application
     */
    public void setApp(Main application) 
    {
        this.application = application;

    }
    
    public void initialize() 
    {
        photopath.setVisible(false);
        photoname.setVisible(false);  
       telephones.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
           if (!newValue.matches("\\d*")) {
               telephones.setText(newValue.replaceAll("[^\\d]", ""));
           }
        });
       codepostals.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
           if (!newValue.matches("\\d*")) {
               codepostals.setText(newValue.replaceAll("[^\\d]", ""));
           }
        });

       emails.addEventFilter(KeyEvent.KEY_TYPED, (Event arg) -> {
            KeyEvent arg0 = (KeyEvent) arg;

            if (!(ptr.matcher(emails.getText()).matches())) {
                emails.setStyle("-fx-background-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
           //     arg0.consume();
            } else {
                emails.setStyle("-fx-background-color: rgb(255, 255, 255, 0.3), linear-gradient(rgb(0, 0, 0, 0.5), rgb(0, 0, 0, 0.8) 50%),rgb(218, 226, 224);");
            }
        });
       OtherTools.ConditionText(codepostals, 3);
       OtherTools.ConditionText(telephones, 7);
       OtherTools.ConditionText2(noms);
       OtherTools.ConditionText2(adress1s);
       OtherTools.ConditionText2(adress2s);
       
       
        
    }
    @FXML
    public void submitsociete(ActionEvent event) throws IOException
    {
        boolean notEmpty = true;
        
        String problem="";
        if (noms.getText().equals("")) 
        {
            notEmpty = false;
            problem="Name cannot be empty";
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
        if (adress1s.getText().equals("")) 
        {
            notEmpty = false;
            problem="Adresse 1 cannot be empty";
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Information");
     
            alert.setHeaderText(problem);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            String exceptionText = sw.toString();

            
            Optional<ButtonType> result = alert.showAndWait();
     
        }
        if (telephones.getText().length()!=8) 
        {
            notEmpty = false;
            problem="telephone cannot contains less or more than 8 numbers";
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Information");
     
            alert.setHeaderText(problem);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            String exceptionText = sw.toString();

            
            Optional<ButtonType> result = alert.showAndWait();
        }
        if (codepostals.getText().length()!=4) 
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
        if (emails.getText().trim().equals("")) 
        {
            notEmpty = false;
            problem="Email cannot be empty";
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Information");
     
            alert.setHeaderText(problem);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            String exceptionText = sw.toString();

            
            Optional<ButtonType> result = alert.showAndWait();
            
        }
        

        if (!(ptr.matcher(emails.getText()).matches())) 
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
        
        if (Societe.getLogosociete()== null) 
        {
            notEmpty = false;
            problem="Your picture cannot be empty";
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
            
     System.out.println ("click"); 
     nomsociete=noms.getText();
     namesociete=noms.getText();
     telephonesociete=telephones.getText();
     adressesociete1=adress1s.getText();
     adressesociete2=adress2s.getText();
     emailsociete=emails.getText();
     codepostalsociete=Double.parseDouble(codepostals.getText());
     logosociete=photoname.getText();
     idr=0;
        Societe s;
        s = new Societe (nomsociete,telephonesociete,emailsociete,logosociete, adressesociete1,adressesociete2,codepostalsociete,idr);
        System.out.println (s.toString());
        File destFile = new File ("C:\\wamp64\\www\\logos\\"+Societe.getLogosociete().getName());
        if (!destFile.createNewFile())
        {
            System.out.println ("non cree");
        } 
        else 
        {
            
            OtherTools.copyFile(Societe.getLogosociete(), destFile);
            destFile.renameTo(new File ("C:\\wamp64\\www\\logos\\"+s.getName()+Societe.getLogosociete().getName()));
            
            s.setLogo(destFile.getName());
            
            System.out.println(s.getLogo());
            Societe.setLogosociete(destFile); 
            destFile.delete();
        }
        
        
       
        
        ServSociete ss = new ServSociete ();
        ss.AjouterSociete(s);
        application.gotoAjouterFiliale();
        }
    }    

    @FXML
    private void uploadlogo(ActionEvent event) 
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
                                Societe.setLogosociete(file);
                                photopath.setText("C:\\wamp64\\www\\logos\\"+file.getName());
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
}

     

   

    

   

         
        

    

    

    
    
    

