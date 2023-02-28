package GUI.Controllers;

import Entities.CategorieCoupon;
import Entities.Coupon;
import Services.CategorieCouponService;
import Services.CouponService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifierCategorieController implements Initializable {

    @FXML
    private TextField nomcategorie, descriptioncategorie;

    Stage stage;
    @FXML
    private AnchorPane scenePane;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void modifier(ActionEvent event)  throws IOException {
        String titre = nomcategorie.getText().trim();
        String description = descriptioncategorie.getText().trim();


        if (titre.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("le champ titre est vide");
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


        CategorieCouponService cs = new CategorieCouponService();
        CategorieCoupon c = new CategorieCoupon(titre, description) ;
        cs.modifier(c);
        stage = (Stage) scenePane.getScene().getWindow();
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("SUCCESS! La catégorie a été modifié.");
        alert.showAndWait();
        stage.close();
    }

    @FXML
    public void setTitreText(String titreCoupon) {
        nomcategorie.setText(titreCoupon);
    }

    @FXML
    public void setDescriptionText(String descriptionCoupon) {
        descriptioncategorie.setText(descriptionCoupon);
    }


}
