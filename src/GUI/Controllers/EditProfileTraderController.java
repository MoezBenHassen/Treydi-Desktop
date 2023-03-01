package GUI.Controllers;

import Services.UtilisateurService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import Entities.Utilisateur;
import Services.UtilisateurService;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.ResourceBundle;

public class EditProfileTraderController implements Initializable {
    private Utilisateur selectedUser;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private Button bteditAvatar;
    @FXML
    private AnchorPane scenePane;
    @FXML
    private ImageView imageview_imageurl ;

    @FXML
    private ImageView ivavatar;
    Stage stage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void setSelectedUser(Utilisateur item) {
        selectedUser = item;
        populateFields();
    }


    @FXML
    private void select_image() {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageview_imageurl.setImage(image);
        }
    }
    private void populateFields() {
        tfnom.setText(selectedUser.getNom());
        tfprenom.setText(selectedUser.getPrenom());
        tfpassword.setText(selectedUser.getPassword());

        Image image = new Image(selectedUser.getAvatar_url());
        imageview_imageurl.setImage(image);

    }


    @FXML
    public void Minimize(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
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



}
