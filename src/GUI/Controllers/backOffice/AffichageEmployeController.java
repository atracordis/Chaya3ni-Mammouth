/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.backOffice;

import Entities.Employe;
import Services.ServEmploye;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import GUI.Interfaces.Main;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AffichageEmployeController extends AnchorPane {
    
    private Main application;
    
    
    @FXML
    private TableColumn<Employe, Employe> photoe;
    @FXML
    private TableColumn<Employe, String> nome;
    @FXML
    private TableColumn<Employe, String> prenome;
    @FXML
    private TableColumn<Employe, String> telephonee;
    @FXML
    private TableColumn<Employe, String> adressee;
    @FXML
    private TableView<Employe> tableemploye;
    @FXML
    private Button supprimemploye;
    public static Employe e = new Employe ();
    @FXML
    private TextField searchemploye;
    @FXML
    private AnchorPane ap6;
    public static int idC;
    public static int idF;        
    public static List<Employe> lste=new ArrayList<>();
    private ObservableList<Employe> EmployeData;
    @FXML
    private Button nouvelemploye;
    @FXML
    private Button returnb;
     public void setApp(Main application) 
    {
        this.application = application;

    }
  
    public AffichageEmployeController() 
    {
        this.EmployeData = FXCollections.observableArrayList();
    }
    
    public void initialize() 
    {
        idF=AffichageFilialeController.f.getIdf();
        idC=AffichageFilialeController.f.getIdcompany();
        ServEmploye se = new ServEmploye();
        if (AffichageFilialeController.f.getIdf()!=0)
        {
            lste=se.ChercherEmployeFiliale(AffichageFilialeController.f.getIdf());
        }
        else
        {
            lste=se.AfficherEmployeSociete(AffichageFilialeController.f.getIdcompany());
        }
        
           
        nome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        prenome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSurname()));
        telephonee.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelephone()));
        adressee.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>((cellData.getValue().getAddress1())));
        
        photoe.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
          
        photoe.setCellFactory(column -> {
            return new TableCell<Employe, Employe>() {
                @Override
                protected void updateItem(Employe item, boolean empty) {
                    if (item != null) {
                        //	super.updateItem(item, empty);
                        VBox vb = new VBox();
                        VBox vb1= new VBox();
                        vb.setAlignment(Pos.CENTER);

                        //      Image img = new Image(getClass().getResource(item.getPhoto()).toExternalForm()); 
                        

                        
                        
                        ImageView imgVw = new ImageView();
                        Image img = new Image("file:///C:/wamp64/www/employe_photos/" + item.getUsername()+item.getPhoto(), 150, 150, true, true);
                        imgVw.setImage(img);
                        imgVw.setFitHeight(100);
                        imgVw.setFitWidth(100);
                        
                        vb1.getChildren().addAll(imgVw);
                        
                        Hyperlink profileAccess = new Hyperlink("", vb1);
                        profileAccess.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                            System.err.println(item.getId());
                           }
                         });                                          
                        vb.getChildren().addAll(profileAccess);
                        setGraphic(vb);
                    }
                }
            };
        });
        EmployeData.addAll(0, lste);
        tableemploye.refresh();
        tableemploye.setItems(EmployeData);
         tableemploye.setOnMousePressed(new EventHandler<MouseEvent>() {
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
            
            e=(Employe) row.getItem();
            
        }
    }
    });
          searchemploye.textProperty().addListener(new ChangeListener<String>() {
    @Override
    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
        EmployeData = FXCollections.observableArrayList(
                lste
                .stream()
                .filter(e
                        -> (e.getUsername()).contains(searchemploye.getText())
                )
                .collect(Collectors.toList())
        );
        tableemploye.setItems(EmployeData);
    }
});
    }    

    @FXML
    private void SupprimerEmploye(ActionEvent event) 
    {
        if (e.getAddress1()!=null)
        {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
     alert.setTitle("Confirmer Suppression Employe");
     
     alert.setHeaderText("Etes vous surs de vouloir supprimer " +e.getName() +" "+ e.getSurname()+" ");
     StringWriter sw = new StringWriter();
     PrintWriter pw = new PrintWriter(sw);
     String exceptionText = sw.toString();

     Label label = new Label("Confirmation :");
   Optional<ButtonType> result = alert.showAndWait();

   if (result.get() == ButtonType.OK)
   {

        ServEmploye se = new ServEmploye ();
        if (e!= null)
        {
            System.out.println(e.toString());
           se.SupprimerEmploye(e); 
           tableemploye.refresh();
        }
        
   }
        }
        else
        {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
     alert2.setTitle("Invalid Operation");
     
     alert2.setHeaderText("Select an Employee to supprim");
     StringWriter sw1 = new StringWriter();
     PrintWriter pw1 = new PrintWriter(sw1);
     String exceptionText1 = sw1.toString();
     Optional<ButtonType> result1 = alert2.showAndWait();
        }
    

        
    }

    @FXML
    private void NouvelEmploye(ActionEvent event) 
    {
        application.gotoNouvelEmploye();
    }

    @FXML
    private void ReturnB(ActionEvent event) 
    {
                application.gotoDashBoardRepresentant();

    }
    
}
