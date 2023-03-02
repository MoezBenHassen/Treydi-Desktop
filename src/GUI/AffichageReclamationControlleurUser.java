package GUI;

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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class AffichageReclamationControlleurUser  implements Initializable {
    @FXML
    private TextField titrechercher;
    private  List<Reclamation> reclist ;
    @FXML
    private TableView<Reclamation> tableView;

    @FXML
    private TableColumn<Reclamation, String> titreC;

    @FXML
    private TableColumn<Reclamation, String> descriptionC;

    @FXML
    private TableColumn<Reclamation,Integer> id_reclamation;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Reclamation selectedReclamation = tableView.getSelectionModel().getSelectedItem();
                // load the affichageReponse.fxml file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageReponseUser.fxml"));
                try {
                    Parent root = loader.load();
                    // assuming you have a controller for the affichageReponse.fxml file
                    AffichageReponseUserControlleur controller = loader.getController();
                    // pass any necessary data to the controller
                    controller.setIdReclamation(selectedReclamation.getId_reclamation());
                    // switch to the affichageReponse.fxml view
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) tableView.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        titreC.setCellValueFactory(new PropertyValueFactory<>("titre_reclamation"));
        descriptionC.setCellValueFactory(new PropertyValueFactory<>("description"));
        id_reclamation.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));
        afficher();
        ServiceReclamation sr = new ServiceReclamation();
        reclist = sr.afficher();

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutReclamationUser.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    /*
    @FXML
    void openReponseaffichage(MouseEvent event) throws IOException {
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
*/
    @FXML
    void openModifcation(MouseEvent event) throws IOException {
        Reclamation selectedReclamation = tableView.getSelectionModel().getSelectedItem();
        String tittre = String.valueOf(selectedReclamation.getTitre_reclamation());
        String description = String.valueOf(selectedReclamation.getDescription());

        if (selectedReclamation != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierReclamationUser.fxml"));
            Parent root = loader.load();
            ModifierReclamationUserControlleur modificationControlleur = loader.getController();
            modificationControlleur.setTitreText(selectedReclamation.getTitre_reclamation());
            modificationControlleur.setDescriptionText(selectedReclamation.getDescription());
            modificationControlleur.setIdReclamation(selectedReclamation.getId_reclamation());

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
           /* stage.setWidth(1024);
            stage.setHeight(768); */
            stage.setScene(scene);
            stage.showAndWait();
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
    @FXML
    private void chercher() {
        String titreCe = titrechercher.getText();
        List<Reclamation> Restream = reclist ;
        if (!(titreCe.equals("") )) {
            Restream = Restream.stream().filter((t) -> t.getTitre_reclamation().toLowerCase().contains(titreCe.toLowerCase())).collect(Collectors.toList());
        }

            tableView.setItems(FXCollections.observableArrayList(Restream));

    }
}
