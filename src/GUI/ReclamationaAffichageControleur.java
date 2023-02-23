package GUI;

import Entities.Reclamation;
import Services.ServiceReclamation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReclamationaAffichageControleur implements Initializable {



    @FXML
    private TableView<Reclamation> tableView;

    @FXML
    private TableColumn<Reclamation, String> titreC;

    @FXML
    private TableColumn<Reclamation, String> descriptionC;




    @Override
    public void initialize(URL url, ResourceBundle rb) {


        titreC.setCellValueFactory(new PropertyValueFactory<>("titre_reclamation"));
        descriptionC.setCellValueFactory(new PropertyValueFactory<>("description"));
        afficher();
    }

    @FXML
    private void close(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("vous voulez quiter");
        alert.showAndWait();
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    private void afficher() {
        ServiceReclamation sr = new ServiceReclamation();
        List<Reclamation> reclamations = sr.afficher() ;
        tableView.getItems().clear();
        tableView.getItems().addAll(reclamations);

    }


    @FXML
    private void gotoajout(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamationfxml.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }



}
