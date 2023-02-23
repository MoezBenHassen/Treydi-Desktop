package GUI;


import Entities.Reponse;

import Services.ServiceReponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReponseAffichageControlleur implements Initializable {



    @FXML
    private TableView<Reponse> tableView;

    @FXML
    private TableColumn<Reponse, String> titreR;

    @FXML
    private TableColumn<Reponse, String> descriptionR;

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        titreR.setCellValueFactory(new PropertyValueFactory<>("titre_reponse"));
        descriptionR.setCellValueFactory(new PropertyValueFactory<>("description_reponse"));
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
        ServiceReponse sr = new ServiceReponse();
        List<Reponse> Reponses = sr.afficher() ;
        tableView.getItems().clear();
        tableView.getItems().addAll(Reponses);

    }


    @FXML
    private void gotoajout(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutReponse.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }



        }







