package GUI.Controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import Entities.Echange;
import Services.EchangeService;
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

public class AffListEchangeLivreur {

    @FXML
    private TableView<Echange> echange_table_view;

    @FXML
    private TableColumn<Echange, String> titre_echange;

    @FXML
    private TableColumn<Echange, String> username1;

    @FXML
    private TableColumn<Echange, String> username2;

    @FXML
    private TableColumn<Echange, Date> date_creation;

    EchangeService es = new EchangeService();
    Echange selectedEchange;


    @FXML
    void initialize() {
        titre_echange.setCellValueFactory(new PropertyValueFactory<>("titre_echange"));

        username1.setCellValueFactory(new PropertyValueFactory<>("username1"));
        username1.setCellFactory(column -> new TableCell<Echange, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    Echange echange = getTableView().getItems().get(getIndex());
                    setText(es.getUsername1(echange));
                }
            }
        });
        username2.setCellValueFactory(new PropertyValueFactory<>("username2"));
        username2.setCellFactory(column -> new TableCell<Echange, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    Echange echange = getTableView().getItems().get(getIndex());
                    setText(es.getUsername2(echange));
                }
            }
        });
        date_creation.setCellValueFactory(new PropertyValueFactory<>("date_echange"));

        echange_table_view.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                selectedEchange = echange_table_view.getSelectionModel().getSelectedItem();

                if (selectedEchange != null) {
                    try {
                        moveToAccEch(event);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        afficher_echange_list();
    }

    private void moveToAccEch(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Controllers/AccEchangeLivreur.fxml"));
        Parent root = loader.load();
        AccEchangeLivreur controller = loader.getController();
        controller.setSelectedEchangeAccLiv(selectedEchange);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        scene.getStylesheets().add("GUI/Assets/css/style.css");
        stage.setTitle("Acc");
        stage.show();
    }

    @FXML
    private void afficher_echange_list() {
        EchangeService es = new EchangeService();
        List<Echange> echanges = es.afficherEchangeAccepter();

        echange_table_view.getItems().clear();
        echange_table_view.getItems().addAll(echanges);
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
