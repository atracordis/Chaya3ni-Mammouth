package GUI.Controllers.backOffice;

import Entities.Filiale;
import static GUI.Controllers.backOffice.AffichageFilialeController.ids;
import static GUI.Controllers.backOffice.AffichageFilialeController.lstf;
import Services.ServFiliale;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import GUI.Interfaces.Main;


public class MapFilialesController extends AnchorPane implements MapComponentInitializedListener {

@FXML
GoogleMapView mapView;
GoogleMap map;
private Main application;
    @FXML
    private Button returnb;

 public void setApp(Main application) 
    {
        this.application = application;
    }
public void initialize() 
{

   ServFiliale sf = new ServFiliale();
        
        lstf = sf.AfficherFilialeSociete(ids);
        
    
    mapView.addMapInializedListener(this);

    
}


@Override
public void mapInitialized() {
    //Set the initial properties of the map.
    MapOptions mapOptions = new MapOptions();

    mapOptions.center(new LatLong(47.6097, -122.3331))
            .mapType(MapTypeIdEnum.ROADMAP)
            .overviewMapControl(false)
            .panControl(false)
            .rotateControl(false)
            .scaleControl(false)
            .streetViewControl(false)
            .zoomControl(false)
            .zoom(12);

    map = mapView.createMap(mapOptions);

    //Add a marker to the map
    ServFiliale sf = new ServFiliale();
        
        lstf = sf.AfficherFilialeSociete(ids);
        List <Marker> liste = new ArrayList<>();
        for (Filiale f : lstf)
        {
            LatLong location = new LatLong (f.getLatitude(),f.getLongitude());
             MarkerOptions markerOptions = new MarkerOptions();
             markerOptions.position(location);
             Marker mark = new Marker(markerOptions);
             liste.add(mark);
        }
    
       
        
    for (Marker m : liste)
    {
        map.addMarker(m);
    }
    

}

    @FXML
    private void ReturnB(ActionEvent event)
    {
                application.gotoDashBoardRepresentant();

    }


}