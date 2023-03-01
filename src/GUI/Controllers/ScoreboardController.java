package GUI.Controllers;

import Entities.Coupon;
import Entities.Utilisateur;
import Services.CouponService;
import Services.UtilisateurService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class ScoreboardController implements Initializable {

    Stage stage;
    @FXML
    private AnchorPane scenePane;

    @FXML
    private TableView scoreboard;
    @FXML
    private TableColumn<Coupon, String> nom;
    @FXML
    private TableColumn<Coupon, String> prenom;
    @FXML
    private TableColumn<Coupon, String> score;

    ObservableList<Utilisateur> UsersList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        afficher();
        scoreboard.setItems(UsersList);
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
    }

    @FXML
    private void afficher() {
        UtilisateurService us = new UtilisateurService();
        List<Utilisateur> users = us.afficherTraders();

        Collections.sort(users, new Comparator<Utilisateur>() {
                    @Override
                    public int compare(Utilisateur u1, Utilisateur u2) {
                        return u2.getScore() - u1.getScore();
                    }
                });

        UsersList.addAll(users);
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
        Stage stage1 = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage1.setIconified(true);
    }

    public void gotocoupon(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainFidelite.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void gotoafficher(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../MyCoupons.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
