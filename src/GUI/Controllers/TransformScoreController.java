package GUI.Controllers;

import Entities.Trader;
import Services.CouponService;
import Services.UtilisateurService;
import Utils.CurrentUser;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private TextField transform1, transform2, transform3;

    Trader user = new Trader(CurrentUser.getInstance().getId());

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
        int sc = u.afficherscore();
        return sc;
    }

    public void goToQR(ActionEvent actionEvent) throws IOException, SQLException {
        int t1 = Integer.parseInt(transform1.getText());
        int t2 = Integer.parseInt(transform2.getText());
        int t3 = Integer.parseInt(transform3.getText());

        int sum = t1 + t2 + t3;
        int i;
        if (!(t1 == 0) || !(t2 == 0) || !(t3 == 0)) {
            if (getScore() >= sum) {
                if (t1 != 0) {
                    CouponService cs = new CouponService();
                    UtilisateurService us = new UtilisateurService();
                    for (i = 0; i < t1; i++) {
                        int c = cs.affecterCouponCasual(user);
                        String code = cs.getCode(c);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../qrcode.fxml"));
                        Parent root = loader.load();
                        QrCodeController qc = loader.getController();
                        qc.setQrCode(code);
                        qc.setTitreText("Coupon Casual " + (i + 1));
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.showAndWait();
                    }
                    us.setScore(user, t1 * 1000);
                }
                if (t2 != 0) {
                    CouponService cs = new CouponService();
                    UtilisateurService us = new UtilisateurService();
                    for (i = 0; i < t2; i++) {
                        int id = cs.affecterCouponSpecial(user);
                        String code = cs.getCode(id);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../qrcode.fxml"));
                        Parent root = loader.load();
                        QrCodeController qc = loader.getController();
                        qc.setQrCode(code);
                        qc.setTitreText("Coupon Special " + (i + 1));
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.showAndWait();
                    }
                    us.setScore(user, t2 * 2000);
                }
                if (t3 != 0) {
                    CouponService cs = new CouponService();
                    UtilisateurService us = new UtilisateurService();
                    for (i = 0; i < t3; i++) {
                        int id = cs.affecterCouponExclusif(user);
                        us.setScore(user, t3 * 5000);
                        String code = cs.getCode(id);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../qrcode.fxml"));
                        Parent root = loader.load();
                        QrCodeController qc = loader.getController();
                        qc.setQrCode(code);
                        qc.setTitreText("Coupon Exclusif " + (i + 1));
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.showAndWait();
                    }
                    us.setScore(user, t3 * 1000);
                }
            } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Score insuffisant!");
                    alert.showAndWait();
                }
            } else {
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
    public void goToFidelite(MouseEvent mouseEvent) {
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

    public void goToItems(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../AfficherItemUserFXML.fxml"));
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

    public void NumberOnly(KeyEvent keyEvent) {
        char c = keyEvent.getCharacter().charAt(0);
        if (!(Character.isDigit(c))) {
            keyEvent.consume();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("ERREUR! Entrez une valeur numérique.");
            alert.showAndWait();
        }
    }

}