/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Interfaces;

import GUI.Controllers.backOffice.AjoutSocieteController;
import GUI.Controllers.*;
import GUI.Controllers.frontOffice.*;
import GUI.Controllers.backOffice.*;
import Entities.User;
import Services.UserService;
import Tools.BCrypt;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Admin
 */
public class Main extends Application {

    private Stage stage;
    private User loggedUser;
    private final double MINIMUM_WINDOW_WIDTH = 390.0;
    private final double MINIMUM_WINDOW_HEIGHT = 500.0;
    public static int idr;
    public static Main App;

    public static Main getApp() {
        return App;
    }

    public static void setApp(Main App) {
        Main.App = App;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(Main.class, (java.lang.String[]) null);
    }

    @Override
    public void start(Stage primaryStage) {
        System.setProperty("java.net.useSystemProxies", "true");
        try {
            stage = primaryStage;
            stage.setTitle("Chaya3ni");
            gotoSplash();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.getIcons().add(new Image("/GUI/Interfaces/LoginLogo.png"));
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public boolean userLogging(String userId, String password) {

        System.out.println("got username " + userId + " password " + password);
        UserService userservice = new UserService();
        User u = userservice.getUserLogin(userId, password);

        if (u.getUsername() != null) {
            if (u.getName() != null) {
                System.out.println("OK");
                User.setCurrentName(u.getName());
                User.setCurrentSurname(u.getSurname());
                User.setCurrentPhoto(u.getPhoto());
                User.setCurrentSecretAnswer(u.getSecretAnswer());
                User.setCurrentSecretQuestion(u.getSecretQuestion());
                User.setCurrentType(u.getType());
            } else {
                User.setCurrentType("a:1:{i:0;s:10:\"ROLE_ADMIN\";}");
                User.setCurrentName(u.getUsername());
                User.setCurrentSurname(u.getEmail());
                User.setCurrentClearanceLevel(u.getClearanceLevel());
                User.setCurrentPhoto("adminme.jpg");
                User.setCurrentSecretAnswer("");
                User.setCurrentSecretQuestion("");
            }
            User.setCurrentUsername(u.getUsername());
            User.setCurrentEmail(u.getEmail());
            User.setCurrentId(u.getId());
            User.setCurrentPassword(u.getPass());

            //   gotoUpdate();
            // userDisplay();    
            System.out.println("GUI.Interfaces.Main.userLogging()"+User.isRepresentative);
            if (User.isAdmin()) {
                gotoAdminMenu();
            } else {
                if (User.isRepresentative) {
                    System.out.println("Representative detected");
                    idr = Integer.valueOf(u.getId());
                    gotoDashBoardRepresentant();
                } else {
                    gotoMenu();
                }
            }
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            stage.setX(bounds.getMinX());
            stage.setY(bounds.getMinY());
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());
            stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
            /*      stage.setMaximized(true);
            stage.setFullScreen(true);*/
            Main.setApp(this);
            return true;
        } else {
            System.out.println("NOT OK");
            return false;
        }
    }

    public void gotoRideDriver() throws IOException {
        String sceneFile = "/GUI/Interfaces/MapajoutRideDriver.fxml";

        URL url = null;
        Parent root = null;
        try {
            url = getClass().getResource(sceneFile);
            root = FXMLLoader.load(url);
            System.out.println("  fxmlResource = " + sceneFile);
        } catch (Exception ex) {
            System.out.println("Exception on FXMLLoader.load()");
            System.out.println("  * url: " + url);
            System.out.println("  * " + ex);
            System.out.println("    ----------------------------------------\n");
            throw ex;
        }     //   Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/GUI/Interfaces/MapajoutRiderDriver.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        stage.show();
    }

    public void gotoRidePassenger() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Interfaces/RidePassenger.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        stage.show();
    }

    public void gotoRidePackage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Interfaces/RidePackageFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        stage.show();
    }

    public void gotoRideDriversstats() throws IOException {
        try {
            RideDriverStatsController login = (RideDriverStatsController) replaceSceneContent("stat.fxml");
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            stage.setX(bounds.getMinX());
            stage.setY(bounds.getMinY());
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());
            stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
            login.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoRideDriverEmployee() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Interfaces/MapajoutRiderDriverEmployee.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        stage.show();
    }

    public void gotoCovoiturageAnimal() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Interfaces/RidePassengerFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        stage.show();
    }

    public void gotoAffichReviews() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Interfaces/AffichReview.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        stage.show();
    }

    public void gotoAffichComplaintsUser() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Interfaces/AffichComplaintUser.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        stage.show();
    }

    public void gotoAffichReviewsAdmin() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Interfaces/AffichReviewsAdmin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        stage.show();
    }

    public void gotoTheOtherSpecialMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Interfaces/AdminMenuOthers.fxml"));
        Scene scene = new Scene(root);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        stage.setMaximized(true);

        stage.setScene(scene);
        stage.show();
    }
    public void gotoSpecialMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Interfaces/MenuOthersFXML.fxml"));
        Scene scene = new Scene(root);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        stage.setMaximized(true);

        stage.setScene(scene);
        stage.show();
    }

    public void gotoAffichComplaints() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Interfaces/AffichComplaints.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        stage.show();
    }

    public void gotoLoginSecretQuestion() {
        try {
            LoginSecretQuestionController login = (LoginSecretQuestionController) replaceSceneContent("LoginSecretQuestion.fxml");
            login.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoSmokers() {
        try {
            SmokersPieChartController login = (SmokersPieChartController) replaceSceneContent("SmokersPieChart.fxml");
            login.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoPie() {
        try {
            MonthBarChartController login = (MonthBarChartController) replaceSceneContent("MonthBarChart.fxml");
            login.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoDriverStats() {
        try {
            RideDriverStatsController login = (RideDriverStatsController) replaceSceneContent("stat.fxml");
            login.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoStatsMenu() {
        try {
            StatsMenuController login = (StatsMenuController) replaceSceneContent("StatsMenu.fxml");
            login.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoLine() {
        try {
            LineChartController login = (LineChartController) replaceSceneContent("LineChart.fxml");
            login.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean userRegister() {

        gotoRegister();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        /*    stage.setMaximized(true);
            stage.setFullScreen(true);*/

        return true;
    }

    public boolean userDisplay() {

        gotoUserDisplay();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        /*    stage.setMaximized(true);
            stage.setFullScreen(true);*/

        return true;
    }

    public void userLogout() {
        loggedUser = null;
        gotoLogin();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        /*  stage.setMaximized(true);
            stage.setFullScreen(true);*/

    }

    public void userUpdatePreferences() {
        loggedUser = null;
        gotoUpdatePreferences();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);

    }

    public void gotoMenuRides() {
        try {
            MenuRidesController profile = (MenuRidesController) replaceSceneContent("MenuRides.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gotoMenu() {
        try {
            MenuController profile = (MenuController) replaceSceneContent("Menu.fxml");
            profile.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gotoEmailWriting() {
        try {
            EmailController profile = (EmailController) replaceSceneContent("Email.fxml");
            profile.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gotoAdminMenu() {
        try {
            AdminMenuController profile = (AdminMenuController) replaceSceneContent("AdminMenu.fxml");
            profile.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gotoSplash() {
        try {
            SplashController profile = (SplashController) replaceSceneContent("Splash.fxml");
            profile.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gotoUserDisplay() {
        try {
            UserDisplayController profile = (UserDisplayController) replaceSceneContent("UserDisplay.fxml");
            profile.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gotoProfile() {
        try {
            UserProfileController profile = (UserProfileController) replaceSceneContent("UserProfile.fxml");
            profile.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoUpdatePreferences() {
        try {
            UpdatePreferencesController profile = (UpdatePreferencesController) replaceSceneContent("UpdatePreferences.fxml");
            profile.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoRegister() {
        try {
            InscriptionController register = (InscriptionController) replaceSceneContent("Inscription.fxml");
            register.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoAdminUserDisplay() {
        try {
            AdminUserDisplayController register = (AdminUserDisplayController) replaceSceneContent("AdminUserDisplay.fxml");
            register.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoAdminUserProfileDisplay() {
        try {
            AdminUserProfileController register = (AdminUserProfileController) replaceSceneContent("AdminUserProfile.fxml");
            register.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoUpdate() {
        try {
            UpdateUserController register = (UpdateUserController) replaceSceneContent("UpdateUser.fxml");
            register.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoUserStatMenu() {
        try {
            UserStatsMenuController register = (UserStatsMenuController) replaceSceneContent("UserStatsMenu.fxml");
            register.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoAdminInscription() {
        try {
            AdminInscriptionController register = (AdminInscriptionController) replaceSceneContent("AdminInscription.fxml");
            register.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoLogin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("Login.fxml");
            login.setApp(this);
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            stage.setX(bounds.getMinX());
            stage.setY(bounds.getMinY());
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());
            stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
            stage.setMaximized(true);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoAjouterSociete() {
        try {
            AjoutSocieteController profile = (AjoutSocieteController) replaceSceneContent("AjoutSociete.fxml");
            profile.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gotoAjouterFiliale() {
        try {
            AjoutFilialeController profile = (AjoutFilialeController) replaceSceneContent("AjoutFiliale.fxml");
            profile.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gotoAjouterEmploye() {
        try {
            AjoutEmployeController profile = (AjoutEmployeController) replaceSceneContent("AjoutEmploye.fxml");
            profile.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gotoAffichageEmploye() {
        try {
            AffichageEmployeController profile = (AffichageEmployeController) replaceSceneContent("AffichageEmploye.fxml");
            profile.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gotoAffichageFiliale() {
        try {
            AffichageFilialeController profile = (AffichageFilialeController) replaceSceneContent("AffichageFiliale.fxml");
            profile.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gotoModifierFiliale() {
        try {
            ModifierFilialeController profile = (ModifierFilialeController) replaceSceneContent("ModifierFiliale.fxml");
            profile.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gotoProfilSociete() {
        try {
            ProfilSocieteController profile = (ProfilSocieteController) replaceSceneContent("ProfilSociete.fxml");
            profile.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gotoNouvelEmploye() {
        try {
            NouvEmployeController profile = (NouvEmployeController) replaceSceneContent("NouvEmploye.fxml");
            profile.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gotoDashBoardRepresentant() {
        try {
            DashboardRepresentantController profile = (DashboardRepresentantController) replaceSceneContent("DashboardRepresentant.fxml");
            profile.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gotoMap() {
        try {
            MapFilialesController login = (MapFilialesController) replaceSceneContent("MapFiliales.fxml");
            login.setApp(this);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Node replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }

        // Store the stage width and height in case the user has resized the window
        double stageWidth = stage.getWidth();
        if (!Double.isNaN(stageWidth)) {
            stageWidth -= (stage.getWidth() - stage.getScene().getWidth());
        }

        double stageHeight = stage.getHeight();
        if (!Double.isNaN(stageHeight)) {
            stageHeight -= (stage.getHeight() - stage.getScene().getHeight());
        }

        Scene scene = new Scene(page);
        if (!Double.isNaN(stageWidth)) {
            page.setPrefWidth(stageWidth);
        }
        if (!Double.isNaN(stageHeight)) {
            page.setPrefHeight(stageHeight);
        }

        stage.setScene(scene);
        stage.sizeToScene();
        return (Node) loader.getController();
    }

}
