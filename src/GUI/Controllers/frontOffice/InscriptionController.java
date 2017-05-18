/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.frontOffice;

import Entities.Preferences;
import Entities.User;
import GUI.Controllers.LoginController;
import Services.UserService;
import java.util.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import GUI.Interfaces.Main;
import Tools.OtherTools;
import Tools.Show;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventType;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Admin
 */
public class InscriptionController extends AnchorPane {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML
    private TextField phone;
    ;
    @FXML
    private ImageView imgVw;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private DatePicker dateBirth;
    @FXML
    private TextField email;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField address1;
    @FXML
    private TextField address2;
    @FXML
    private TextField postalCode;
    @FXML
    private Label message;
    @FXML
    private Label photopath;
    @FXML
    private Label photoname;
    @FXML
    private Label latLabel;
    @FXML
    private Label longLabel;
    @FXML
    private ComboBox<String> secretQuestion;
    @FXML
    private TextField secretAnswer;
    @FXML
    private CheckBox newsletter;
    @FXML
    private Button update;
    @FXML
    private Button upload;
    @FXML
    private Label success;

    private Main application;

    public void setApp(Main application) {
        this.application = application;
        newsletter.setSelected(true);
        success.setOpacity(0);
        User.setRegPic(null);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        try {
            File dbFile = new File(OtherTools.DATABASE_CITY_PATH);
            DatabaseReader reader = new DatabaseReader.Builder(dbFile).build();
            InetAddress ipAddress = OtherTools.getIP();
            CityResponse response = reader.city(ipAddress);
            Country country = response.getCountry();
            System.out.println("Country IsoCode: " + country.getIsoCode()); // 'US'
            System.out.println("Country Name: " + country.getName()); // 'United States'
            System.out.println(country.getNames().get("zh-CN")); // '美国'

            Subdivision subdivision = response.getMostSpecificSubdivision();
            System.out.println("Subdivision Name: " + subdivision.getName()); // 'Minnesota'
            System.out.println("Subdivision IsoCode: " + subdivision.getIsoCode()); // 'MN'

            City city = response.getCity();
            System.out.println("City Name: " + city.getName()); // 'Minneapolis'
            address2.setText(country.getName());
            Postal postal = response.getPostal();
            System.out.println(postal.getCode()); // '55455'

            Location location = response.getLocation();
            System.out.println("Latitude: " + location.getLatitude()); // 44.9733
            System.out.println("Longitude: " + location.getLongitude()); // -93.2323
            latLabel.setText(location.getLatitude().toString());
            longLabel.setText(location.getLongitude().toString());

        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeoIp2Exception ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        message.setVisible(false);
        photopath.setVisible(false);
        photoname.setVisible(false);
        email.addEventFilter(KeyEvent.KEY_TYPED, (Event arg) -> {
            KeyEvent arg0 = (KeyEvent) arg;

            boolean matches = OtherTools.validate(email.getText());
            if (!(matches)) {
                email.setStyle("-fx-background-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
            } else {
                email.setStyle("-fx-background-color: rgb(255, 255, 255, 0.3), linear-gradient(rgb(0, 0, 0, 0.5), rgb(0, 0, 0, 0.8) 50%),rgb(218, 226, 224);");
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
                if (!newValue.matches("[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç ]")) {
                    name.setText(newValue.replaceAll("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]", ""));
                }
            }
        });

        OtherTools.setTextLimit(phone, 8);
        OtherTools.setTextLimit(name, 253);
        OtherTools.setTextLimit(surname, 253);
        OtherTools.setTextLimit(email, 253);
        OtherTools.setTextLimit(username, 24);
        OtherTools.setTextLimit(password, 24);
        OtherTools.setTextLimit(address1, 49);
        OtherTools.setTextLimit(address2, 49);
        OtherTools.setTextLimit(secretAnswer, 253);
        OtherTools.setTextLimit(postalCode, 253);

        password.addEventFilter(KeyEvent.KEY_TYPED, (Event arg) -> {
            KeyEvent arg0 = (KeyEvent) arg;
            int length = password.getText().length();
            if (length < 8) {
                message.setText("Password Strength: Bad");
                message.setStyle("-fx-text-fill: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
                message.setVisible(true);
                password.setStyle("-fx-background-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
            } else if (length < 12) {
                message.setText("Password Strength: Medium");
                message.setStyle("-fx-text-fill: orangered,linear-gradient(to bottom, derive(orangered,60%) 5%,derive(orangered,90%) 40%);");
                message.setVisible(true);
                password.setStyle("-fx-background-color: orangered,linear-gradient(to bottom, derive(orangered,60%) 5%,derive(orangered,90%) 40%);");
            } else if (length < 18) {
                message.setText("Password Strength: Good");
                message.setStyle("-fx-text-fill: orange,linear-gradient(to bottom, derive(orange,60%) 5%,derive(orange,90%) 40%);");
                message.setVisible(true);
                password.setStyle("-fx-background-color: orange,linear-gradient(to bottom, derive(orange,60%) 5%,derive(orange,90%) 40%);");
            } else if (length > 17) {
                message.setText("Password Strength: Best");
                message.setStyle("-fx-text-fill:  green,linear-gradient(to bottom, derive(green,60%) 5%,derive(green,90%) 40%);");
                message.setVisible(true);
                password.setStyle("-fx-background-color: green,linear-gradient(to bottom, derive(green,60%) 5%,derive(green,90%) 40%);");
            } else {
                password.setStyle("-fx-background-color: rgb(255, 255, 255, 0.3), linear-gradient(rgb(0, 0, 0, 0.5), rgb(0, 0, 0, 0.8) 50%),rgb(218, 226, 224);");
                message.setVisible(false);
            }

        });
    }

    public void processLogout(ActionEvent event) {
        if (application == null) {
            return;
        }

        application.userLogout();
    }

    public void processUpdateUsername(ActionEvent event) {

    }

    public void processUpdateEmail(ActionEvent event) {

    }

    public void processUpdate(ActionEvent event) throws IOException, DocumentException, ParseException {
        if (application == null) {
            animateMessage();
            return;
        }
        boolean notEmpty = true;
        boolean control = false;
        String problem = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date firstDate = sdf.parse("1900-01-01");
        Date lastDate = sdf.parse("2000-01-01");

        if (email.getText().equals("")) {
            notEmpty = false;
            problem = "Email cannot be empty";
        }
        if (username.getText().equals("")) {
            notEmpty = false;
            problem = "Username cannot be empty";
        }
        if (password.getText().equals("")) {
            notEmpty = false;
            problem = "Password cannot be empty";
        }
        if (secretAnswer.getText().trim().equals("")) {
            notEmpty = false;
            problem = "Your secret answer cannot be empty";
        }
        if (name.getText().trim().equals("")) {
            notEmpty = false;
            problem = "Your name cannot be empty";
        }
        if (surname.getText().trim().equals("")) {
            notEmpty = false;
            problem = "Your surname cannot be empty";
        }
        if (phone.getText().trim().equals("")) {
            notEmpty = false;
            problem = "Your phone cannot be empty";
        }
        if (address1.getText().trim().equals("")) {
            notEmpty = false;
            problem = "Your address cannot be empty";
        }
        if (address2.getText().trim().equals("")) {
            notEmpty = false;
            problem = "Your address cannot be empty";
        }
        if (postalCode.getText().trim().equals("")) {
            notEmpty = false;
            problem = "Your postal code cannot be empty";
        }
        if (dateBirth.getValue() == null) {
            notEmpty = false;
            problem = "Your date of birth cannot be empty";
        }
        if (secretQuestion.getValue() == null) {
            notEmpty = false;
            problem = "Your secret question cannot be empty";
        }
        if (gender.getValue() == null) {
            notEmpty = false;
            problem = "Your gender cannot be empty";
        }
        if (User.getRegPic() == null) {
            notEmpty = false;
            problem = "Your picture cannot be empty";
        }
        Show.me(User.getRegPic() == null);
        boolean valid = true;
        if (notEmpty) {
            Date testDateBirth = sdf.parse(dateBirth.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            UserService us = new UserService();
            if (testDateBirth.before(firstDate)) {
                valid = false;
                problem = "You're a bit too old";
            }
            if (testDateBirth.after(lastDate)) {
                valid = false;
                problem = "You're a bit too young";
            }
            if (us.existUser(username.getText(), "username")) {
                valid = false;
                problem = "This username already exists";
            }
            if (us.existpassword(password.getText())) {
                valid = false;
                problem = "This password is compromised";
            }

            if (us.existUser(email.getText(), "email")) {
                valid = false;
                problem = "This email already exists";
            }
            if (!(OtherTools.validate(email.getText()))) {
                valid = false;
                problem = "Your email is invalid";
            }
            if (password.getText().length() < 17) {
                valid = false;
                problem = "Your password is too weak";
            }

        }
        if (valid && notEmpty) {
            control = true;
        }
        if (control) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.now();

            String firstName = name.getText().trim();
            String lastName = surname.getText().trim();
            String sex = gender.getValue();
            if (sex.equals("Male")) {
                sex = "homme";
            } else {
                sex = "femme";
            }
            String birthDate = dateBirth.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String mail = email.getText().trim();
            String pseudo = username.getText().trim();
            String pass = password.getText();
            String telephone = phone.getText();
            String pic = photoname.getText();
            String questionSecret = secretQuestion.getValue();
            String answerSecret = secretAnswer.getText();
            String adress1 = address1.getText();
            String adress2 = address2.getText();
            String codePostal = postalCode.getText();
            String news;

            File destFile = new File("C:\\wamp\\www\\PIDEV\\temp\\" + User.getRegPic().getName());

            if (newsletter.isSelected()) {
                news = "1";
            } else {
                news = "0";
            }
            Preferences Pref = new Preferences("0", "1", "0", "0",
                    "0", "0", "1", "folk",
                    "0", "0", "0", "0",
                    "Tres confortable", "454 TDI", "Volvo");

            User user = new User(firstName, lastName, sex, birthDate, mail,
                    pseudo, pass, telephone, adress1, adress2,
                    codePostal, pic, dtf.format(localDate), news, "0",
                    latLabel.getText(), longLabel.getText(), questionSecret, answerSecret);
            user.setPreferences(Pref);

            UserService userservice = new UserService();
            System.out.println(user);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("User agreement");
            alert.setHeaderText("Please read the agreement carefully");
            alert.setContentText("Do not accept until you have read the user agreement");

// Create expandable Exception.
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            String useragreement = "Chaya3ni\n"
                    + "End-User License Agreement (\"Agreement\")\n"
                    + "\n"
                    + "Last updated: February 08, 2017\n"
                    + "\n"
                    + "Please read this End-User License Agreement (\"Agreement\") carefully before clicking the \"I Agree\" button, downloading or using Chaya3ni (\"Application\").\n"
                    + "\n"
                    + "By clicking the \"I Agree\" button, downloading or using the Application, you are agreeing to be bound by the terms and conditions of this Agreement.\n"
                    + "\n"
                    + "This Agreement is a legal agreement between you (either an individual or a single entity) and Chaya3ni and it governs your use of the Application made available to you by Chaya3ni.\n"
                    + "\n"
                    + "If you do not agree to the terms of this Agreement, do not click on the \"I Agree\" button and do not download or use the Application.\n"
                    + "\n"
                    + "The Application is licensed, not sold, to you by Chaya3ni for use strictly in accordance with the terms of this Agreement. This EULA was created with (TermsFeed).\n"
                    + "\n"
                    + "License\n"
                    + "\n"
                    + "Chaya3ni grants you a revocable, non-exclusive, non-transferable, limited license to download, install and use the Application solely for your personal, non-commercial purposes strictly in accordance with the terms of this Agreement.\n"
                    + "\n"
                    + "Restrictions\n"
                    + "You agree not to, and you will not permit others to license, sell, rent, lease, assign, distribute, transmit, host, outsource, disclose or otherwise commercially exploit the Application or make the application available to any third party.\n"
                    + "\n"
                    + "Modifications to Application\n"
                    + "Mammouth reserves the right to modify, suspend or discontinue, temporarily or permanently, the Application or any service to which it connects, with or without notice and without liability to you.\n"
                    + "\n"
                    + "Term and Termination\n"
                    + "\n"
                    + "This Agreement shall remain in effect until terminated by you or Chaya3ni.\n"
                    + "\n"
                    + "Chaya3ni may, in its sole discretion, at any time and for any or no reason, suspend or terminate this Agreement with or without prior notice.\n"
                    + "\n"
                    + "This Agreement will terminate immediately, without prior notice from Chaya3ni, in the event that you fail to comply with any provision of this Agreement. You may also terminate this Agreement by deleting the Application and all copies thereof from your mobile device or from your computer.\n"
                    + "\n"
                    + "Upon termination of this Agreement, you shall cease all use of the Application and delete all copies of the Application from your mobile device or from your computer.\n"
                    + "\n"
                    + "Termination of this Agreement will not limit any of Chaya3ni's rights or remedies at law or in equity in case of breach by you (during the term of this Agreement) of any of your obligations under the present Agreement.\n"
                    + "\n"
                    + "Entire Agreement\n"
                    + "\n"
                    + "The Agreement constitutes the entire agreement between you and Chaya3ni regarding your use of the Application and supersedes all prior and contemporaneous written or oral agreements between you and Chaya3ni.\n"
                    + "\n"
                    + "You may be subject to additional terms and conditions that apply when you use or purchase other Chaya3ni's services, which Chaya3ni will provide to you at the time of such use or purchase.\n"
                    + "";

            pw.write(useragreement);
            String exceptionText = sw.toString();

            Label label = new Label("User agreement:");

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            Hyperlink link = new Hyperlink("Download our agreement");
            link.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    Document document = new Document();
                    PdfWriter writer;
                    try {
                        writer = PdfWriter.getInstance(document, new FileOutputStream("./Licence.pdf"));

                        document.open();

                        PdfContentByte cb = writer.getDirectContent();
                        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

                        cb.beginText();
                        cb.setFontAndSize(bf, 9);
                        cb.setRGBColorFill(0000, 0000, 0000);
                        ColumnText ct = new ColumnText(cb);
                        Phrase myText = new Phrase(useragreement);
                        ct.setSimpleColumn(myText, 20, 750, 580, 20, 10, Element.ALIGN_LEFT);
                        ct.go();
                        /*
            ct.setSimpleColumn(36, 600, 400, 800);
            ct.addElement(new Paragraph(useragreement));
                         */
                        cb.endText();
                        document.close();
                    } catch (Exception ex) {
                        Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 2);
            expContent.add(link, 0, 1);

// Set expandable Exception into the dialog pane.
            alert.getDialogPane().setContent(expContent);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                animateMessage();
                User u = userservice.getUserLogin(pseudo, pass);
                User.setCurrentId(u.getId());
                User.setCurrentUsername(u.getUsername());
                User.setCurrentPassword(u.getPass());

                OtherTools.copyFile(User.getRegPic(), destFile);
                destFile.renameTo(new File("C:\\wamp\\www\\PIDEV\\temp\\" + user.getUsername() + user.getPhoto()));
                File destFile2 = new File("C:\\wamp\\www\\PIDEV\\pics\\" + user.getUsername() + user.getPhoto());
                user.setPhoto(user.getUsername() + user.getPhoto());
                File destFile3 = new File("C:\\wamp\\www\\PIDEV\\temp\\" + user.getPhoto());
                OtherTools.copyFile(destFile3, destFile2);
                User.setRegPic(destFile2);
                System.out.println(User.getRegPic().getAbsolutePath());
                destFile.delete();
                userservice.insertUser(user);
                if (!application.userLogging(pseudo, pass)) {
                    System.out.println("erreur de connection post ajout");
                }
            }

        } else {
            TrayNotification tray = new TrayNotification("Registration failure", problem, Notifications.ERROR);
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
                    System.err.println("*********************");
                    System.out.println(User.getRegPic().getAbsolutePath());
                    System.err.println("*********************");
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
