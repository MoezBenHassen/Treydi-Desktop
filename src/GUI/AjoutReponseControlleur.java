package GUI;

import Entities.Reclamation;
import Entities.Reponse;
import Services.ServiceReclamation;
import Services.ServiceReponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage  ;
import jdk.nashorn.internal.objects.Global;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AjoutReponseControlleur implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    private Reclamation selectedReclamation;
    @FXML
    private TextField textfield_titre;
    @FXML
    private TextField textfield_description;
    @FXML
    private int idReclamation;
    public void setIdReclamation(int idReclamation) {this.idReclamation = idReclamation;}
    @FXML
    void ajouter(ActionEvent event) {
        String titre = textfield_titre.getText().trim();
        String description = textfield_description.getText().trim();


        if (titre.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("le champ titre est vide");
            alert.showAndWait();
            return;
        }
        if (titre.length() > 20) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("le titre ne doit pas dépasser 20 caractères");
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
        Reponse R = new Reponse(titre, description,idReclamation) ;
        sre.ajouter(R);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("La Reponse a été ajoutée avec succès !");
        alert.setOnCloseRequest(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ReclamationAffichage.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        alert.showAndWait();


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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReclamationAffichage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
















