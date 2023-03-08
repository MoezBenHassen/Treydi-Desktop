
package GUI.Controllers;

import Entities.CategorieCoupon;
import Entities.Coupon;
import Services.CategorieCouponService;
import Services.CouponService;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

public class GestionCategoriesController implements Initializable {

    @FXML
    private Button ajouter, modification, supprimer,afficher;
    @FXML
    private TableView tablecategories;
    @FXML
    private TableColumn<Coupon, String> nomcategorie;
    @FXML
    private TableColumn<Coupon, String> descriptioncategorie;

    @FXML
    private AnchorPane scenePane;
    @FXML
    Stage stage;

    @FXML
    ObservableList<CategorieCoupon> categorieslist = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb) {
        afficher();
        tablecategories.setItems(categorieslist);
        nomcategorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
        descriptioncategorie.setCellValueFactory(new PropertyValueFactory<>("description_categorie"));
    }

    @FXML
    private void afficher() {
        {
            CategorieCouponService cs = new CategorieCouponService();
            List<CategorieCoupon> categories = cs.afficher();
            categorieslist.addAll(categories);
        }
    }

    @FXML
    private void modifier(ActionEvent event) throws ParseException, IOException {
        CategorieCoupon selectedcat = (CategorieCoupon) tablecategories.getSelectionModel().getSelectedItem();
        if (selectedcat != null) {
        String nom = String.valueOf(selectedcat.getNom_categorie());
        String description = String.valueOf(selectedcat.getDescription_categorie());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../modificationcat.fxml"));
            Parent root = loader.load();
            ModifierCategorieController modificationControlleur = loader.getController();
            modificationControlleur.setTitreText(selectedcat.getNom_categorie());
            modificationControlleur.setDescriptionText(selectedcat.getDescription_categorie());

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
            afficher();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Aucune ligne selectionnée!");
            alert.showAndWait();
        }
    }

    @FXML
    private void supprimer(ActionEvent event) throws ParseException {
        CategorieCoupon selectedrep = (CategorieCoupon) tablecategories.getSelectionModel().getSelectedItem();
        System.out.println(selectedrep);
        if (selectedrep != null) {
            CategorieCouponService servicerep = new CategorieCouponService();

            boolean result = servicerep.supprimer(selectedrep);

            if (result) {
                tablecategories.getItems().remove(selectedrep);
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


    @FXML
    public void minimize(MouseEvent mouseEvent) {
        Stage stage1 = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage1.setIconified(true);
    }

    @FXML
    public void gotoAjouter(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../AjouterCategorie.fxml"));
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
    public void gotoCoupon(ActionEvent actionEvent) {
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

}