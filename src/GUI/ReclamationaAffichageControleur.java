package GUI;

import Entities.Reclamation;
import Entities.Reponse;
import Services.ServiceReclamation;
import Services.ServiceReponse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReclamationaAffichageControleur implements Initializable {



    @FXML
    private TableView<Reclamation> tableView;

    @FXML
    private TableColumn<Reclamation, String> titreC;

    @FXML
    private TableColumn<Reclamation, String> descriptionC;

    private TableColumn<Reclamation,Integer> id_reclamation;


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

    @FXML
    void openReponse(MouseEvent event) throws IOException {
        Reclamation selectedReclamation = tableView.getSelectionModel().getSelectedItem();

        // check if a reclamation is selected
        if (selectedReclamation == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select a reclamation");
            alert.showAndWait();
            return;
        }

        // create a new FXMLLoader instance and load the AjoutReponse.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutReponse.fxml"));
        Parent root = loader.load();

        // get the controller of the AjoutReponseController
        AjoutReponseControlleur ajoutReponseController = loader.getController();

        // pass the id_reclamation to the AjoutReponseController
        ajoutReponseController.setIdReclamation(selectedReclamation.getId_reclamation());

        // create a new Scene and set the stage
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }








    @FXML
    void supprimer(MouseEvent event) {
        Reclamation selectedre = tableView.getSelectionModel().getSelectedItem();
        if (selectedre != null) {

            ServiceReclamation servicere = new ServiceReclamation();

            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation");
            confirmation.setHeaderText(null);
            confirmation.setContentText("Êtes-vous sûr de vouloir supprimer ?");
            Optional<ButtonType> result = confirmation.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                boolean supprime = servicere.supprimer(selectedre);
                if (supprime) {
                    tableView.getItems().remove(selectedre);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Suppression réussie");
                    alert.setHeaderText(null);
                    alert.setContentText("Suppression effectuée avec succès.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Erreur lors de la suppression.");
                    alert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une réclamation.");
            alert.showAndWait();
        }

    }



}
