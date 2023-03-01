/*
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
    private ImageView ivavatar;
    Stage stage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void handleEditAvatar(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Avatar Image");

        // configure file chooser to restrict file type to images
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        // show the file chooser and retrieve the selected file
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                // load the selected file into a byte array
                byte[] imageData = Files.readAllBytes(selectedFile.toPath());

                // create a new Utilisateur object and set the avatar URL to the byte array
                Utilisateur user = new Utilisateur();
                user.setAvatar_url(imageData);

                // call the modifier method to save the user with the updated avatar URL in the database
                UtilisateurService utilisateurService = new UtilisateurService();
                utilisateurService.modifier(user);

                // load the selected file into an Image object
                Image image = new Image(selectedFile.toURI().toString());

                // set the Image object in the ImageView ivavatar
                ivavatar.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
*/