/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.backOffice;

import Entities.Societe;
import Services.ServEmploye;
import Services.ServSociete;
import Tools.OtherTools;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
public class ProfilSocieteController extends AnchorPane {
    
    private Main application;
    @FXML
    private Label noms;
    @FXML
    private Label telephones;
    @FXML
    private Label mails;
    @FXML
    private Label adresse1s;
    @FXML
    private Label adresse2s;
    @FXML
    private Label cdps;
    @FXML
    private ImageView logos;
    @FXML
    private TextField nomsmodif;
    @FXML
    private TextField adresse1smodif;
    @FXML
    private TextField telephonesmodif;
    @FXML
    private TextField adresses2modif;
    @FXML
    private TextField emailsmodif;
    @FXML
    private TextField codepostalsmodif;
    @FXML
    private Button modifiersociete;
    @FXML
    private Button confirmmodif;
    @FXML
    private Button umploadmodif;
     @FXML
    private Button ConsulterFiliale;
     @FXML
    private Button desactivercompte;
    private Label photopath;
    private Label photoname;

    /**
     * Initializes the controller class.
     */
    public static Societe s= new Societe();
    @FXML
    private Button returnb;
    
   
    
    
     public void setApp(Main application) 
    {
        this.application = application;

    }
  
    
    public void initialize() 
    {
    nomsmodif.setVisible(false);
    adresse1smodif.setVisible(false);
    telephonesmodif.setVisible(false);
    adresses2modif.setVisible(false);
    emailsmodif.setVisible(false);
    codepostalsmodif.setVisible(false);
    umploadmodif.setVisible(false);
    
    confirmmodif.setVisible(false);
        ServSociete ss= new ServSociete ();
        
        s= ss.ChercherSocieteParid(Main.idr);
        System.out.println (s.toString());
    noms.setText(s.getName());
    String t= s.getTelephone();
    
    telephones.setText(t);
    mails.setText(s.getEmail());
    adresse1s.setText (s.getAdresse1());
    adresse2s.setText (s.getAdresse2());
    double c= s.getCodepostal();
    cdps.textProperty().bind(new SimpleDoubleProperty(c).asString());
    
    Image img = new Image("file:///C:/wamp64/www/logos/" + s.getName()+s.getLogo(), 150, 150, true, true);
    logos.setImage(img);
    logos.setFitHeight(98);
    logos.setFitWidth(174);
    }    

    @FXML
    private void ModifierSociete(ActionEvent event) 
    {
    nomsmodif.setVisible(true);
    adresse1smodif.setVisible(true);
    telephonesmodif.setVisible(true);
    adresses2modif.setVisible(true);
    emailsmodif.setVisible(true);
    codepostalsmodif.setVisible(true);
    umploadmodif.setVisible(true);
    confirmmodif.setVisible(true);
    modifiersociete.setVisible(false);
    ConsulterFiliale.setVisible(false);
    
    nomsmodif.setText(s.getName());
    String t= s.getTelephone();
    
    telephonesmodif.setText(t);
    emailsmodif.setText(s.getEmail());
    adresse1smodif.setText (s.getAdresse1());
    adresses2modif.setText (s.getAdresse2());
    double c= s.getCodepostal();
    codepostalsmodif.setText(String.valueOf(s.getCodepostal()));
    }

    @FXML
    private void ConfirmModif(ActionEvent event) throws IOException 
    {
        s.setName(nomsmodif.getText());
        s.setTelephone(telephonesmodif.getText());
        s.setAdresse1(adresse1smodif.getText ());
        s.setAdresse2(adresses2modif.getText ());
        
        s.setCodepostal(Double.parseDouble(codepostalsmodif.getText()));
        s.setEmail(emailsmodif.getText());
        File destFile = new File ("C:\\wamp64\\www\\logos\\"+Societe.getLogosociete().getName());
        if (!destFile.createNewFile())
        {
            System.out.println ("non cree");
        } 
        else {
            
            OtherTools.copyFile(Societe.getLogosociete(), destFile);
            destFile.renameTo(new File ("C:\\wamp64\\www\\logos\\"+s.getName()+Societe.getLogosociete().getName()));
            
            s.setLogo(destFile.getName());
            
            System.out.println(s.getLogo());
            Societe.setLogosociete(destFile); 
            destFile.delete();
        }
        ServSociete ss= new ServSociete ();
        ss.ModifierSociete(s);
        nomsmodif.setVisible(false);
    adresse1smodif.setVisible(false);
    telephonesmodif.setVisible(false);
    adresses2modif.setVisible(false);
    emailsmodif.setVisible(false);
    codepostalsmodif.setVisible(false);
    umploadmodif.setVisible(false);
    confirmmodif.setVisible(false);
    modifiersociete.setVisible(true);
    ConsulterFiliale.setVisible(true);
     noms.setText(s.getName());
    String t= s.getTelephone();
    
    telephones.setText(t);
    mails.setText(s.getEmail());
    adresse1s.setText (s.getAdresse1());
    adresse2s.setText (s.getAdresse2());
    double c= s.getCodepostal();
    cdps.textProperty().bind(new SimpleDoubleProperty(c).asString());    
    }

    @FXML
    private void UploadModif(ActionEvent event) 
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
                                logos.setImage(img);
                                logos.setFitHeight(145);
                                logos.setFitWidth(179);
                                Societe.setLogosociete(file);
                                
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
    private void ConsulterFiliale(ActionEvent event) 
    {
        application.gotoAffichageFiliale();
    }

    @FXML
    private void DesactiverCompte(ActionEvent event) 
    {
        ServSociete ss= new ServSociete ();
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
     alert.setTitle("Confirmer Suppression Employe");
     
     alert.setHeaderText("Etes vous surs de vouloir supprimer " +s.getName()+" de facon definitve?");
     StringWriter sw = new StringWriter();
     PrintWriter pw = new PrintWriter(sw);
     

     
     String exceptionText = sw.toString();

     Label label = new Label("Confirmation :");

     

   

// Set expandable Exception into the dialog pane.
    

   Optional<ButtonType> result = alert.showAndWait();

   if (result.get() == ButtonType.OK)
   {

        
        if (s!= null)
        {
            ss.SupprimerSociete(s);
        }
        
   }
    }

    @FXML
    private void ReturnB(ActionEvent event) 
    {
        application.gotoDashBoardRepresentant();
    }
    
}
