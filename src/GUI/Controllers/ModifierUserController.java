package GUI.Controllers;

import Entities.Livreur;
import Entities.Trader;
import Entities.Utilisateur;
import Services.UtilisateurService;
import Utils.Enums.Roles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ModifierUserController implements Initializable {
    @FXML
    private TextField textfield_id;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfscore;
    @FXML
    private ToggleGroup role;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML

    public void modifier(javafx.event.ActionEvent event) throws IOException {
        UtilisateurService us = new UtilisateurService();
        RadioButton selected_role = (RadioButton) role.getSelectedToggle();
        Roles rt = null;
        String srt = selected_role.getText();

        List<Utilisateur> users = us.afficher();
        boolean found = false;
        Utilisateur userToUpdate = null;
        for (Utilisateur user : users) {
            if (user.getId_user() == Integer.parseInt(textfield_id.getText())) {
                found = true;
                userToUpdate = user;
                break;
            }
        }

        if (!found) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Vous n'avez pas cet utilisateur.");
            a.show();
        } else {
            if (srt.equals("trader")) {
                rt = Roles.valueOf("trader");
                Utilisateur tr = new Trader(tfpassword.getText(),tfnom.getText(),tfprenom.getText(),tfemail.getText(),tfadresse.getText(),null,rt,0,new Date(0,0,0),Integer.parseInt(textfield_id.getText()));

                System.out.println("Before update: " + tr.toString());
                us.modifier(tr);
                boolean updated = us.modifier(tr); // or us.modifier(livreur) depending on the selected role
                if (updated) {
                    System.out.println("User updated successfully.");
                } else {
                    System.out.println("Error updating user.");
                }
                System.out.println("After update: " + tr.toString());
            } else if (srt.equals("livreur")) {
                rt = Roles.livreur;
                Utilisateur lv = new Trader(tfpassword.getText(),tfnom.getText(),tfprenom.getText(),tfemail.getText(),tfadresse.getText(),null,rt,0,new Date(0,0,0),Integer.parseInt(textfield_id.getText()));

                System.out.println("Before update: " + lv.toString());
                us.modifier(lv);
                boolean updated = us.modifier(lv);
                if (updated) {
                    System.out.println("User updated successfully.");
                } else {
                    System.out.println("Error updating user.");
                }
                System.out.println("After update: " + lv.toString());
            }
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("L'entrée d'utilisateur a été modifiée avec succès!");
            a.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../DashboardAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
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
