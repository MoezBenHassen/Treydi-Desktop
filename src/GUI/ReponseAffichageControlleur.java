package GUI;


import Entities.Reclamation;
import Entities.Reponse;

import Services.ServiceReclamation;
import Services.ServiceReponse;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ReponseAffichageControlleur implements Initializable {
    
    @FXML
    private TableView<Reponse> tableView;

    @FXML
    private TableColumn<Reponse, String> titreR;

    @FXML
    private TableColumn<Reponse, String> descriptionR;

    @FXML
    private TextField titrecher ;
    private List<Reponse> reponsess ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        titreR.setCellValueFactory(new PropertyValueFactory<>("titre_reponse"));
        descriptionR.setCellValueFactory(new PropertyValueFactory<>("description_reponse"));
        afficher();
    }

    @FXML
    private void afficher() {
        ServiceReponse sr = new ServiceReponse();
        List<Reponse> Reponses = sr.afficher() ;
        tableView.getItems().clear();
        tableView.getItems().addAll(Reponses);

    }


    @FXML
     void gotoajout(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutReponse.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
//jdid
    @FXML
    void openModifcation(MouseEvent event) throws IOException {
        Reponse selectedReponse = tableView.getSelectionModel().getSelectedItem();
        String titreR = String.valueOf(selectedReponse.getTitre_reponse());
        String descriptionR = String.valueOf(selectedReponse.getDescription_reponse());

        if (selectedReponse != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificationReponse.fxml"));
            Parent root = loader.load();
            ModificationReponseControlleur modificationreponseControlleur = loader.getController();
            modificationreponseControlleur.setTitreText(selectedReponse.getTitre_reponse());
            modificationreponseControlleur.setDescriptionText(selectedReponse.getDescription_reponse());
            modificationreponseControlleur.setIdRepnse(selectedReponse.getId_reponse());

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.showAndWait();
            afficher();
        }
    }
    @FXML
    void supprimer(MouseEvent event) {
        Reponse selectedrep = tableView.getSelectionModel().getSelectedItem();
        if (selectedrep != null) {
            ServiceReponse servicerep = new ServiceReponse();

            boolean result = servicerep.supprimer(selectedrep);

            if (result) {
                tableView.getItems().remove(selectedrep);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Erreur lors de la suppression .");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner  .");
            alert.showAndWait();
        }
    }
    @FXML
    void chercher() {
        String searchText = titrecher.getText().toLowerCase();
        ServiceReponse sp = new ServiceReponse() ;
        reponsess = sp.afficher();
        List<Reponse> repstream = reponsess.stream()
                .filter((t) -> t.getTitre_reponse().toLowerCase().contains(searchText)
                        || t.getDescription_reponse().toLowerCase().contains(searchText))
                .collect(Collectors.toList());

        List<Reponse> filteredrep = new ArrayList<>();



        if (filteredrep.isEmpty()) {
            tableView.setItems(FXCollections.observableArrayList(repstream));
        } else {
            tableView.setItems(FXCollections.observableArrayList(filteredrep));
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







