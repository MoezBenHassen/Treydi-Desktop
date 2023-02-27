package GUI;

import APIs.ToPDFCategorie_Items;
import APIs.ToXLSCategorie_Items;
import Entities.Categorie_Items;
import Services.CategorieItemsService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CategorieItemsAdminController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private TextField textfield_libelle;

    @FXML
    private TableView<Categorie_Items> tableView;

    @FXML
    private TableColumn<Categorie_Items, String> idcategorieColumn;

    @FXML
    private TableColumn<Categorie_Items, String> nomColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idcategorieColumn.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));


        afficher();


    }

    @FXML
    private void supprimer(ActionEvent event) {
        Categorie_Items selectdel = tableView.getSelectionModel().getSelectedItem();
        if (selectdel != null) {
            CategorieItemsService sp = new CategorieItemsService();

            Alert b = new Alert(Alert.AlertType.CONFIRMATION);
            b.setHeaderText("Operation");

            b.setContentText("Voulez-vous supprimer cet categorie item?");
            b.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    boolean res = sp.supprimer(selectdel);
                    if (res) {
                        tableView.getItems().remove(selectdel);
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setHeaderText("Notification");
                        a.setContentText("La categorie item sélectionné a été supprimé avec succès!");
                        a.show();
                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setHeaderText("Erreur");

                        a.setContentText("Une erreur s'est produite, Principalement parce que cette catégorie contient des instances d'items.");
                        a.show();

                    }

                } else {
                }
            });


        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Operation");

            a.setContentText("Sélectionner categorie item à supprimer:");
            a.show();
        }
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        Categorie_Items selectmod = tableView.getSelectionModel().getSelectedItem();
        if (selectmod != null) {
            CategorieItemsService sp = new CategorieItemsService();
            Categorie_Items i = new Categorie_Items (selectmod.getId_categorie(),textfield_libelle.getText());
            sp.modifier(i) ;

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Notification");
            a.setContentText("L'entrée de categorie item a été modifiée avec succès!");
            a.show();

        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Operation");

            a.setContentText("Sélectionner categorie item à modifier:");
            a.show();

        }


    }

    @FXML
    private void afficher() {

        CategorieItemsService sp = new CategorieItemsService();
        List<Categorie_Items> categories = sp.afficher();
        tableView.getItems().clear();
        tableView.setItems(FXCollections.observableArrayList(categories));


    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        CategorieItemsService sp = new CategorieItemsService();

        if (textfield_libelle.getText().equals("") || textfield_libelle.getText().equals("Insérer nom")) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Erreur");

            a.setContentText("Assurez-vous d'insérer le nom votre nouveau categorie item.");
            a.show();
        } else {
            Categorie_Items i = new Categorie_Items (textfield_libelle.getText());
            sp.ajouter(i);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            afficher();
            a.setHeaderText("Operation");

            a.setContentText("L'entrée de categorie item a été insérée avec succès!");
            a.show();

        }


    }


    @FXML
    private void exls(MouseEvent event) {
        ToXLSCategorie_Items exporter = new ToXLSCategorie_Items();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setTitle("Exporter Items XLS");
        FileChooser.ExtensionFilter xlsFilter = new FileChooser.ExtensionFilter("XLS Files (*.xls)", "*.xls");
        fileChooser.getExtensionFilters().add(xlsFilter);
        Window stage = null;
        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {
            exporter.ToXLSCategorie_Items(tableView, selectedFile);
        }


    }

    @FXML
    private void epdf(MouseEvent event) throws IOException {
        ToPDFCategorie_Items exporter = new ToPDFCategorie_Items();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setTitle("Exporter Items PDF");
        FileChooser.ExtensionFilter xlsFilter = new FileChooser.ExtensionFilter("PDF Files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(xlsFilter);
        Window stage = null;
        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {
            exporter.ToPDFCategorie_Items(tableView, selectedFile.getPath());
        }

    }


    @FXML
    private void shutdown(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    @FXML
    private void minimize(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
}
