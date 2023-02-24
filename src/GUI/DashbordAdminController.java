package GUI;
import Entities.Utilisateur;
import Services.UtilisateurService;
import Utils.Enums.Roles;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DashbordAdminController implements Initializable {

    @FXML
    private TableView<Utilisateur> tableView;

    @FXML
    private TableColumn<Utilisateur, Integer> tfarchived;

    @FXML
    private TableColumn<Utilisateur, Integer> tfid_user;
    @FXML
    private TableColumn<Utilisateur, String> tfnom;

    @FXML
    private TableColumn<Utilisateur, String> tfprenom;

    @FXML
    private TableColumn<Utilisateur, String> tfemail;

    @FXML
    private TableColumn<Utilisateur, String> tfadresse;

    @FXML
    private TableColumn<Utilisateur, Roles> tfrole;
    @FXML
    private ImageView route_AI;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tfarchived.setCellValueFactory(new PropertyValueFactory<>("archived"));
        tfid_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        tfprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tfnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tfemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tfadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tfrole.setCellValueFactory(new PropertyValueFactory<>("role"));

        afficher() ;



    }

    @FXML
    private void afficher() {
        UtilisateurService us = new UtilisateurService();
        List<Utilisateur> utilisateurs = us.afficher();

        tableView.getItems().clear();
        tableView.getItems().addAll(utilisateurs);




    }
/*
    @FXML
    private void route_AjouterUtilisateur(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterItemFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void route_ModifierUtilisateur(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierItemFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void route_SupprimerUtilisateur(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SupprimerItemFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

 */
}