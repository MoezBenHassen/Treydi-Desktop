package GUI.Controllers;

import Entities.CategorieCoupon;
import Entities.Coupon;
import Entities.Utilisateur;
import Services.CategorieCouponService;
import Services.CouponService;
import com.sun.org.apache.xml.internal.security.Init;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.util.Callback;
import javafx.beans.property.SimpleObjectProperty;

public class AfficherCouponsUser implements Initializable {
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

    @FXML
    private TableView tablecoupons;

    @FXML
    private Pane pane;

    @FXML
    private TextField titre;
    @FXML
    private TextField motscles;
    @FXML
    private ComboBox categoriecouponsbox;

    private List<CategorieCoupon> categories;
    private List<Coupon> coupons;
    Stage stage;
    @FXML
    private AnchorPane scenePane;
    ObservableList<Coupon> CouponList = FXCollections.observableArrayList();


    Utilisateur u = new Utilisateur(5);

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


    public void initialize(URL location, ResourceBundle resources) {
        CouponService sp = new CouponService();
        coupons = sp.afficherParUser(u);
        CategorieCouponService sp2 = new CategorieCouponService();
        categories = sp2.afficher();
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
        CouponService cs = new CouponService();
        List<Coupon> coupons = cs.afficherParUser(u);
        CouponList.addAll(coupons);

    }

    public void Minimize(MouseEvent mouseEvent) {
        Stage stage1 = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage1.setIconified(true);
    }

    public void envoyer() {

        String titreText = titre.getText();
        String catText = (String) categoriecouponsbox.getValue();
        int c_cat = categories.stream().filter((t) -> t.getNom_categorie().equals(catText)).mapToInt((t) -> t.getId_categoriecoupon()).sum();
        System.out.println(c_cat);
        String descText = motscles.getText();

        List<Coupon> couponstream = coupons;

        if (!(titreText.equals("") || titreText.equals("Titre"))) {
            couponstream = couponstream.stream().filter((t) -> t.getTitre_coupon().toLowerCase().contains(titreText.toLowerCase())).collect(Collectors.toList());
        }

        if (!(descText.equals("") || descText.equals("Mots clés"))) {
            couponstream = couponstream.stream().filter(t -> t.getDescription_coupon().toLowerCase().contains(descText.toLowerCase())).collect(Collectors.toList());

        }

        if (catText != null && !catText.equals("Catégories")) {
            System.out.println("message");
            couponstream = couponstream.stream().filter((t) -> t.getId_categoriecoupon() == c_cat).collect(Collectors.toList());
        }

        tablecoupons.setItems(FXCollections.observableArrayList(couponstream));
    }


    public void chercher(MouseEvent mouseEvent) {
        categoriecouponsbox.setValue("Catégories");
        for (CategorieCoupon categorieCoupon : categories) {
            categoriecouponsbox.getItems().add(categorieCoupon.getNom_categorie());
        }
        pane.setVisible(true);
    }

    public void refresh(MouseEvent mouseEvent) {
        titre.clear();
        motscles.clear();
        categoriecouponsbox.getItems().clear();

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
}

