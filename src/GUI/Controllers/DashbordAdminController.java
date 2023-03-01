package GUI.Controllers;
import Entities.Utilisateur;
import Services.UtilisateurService;
import Utils.Enums.Roles;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DashbordAdminController implements Initializable {

    @FXML
    private TableView<Utilisateur> tableView;
    @FXML
    private TableColumn<Utilisateur, Integer> tfid_user;
    @FXML
    private TableColumn<Utilisateur, Integer> tfarchived;
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
    private TableColumn<Utilisateur, Date> tfdate;
    @FXML
    private TableColumn<Utilisateur, Integer> tfscore;
    @FXML
    private TextField textfield_email;
    @FXML
    private TextArea textarea_adresse;
    @FXML
    private TextField textfield_nom;
    @FXML
    private CheckBox cb_type_1 ;

    @FXML
    private CheckBox cb_type_2 ;

    @FXML
    private CheckBox cb_type_3 ;

    @FXML
    private Pane chercherPane;
    @FXML
    private ImageView minimize;
    @FXML
    private AnchorPane scenePane;

    private List<Utilisateur> users;
    Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tfarchived.setCellValueFactory(new PropertyValueFactory<>("archived"));
        tfprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tfnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tfemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tfadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tfrole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tfscore.setCellValueFactory(new PropertyValueFactory<>("score"));
        tfdate.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));


        afficher() ;
        UtilisateurService us = new UtilisateurService();
        users = us.afficher();
        tableView.setItems(FXCollections.observableArrayList(users));


    }

    @FXML
    private void afficher() {
        UtilisateurService us = new UtilisateurService();
        List<Utilisateur> utilisateurs = us.afficher();

        tableView.getItems().clear();
        tableView.getItems().addAll(utilisateurs);

    }

    @FXML
    private void chercher() {
        String c_email = textfield_email.getText();
        String c_adr = textarea_adresse.getText();
        String c_nom = textfield_nom.getText();

        List<Utilisateur> userstream = users;

        if (!(c_email.equals("") || c_email.equals("Insérer email"))) {
            userstream = userstream.stream().filter((t) -> t.getEmail().toLowerCase().contains(c_email.toLowerCase())).collect(Collectors.toList());
        }

        if (!(c_adr.equals("") || c_adr.equals("Insérer mots clés"))) {
            userstream = userstream.stream().filter(t -> t.getAdresse().toLowerCase().contains(c_adr.toLowerCase())).collect(Collectors.toList());
        }
        if (!(c_nom.equals("") || c_nom.equals("Insérer nom"))) {
            userstream = userstream.stream().filter(t -> t.getNom().toLowerCase().contains(c_nom.toLowerCase())).collect(Collectors.toList());
        }

        List<Utilisateur> userstreamc1 = userstream;
        List<Utilisateur> userstreamc2 = userstream;
        List<Utilisateur> userstreamc3 = userstream;


        if (cb_type_1.isSelected()) {
            userstreamc1 = userstreamc1.stream().filter((t) -> t.getRole() == Roles.admin)
                    .collect(Collectors.toList());
        } else {
            userstreamc1 = new ArrayList<>();
        }

        if (cb_type_2.isSelected()) {
            userstreamc2 = userstreamc2.stream().filter((t) -> t.getRole() == Roles.trader)
                    .collect(Collectors.toList());
        } else {
            userstreamc2 = new ArrayList<>();
        }

        if (cb_type_3.isSelected()) {
            userstreamc3 = userstreamc3.stream().filter((t) -> t.getRole() == Roles.livreur)
                    .collect(Collectors.toList());
        } else {
            userstreamc3 = new ArrayList<>();
        }

        if (!((cb_type_1.isSelected()) || (cb_type_2.isSelected()) || (cb_type_3.isSelected()))) {
            tableView.setItems(FXCollections.observableArrayList(userstream));
        } else {
            List<Utilisateur> userstreamcf = userstreamc1;
            userstreamcf.addAll(userstreamc2);
            userstreamcf.addAll(userstreamc3);
            tableView.setItems(FXCollections.observableArrayList(userstreamcf));
        }
    }

    @FXML
    void supprimer(javafx.event.ActionEvent event) {
        Utilisateur selectedre = tableView.getSelectionModel().getSelectedItem();
        if (selectedre != null) {

            UtilisateurService us = new UtilisateurService();

            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation");
            confirmation.setHeaderText(null);
            confirmation.setContentText("Êtes-vous sûr de vouloir supprimer ?");
            Optional<ButtonType> result = confirmation.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                boolean supprime = us.supprimer(selectedre);
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
    public void logout(javafx.event.ActionEvent actionEvent) {

        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to exit ! ");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("YOU XYZ");
            stage.close();
        }
    }

    @FXML
    public void Minimize(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void route_ChercherItem(javafx.event.ActionEvent event) throws IOException {
        chercherPane.setVisible(true);
    }

    @FXML
    private void route_ChercherItemH(MouseEvent event) throws IOException {
        chercherPane.setVisible(false);
    }


    @FXML
    private void route_ChercherItemR(MouseEvent event) throws IOException {
        textfield_email.setText("Insérer email");
        textarea_adresse.setText("Insérer mots clés");
        textfield_nom.setText("Insérer nom");

        cb_type_1.setSelected(false);
        cb_type_2.setSelected(false);
        cb_type_3.setSelected(false);
        chercher() ;
    }



/*
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
    private void route_ModifierUtilisateur(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ModifierUser.fxml"));
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
*/
}