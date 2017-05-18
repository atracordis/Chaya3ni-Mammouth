/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers.backOffice;

import Services.Statistics;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import Services.ServiceReviews;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author SALMA
 */

    public class StatisticsEvController implements Initializable {
@FXML 
    private PieChart chart;
@FXML 
    private TextField textField;
    private Statistics stat=new Statistics();
private ObservableList<PieChart.Data> pcData=stat.list_Stat();
    @FXML
    private AnchorPane affich;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        chart.setData(pcData);
        chart.setTitle("Statistique selon le nombre de points");
     setupAnimation();
    }
    private void resetTextField() {
        FadeTransition ft = new FadeTransition(
                Duration.millis(1000), textField);
        ft.setToValue(0.0);
        ft.playFromStart();
        ft.setOnFinished(event -> {
            textField.setTranslateX(0);
            textField.setTranslateY(0);
        });
    }
    private void setupAnimation() {
        pcData.stream().forEach(pieData -> {
            System.out.println(pieData.getName() + ": " + pieData.getPieValue());
            pieData.getNode().addEventHandler(
                    MouseEvent.MOUSE_CLICKED, event -> {
                        if (event.isControlDown()) {
                            // Move the textfield to where the mouse click is
                            textField.setTranslateX(event.getSceneX() - textField.getLayoutX());
                            textField.setTranslateY(event.getSceneY() - textField.getLayoutY());
                            textField.setText(String.valueOf(pieData.getPieValue()));
                            textField.setOpacity(1.0);
                            textField.setOnAction(evt -> {
                                try {
                                    System.out.println("You entered: " + textField.getText());
                                    final Double num = Double.valueOf(textField.getText());
                                    if (num > 0) {
                                        pieData.setPieValue(num);
                                        resetTextField();
                                    } else {
                                        textField.setText(String.valueOf(pieData.getPieValue()));
                                    }
                                } catch (NumberFormatException e) {
                                    // Just use the original number if the format is bad
                                    textField.setText(String.valueOf(pieData.getPieValue()));
                                }
                            });
                        } else {
                            resetTextField();
                            pieData.getNode().setTranslateX(0);
                            pieData.getNode().setTranslateY(0);
                            Bounds b1 = pieData.getNode().getBoundsInLocal();
                            double newX = (b1.getWidth()) / 2 + b1.getMinX();
                            double newY = (b1.getHeight()) / 2 + b1.getMinY();
                            // Make sure pie wedge location is reset
                            pieData.getNode().setTranslateX(0);
                            pieData.getNode().setTranslateY(0);
                            // Create the animation
                            TranslateTransition tt = new TranslateTransition(
                                    Duration.millis(1500), pieData.getNode());
                            tt.setToX(newX);
                            tt.setToY(newY);
                            tt.setAutoReverse(true);
                            tt.setCycleCount(2);
                            tt.playFromStart();
                        }
                    });
        });
    }

   
   
}

