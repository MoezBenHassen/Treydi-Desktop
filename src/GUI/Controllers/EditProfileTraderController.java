package GUI.Controllers;

import Entities.Trader;
import Services.UtilisateurService;
import Utils.CurrentUser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import Entities.Utilisateur;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditProfileTraderController implements Initializable {
    private double xOffset = 0;
    private double yOffset = 0;
    private Trader selectedUser;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfdate;
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
    private ImageView avatarImg;

    Stage stage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateFields();
        Image image = new Image(CurrentUser.getInstance().getAvatar_url());
        imageview_imageurl.setImage(image);
        avatarImg.setImage(image);

    }
    public void setSelectedUser(Trader tr) {
        selectedUser = tr;
        populateFields();
    }


    @FXML
    private void select_image() {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        // Set extension filter to allow selecting all JPG and PNG files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.jpg, *.png)", "*.jpg", "*.JPG", "*.png", "*.PNG");
        fileChooser.getExtensionFilters().add(extFilter);


        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageview_imageurl.setImage(image);
        }
    }
    private void populateFields() {
        tfnom.setText(CurrentUser.getInstance().getNom());
        tfprenom.setText(CurrentUser.getInstance().getPrenom());
        tfpassword.setText(CurrentUser.getInstance().getPassword());
        tfdate.setText(CurrentUser.getInstance().getDate_naissance());
        Image image = new Image(CurrentUser.getInstance().getAvatar_url());
        imageview_imageurl.setImage(image);
        avatarImg.setImage(image);
    }
    @FXML
    private void modifier(ActionEvent event) throws IOException {
        UtilisateurService us = new UtilisateurService();
        if (tfnom.getText().equals("") || tfprenom.getText().equals("") || tfpassword.getText().equals("")|| tfdate.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Erreur");
            a.setContentText("Veuillez remplir tous les champs.");
            a.show();

        } else  {

            Utilisateur newUser = new Trader(tfpassword.getText(), tfnom.getText(), tfprenom.getText(),CurrentUser.getInstance().getEmail(),CurrentUser.getInstance().getAdresse(),imageview_imageurl.getImage().impl_getUrl(),CurrentUser.getInstance().getRoles(),CurrentUser.getInstance().getScore(), tfdate.getText(),CurrentUser.getInstance().getId_user(),0);

            us.modifier(newUser);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Notification");
            a.setContentText("L'utilisateur a été modifié avec succès!");
            a.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../AfficherItemUserFXML.fxml"));
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
            // Move the window
            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            });
            stage.setScene(scene);
            stage.show();

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
