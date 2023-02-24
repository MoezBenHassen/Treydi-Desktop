package GUI;
import Entities.Utilisateur;
import Services.UtilisateurService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import Utils.Enums.Roles;
import java.util.ResourceBundle;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController implements  Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField erreurfd;
    @FXML
    private Hyperlink inscriptionLink;
    Connection cnx;



    @FXML
    public void login(javafx.event.ActionEvent event) {
        UtilisateurService us = new UtilisateurService();
        int id = us.Validate(tfemail.getText(), tfpassword.getText());
        if (id != 0) {
            Utilisateur user = us.getUserById(id);
            try {
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setTitle("Welcome to Treydi");
                System.out.println("User role: " + user.getRole());
                if (user.getRole().name().equalsIgnoreCase(Roles.admin.name())) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardAdmin.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Login");
                    stage.setScene(scene);
                    stage.show();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
               }
                if (user.getRole().name().equalsIgnoreCase(Roles.livreur.name())) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("LivreurHome.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Login");
                    stage.setScene(scene);
                    stage.show();

                    // Close the current window
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                }
                if (user.getRole().name().equalsIgnoreCase(Roles.trader.name())) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("TraderHome.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Login");
                    stage.setScene(scene);
                    stage.show();

                    // Close the current window
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                }
                } catch (IOException ex) {
                System.out.println("Exception loading FXML file: " + ex.getMessage());
            }
        } else {
            erreurfd.setText("Vérifiez votre Email et Mot de passe");
        }
    }
    @FXML
    public void inscription(javafx.event.ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscription.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Get the current stage and set the new scene
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.setTitle("Inscription");
            currentStage.show();
        } catch (IOException ex) {
            System.out.println("Exception loading FXML file: " + ex.getMessage());
        }
    }



}
