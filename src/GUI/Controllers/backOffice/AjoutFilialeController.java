/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.backOffice;

import Entities.Employe;
import Entities.Filiale;
import Entities.Societe;
import static GUI.Controllers.backOffice.AjoutSocieteController.ptr;
import Services.ServEmploye;
import Services.ServFiliale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Services.ServSociete;
import Tools.OtherTools;
import Tools.MailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javax.mail.MessagingException;
import static jdk.nashorn.tools.Shell.SUCCESS;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import GUI.Interfaces.Main;
/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjoutFilialeController extends AnchorPane {
    
    private Main application;
    @FXML
    private TextField adressef1;
    @FXML
    private TextField adressef2;
    @FXML
    private TextField latitudef;
    @FXML
    private TextField longitudef;
    @FXML
    private TextField codepostalf;
    @FXML
    private AnchorPane ap1;
    
    public static Filiale f;
    private String adresse1;
    private String adresse2;
    private double latitude;
    private double longitude;
    private double codepostal;
    public static Societe s = new Societe ();
    @FXML
    private Button submitfiliale;
    @FXML
    private Button ajouterlisteemploye;
     public void setApp(Main application) 
    {
        this.application = application;

    }
  
    public void initialize() 
    {
        ServSociete ss = new ServSociete ();
        
        if (s.getName()== null)
        {
            System.out.println("if");
            s = ss.ChercherSociete(AjoutSocieteController.namesociete);
        } 
        System.out.println(s.toString());
        latitudef.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
           if (!newValue.matches("\\d*")) {
               latitudef.setText(newValue.replaceAll("[^\\d]", ""));
           }
        });
        longitudef.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
           if (!newValue.matches("\\d*")) {
               longitudef.setText(newValue.replaceAll("[^\\d]", ""));
           }
        });
       codepostalf.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
           if (!newValue.matches("\\d*")) {
               codepostalf.setText(newValue.replaceAll("[^\\d]", ""));
           }
        });
        OtherTools.ConditionText(codepostalf, 4);
        OtherTools.ConditionText2 (latitudef);
        OtherTools.ConditionText2 (longitudef);
        OtherTools.ConditionText2 (adressef1);
        OtherTools.ConditionText2 (adressef2);
    }
   

    @FXML
    private void AjouterListeEmploye (ActionEvent event) throws IOException 
    {
        Boolean notEmpty=true;
        String problem="";
    if (adressef1.getText().equals("")) 
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
        if (latitudef.getText().equals("")) 
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
        if (longitudef.getText().equals("")) 
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
        if (codepostalf.getText().length()!=4) 
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
    if (notEmpty==true)
    {
    adresse1=adressef1.getText();
    adresse2=adressef2.getText();
    latitude=Double.parseDouble(latitudef.getText());
    longitude=Double.parseDouble(longitudef.getText());
    codepostal=Double.parseDouble(codepostalf.getText());
    
        
        
        
        System.out.println (s.toString());
        
        f = new Filiale (s.getIds(),adresse1,adresse2,codepostal,latitude,longitude);
        
        application.gotoAjouterEmploye();
    }  
    }

    @FXML
    @SuppressWarnings("empty-statement")
    private void SubmitFiliale (ActionEvent event) throws MessagingException 
    {
        String from="rami.thamri@esprit.tn";
        String pass="20125018";
        String sujet="New Account in Chaya3ni";
        
        MailDAO mail = new MailDAO ();
        List <Filiale> liste=  new ArrayList <>();
        liste=s.getListeFilliale();
        ServFiliale sf = new ServFiliale ();
        
        if (liste.isEmpty())
        {
            
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Information");
     
            alert.setHeaderText("You have to Add at least one employee");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            String exceptionText = sw.toString();

            
            Optional<ButtonType> result = alert.showAndWait();
        }
        else
        {
            System.out.println("liste non nulle");
            for (Filiale f : liste)
            {
                
                System.out.println (f.toString());
                sf.AjouterFiliale(f);
                List <Employe> liste1=  new ArrayList <>();
                liste1=f.getListeEmploye();
                ServEmploye se = new ServEmploye ();
                Filiale f1;
                f1 = sf.ChercherFiliale(f.getCodepostal());
                for (Employe e : liste1)
                {
                    e.setIdFiliale(f1.getIdf());
                    e.setIdcompany(f1.getIdcompany());
                    se.AjouterEmploye(e);
                    String email = e.getEmail();
                    String[] to={""+email}; ; 
                    String contenu="Your company "+AjoutSocieteController.namesociete+ " get you an account in our Application Chaya3ni/n "+
                            "your username is : "+e.getUsername() +"/n"+ "Your password is : "+e.getPass()+"/n";
                    MailDAO.sendFromGMail(from, pass, to, sujet, contenu);
        
                }    
        }
            TrayNotification tray = new TrayNotification("Votre Societe possede desormais un compte!", "", Notifications.SUCCESS);
            tray.showAndWait();
    }
    }
}

    

    
   
    

