package Controllers;

import Entities.Livreur;
import Entities.Utilisateur;
import Services.UtilisateurService;
import Entities.Trader;
import Utils.Enums.Roles;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;


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
    private void add(ActionEvent event) {
        UtilisateurService us= new UtilisateurService();
        RadioButton selected_role = (RadioButton) role.getSelectedToggle() ;

        Roles rt = null;

        String srt = selected_role.getText() ;
        System.out.println(srt);
        if (srt.equals("trader")) {
            rt = Roles.valueOf("trader");

        } else if (srt.equals("livreur")) {
            rt = Roles.valueOf("livreur");
        }

       // Utilisateur user = new Trader(tfpassword.getText(),tfnom.getText(),tfprenom.getText(),tfemail.getText(),tfadresse.getText(),null,rt,0,new Date(0,0,0));
        Utilisateur user = new Livreur(tfpassword.getText(),tfnom.getText(),tfprenom.getText(),tfemail.getText(),tfadresse.getText(),null,rt);
       us.add(user);



    }


}
