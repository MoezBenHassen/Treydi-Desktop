package GUI;

import Entities.Reponse;

import Services.ServiceReponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModificationReponseControlleur implements Initializable  {
    private Reponse selectedReponse;
    @FXML
    private TextField titreR;
    @FXML
    private TextField descriptionR;
    private int idReponse  ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    public void setIdRepnse(int idReponse) {this.idReponse = idReponse;}

    @FXML
    void modifier(ActionEvent event)  throws IOException {
        String titre = titreR.getText().trim();
        String description = descriptionR.getText().trim();
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
        Reponse R = new Reponse(idReponse,titre, description) ;
        sre.modifier(R);
        stage = (Stage) scenePane.getScene().getWindow();

        stage.close();
    }
    public void setTitreText(String text) {
        titreR.setText(text);
    }
    public void setDescriptionText(String text) {
        descriptionR.setText(text);
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
