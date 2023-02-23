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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AjoutReponseControlleur implements Initializable {

        @Override
        public void initialize(URL location, ResourceBundle resources) {

        }
        @FXML
        private TextField textfield_titre;
        @FXML
        private TextArea textarea_description;
        @FXML
        private ImageView imageAffichage;

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
        void ajouter(ActionEvent event) {
            String titre = textfield_titre.getText().trim();
            String description = textarea_description.getText().trim();


            if (titre.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("le champ titre est vide");
                alert.showAndWait();
                return;
            }

            if (description.isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("le champ description est vide !!!");
                alert.showAndWait();
                return;
            }

            ServiceReponse sre = new ServiceReponse();
           Reponse R = new Reponse(titre,description,2);
            sre.ajouter(R);
        }

        @FXML
        private void gotodisplay(MouseEvent event) throws IOException {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReponseAffichage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }

    @FXML
    void gotoreclamation(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamationfxml.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }





    }








