package GUI.Controllers;
import Entities.Trader;
import Entities.Utilisateur;
import Services.UtilisateurService;
import Utils.CurrentUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import Utils.Enums.Roles;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Utils.CurrentUser;
import javafx.stage.StageStyle;

import javax.swing.text.html.ImageView;

public class LoginController implements  Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private Roles role;
    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane scenePane;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField erreurfd;
    @FXML
    private ImageView minimize;
    @FXML
    private Hyperlink inscriptionLink;
    Connection cnx;



    @FXML
    public void login(javafx.event.ActionEvent event) {
        UtilisateurService us = new UtilisateurService();
        int id = us.Validate(tfemail.getText(), tfpassword.getText());
        if (id != 0) {
            Utilisateur user = us.getUserById(id);
            Trader trader =  us.getTraderById(id);
            try {
                // Set the current user instance
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setTitle("Welcome to Treydi");
                System.out.println("User role: " + user.getRole());
                if (user.getRole().name().equalsIgnoreCase(Roles.admin.name())) {
                    CurrentUser.setInstance(CurrentUser.getInstance(
                            user.getPassword(), user.getNom(), user.getPrenom(),
                            user.getEmail(), user.getAdresse(), user.getAvatar_url(),
                            user.getRole(), user.getId_user()));
                    System.out.println(CurrentUser.getInstance().getId_user());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../DashboardAdmin.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Login");
                    stage.setScene(scene);



                    stage.initStyle(StageStyle.UNDECORATED);
                    scene.setFill(Color.TRANSPARENT);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    Image image = new Image("GUI/Assets/images/log-04.png",32,32,true,true);
                    stage.getIcons().add(image);
                    stage.show();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                    CurrentUser currentUser = CurrentUser.getInstance();
                    Roles userRole = currentUser.getRole();
                    System.out.println("User role: " + userRole);

                }
                if (user.getRole().name().equalsIgnoreCase(Roles.livreur.name())) {
                    CurrentUser.setInstance(CurrentUser.getInstance(
                            user.getPassword(), user.getNom(), user.getPrenom(),
                            user.getEmail(), user.getAdresse(), user.getAvatar_url(),
                            user.getRole(), user.getId_user()));

                    System.out.println(CurrentUser.getInstance().getId_user());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../LivreurHome.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Login");
                    stage.setScene(scene);
                    stage.show();
                    CurrentUser currentUser = CurrentUser.getInstance();
                    Roles userRole = currentUser.getRole();
                    System.out.println("User role: " + userRole);
                    // Close the current window
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    stage.initStyle(StageStyle.UNDECORATED);
                    scene.setFill(Color.TRANSPARENT);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    Image image = new Image("GUI/Assets/images/log-04.png",32,32,true,true);
                    stage.getIcons().add(image);
                 /*   System.out.println(CurrentUser.getInstance().getScore());
                    System.out.println(CurrentUser.getInstance().getDate_naissance());
                    System.out.println("----------");
                    System.out.println(CurrentUser.getInstance().getAvatar_url());
                    System.out.println("----------");
                    System.out.println(CurrentUser.getInstance().getNom());
                    *
                  */
                }
                if (trader.getRole().name().equalsIgnoreCase(Roles.trader.name())) {
                    CurrentUser.setInstance(CurrentUser.getInstance(
                            user.getPassword(), user.getNom(), user.getPrenom(),
                            user.getEmail(), user.getAdresse(), user.getAvatar_url(),
                            user.getRole(),trader.getScore(),trader.getDate_naissance(), user.getId_user()));
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../AfficherItemUserFXML.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();

                    stage.initStyle(StageStyle.UNDECORATED);
                    scene.setFill(Color.TRANSPARENT);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    Image image = new Image("GUI/Assets/images/log-04.png",32,32,true,true);
                    stage.getIcons().add(image);

                    stage.setTitle("Login");
                    stage.setScene(scene);
                    stage.show();
                    CurrentUser currentUser = CurrentUser.getInstance();
                    Roles userRole = currentUser.getRole();
                    System.out.println("User role: " + userRole);
                    // Close the current window
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                  /*  System.out.println(CurrentUser.getInstance().getScore());
                    System.out.println(CurrentUser.getInstance().getAdresse());
                    System.out.println(CurrentUser.getInstance().getEmail());
                    System.out.println(CurrentUser.getInstance().getPassword());
                    System.out.println(CurrentUser.getInstance().getPrenom());
                    System.out.println(CurrentUser.getInstance().getAvatar_url());
                    System.out.println("----------");
                    System.out.println(CurrentUser.getInstance().getDate_naissance());
                    System.out.println(CurrentUser.getInstance().getNom());
                    */
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Inscription.fxml"));
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
    public void ForgetPass(javafx.event.ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ForgetPass.fxml"));
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
