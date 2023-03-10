package GUI.Controllers;

import Entities.Trader;
import Entities.Utilisateur;
import Services.UtilisateurService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFideliteController implements Initializable {

    Stage stage;
    @FXML
    private AnchorPane scenePane;
        @FXML
        private Button logoutButton;

        @FXML
        private Label score;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      getScore();

    }

    public void logout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to exit ! ");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("EXIT MODE");
            stage.close();
        }
    }

    public void Minimize(MouseEvent mouseEvent) {
        Stage stage1= (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage1.setIconified(true);
    }

    public void TransformerScore(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../TransformScore.fxml"));
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


    public void ShowScoreboard(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Scoreboard.fxml"));
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



    public void getScore() {

        UtilisateurService u= new UtilisateurService();
        int sc= u.afficherscore();
        score.setText(String.valueOf(sc));

    }

    public void gotoafficher(MouseEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../MyCoupons.fxml"));
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
