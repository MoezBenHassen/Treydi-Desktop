package GUI;


import Entities.Reponse;

import Services.ServiceReponse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AffichageReponseUserControlleur  implements Initializable {


    @FXML
    private VBox pnrep = null;
    @FXML
    private Label titrerep;

    @FXML
    private Label descriptionrep;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label idrep;
    private List<Reponse> reponses;

    @FXML
    private int idReclamation;

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
        System.out.println(idReclamation);
        afficherRe(idReclamation);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {




        }


    @FXML
    private void afficherRe(int idReclamation) {
        ServiceReponse sp = new ServiceReponse();
        reponses = sp.afficherRep(idReclamation);
        System.out.println(idReclamation);
        VBox vbox = new VBox();

        scrollPane.setContent(vbox);

        Slider slider = new Slider(0.5, 2, 1);
        slider.setStyle("-fx-background-color: transparent; -fx-control-inner-background: transparent;");
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            vbox.setScaleX(newValue.doubleValue());
            vbox.setScaleY(newValue.doubleValue());
        });
        scrollPane.setStyle("-fx-background-color: transparent;");

        for (Reponse obj : reponses) {

            Label titrerep = new Label(obj.getTitre_reponse());
            Label descriptionrep = new Label(obj.getDescription_reponse());

            titrerep.setStyle("-fx-text-fill: black;-fx-font-family: 'Sans serif' !important;");
            descriptionrep.setStyle("-fx-text-fill: black;-fx-font-family: 'Sans serif' !important;");
            HBox hbox = new HBox(titrerep, descriptionrep);
            titrerep.setAlignment(Pos.CENTER_LEFT);

            descriptionrep.setAlignment(Pos.CENTER);
            hbox.setSpacing(150);
            hbox.setPrefWidth(1090);
            hbox.setPrefHeight(150);
            hbox.setStyle("-fx-background-radius: 25");
            hbox.setOnMouseEntered(e -> hbox.setStyle("-fx-background-color: #56144D; -fx-text-fill: #56144D !important; -fx-border-radius: 5;-fx-background-radius: 5;"));
            hbox.setOnMouseExited(e -> hbox.setStyle("-fx-background-color: transparent;"));
            hbox.setAlignment(Pos.CENTER_LEFT);
            vbox.setAlignment(Pos.CENTER);
            vbox.setStyle("-fx-background-color: transparent; -fx-background-radius: 25");

            vbox.getChildren().add(hbox);


        }
}



   /*@FXML
    private void afficherRep(int idReclamation) {
        ServiceReponse sr = new ServiceReponse();

         tableView.getItems().clear();
        tableView.getItems().addAll(Reponsess);

    }*/

    /*@FXML
    private void afficher() {
        ServiceReponse sr = new ServiceReponse();
        List<Reponse> Reponses = sr.afficher() ;
        tableView.getItems().clear();
        tableView.getItems().addAll(Reponses);

    }
*/
/*
    @FXML
    void gotoajout(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutReponse.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }*/
    /*
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
            stage.setScene(scene);
            stage.showAndWait();
        }
    }*/
    /*@FXML
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
    }*/

        Stage stage;
        @FXML
        private AnchorPane scenePane;
        public void logout (MouseEvent event){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText("You're about to exit ! ");

            if (alert.showAndWait().get() == ButtonType.OK) {
                stage = (Stage) scenePane.getScene().getWindow();

                stage.close();
            }
        }

        @FXML
        public void Minimize (MouseEvent event ){
            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage1.setIconified(true);
        }

    }

