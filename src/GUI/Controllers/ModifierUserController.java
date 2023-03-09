package GUI.Controllers;

import Entities.Livreur;
import Entities.Trader;
import Entities.Utilisateur;
import Services.UtilisateurService;
import Utils.Enums.Roles;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ModifierUserController implements Initializable {
    private Utilisateur selectedUser;
    private Trader selectedTrader;
    private double xOffset = 0;
    private double yOffset = 0;
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
    private AnchorPane scenePane;
    @FXML
    private TextField tfscore;
    @FXML
    private ToggleGroup role;
    Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setSelectedUser(Utilisateur user) {
        selectedUser = user;
        populateFields();
    }
    public void setSelectedUser(Trader tra) {
        selectedUser = tra;
        populateFields();
    }


    private void populateFields() {
        tfnom.setText(selectedUser.getNom());
        tfprenom.setText(selectedUser.getPrenom());
        tfadresse.setText(selectedUser.getAdresse());
        tfemail.setText(selectedUser.getEmail());
        tfpassword.setText(selectedUser.getPassword());
        textfield_id.setText(String.valueOf(selectedUser.getId_user()));
    }


    @FXML
    private void modifier(ActionEvent event) throws IOException {
        UtilisateurService us = new UtilisateurService();
            List<Utilisateur> users = us.afficher();
            boolean found = false;
            for (Utilisateur user : users) {
                if (user.getId_user() == Integer.parseInt(textfield_id.getText())) {
                    found = true;
                }
            }

            if (found == false) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Erreur");

                a.setContentText("Vous n'avez pas cet user.");
                a.show();
            } else {
                if (tfnom.getText().equals("Insérer nom") || tfprenom.getText().equals("Insérer prenom") || tfadresse.getText().equals("Insérer adresse") || tfemail.getText().equals("Insérer email") || tfpassword.getText().equals("Insérer password")) {
                    Alert a = new Alert(Alert.AlertType.ERROR);

                    a.setHeaderText("Erreur");a.setContentText("Assurez-vous d'insérer des entrées valides pour les détails de votre utilisateur.");
                    a.show();
                } else {
                    String role = String.valueOf(selectedUser.getRole());
                    if (role.equals("livreur")) {
                        Utilisateur livreur = new Livreur(tfpassword.getText(), tfnom.getText(), tfprenom.getText(),  tfemail.getText(), tfadresse.getText() ,  selectedUser.getAvatar_url(),selectedUser.getRole(),Integer.parseInt(textfield_id.getText()), selectedUser.getArchived());
                        us.modifier(livreur);
                    } else if (role.equals("trader")) {
                        Trader trader = new Trader(tfpassword.getText(), tfnom.getText(), tfprenom.getText(),  tfemail.getText(), tfadresse.getText() ,  selectedUser.getAvatar_url(),selectedUser.getRole(),Integer.parseInt(textfield_id.getText()), selectedUser.getArchived());
                        us.modifier(trader);
                    }
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setHeaderText("Notification");
                    a.setContentText("L'entrée d'utilisateur a été modifiée avec succès!");
                    a.show();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/DashboardAdmin.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    root.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            xOffset = event.getSceneX();
                            yOffset = event.getSceneY();
                        }
                    });
                    //move around here
                    root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            stage.setX(event.getScreenX() - xOffset);
                            stage.setY(event.getScreenY() - yOffset);
                        }
                    });
                    scene.setFill(Color.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();


                }
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
    private void route_userlist(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/DashboardAdmin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
