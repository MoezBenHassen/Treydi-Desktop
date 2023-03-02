package GUI;

import Entities.Reclamation;
import Entities.Reponse;
import Services.ServiceReclamation;
import Services.ServiceReponse;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ModifierReclamationUserControlleur implements Initializable {
    private Reclamation selectedReclamation;
    @FXML
    private TextField titreTextField;
    @FXML
    private TextField descriptionTextField;
    private int idReclamation  ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    public void setIdReclamation(int idReclamation) {this.idReclamation = idReclamation;}

    @FXML
    void modifier(ActionEvent event)  throws IOException {
        String titre = titreTextField.getText().trim();
        String description = descriptionTextField.getText().trim();
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

        ServiceReclamation sre = new ServiceReclamation();
        Reclamation R = new Reclamation(idReclamation,titre, description) ;
        sre.modifier(R);
        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }
    public void setTitreText(String text) {
        titreTextField.setText(text);
    }
    public void setDescriptionText(String text) {
        descriptionTextField.setText(text);
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