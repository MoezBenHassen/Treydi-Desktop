package GUI;

import APIs.StatistiqueLivraison;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class StatistiqueLivraisonControleur implements Initializable {
        @FXML
        private BarChart barChart;

        @FXML
        private CategoryAxis xAxis;

        @FXML
        private NumberAxis yAxis;

        @Override
        public void initialize(URL url, ResourceBundle rb) {
            StatistiqueLivraison li = new StatistiqueLivraison();
            ObservableList<XYChart.Series<String, Number>> barChartData = FXCollections.observableArrayList();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            XYChart.Series<String, Number> series2 = new XYChart.Series<>();
            series.setName("Livraison");
            String[] mois = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
            for (int i = 0; i < mois.length ; i++) {
                int NombrelivrasonA = li.getNombreLivraisonAnnulerParMois(i+1, 2023);
                int nbrLT = li.getNombreLivraisonTerminerParMois(i+1, 2023);
                series.getData().add(new XYChart.Data<>(mois[i], NombrelivrasonA));
                series2.getData().add(new XYChart.Data<>(mois[i], nbrLT));

            }
            barChartData.addAll(series,series2);

            xAxis.setStyle("-fx-text-fill: white;-fx-font-size: 16px; -fx-font-weight: bold;");
            yAxis.setStyle("-fx-text-fill: white;-fx-font-size: 16px; -fx-font-weight: bold;");

            series.setName("Livraison");
            series2.setName("Livraison Annuler");
            xAxis.setLabel("Mois");
            yAxis.setLabel("Livraison Terminer");
            barChart.setData(barChartData);
            for (int i = 0; i < barChartData.size(); i++) {
                for (int j = 0; j < barChartData.get(i).getData().size(); j++) {
                    String categoryName = barChartData.get(i).getData().get(j).getXValue();
                }
            }


        }
        Stage stage;
        @FXML
        private AnchorPane scenePane;
        public void logout(MouseEvent event) {

            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText("You're about to exit ! ");

            if(alert.showAndWait().get() == ButtonType.OK){
                stage = (Stage) scenePane.getScene().getWindow();

                stage.close();
            }
        }

        @FXML
        public void Minimize (MouseEvent event ){
            Stage stage1= (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage1.setIconified(true);
        }



}