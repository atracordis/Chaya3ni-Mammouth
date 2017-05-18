/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.backOffice;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Entities.Filiale;
import static GUI.Controllers.backOffice.AffichageEmployeController.e;
import Services.ServEmploye;
import Services.ServFiliale;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import GUI.Interfaces.Main;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AffichageFilialeController extends AnchorPane {
    
    private Main application;
    

    @FXML
    private TableColumn<Filiale, String> adressef1;
    @FXML
    private TableColumn<Filiale, String> adressef2;
    @FXML
    private TableColumn<Filiale, Double> longitudef;
    @FXML
    private TableColumn<Filiale, Double> latitudef;
    @FXML
    private TableColumn<Filiale, Double> codepostalf;
    @FXML
    private TableView<Filiale> tablefiliale;

    
    @FXML
    private AnchorPane ap4;
    @FXML
    private Button supprimerfiliale;
    @FXML
    private TextField searchfiliale;
    @FXML
    private Button ModifierFiliale;
    
    private ObservableList<Filiale> FilialeData = FXCollections.observableArrayList();
   
    public static Filiale f= new Filiale();
    
    public static List<Filiale> lstf=new ArrayList<>();
    
    public static int ids=ProfilSocieteController.s.getIds();
    @FXML
    private Button nouvellefiliale;
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
        f.setIdcompany(ids);
       
        
        
           
        adressef1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse1()));
        adressef2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse2()));
        longitudef.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>((cellData.getValue().getLongitude())));
        latitudef.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>((cellData.getValue().getLatitude())));
        codepostalf.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>((cellData.getValue().getCodepostal())));

        FilialeData.addAll(0, lstf);
        tablefiliale.refresh();
        tablefiliale.setItems(FilialeData);
        
         tablefiliale.setOnMousePressed(new EventHandler<MouseEvent>() {
    @Override 
    public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
            Node node = ((Node) event.getTarget()).getParent();
            TableRow row;
            if (node instanceof TableRow) 
            {
                row = (TableRow) node;
            } else 
            {
                // clicking on text part
                row = (TableRow) node.getParent();
            }
            
            f=(Filiale) row.getItem();
            
        }
        else if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            Node node = ((Node) event.getTarget()).getParent();
            TableRow row;
            if (node instanceof TableRow) 
            {
                row = (TableRow) node;
            } else 
            {
                // clicking on text part
                row = (TableRow) node.getParent();
            }
            
            f=(Filiale) row.getItem();
            application.gotoAffichageEmploye();
            
        }
    }
    });
        searchfiliale.textProperty().addListener(new ChangeListener<String>() {
    @Override
    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
        FilialeData = FXCollections.observableArrayList(
                lstf
                .stream()
                .filter(f
                        -> (f.getAdresse1()).contains(searchfiliale.getText())
                )
                .collect(Collectors.toList())
        );
        tablefiliale.setItems(FilialeData);
    }
});
}

    @FXML
    private void SupprimerfFliale(ActionEvent event) 
    {
        if (f.getAdresse1()!= null)
        {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
     alert.setTitle("Confirmer Suppression Filiale");
     
     alert.setHeaderText("Etes vous surs de vouloir supprimer la filiale se trouvant a " +f.getAdresse1());
     StringWriter sw = new StringWriter();
     PrintWriter pw = new PrintWriter(sw);

     String exceptionText = sw.toString();

     Label label = new Label("Confirmation :");

   Optional<ButtonType> result = alert.showAndWait();

   if (result.get() == ButtonType.OK)
   {

        ServFiliale sf = new ServFiliale ();
        
            System.out.println(f.toString());
           sf.SupprimerFiliale(f); 
           tablefiliale.refresh();
        }
        }
        else
        {
              Alert alert2 = new Alert(Alert.AlertType.WARNING);
     alert2.setTitle("Invalid Operation");
     
     alert2.setHeaderText("Select a Filiale to supprim");
     StringWriter sw1 = new StringWriter();
     PrintWriter pw1 = new PrintWriter(sw1);
     String exceptionText1 = sw1.toString();
     Optional<ButtonType> result1 = alert2.showAndWait();
        }
        
   
        
    }

    

    @FXML
    private void ModifierFiliale(ActionEvent event) 
    {
        if (f.getAdresse1()!= null)
        {
            application.gotoModifierFiliale();
        }
        else
        {
              Alert alert2 = new Alert(Alert.AlertType.WARNING);
     alert2.setTitle("Invalid Operation");
     
     alert2.setHeaderText("Select a Filiale to Modif");
     StringWriter sw1 = new StringWriter();
     PrintWriter pw1 = new PrintWriter(sw1);
     String exceptionText1 = sw1.toString();
     Optional<ButtonType> result1 = alert2.showAndWait();
        }
        
    }

    @FXML
    private void NouvelleFiliale(ActionEvent event) 
    {
        application.gotoModifierFiliale();
    }

    @FXML
    private void ReturnB(ActionEvent event) 
    {
        application.gotoDashBoardRepresentant();

    }
        
        
    
}
