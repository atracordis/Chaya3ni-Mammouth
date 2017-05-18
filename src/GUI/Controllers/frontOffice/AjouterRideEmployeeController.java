/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.frontOffice;

import Entities.RideEmployee;
import Services.ServiceRideEmployee;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import static GUI.Controllers.frontOffice.MapajoutRiderDriverEmployeeController.CityDestination;
import static GUI.Controllers.frontOffice.MapajoutRiderDriverEmployeeController.CitySource;
import static GUI.Controllers.frontOffice.MapajoutRiderDriverEmployeeController.LatDestination;
import static GUI.Controllers.frontOffice.MapajoutRiderDriverEmployeeController.LatSource;
import static GUI.Controllers.frontOffice.MapajoutRiderDriverEmployeeController.LongDestination;
import static GUI.Controllers.frontOffice.MapajoutRiderDriverEmployeeController.LongSource;
import GUI.Interfaces.Main;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import static tray.notification.NotificationType.ERROR;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Deathscythvi
 */
public class AjouterRideEmployeeController implements Initializable {
    private double price;
    private String citySource;
    private String placeSource;
    private Double longSource;
    private Double latSource;
    private String cityDestination;
    private String placeDestination;
    private Double longDestination;
    private Double latDestination;
    private Date dateTimeSource;
    private int nbrPlaces;
    private String confortVoiture;
    private String marqueVoiture;
    private String modeleVoiture;
    private byte handicap;
    private byte animal;
     private byte haveAnimal;
     private byte haveLuggage;
    private float luggageMass;
    private byte music;
    private String musicTaste;
    private byte smoking;
    private byte allowSmoking;
    private int idDriver;
    private int frequency;
    private String frequencyUnit;
    private int idFiliale;

    @FXML
    private AnchorPane ap;
    @FXML
    private TextField text1;
    @FXML
    private TextField text2;
    @FXML
    private TextField text4;
    @FXML
    private TextField text3;
    @FXML
    private TextField text5;
    @FXML
    private TextField text6;
    @FXML
    private TextField text7;
    @FXML
    private TextField text8;
    @FXML
    private TextField text9;
    @FXML
    private TextField text10;
    @FXML
    private TextField text11;
    @FXML
    private TextField text12;
    @FXML
    private TextField text13;
    @FXML
    private TextField text14;
    @FXML
    private TextField text15;
    @FXML
    private CheckBox cb1;
    @FXML
    private CheckBox cb2;
    @FXML
    private CheckBox cb3;
    @FXML
    private CheckBox cb4;
    @FXML
    private CheckBox cb5;
    @FXML
    private CheckBox cb6;
    @FXML
    private CheckBox cb7;
    @FXML
    private DatePicker dt;
    @FXML
    private TextField text17;
    @FXML
    private TextField text18;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;

    /**
     * Initializes the controller class.
     */
    public static AnchorPane rootP;
    @FXML
    private Button submit;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         text4.setText(""+LongSource );
       text5.setText(""+LatSource );
       text8.setText(""+LongDestination );
       text9.setText(""+LatDestination );
       text2.setText(CitySource);
       text6.setText(CityDestination);
       text4.setDisable(true);
       text5.setDisable(true);
       text8.setDisable(true);
       text9.setDisable(true);
       text2.setDisable(true);
       text6.setDisable(true);
         text1.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                text1.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
    });
         
           text2.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]")) {
                text2.setText(newValue.replaceAll("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]", ""));
            }
                throw new UnsupportedOperationException("Not supported yet."); 
            }
            
        });
               text3.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]")) {
                text3.setText(newValue.replaceAll("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]", ""));
            }
                throw new UnsupportedOperationException("Not supported yet."); 
            }
            
        });
                text4.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                text4.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
    });
           text5.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                text5.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
    });
            text6.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                text6.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
    });
             text7.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                text7.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
    });
              text8.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                text8.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
    });
               text9.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                text9.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
    });
                text10.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                text10.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
    });
               text14.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                text14.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
    });
              
               
               text11.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]")) {
                text11.setText(newValue.replaceAll("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]", ""));
            }
                throw new UnsupportedOperationException("Not supported yet."); 
            }
            
        });
               text12.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]")) {
                text12.setText(newValue.replaceAll("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]", ""));
            }
                throw new UnsupportedOperationException("Not supported yet."); 
            }
            
        });
               text13.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]")) {
                text13.setText(newValue.replaceAll("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]", ""));
            }
                throw new UnsupportedOperationException("Not supported yet."); 
            }
            
        });
               text15.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]")) {
                text15.setText(newValue.replaceAll("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]", ""));
            }
                throw new UnsupportedOperationException("Not supported yet."); 
            }
            
        });
                text17.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                text17.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
    });
               text18.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]")) {
                text18.setText(newValue.replaceAll("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]", ""));
            }
                throw new UnsupportedOperationException("Not supported yet."); 
            }
            
        });
               
        
        
        rootP=ap;
       
        try {
           VBox box = FXMLLoader.load(getClass().getResource("/GUI/Interfaces/Sidemenu.fxml"));
           drawer.setSidePane(box);
           
           for(Node node : box.getChildren()){
            if(node.getId()!=null){
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) ->{
                     
                   if (node.getId().equals("affichetrajet")){
                        
                        
                        try {
                            AnchorPane pane=FXMLLoader.load(getClass().getResource("/GUI/Interfaces/AfficherMestrajetEmployee.fxml"));
                            ap.getChildren().setAll(pane);
                        } catch (IOException ex) {
                            Logger.getLogger(AjouteRideDriverController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                        
                        
                    }else if (node.getId().equals("ajoutertrajet")){
                        
                         try {
                            AnchorPane pane=FXMLLoader.load(getClass().getResource("/GUI/Interfaces/MapajoutRiderDriverEmployee.fxml"));
                            ap.getChildren().setAll(pane);
                        } catch (IOException ex) {
                            Logger.getLogger(AjouteRideDriverController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else if (node.getId().equals("exit"))
                        {
                            try {
                                Main.getApp().gotoSpecialMenu();
                            } catch (IOException ex) {
                                Logger.getLogger(AjouteRideDriverController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                           
                           
                           
                            
                            //ap.setBackground(new Background(new BackgroundFill(Paint.valueOf("#fc0000"),CornerRadii.EMPTY,Insets.EMPTY)));
                        
                    
                });
            }
        }
        
            HamburgerBackArrowBasicTransition burgerTask2=new HamburgerBackArrowBasicTransition(hamburger);
            burgerTask2.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) ->{
                burgerTask2.setRate(burgerTask2.getRate() * -1);
                burgerTask2.play();
                if(drawer.isShown()){
                    drawer.close();
                }else
                    drawer.open();
                
                
            }); } catch (IOException ex) {
            Logger.getLogger(AjouteRideDriverController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        }

    @FXML
    private void submit(ActionEvent event) {
                 if(text13.getText().equals("")||text1.getText().equals("") || text2.getText().equals("") ||text3.getText().equals("") || text4.getText().equals("") || text5.getText().equals("") || text6.getText().equals("") || text7.getText().equals("")||text8.getText().equals("")||text9.getText().equals("")||text10.getText().equals("")||text11.getText().equals("")||text12.getText().equals("") || text17.getText().equals("") || text18.getText().equals("")){
                      TrayNotification tray = new TrayNotification("Error","Field is empty", ERROR);
                     tray.showAndWait();
                 }else{

        price =Double.parseDouble (text1.getText());
        citySource = text2.getText();
        placeSource = text3.getText();
        longSource = Double.parseDouble(text4.getText());
        latSource =Double.parseDouble(text5.getText());
        cityDestination = text6.getText();
        placeDestination = text7.getText();
        longDestination =Double.parseDouble (text8.getText());
        latDestination =Double.parseDouble (text9.getText());
        dateTimeSource= java.sql.Date.valueOf(dt.getValue());
        nbrPlaces =Integer.parseInt( text10.getText());
        confortVoiture = text11.getText();
        marqueVoiture = text12.getText();
        modeleVoiture = text13.getText();
        if(cb1.isSelected()){
            handicap=(byte)1;
            
        }else{
            handicap=(byte)0;
        }
        if(cb2.isSelected()){
            animal=(byte)1;
            
        }else{
            animal=(byte)0;
        }
        if(cb3.isSelected()){
            haveAnimal=(byte)1;
            
        }else{
            haveAnimal=(byte)0;
        }
        if(cb4.isSelected()){
            haveLuggage=(byte)1;
            
        }else{
            haveLuggage=(byte)0;
        }
        luggageMass =Float.parseFloat(text14.getText());
         if(cb5.isSelected()){
            music=(byte)1;
            
        }else{
            music=(byte)0;
        }
        musicTaste = text15.getText();
         if(cb6.isSelected()){
            smoking=(byte)1;
            
        }else{
            smoking=(byte)0;
        }
          if(cb7.isSelected()){
            allowSmoking=(byte)1;
            
        }else{
            allowSmoking=(byte)0;
        }
        
        frequency = Integer.parseInt(text17.getText());
        frequencyUnit = text18.getText();
        
          ServiceRideEmployee srd = new ServiceRideEmployee();
        RideEmployee dr = new RideEmployee(price,citySource,placeSource,longSource,latSource,cityDestination,placeDestination,longDestination,latDestination,dateTimeSource,nbrPlaces,confortVoiture,marqueVoiture,modeleVoiture,handicap,animal,haveAnimal,haveLuggage,luggageMass,music,musicTaste,smoking,allowSmoking,frequency,frequencyUnit,1);
        srd.ajouterRideEmployee(dr);
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("/GUI/Interfaces/AfficherMestrajetEmployee.fxml"));
              ap.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(ModifiertrajetEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 }
        
    }
    }    

    

