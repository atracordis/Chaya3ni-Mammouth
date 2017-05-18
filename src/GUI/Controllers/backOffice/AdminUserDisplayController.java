/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.backOffice;

/**
 *
 * @author Admin
 */
    



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import Tools.OtherTools;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import org.relique.jdbc.csv.CsvDriver;

/**
 *
 * @author Admin
 */
public class AdminUserDisplayController extends AnchorPane {


    @FXML
    private TableView<User> personTable;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User, String> surname;
    @FXML
    private TableColumn<User, LocalDate> dateBirth;
    @FXML
    private TableColumn<User, User> photo;
    @FXML
    private TableColumn<User, User> manage;
    @FXML
    private TableColumn<User, User> contact;
    @FXML
    private TextField search;

    private ObservableList<User> personData = FXCollections.observableArrayList();

    private boolean control;

    private Main application;
    @FXML
    private Hyperlink logout;
    @FXML
    private Hyperlink backToMenu;

    public void setApp(Main application) {
        this.application = application;

    }
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws MalformedURLException {
        UserService us = new UserService();
        // GHAITH START LOOKING
        // GET ALL USERS IN LIST THEN ADD THEM ALL TO TABLEVIEW
        List<User> users = us.selectUser();
        personData.addAll(0, users);
// ICI RECHERCHE. EVERY TIME YOU ADD A CHARACTER WE REFRESH THE LIST
        search.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                UserService us = new UserService();
                List<User> users = us.searchUser(search.getText());
                personData.removeAll(personData);
                personData.addAll(0, users);
                personTable.refresh();
                personTable.setItems(personData);
            }
        });


//HERE COLUMN PHOTO
        // Initialize the columns.
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.ENGLISH);
        photo.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));

        photo.setCellFactory(column -> {
            return new TableCell<User, User>() {
                @Override
                protected void updateItem(User item, boolean empty) {
                    if (item != null) {
                        //	super.updateItem(item, empty);
                        VBox vb = new VBox();
                        VBox vb1= new VBox();
                        vb.setAlignment(Pos.CENTER);

                        //      Image img = new Image(getClass().getResource(item.getPhoto()).toExternalForm()); 
                        

                        Label nameSurname = new Label(item.getName() + " " + item.getSurname());
                        nameSurname.setFont(Font.font("Cambria", 20));
                        nameSurname.setWrapText(true);
                        
                        ImageView imgVw = new ImageView();
                        Image img = new Image("file:///C:/wamp/www/PIDEV/pics/" + item.getPhoto(), 150, 150, true, true);
                        imgVw.setImage(img);
                        imgVw.setFitHeight(160);
                        imgVw.setFitWidth(160);
                        
                        vb1.getChildren().addAll(imgVw, nameSurname);
                        
                        Hyperlink profileAccess = new Hyperlink("", vb1);
                        profileAccess.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                        User.setSelectedId(item.getId());
                        application.gotoAdminUserProfileDisplay();                        
                        }
                         });                                          
                        vb.getChildren().addAll(profileAccess);
                        setGraphic(vb);
                    }
                }
            };
        });
        // GHAITH STOP LOOKING
                contact.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));

        contact.setCellFactory(column -> {
            return new TableCell<User, User>() {
                @Override
                protected void updateItem(User item, boolean empty) {
                    if (item != null) {
                        //	super.updateItem(item, empty);
                        VBox vb = new VBox();
                        vb.setAlignment(Pos.CENTER);
                        Hyperlink deleteAccount = new Hyperlink("Contact");
                        deleteAccount.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                        User.setSelectedId(item.getId());
                       User.setpickedNewsletter(false);
                        application.gotoEmailWriting();
                        }
                         });                                          
                        vb.getChildren().addAll(deleteAccount);
                        setGraphic(vb);
                    }
                }
            };
        });
        
        manage.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));

        manage.setCellFactory(column -> {
            return new TableCell<User, User>() {
                @Override
                protected void updateItem(User item, boolean empty) {
                    if (item != null) {
                        //	super.updateItem(item, empty);
                        VBox vb = new VBox();
                        vb.setAlignment(Pos.CENTER);

                        
                        
                        Hyperlink deleteAccount = new Hyperlink("Delete Account");
                        deleteAccount.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                            System.err.println("we're herenow");
                        UserService us = new UserService();
                        us.deleteUser(item);
                        personTable.refresh();
                        }
                         });                                          
                        vb.getChildren().addAll(deleteAccount);
                        setGraphic(vb);
                    }
                }
            };
        });
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        surname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSurname()));
        dateBirth.setCellValueFactory(cellData
                -> new SimpleObjectProperty(
                        LocalDate
                        .parse(cellData.getValue().getDateBirth(),
                                formatter)
                )
        );

        // Custom rendering of the table cell.
        dateBirth.setCellFactory(column -> {
            return new TableCell<User, LocalDate>() {
                @Override
                protected void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        // Format date.
                        setText(formatter.format(item));

                        // Style all dates in March with a different color.
                   /*     if (item.getMonth() == Month.MARCH) {
                            setTextFill(Color.CHOCOLATE);
                            setStyle("-fx-background-color: yellow");
                        } else {
                            setTextFill(Color.BLACK);
                            setStyle("");
                        }*/
                    }
                }
            };
        });
    }
    
   public void processMyAccount(ActionEvent event)
   {
       
        if (application == null) {
            return;
        }

        application.gotoUpdate();
   }
    @FXML
    public void processLogout(ActionEvent event) {
        if (application == null) {
            return;
        }

        application.userLogout();
    }

   
    @FXML
      public void processBackToMenu(ActionEvent event) {
        if (application == null) {
            return;
        }

        application.gotoAdminMenu();
    }   
    @FXML
      public void processExportToCSV(ActionEvent event) {
        if (application == null) {
            return;
        }
        UserService us = new UserService();
        try {
            File tempFile = new File("./Users.csv");
            FileOutputStream fout = new FileOutputStream(tempFile);
            PrintStream out = new PrintStream(fout);

            CsvDriver.writeToCsv(us.selectUserResultSet(),out, true);

        } catch (Exception ex) {
            Logger.getLogger(AdminUserDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }

      }

    @FXML
    private void processnewsletter(ActionEvent event) {
                            User.setpickedNewsletter(true);
                            application.gotoEmailWriting();
    }
}
