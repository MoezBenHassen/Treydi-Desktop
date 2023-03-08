

package GUI.Controllers;
import APIs.GenerateQRCode;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import Entities.CategorieCoupon;
import Services.CategorieCouponService;
import Services.CouponService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import Entities.Coupon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.text.html.ImageView;


public class GestionCouponController implements Initializable {

    @FXML
    private Button afficher, suppression, modification, ajouter, logoutButton;
    @FXML
    private TableView tablecoupons;
    @FXML
    private TableColumn<Coupon, String> nomcoupon;
    @FXML
    private TableColumn<Coupon, String> descriptioncoupon;
    @FXML
    private TableColumn<Coupon, String> etatcoupon;
    @FXML
    private TableColumn<Coupon, String> dateexpiration;
    @FXML
    private TableColumn<Coupon, String> categoriecoupon;

    @FXML
    private TableColumn<Coupon, String> codecoupon;

    Stage stage;
    @FXML
    private AnchorPane scenePane;
    private double xOffset = 0;
    private double yOffset = 0;

    private List<CategorieCoupon> categories;

    ObservableList<Coupon> CouponList = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb) {
        CategorieCouponService c = new CategorieCouponService();
        categories = c.afficher();
        afficher();
        tablecoupons.setItems(CouponList);
        nomcoupon.setCellValueFactory(new PropertyValueFactory<>("titre_coupon"));
        descriptioncoupon.setCellValueFactory(new PropertyValueFactory<>("description_coupon"));
        etatcoupon.setCellValueFactory(new PropertyValueFactory<>("etat_coupon"));
        dateexpiration.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
        codecoupon.setCellValueFactory(new PropertyValueFactory<>("code"));


        categoriecoupon.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Coupon, String>, ObservableValue<String>>() {

            public ObservableValue<String> call(TableColumn.CellDataFeatures<Coupon, String> param) {
                try {
                    int catid = param.getValue().getId_categoriecoupon();
                    String cat = categories.stream().filter((t) -> t.getId_categoriecoupon() == catid).limit(1).map((t) -> t.getNom_categorie()).collect(Collectors.joining(", "));
                    return new SimpleObjectProperty<>(cat);
                } catch (Exception e) {
                    System.out.println(e);
                }
                return null;
            }
        });
    }

    @FXML
    private void afficher() {
        CouponService cs= new CouponService();
        List<Coupon> coupons = cs.afficher();
        CouponList.addAll(coupons);
    }


    @FXML
    private void modifier(ActionEvent event) throws IOException {
        Coupon selectedcoupon = (Coupon) tablecoupons.getSelectionModel().getSelectedItem();
        if (selectedcoupon != null) {
            String nom = String.valueOf(selectedcoupon.getTitre_coupon());
            String description = String.valueOf(selectedcoupon.getDescription_coupon());
            String etat = String.valueOf(selectedcoupon.getEtat_coupon());
            String date = String.valueOf(selectedcoupon.getDate_expiration());
            String code = String.valueOf(selectedcoupon.getCode());


            FXMLLoader loader = new FXMLLoader(getClass().getResource("../modification.fxml"));
            Parent root = loader.load();
            ModifierCouponController modificationControlleur = loader.getController();
            modificationControlleur.setTitreText(selectedcoupon.getTitre_coupon());
            modificationControlleur.setDescriptionText(selectedcoupon.getDescription_coupon());
            modificationControlleur.setEtatText(selectedcoupon.getEtat_coupon());
            modificationControlleur.setDate(selectedcoupon.getDate_expiration());
            modificationControlleur.setCode(selectedcoupon.getCode());

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();


        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Aucune ligne selectionnée!");
            alert.showAndWait();
        }
    }

    public void supprimer(ActionEvent actionEvent) {
        Coupon selectedrep = (Coupon) tablecoupons.getSelectionModel().getSelectedItem();
        System.out.println(selectedrep);
        if (selectedrep != null) {
            CouponService servicerep = new CouponService();

            boolean result = servicerep.supprimer(selectedrep);

            if (result) {
                tablecoupons.getItems().remove(selectedrep);
                Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("La ligne a été supprimé avec succès!");
                alert.show();
                afficher();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Erreur lors de la suppression .");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Aucune ligne selectionnée!");
            alert.showAndWait();
        }
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

    public void minimize(javafx.scene.input.MouseEvent mouseEvent) {
        Stage stage1= (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage1.setIconified(true);
    }

    @FXML
    void gotoCategorie(ActionEvent event) {
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
    void gotoAjouter(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../AjouterCoupon.fxml"));
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

}