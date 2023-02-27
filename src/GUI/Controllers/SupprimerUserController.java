package GUI.Controllers;

import Entities.Utilisateur;
import Services.UtilisateurService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SupprimerUserController implements Initializable {
    @FXML
    private TextField textfield_id;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void supprimer (javafx.event.ActionEvent event) throws IOException {
        UtilisateurService sp = new UtilisateurService();
        List<Utilisateur> users = sp.afficher();
        boolean found = false ;

        if (textfield_id.getText().matches("-?\\d+")) {
            for (Utilisateur user : users) {
                if (user.getId_user() == Integer.parseInt(textfield_id.getText())) {
                    found = true;
                    sp.supprimer(user);
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("L'entrée d'utilisateur a été supprimée avec succès!");
                    a.show();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../DashboardAdmin.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }
            }


            if (found == false) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Vous n'avez pas cet article.");
                a.show();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Veuillez insérer un id de votre article.");
            a.show();

        }
    }

    @FXML
    private void route_ModifierUtilisateur(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ModifierUser.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void route_AfficherUtilisateur(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../DashboardAdmin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void route_SupprimerUtilisateur(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../SupprimerUser.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
