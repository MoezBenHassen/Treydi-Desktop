
package GUI.Controllers;

import Entities.CategorieCoupon;
import Entities.Coupon;
import Services.CategorieCouponService;
import Services.CouponService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class AjouterCouponController implements Initializable {
    @FXML
    private Button logoutButton;

    @FXML
    private ComboBox categoriecouponsbox;

    @FXML
    private AnchorPane scenePane;

    Stage stage;
    @FXML
    private DatePicker dateexpiration;

    @FXML
    private TextField nomcoupon, descriptioncoupon;

    @FXML
    private TextField etatcoupon;

    public void initialize(URL url, ResourceBundle rb) {
        afficher_combobox();
    }

    public void afficher_combobox(){
        CategorieCouponService cat= new CategorieCouponService();
        List<CategorieCoupon> list= cat.afficher();
        for (CategorieCoupon categorieCoupon : list) {
            categoriecouponsbox.getItems().add(categorieCoupon.getNom_categorie());
        }

    }

    public void logout(javafx.event.ActionEvent actionEvent) {

        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to exit ! ");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("EXIT MODE");
            stage.close();
        }
    }

   @FXML
    private void ajouter(ActionEvent event) throws ParseException {
       String nom=nomcoupon.getText().trim();
       String desc=descriptioncoupon.getText().trim();
       CouponService cs = new CouponService();
       CategorieCouponService cat = new CategorieCouponService();
       List<CategorieCoupon> list = cat.afficher();
       String cbs = (String) categoriecouponsbox.getValue();
       int id_cat = list.stream().filter((t) -> t.getNom_categorie().equals(cbs)).mapToInt((t) -> t.getId_categoriecoupon()).sum();
       System.out.println(id_cat);

       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
       if (dateexpiration.getValue()==null){
           Alert alert= new Alert(Alert.AlertType.ERROR);
           alert.setHeaderText(null);
           alert.setContentText("le champ date est vide");
           alert.showAndWait();
           return ;
       }
       LocalDate dateChoisie = dateexpiration.getValue();
       LocalDate dateAujourdhui = LocalDate.now();
       if (dateChoisie.isBefore(dateAujourdhui)) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setHeaderText(null);
           alert.setContentText("La date choisie est antérieure à la date d'aujourd'hui");
           alert.showAndWait();
           return;
       }
       String date = dateChoisie.format(formatter);
       String etat=etatcoupon.getText().trim();

       if (nom.isEmpty()) {
           Alert alert= new Alert(Alert.AlertType.ERROR);
           alert.setHeaderText(null);
           alert.setContentText("le champ nom est vide");
           alert.showAndWait();
           return;
       }

       if (desc.isEmpty()) {
           Alert alert= new Alert(Alert.AlertType.ERROR);
          alert.setHeaderText(null);
          alert.setContentText("le champ description est vide");
          alert.showAndWait();
           return;
       }

       if (etat.isEmpty()) {
           Alert alert= new Alert(Alert.AlertType.ERROR);
           alert.setHeaderText(null);
           alert.setContentText("le champ etat est vide");
           alert.showAndWait();
           return;
       }

        Coupon c= new Coupon(nom, desc, etat, date, id_cat);
        cs.add(c);
           Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
           alert.setHeaderText(null);
           alert.setContentText("SUCCESS! Le coupon a été ajouté.");
           alert.showAndWait();
       }

    public void minimize(javafx.scene.input.MouseEvent mouseEvent) {
        Stage stage1= (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage1.setIconified(true);
    }

    @FXML
    void goToCategorie(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../AjouterCategorie.fxml"));
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
    public void goToAfficherCoupons(ActionEvent actionEvent) {
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
    }
