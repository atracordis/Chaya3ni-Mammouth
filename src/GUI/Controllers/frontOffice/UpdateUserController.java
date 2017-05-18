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
import Tools.BCrypt;
import Tools.OtherTools;
import Tools.Show;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
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
public class UpdateUserController extends AnchorPane {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private Hyperlink deactivateAccount;
    @FXML
    private TextField phone;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private Label photopath;
    @FXML
    private Label photoname;
    @FXML
    private ImageView imgVw;
    @FXML
    private DatePicker dateBirth;
    @FXML
    private Label email;
    @FXML
    private Label username;
    @FXML
    private TextField password;
    @FXML
    private TextField address1;
    @FXML
    private TextField address2;
    @FXML
    private TextField postalCode;
    @FXML
    private TextField photo;
    @FXML
    private ComboBox<String> secretQuestion;
    @FXML
    private TextField secretAnswer;
    @FXML
    private CheckBox newsletter;
    @FXML
    private Hyperlink logout;
    @FXML
    private Button update;
    @FXML
    private Label message;
    @FXML
    private Label success;
    @FXML
    private Hyperlink updatePreferences;
    @FXML
    private Hyperlink searchUser;
    @FXML
    private PasswordField Oldpassword;
    private boolean control;

    private Main application;

    public void setApp(Main application) {
        this.application = application;
        /*
        User loggedUser = application.getLoggedUser();
        user.setText(User.getCurrentName());
        email.setText(User.getCurrentEmail());
        phone.setText(User.getCurrentSurname());
        if (User.getCurrentSecretAnswer()!= null) {
            address.setText(User.getCurrentSecretAnswer());
        }
         */
        newsletter.setSelected(true);
        success.setOpacity(0);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws MalformedURLException {
        control = false;
        UserService us = new UserService();
        User used = us.getUserFromUserName(User.getCurrentUsername());
        Oldpassword.addEventFilter(KeyEvent.ANY, (Event arg) -> {
            KeyEvent arg0 = (KeyEvent) arg;
            String password = Oldpassword.getText();
            System.out.println("a");
            System.out.println(BCrypt.checkpw("adminadminadmin", "$2y$10$dOqaZy8NH.ee7BlhBv100.HbAstIxpQnoaCOu5zsPA9ljzuHWIIoq"));
            System.out.println("b");

            
 
            if (BCrypt.checkpw(password, used.getPass())) {
                message.setText("Correct password");
                message.setStyle("-fx-text-fill:  green,linear-gradient(to bottom, derive(green,60%) 5%,derive(green,90%) 40%);");
                message.setVisible(true);
                Oldpassword.setStyle("-fx-background-color: green,linear-gradient(to bottom, derive(green,60%) 5%,derive(green,90%) 40%);");
                control = true;
            } else {
                message.setText("Wrong password");
                message.setStyle("-fx-text-fill: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
                message.setVisible(true);
                Oldpassword.setStyle("-fx-background-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
                control = false;
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
    phone.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                phone.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
    });
        surname.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç ]")) {
                surname.setText(newValue.replaceAll("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]", ""));
            }
        }
    });
        name.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç ]")) {
                name.setText(newValue.replaceAll("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]", ""));
            }
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
    
   
    
    
OtherTools.setTextLimit(phone, 8);
OtherTools.setTextLimit(name, 253);
OtherTools.setTextLimit(surname, 253);
OtherTools.setTextLimit(password, 24);
OtherTools.setTextLimit(address1, 49);
OtherTools.setTextLimit(address2, 49);
OtherTools.setTextLimit(secretAnswer, 253);
OtherTools.setTextLimit(postalCode, 253);
    
        password.addEventFilter(KeyEvent.ANY, (Event arg) -> {
            int length = password.getText().length();
            if (length < 8) {
                message.setText("Password Strength: Bad");
                message.setStyle("-fx-text-fill: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
                message.setVisible(true);
                password.setStyle("-fx-background-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
                control = false;
            } else if (length < 12) {
                message.setText("Password Strength: Medium");
                message.setStyle("-fx-text-fill: orangered,linear-gradient(to bottom, derive(orangered,60%) 5%,derive(orangered,90%) 40%);");
                message.setVisible(true);
                password.setStyle("-fx-background-color: orangered,linear-gradient(to bottom, derive(orangered,60%) 5%,derive(orangered,90%) 40%);");
                control = true;

            } else if (length < 18) {
                message.setText("Password Strength: Good");
                message.setStyle("-fx-text-fill: orange,linear-gradient(to bottom, derive(orange,60%) 5%,derive(orange,90%) 40%);");
                message.setVisible(true);
                password.setStyle("-fx-background-color: orange,linear-gradient(to bottom, derive(orange,60%) 5%,derive(orange,90%) 40%);");
                control = true;
            } else if (length > 17) {
                message.setText("Password Strength: Best");
                message.setStyle("-fx-text-fill:  green,linear-gradient(to bottom, derive(green,60%) 5%,derive(green,90%) 40%);");
                message.setVisible(true);
                password.setStyle("-fx-background-color: green,linear-gradient(to bottom, derive(green,60%) 5%,derive(green,90%) 40%);");
                control = true;
            } else {
                password.setStyle("-fx-background-color: rgb(255, 255, 255, 0.3), linear-gradient(rgb(0, 0, 0, 0.5), rgb(0, 0, 0, 0.8) 50%),rgb(218, 226, 224);");
                message.setVisible(false);
                control = false;
            }
        });
        message.setVisible(false);
        photopath.setVisible(false);
        photoname.setVisible(false);
        UserService userservice = new UserService();
        User currentUser = userservice.getUserLogin(User.getCurrentUsername(), User.getCurrentPassword());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.ENGLISH);
        if (currentUser.getDateBirth()!=null){
        LocalDate birthday = LocalDate.parse(currentUser.getDateBirth(), formatter);
        dateBirth.setValue(birthday);}

        name.setText(currentUser.getName());
        surname.setText(currentUser.getSurname());
        String sex = currentUser.getGender();
        if (sex!=null){
        if (sex.equals("homme")) {
            sex = "Male";
        } else {
            sex = "Female";
        }
        gender.setValue(sex);
        }
          
        email.setText(currentUser.getEmail());
        username.setText(currentUser.getUsername());
        phone.setText(currentUser.getTelephone());
        UserService u = new UserService();

        User user = u.getUserLogin(User.getCurrentUsername(), User.getCurrentPassword());
        File file = new File("C:/wamp/www/PIDEV/pics/" + user.getPhoto());
        //Image img = new Image(file.toURI().toURL().toExternalForm());

        Image img = new Image("file:///C:/wamp/www/PIDEV/pics/" + user.getPhoto());
        imgVw.setImage(img);
        User.setRegPic(file);
        Show.me(User.getRegPic().getAbsolutePath());
        photopath.setText("file:///C:/wamp/www/PIDEV/pics/"+ User.getRegPic().getName());
        photoname.setText(User.getRegPic().getName());

        secretQuestion.setValue(currentUser.getSecretQuestion());
        secretAnswer.setText(currentUser.getSecretAnswer());
        address1.setText(currentUser.getAddress1());
        address2.setText(currentUser.getAddress2());
        postalCode.setText(currentUser.getCodePostal());

        String news = currentUser.getNewsletter();
        if (news == "1") {
            newsletter.setSelected(true);
        } else {
            newsletter.setSelected(false);
        }
    }

    public void processLogout(ActionEvent event) {
        if (application == null) {
            return;
        }
        application.userLogout();
    }

    public void processBackToMenu(ActionEvent event) {
        if (application == null) {
            return;
        }
        application.gotoMenu();
    }

    public void processUpdatePreferences(ActionEvent event) {
        if (application == null) {
            return;
        }
        application.userUpdatePreferences();
    }

    public void processSearchUser(ActionEvent event) {
        if (application == null) {
            return;
        }
        application.userDisplay();
    }

    public void processDeactivate(ActionEvent event) {
        if (application == null) {
            return;
        }

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Deactivation confirmation");
        alert.setHeaderText("CONFIRMATION");
        alert.setContentText("Are you sure? You can always reactivate your account by reconnecting.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            UserService userservice = new UserService();
            User currentUser = userservice.getUserLogin(User.getCurrentUsername(), User.getCurrentPassword());
            userservice.deleteUser(currentUser);
            Alert alert2 = new Alert(AlertType.INFORMATION);
            alert2.setTitle("Information");
            alert2.setHeaderText(null);
            alert2.setContentText("Deactivation successful!");

            alert2.showAndWait();
            application.userLogout();
        } else {
            System.err.println("Do nothing");
        }
    }

    public void processUpdateUsername(ActionEvent event) {

    }

    public void processUpdateEmail(ActionEvent event) {

    }

    public void processUpdate(ActionEvent event) throws IOException {
        if (application == null) {
            animateMessage();
            return;
        }
        boolean notEmpty = true;
        boolean control=false;
        String problem="";
        if (Oldpassword.getText().equals("")) {
            notEmpty = false;
            problem="Please type in your old password";
        }      
        if (email.getText().equals("")) {
            notEmpty = false;
            problem="Email cannot be empty";
        }
        if (username.getText().equals("")) {
            notEmpty = false;
            problem="Username cannot be empty";
        }
        if (password.getText().equals("")) {
            notEmpty = false;
            problem="Password cannot be empty";
        }
        if (secretAnswer.getText().trim().equals("")) {
            notEmpty = false;
            problem="Your secret answer cannot be empty";
        }
        if (name.getText().trim().equals("")) {
            notEmpty = false;
            problem="Your name cannot be empty";
        }
        if (surname.getText().trim().equals("")) {
            notEmpty = false;
            problem="Your surname cannot be empty";
        }
        if (phone.getText().trim().equals("")) {
            notEmpty = false;
            problem="Your phone cannot be empty";
        }
        if (address1.getText().trim().equals("")) {
            notEmpty = false;
            problem="Your address cannot be empty";
        }
        if (address2.getText().trim().equals("")) {
            notEmpty = false;
            problem="Your address cannot be empty";
        }
        if (postalCode.getText().trim().equals("")) {
            notEmpty = false;
            problem="Your postal code cannot be empty";
        }
        if (dateBirth.getValue() == null) {
            notEmpty = false;
            problem="Your date of birth cannot be empty";
        }
        if (secretQuestion.getValue() == null) {
            notEmpty = false;
            problem="Your secret question cannot be empty";
        }
        if (gender.getValue() == null) {
            notEmpty = false;
            problem="Your gender cannot be empty";
        }
        if (User.getRegPic() == null) {
            notEmpty = false;
            problem="Your picture cannot be empty";
        }
        Show.me(User.getRegPic() == null);
        boolean valid = true;        
        UserService us = new UserService();
        User used = us.getUserFromUserName(User.getCurrentUsername());

        if (notEmpty) {
            if (password.getText().length() < 17) {
                valid = false;
                 problem="Your password is too weak";
            }
             if (!(BCrypt.checkpw(Oldpassword.getText(), used.getPass()))) {
                valid = false;
                 problem="Wrong password";
        }
        }

        if (valid && notEmpty) {
            control = true;
        }

        if (control) {
            UserService userservice = new UserService();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.now();

            String firstName = name.getText();
            String lastName = surname.getText();
            String sex = gender.getValue();
            if (sex.equals("Male")) {
                sex = "homme";
            } else {
                sex = "femme";
            }
            Show.me("ddddddddddddddddddddddd");
            Show.me(photoname.getText());

            String birthDate = dateBirth.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String mail = email.getText();
            String pseudo = username.getText();
            String pass = password.getText();
            String telephone = phone.getText();
            String pic = photoname.getText();
            String questionSecret = secretQuestion.getValue();
            String answerSecret = secretAnswer.getText();
            String adress1 = address1.getText();
            String adress2 = address2.getText();
            String codePostal = postalCode.getText();
            File destFile = new File("C:/wamp/www/PIDEV/temp/" + User.getRegPic().getName());
            System.out.println("*******************************************");
            System.out.println(destFile.getAbsolutePath());
            System.out.println("*******************************************");

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

            User user = new User(firstName, lastName, sex, birthDate, mail,
                    pseudo, pass, telephone, adress1, adress2,
                    codePostal, pic, dtf.format(localDate), news, "0",
                    "0", "0", questionSecret, answerSecret);
            user.setPreferences(Pref);

            System.out.println("*******************************************");
            System.out.println(User.getRegPic().getAbsolutePath());
            OtherTools.copyFile(User.getRegPic(),
                    destFile);
            destFile.renameTo(new File("C:\\wamp\\www\\PIDEV\\temp\\" + user.getUsername() + user.getPhoto()));
            File destFile2 = new File("C:\\wamp\\www\\PIDEV\\pics\\" + user.getUsername() + user.getPhoto());
            user.setPhoto(user.getUsername() + user.getPhoto());

            File destFile3 = new File("C:\\wamp\\www\\PIDEV\\temp\\" + user.getPhoto());

            OtherTools.copyFile(destFile3, destFile2);
            User.setRegPic(destFile2);
            destFile.delete();

            userservice.updateUser(user);

            animateMessage();
        } else {
            TrayNotification tray = new TrayNotification("Update failure", problem, Notifications.ERROR);
            tray.showAndDismiss(Duration.seconds(3));

        }

    }
    

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(3000), success);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }

    public void processUpload(ActionEvent event) {
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
        } else {

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter exjpg = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter exjpg2 = new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg");
            FileChooser.ExtensionFilter expng = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(exjpg, exjpg2, expng);
            fileChooser.setTitle("Choose an image File");

            File file = fileChooser.showOpenDialog(null);

            if (file != null) {
                if (file.length() < 6000000) {
                    System.out.print("Condition ok");

                    Image img = new Image(file.toURI().toString());
                    imgVw.setImage(img);
                    imgVw.setFitHeight(150);
                    imgVw.setFitWidth(150);
                    User.setRegPic(file);
                    photopath.setText("C:\\wamp\\www\\pics\\" + file.getName());
                    photoname.setText(file.getName());
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Permiss");
                    alert.setHeaderText("Permission denied");
                    alert.setContentText("Your Image file is too big to upload \nplease choise another image");
                }

                /*              File destFile = new File ("C:\\wamp\\www\\pics\\"+file.getName());
                     
                             if (file != null) {
            if (file.length() < 6000000) {
                System.out.print("Condition ok");
                System.out.println(file.length());
            try {
                OtherTools.copyFile(file, destFile);
            } catch (IOException ex) {
                Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
            }
              /*  BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                retImage.setFill(new ImagePattern(image));
                imgPath = file.getAbsolutePath();
            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Size problem");
                alert.setHeaderText("Permission denied");
                alert.setContentText("Your Image file is too big to upload \nplease choise another image");
            }

        }
                 */
            }
        }

    }
}
