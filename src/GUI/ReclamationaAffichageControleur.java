package GUI;

import Entities.Etat_reclamation;
import Entities.Reclamation;
import Entities.Reponse;
import Services.ServiceReclamation;
import Services.ServiceReponse;
import javafx.collections.FXCollections;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ReclamationaAffichageControleur implements Initializable {



    @FXML
    private TableView<Reclamation> tableView;

    @FXML
    private TableColumn<Reclamation, String> titreC;

    @FXML
    private TableColumn<Reclamation, String> descriptionC;

    @FXML
    private TableColumn<Reclamation,Integer> id_reclamation;
    @FXML
    private TableColumn<Reclamation, Etat_reclamation> etat_reclamation;

    @FXML
    private TextField  titrecher ;

    private List<Reclamation> reclamationss ;

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        titreC.setCellValueFactory(new PropertyValueFactory<>("titre_reclamation"));
        descriptionC.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionC.setCellFactory(column -> {
            TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {
                private final Text text;

                {
                    text = new Text();
                    text.setFill(Color.WHITE);
                    text.wrappingWidthProperty().bind(descriptionC.widthProperty());
                    setGraphic(text);
                    setPrefHeight(Control.USE_COMPUTED_SIZE);
                    setWrapText(true);
                }

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    text.setText(empty ? null : item);
                }
            };
            return cell;
        });

        id_reclamation.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));
        etat_reclamation.setCellValueFactory(new PropertyValueFactory<>("etat_reclamation"));
        afficher();


        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                try {
                    openReponse(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    @FXML
    private void afficher() {
        ServiceReclamation sr = new ServiceReclamation();
        List<Reclamation> reclamations = sr.afficher() ;
        tableView.getItems().clear();
        tableView.getItems().addAll(reclamations);
    }


    /*@FXML
    private void gotoajout(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutReclamationAdmin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }*/
    @FXML
    private void gotoAffichagereponse(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReponseAffichage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void openReponse(MouseEvent event) throws IOException {
        Reclamation selectedReclamation = tableView.getSelectionModel().getSelectedItem();

        if (selectedReclamation == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select a reclamation");
            alert.showAndWait();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutReponse.fxml"));
        Parent root = loader.load();

        AjoutReponseControlleur ajoutReponseController = loader.getController();

        ajoutReponseController.setIdReclamation(selectedReclamation.getId_reclamation());

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
     void chercher() {
        String searchText = titrecher.getText().toLowerCase();
        ServiceReclamation sp = new ServiceReclamation() ;
        reclamationss = sp.afficher();
        List<Reclamation> recstream = reclamationss.stream()
                .filter((t) -> t.getTitre_reclamation().toLowerCase().contains(searchText)
                        || t.getDescription().toLowerCase().contains(searchText))
                .collect(Collectors.toList());

        List<Reclamation> filtererec = new ArrayList<>();

        if (filtererec.isEmpty()) {
            tableView.setItems(FXCollections.observableArrayList(recstream));
        } else {
            tableView.setItems(FXCollections.observableArrayList(filtererec));
        }
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
