package GUI.Controllers.frontOffice;

import GUI.Interfaces.Main;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.service.directions.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import com.lynden.gmapsfx.javascript.object.LatLong;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class MapmodifierRideDriverController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;
    public static AnchorPane AnchorPane;
    public static double LongSource;
    public static double LatSource;
    public static double LongDestination;
    public static double LatDestination;
    public static String CitySource;
    public static String CityDestination;

    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();

    @FXML
    protected GoogleMapView mapView;

    @FXML
    protected TextField fromTextField;

    @FXML
    protected TextField toTextField;
    @FXML
    private Button go;
    @FXML
    private AnchorPane ap;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;

    @FXML
    private void toTextFieldAction(ActionEvent event) {
        DirectionsRequest request = new DirectionsRequest(from.get(), to.get(), TravelModes.DRIVING);

        directionsService.getRoute(request, this, new DirectionsRenderer(false, mapView.getMap(), directionsPane));

        if (directionsService != null) {
            MapOptions options = new MapOptions();

            options.center(new LatLong(36.81897, 10.16579))
                    .zoomControl(true)
                    .zoom(8)
                    .overviewMapControl(false)
                    .mapType(MapTypeIdEnum.ROADMAP);
            GoogleMap map = mapView.createMap(options);
            directionsService = new DirectionsService();
            directionsPane = mapView.getDirec();

            DirectionsRequest request1 = new DirectionsRequest(from.get(), to.get(), TravelModes.DRIVING);
            directionsService.getRoute(request1, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));

        }

    }

    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) {

        List<DirectionsRoute> x = results.getRoutes();
        List<DirectionsRoute> z = results.getRoutes();
        LatLong y = x.get(0).getBounds().getNorthEast();
        LatLong w = z.get(0).getBounds().getSouthWest();
        LongSource = y.getLatitude();
        LatSource = y.getLongitude();
        LongDestination = w.getLatitude();
        LatDestination = w.getLongitude();
        CitySource = from.get();
        CityDestination = to.get();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
        to.bindBidirectional(toTextField.textProperty());
        from.bindBidirectional(fromTextField.textProperty());
          try {
           VBox box = FXMLLoader.load(getClass().getResource("/GUI/Interfaces/Sidemenu.fxml"));
           drawer.setSidePane(box);
           
           for(Node node : box.getChildren()){
            if(node.getId()!=null){
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) ->{
                     
                    if(node.getId().equals("affichetrajet")){
                        
                        
                        try {
                            AnchorPane pane=FXMLLoader.load(getClass().getResource("/GUI/Interfaces/AfficherMestrajet.fxml"));
                            ap.getChildren().setAll(pane);
                        } catch (IOException ex) {
                            Logger.getLogger(AjouteRideDriverController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                            
                            
                            }else if (node.getId().equals("ajoutertrajet")){
                                 try {
                            AnchorPane pane=FXMLLoader.load(getClass().getResource("/GUI/Interfaces/MapajoutRideDriver.fxml"));
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

   

    

    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();

        options.center(new LatLong(36.81897, 10.16579))
                .zoomControl(true)
                .zoom(8)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();
        

    }

    @FXML
    private void go(ActionEvent event) throws IOException {
        if (fromTextField.getText().equals("") & toTextField.getText().equals("")) {

        } else {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Interfaces/Modifiertrajet.fxml"));
        ap.getChildren().setAll(pane);
    }
    }

}
