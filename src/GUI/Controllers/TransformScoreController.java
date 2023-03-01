package GUI.Controllers;

import Entities.Coupon;
import Entities.Utilisateur;
import Services.CouponService;
import Services.UtilisateurService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TransformScoreController implements Initializable {

    Stage stage;
    @FXML
    private AnchorPane scenePane;

    @FXML
    private Label score;
    @FXML
    private TextField transformme;

    Utilisateur user = new Utilisateur(5);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int sc = getScore();
        score.setText(String.valueOf(sc));

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

    public int getScore() {
        UtilisateurService u = new UtilisateurService();
        int sc = u.afficherscore(user);
        return sc;
    }

    public void goToQR(ActionEvent actionEvent) throws IOException, SQLException {
        String s = transformme.getText();
        int sum = Integer.parseInt(s);
        if (!s.isEmpty()) {

            if (getScore() > sum && (sum==1000) || (sum==2000) || (sum==5000)) {
                if (sum == 1000) {
                    CouponService cs = new CouponService();
                    UtilisateurService us = new UtilisateurService();
                    int c = cs.affecterCouponCasual(user);
                    us.setScore(user, sum);
                    String code = cs.getCode(c);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../qrcode.fxml"));
                    Parent root = loader.load();
                    QrCodeController qc = loader.getController();
                    qc.setQrCode(code);
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.showAndWait();
                    getScore();
                } else if (sum == 2000) {
                    CouponService cs = new CouponService();
                    UtilisateurService us = new UtilisateurService();
                    int id = cs.affecterCouponSpecial(user);
                    us.setScore(user, sum);
                    String code = cs.getCode(id);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../qrcode.fxml"));
                    Parent root = loader.load();
                    QrCodeController qc = loader.getController();
                    qc.setQrCode(code);
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.showAndWait();
                    getScore();
                } else if (sum == 5000) {
                    CouponService cs = new CouponService();
                    UtilisateurService us = new UtilisateurService();
                    int id = cs.affecterCouponExclusif(user);
                    us.setScore(user, sum);
                    String code = cs.getCode(id);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../qrcode.fxml"));
                    Parent root = loader.load();
                    QrCodeController qc = loader.getController();
                    qc.setQrCode(code);
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.showAndWait();
                    getScore();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Nombre inseré invalide!");
                alert.showAndWait();
            }
        }
            else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un nombre.");
            alert.showAndWait();
        }
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


