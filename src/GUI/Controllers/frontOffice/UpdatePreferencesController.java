/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.frontOffice;

import Entities.Preferences;
import Entities.User;
import Services.UserService;
import Tools.NumberTextField;
import java.util.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import GUI.Interfaces.Main;
import GUI.Interfaces.Main;
import Services.PreferencesService;
import Tools.OtherTools;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 *
 * @author Admin
 */
public class UpdatePreferencesController extends AnchorPane {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private TextField musicTaste;
    @FXML
    private TextField vehicleComfort;
    @FXML 
    private TextField vehicleBrand;
    @FXML
    private TextField vehicleSeries;
    @FXML
    private Label vehicleComfortLabel;
    @FXML
    private Label vehicleBrandLabel;
    @FXML
    private Label vehicleSeriesLabel;
    @FXML
    private Label nameSurname;
    @FXML
    private Label success;
    @FXML
    private ComboBox<String> car;
    @FXML
    private CheckBox animal;
    @FXML
    private CheckBox allowAnimal;
    @FXML
    private CheckBox email;
    @FXML
    private CheckBox telephone;
    @FXML
    private CheckBox address;
    @FXML
    private CheckBox music;
    @FXML
    private CheckBox smoking;
    @FXML
    private CheckBox allowSmoking;
    @FXML 
    private ImageView imgVw;
    @FXML
    private Hyperlink updateUser ;
    @FXML
    private Hyperlink logout;
    
    private boolean control;

    private Main application;

    public void setApp(Main application) {
        this.application = application;
   
        
        
        
        animal.setSelected(true);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws MalformedURLException {
        vehicleComfort.setVisible(false);
        vehicleBrand.setVisible(false);
        vehicleSeries.setVisible(false);
        vehicleComfortLabel.setVisible(false);
        vehicleBrandLabel.setVisible(false);
        vehicleSeriesLabel.setVisible(false);
        control=false;
       
     car.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {
        if ("I have a car".equals(t1))
        {            
        vehicleComfort.setVisible(true);
        vehicleBrand.setVisible(true);
        vehicleSeries.setVisible(true);
        vehicleComfortLabel.setVisible(true);
        vehicleBrandLabel.setVisible(true);
        vehicleSeriesLabel.setVisible(true);
        }
        else
        {
        vehicleComfort.setVisible(false);
        vehicleBrand.setVisible(false);
        vehicleSeries.setVisible(false);
        vehicleComfortLabel.setVisible(false);
        vehicleBrandLabel.setVisible(false);
        vehicleSeriesLabel.setVisible(false);            
        }
           
        }    
    });

                        
        UserService userservice=new UserService();
        User currentUser= userservice.getUserLogin(User.getCurrentUsername(), User.getCurrentPassword());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.ENGLISH );
        LocalDate birthday = LocalDate.parse(currentUser.getDateBirth(), formatter);
        
        
        nameSurname.setText(currentUser.getName()+" "+currentUser.getSurname());


        UserService u = new UserService();
        
         
        Preferences pref = new Preferences();
        PreferencesService prefser = new PreferencesService();
        User user = prefser.selectPreferences(User.getCurrentUsername(), User.getCurrentPassword());
        
        
      Image img = new Image("file:///C:/wamp/www/PIDEV/pics/"+user.getPhoto()) ;
      System.out.println("file:///C:/wamp/www/PIDEV/pics/"+user.getPhoto());
      imgVw.setImage(img);

        
        if (user.getPreferences().getAddress().equals("1"))
            address.setSelected(true);
        else address.setSelected(false);
        
        if (user.getPreferences().getEmail().equals("1"))
            email.setSelected(true);
        else email.setSelected(false);
        
        if (user.getPreferences().getAllowSmoking().equals("1"))
            allowSmoking.setSelected(true);
        else allowSmoking.setSelected(false);
        
        if (user.getPreferences().getSmoking().equals("1"))
            smoking.setSelected(true);
        else smoking.setSelected(false);
        
        if (user.getPreferences().getHaveAnimal().equals("1"))
            animal.setSelected(true);
        else animal.setSelected(false);
                
        if (user.getPreferences().getAnimal().equals("1"))
            allowAnimal.setSelected(true);        
        else allowAnimal.setSelected(false);
        
        if (user.getPreferences().getMusic().equals("1"))
            music.setSelected(true);
        else music.setSelected(false);
        
        if (user.getPreferences().getTelephone().equals("1"))
            telephone.setSelected(true);
        else telephone.setSelected(false);
        
        if (user.getPreferences().getTelephone().equals("1"))
            telephone.setSelected(true);
        else telephone.setSelected(false);
        
        musicTaste.setText(user.getPreferences().getMusicTaste());
        
        
        if ( user.getPreferences().getConfortVoiture() != null)
        {
            car.setValue("I have a car");
        vehicleComfort.setVisible(true);
        vehicleBrand.setVisible(true);
        vehicleSeries.setVisible(true);
        vehicleComfortLabel.setVisible(true);
        vehicleBrandLabel.setVisible(true);
        vehicleSeriesLabel.setVisible(true);
        vehicleComfort.setText(user.getPreferences().getConfortVoiture());
        vehicleBrand.setText(user.getPreferences().getMarqueVoiture());
        vehicleSeries.setText(user.getPreferences().getModeleVoiture());
        
        }
        else 
            car.setValue("I don't have a car");

   
        
    }

    public void processLogout(ActionEvent event) {
        if (application == null) {
            return;
        }

        application.userLogout();
    }
    public void processUpdateUser(ActionEvent event)
    {
        if (application == null) {
            return;
        }

        application.gotoUpdate();
        
    }

    public void processUpdateUsername(ActionEvent event) {

    }

    public void processUpdateEmail(ActionEvent event) {

    }
    
    public void processBackToMenu(ActionEvent event) {
        if (application == null) {
            return;
        }

        application.gotoMenu();
    }

    public void processUpdate(ActionEvent event) throws IOException {
        if (application == null) {
            animateMessage();
            return;
        }
       PreferencesService prefservice = new PreferencesService();

       
        String emailBuffer;
        if (email.isSelected()) {
            emailBuffer = "1";
        } else {
            emailBuffer = "0";
        }
               
        String musicBuffer;
        if (music.isSelected()) {
            musicBuffer = "1";
        } else {
            musicBuffer = "0";
        }
               
        String smokingBuffer;
        if (smoking.isSelected()) {
            smokingBuffer = "1";
        } else {
            smokingBuffer = "0";
        }
               
        String allowSmokingBuffer;
        if (allowSmoking.isSelected()) {
            allowSmokingBuffer = "1";
        } else {
            allowSmokingBuffer = "0";
        }
               
        String animalBuffer;
        if (animal.isSelected()) {
            animalBuffer = "1";
        } else {
            animalBuffer = "0";
        }
               
        String allowAnimalBuffer;
        if (allowAnimal.isSelected()) {
            allowAnimalBuffer = "1";
        } else {
            allowAnimalBuffer = "0";
        }
               
        String addressBuffer;
        if (address.isSelected()) {
            addressBuffer = "1";
        } else {
            addressBuffer = "0";
        }
             
        String telephoneBuffer;
        if (telephone.isSelected()) {
            telephoneBuffer = "1";
        } else {
            telephoneBuffer = "0";
        }
        
        String musicTasteBuffer = musicTaste.getText();
        String vehicleComfortBuffer= vehicleComfort.getText();
        String vehicleBrandBuffer=vehicleBrand.getText();
        String vehicleSeriesBuffer= vehicleSeries.getText();
        
        

        Preferences Pref = new 
                Preferences(User.getCurrentId(), emailBuffer, telephoneBuffer,
                addressBuffer , "0" , "0",
                musicBuffer , musicTasteBuffer , smokingBuffer , 
                allowSmokingBuffer, allowAnimalBuffer ,animalBuffer , 
                vehicleComfortBuffer , vehicleBrandBuffer , vehicleSeriesBuffer ) ;

        prefservice.updatePreferences(Pref, User.getCurrentId());

        animateMessage();
    }

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(3000), success);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }

  
            
        


}
