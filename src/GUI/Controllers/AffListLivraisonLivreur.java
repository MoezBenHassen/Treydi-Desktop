package GUI.Controllers;

import Entities.Livraison;
import Services.LivraisonService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AffListLivraisonLivreur {

    @FXML
    private TableColumn<Livraison, String> titre_echange;

    @FXML
    private TableColumn<Livraison, String> adresse_livraison_1;

    @FXML
    private TableColumn<Livraison, String> adresse_livraison_2;

    @FXML
    private TableView<Livraison> livraison_table_view;

    LivraisonService ls = new LivraisonService();

    Livraison selectedLivraison;

    @FXML
    void initialize() {
        titre_echange.setCellValueFactory(new PropertyValueFactory<>("echange_titre"));
        titre_echange.setCellFactory(column -> new TableCell<Livraison, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    Livraison livraison = getTableView().getItems().get(getIndex());
                    setText(ls.getTitreEchange(livraison));
                }
            }
        });
        adresse_livraison_1.setCellValueFactory(new PropertyValueFactory<>("adresse_livraison1"));
        adresse_livraison_2.setCellValueFactory(new PropertyValueFactory<>("adresse_livraison2"));

        livraison_table_view.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                selectedLivraison = livraison_table_view.getSelectionModel().getSelectedItem();

                if (selectedLivraison != null) {
                    try {
                        moveToInfoLivraisonLivreur(event);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        afficher_livraison_list();
    }

    @FXML
    private void afficher_livraison_list() {
        //CURRENT USER ID
        List<Livraison> livraisons = ls.afficherListLivraisonLivreur(4);
        livraison_table_view.getItems().clear();
        livraison_table_view.getItems().addAll(livraisons);
    }

    private void moveToInfoLivraisonLivreur(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Controllers/InfoLivraisonLivreur.fxml"));
        Parent root = loader.load();
        InfoLivraisonLivreur controller = loader.getController();
        controller.setSelectedLivraisonInfo(selectedLivraison);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        scene.getStylesheets().add("GUI/Assets/css/style.css");
        stage.setTitle("Acc");
        stage.show();
    }

    //LOGOUT
    @FXML
    private Button logoutButton;

    @FXML
    private AnchorPane scenePane;

    @FXML
    private ImageView minimize;
    Stage stage;
    public void logout(javafx.event.ActionEvent actionEvent) {

        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to exit ! ");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("YOU XYZ");
            stage.close();
        }
    }

    @FXML
    public void Minimize (MouseEvent event ){
        Stage stage1= (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage1.setIconified(true);
    }
}
