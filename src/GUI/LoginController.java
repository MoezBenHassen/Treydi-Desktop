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
    Connection cnx;



    @FXML
    public void login(javafx.event.ActionEvent event) {
        UtilisateurService us = new UtilisateurService();
        int id = us.Validate(tfemail.getText(), tfpassword.getText());
        if (id != 0) {

            Utilisateur user = us.getUserById(id);
            if (user.getRole().equals(Roles.trader.toString())) {
                try {
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AjouterItem.fxml"));
                    Scene scene = new Scene(root);
                    Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("Welcome to Treydi");
                    primaryStage.show();


                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            if (user.getRole().equals(Roles.livreur.toString())) {
                try {
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LivreurHome.fxml"));
                    Scene scene = new Scene(root);

                    Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("Welcome to JobTopia");
                    primaryStage.show();

                }  catch (IOException ex) {
                    System.out.println("Exception loading FXML file: " + ex.getMessage());
                }

            }

        } else {
            erreurfd.setText("Vérifiez votre pseudo et mot de passe");
        }
    }
}
