package GUI;

import Services.ServiceReclamation;
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
import APIs.StatistiqueReclamation;

import java.net.URL;
import java.util.ResourceBundle;

public class StatistiqueReclamationControlleur implements Initializable {

    @FXML
    private BarChart barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StatistiqueReclamation re = new StatistiqueReclamation();
       ObservableList<XYChart.Series<String, Number>> barChartData = FXCollections.observableArrayList();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series.setName("Nombre Reclamation");
        String[] mois = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
        for (int i = 0; i < mois.length ; i++) {
            int NombreReclamation = re.getNombreReclamationParMois(i+1, 2023);
            int nbrTR = re.getNombreReclamationTraiteesParMois(i+1, 2023);
            series.getData().add(new XYChart.Data<>(mois[i], NombreReclamation));
            series2.getData().add(new XYChart.Data<>(mois[i], nbrTR));
            System.out.println( nbrTR);
        }

        barChartData.addAll(series,series2);
        series.setName("reclamation");
        series2.setName("reclamation traité");
        xAxis.setLabel("Mois");
        yAxis.setLabel("Nombre Reclamation");
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




