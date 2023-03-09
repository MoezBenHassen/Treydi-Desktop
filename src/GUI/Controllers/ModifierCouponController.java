package GUI.Controllers;

import Entities.Coupon;
import Services.CouponService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ModifierCouponController implements Initializable {
    @FXML
    private TextField nomcoupon1, etatcoupon1, descriptioncoupon1, code1;

    @FXML
    private TextField dateexpiration1;

    Stage stage;
    @FXML
    private AnchorPane scenePane;

    private Coupon selectedCoupon;
    private int idcoupon  ;

    public void initialize(URL url, ResourceBundle rb) {

        }

        @FXML
        void modifier(ActionEvent event)  throws IOException {
            String titre = nomcoupon1.getText().trim();
            String description = descriptioncoupon1.getText().trim();
            String etat = etatcoupon1.getText().trim();
            String date = dateexpiration1.getText().trim();
            String code = code1.getText().trim();

            LocalDate expirationDate = LocalDate.parse(date);

            LocalDate today = LocalDate.now();

            if (expirationDate.isBefore(today)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("La date d'expiration est antérieure à la date d'aujourd'hui !");
                alert.showAndWait();
                return;
            }

            if (titre.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("le champ titre est vide!!!");
                alert.showAndWait();
                return;
            }

            if (description.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("le champ description est vide !!!");
                alert.showAndWait();
                return;
            }

            if (etat.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("le champ etat est vide !!!");
                alert.showAndWait();
                return;
            }

            if (date.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("le champ date est vide !!!");
                alert.showAndWait();
                return;
            }

            if (code.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("le champ code est vide !!!");
                alert.showAndWait();
                return;
            }


            CouponService cs = new CouponService();
            Coupon c = new Coupon(idcoupon,titre, description, etat, date, code) ;
            cs.modifier(c);
            stage = (Stage) scenePane.getScene().getWindow();
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("SUCCESS! Le coupon a été modifié.");
            alert.showAndWait();
            stage.close();

        }


    public void setTitreText(String titreCoupon) {
        nomcoupon1.setText(titreCoupon);
    }

    public void setDescriptionText(String descriptionCoupon) {
        descriptioncoupon1.setText(descriptionCoupon);
    }

    public void setEtatText(String etatCoupon) {
        etatcoupon1.setText(etatCoupon);
    }

    public void setDate(String dateCoupon) {
        dateexpiration1.setText(dateCoupon);
    }

    public void setCode(String codecoupon) {
        code1.setText(codecoupon);
    }

    public void setSelectedItem(Coupon coupon) {
        selectedCoupon = coupon;}

    public void setIdCoupon(int idcoupon) {this.idcoupon = idcoupon;}

}
