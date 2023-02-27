package GUI.Controllers;

import Entities.Livreur;
import Entities.Utilisateur;
import Services.UtilisateurService;
import Entities.Trader;
import Utils.Enums.Roles;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class InscriptionController implements Initializable {
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
    private ToggleGroup role;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void openLoginPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();

            // Close the current window
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            System.out.println("Exception loading FXML file: " + ex.getMessage());
        }
    }

      @FXML
      private void add(ActionEvent event) {
          UtilisateurService us= new UtilisateurService();
          RadioButton selected_role = (RadioButton) role.getSelectedToggle() ;

          Roles rt = null;

          String srt = selected_role.getText() ;
          if (srt.equals("trader")) {
              rt = Roles.valueOf("trader");
              Utilisateur user = new Trader(tfpassword.getText(),tfnom.getText(),tfprenom.getText(),tfemail.getText(),tfadresse.getText(),null,rt,0,new Date(0,0,0),0);
              if (tfpassword.getText().isEmpty() || tfnom.getText().isEmpty() || tfprenom.getText().isEmpty() || tfemail.getText().isEmpty() || tfadresse.getText().isEmpty()) {

                  System.out.println("Error: Please fill in all the fields.");
              } else {
                  us.add(user);
                  openLoginPage(event);
              }
          }
          else if (srt.equals("livreur")) {
              rt = Roles.valueOf("livreur");
              Utilisateur user2 = new Livreur(tfpassword.getText(),tfnom.getText(),tfprenom.getText(),tfemail.getText(),tfadresse.getText(),null,Roles.livreur,0);
              if (tfpassword.getText().isEmpty() || tfnom.getText().isEmpty() || tfprenom.getText().isEmpty() || tfemail.getText().isEmpty() || tfadresse.getText().isEmpty()) {

                  System.out.println("Error: Please fill in all the fields.");
              } else {
                  us.add(user2);
                  openLoginPage(event);
              }
          }
      }




}
