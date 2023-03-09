package GUI.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import Entities.Echange;
import Services.EchangeService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class AffListEchange {

    @FXML
    private TableView<Echange> echange_table_view;

    @FXML
    private TableColumn<Echange, String> titre_echange;

    @FXML
    private TableColumn<Echange, String> username1;

    @FXML
    private TableColumn<Echange, Date> date_creation;

    @FXML
    private TextField recherche;

    EchangeService es = new EchangeService();

    private List<Echange> echanges;

    Echange selectedEchange;

    private double xOffset;
    private double yOffset;

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

        date_creation.setCellValueFactory(new PropertyValueFactory<>("date_echange"));

        afficher_echange_list();

        echange_table_view.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                selectedEchange = echange_table_view.getSelectionModel().getSelectedItem();

                if (selectedEchange != null) {
                    try {
                        moveToProposerEch(event);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    @FXML
    private void chercher() {
        String searchText = recherche.getText().toLowerCase();
        List<Echange> echangestream = echanges.stream().filter((t) -> t.getTitre_echange().toLowerCase().contains(searchText))
                .collect(Collectors.toList());

        List<Echange> filteredEchanges = new ArrayList<>();

        if (filteredEchanges.isEmpty()) {
            echange_table_view.setItems(FXCollections.observableArrayList(echangestream));
        } else {
            echange_table_view.setItems(FXCollections.observableArrayList(filteredEchanges));
        }
    }

    @FXML
    private void afficher_echange_list() {
        EchangeService es = new EchangeService();
        echanges = es.afficherEchangeNonAccepter();

        echange_table_view.getItems().clear();
        echange_table_view.getItems().addAll(echanges);

    }

    private void moveToProposerEch(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Controllers/ProposerEchange.fxml"));
        Parent root = loader.load();
        ProposerEchange controller = loader.getController();
        controller.setSelectedEchangeProposer(selectedEchange);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        scene.getStylesheets().add("GUI/Assets/css/style.css");
        stage.setTitle("Acc");
        stage.show();
    }

    @FXML
    private void moveToCreerEchange(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreerEchangeInterface.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add("GUI/Assets/css/style.css");
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        //move around here
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
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
    @FXML
    void goToMesEchanges(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffListMesEchanges.fxml"));
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
