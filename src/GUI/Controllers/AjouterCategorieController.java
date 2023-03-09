package GUI.Controllers;

import Entities.CategorieCoupon;
import Entities.Coupon;
import Services.CategorieCouponService;
import Services.CouponService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class AjouterCategorieController implements Initializable {
    @FXML
    private TextField nomcategorie;
    @FXML
    private TextField descriptioncategorie;
    @FXML
    private AnchorPane scenePane;
    @FXML
    Stage stage;

    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void ajouter(ActionEvent event) throws ParseException {
        String nom = nomcategorie.getText().trim();
        String desc = descriptioncategorie.getText().trim();
        CategorieCouponService cs = new CategorieCouponService();
        if (nom.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("le champ description est vide");
            alert.showAndWait();
            return;
        }

        if (desc.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("le champ description est vide");
            alert.showAndWait();
            return;
        }

        CategorieCoupon c = new CategorieCoupon(nom, desc);
        cs.add(c);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("SUCCESS! Le coupon a été ajouté.");
        alert.showAndWait();
    }

    public void logout(javafx.event.ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to exit ! ");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("EXIT MODE");
            stage.close();
        }
    }

    @FXML
    public void minimize(javafx.scene.input.MouseEvent mouseEvent) {
        Stage stage1= (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage1.setIconified(true);
    }

     @FXML
    public void goToCoupon(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GestionCoupon.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void goToCategorie(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GestionCategories.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goToItems(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../AfficherItemAdminFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double xOffset = event.getSceneX();
                double yOffset = event.getSceneY();
            }
        });
        //move around here

        scene.setFill(Color.TRANSPARENT);

        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Image image = new Image("GUI/Assets/images/log-04.png",32,32,true,true);
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.show();
    }
}