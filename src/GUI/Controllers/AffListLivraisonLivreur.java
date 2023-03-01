package GUI.Controllers;

import Entities.Livraison;
import Services.LivraisonService;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

        afficher_livraison_list();
    }

    @FXML
    private void afficher_livraison_list() {
        List<Livraison> livraisons = ls.afficher();
        livraison_table_view.getItems().clear();
        livraison_table_view.getItems().addAll(livraisons);
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
