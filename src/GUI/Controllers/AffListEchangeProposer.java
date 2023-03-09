package GUI.Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import Entities.Echange;
import Entities.EchangeProposer;
import Services.EchangeProposerService;
import Services.EchangeService;
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

import static java.sql.Types.NULL;

public class AffListEchangeProposer {

    @FXML
    private TableView<EchangeProposer> echange_table_view;

    @FXML
    private TableColumn<Echange, String> titre_echange;

    @FXML
    private TableColumn<EchangeProposer, String> username;

    @FXML
    private TableColumn<EchangeProposer, Date> date_prop;

    EchangeService es = new EchangeService();
    EchangeProposerService eps = new EchangeProposerService();

    Echange selectedEchange;
    EchangeProposer selectedEchangeProposer;
    private double xOffset;
    private double yOffset;


    @FXML
    void initialize() {
        titre_echange.setCellValueFactory(new PropertyValueFactory<>("titre_echange"));
        titre_echange.setCellFactory(column -> new TableCell<Echange, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(es.getTitreEchange(selectedEchange));
                }
            }
        });

        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        username.setCellFactory(column -> new TableCell<EchangeProposer, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    //EchangeProposer echangeProp = getTableView().getItems().get(getIndex());
                    EchangeProposer echangeProp = getTableView().getItems().get(getIndex());
                    setText(eps.getUsernameProp(echangeProp));
                }
            }
        });

        date_prop.setCellValueFactory(new PropertyValueFactory<>("date_proposer"));



        echange_table_view.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                selectedEchangeProposer = echange_table_view.getSelectionModel().getSelectedItem();

                if (selectedEchange != null) {
                    try {
                        moveToAccRefProp(event);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    @FXML
    private void afficher_echange_list() {
        //List<Echange> echanges = es.afficherEchangeNonAccepter();
        //System.out.println(selectedEchange);
        List<EchangeProposer> epl = eps.afficherPropEch(selectedEchange);

        echange_table_view.getItems().clear();
        echange_table_view.getItems().addAll(epl);

    }

    public void setSelectedEchangeInfoProp(Echange echange) {
        selectedEchange = echange;
        System.out.println(selectedEchange);
        afficher_echange_list();
    }

    private void moveToAccRefProp(MouseEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("/GUI/AccEchangeLivreur.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Controllers/InfoEchangeUserProp.fxml"));
        Parent root = loader.load();
        InfoEchangeUserProp controller = loader.getController();
        controller.setSelectedEchangeInfoAccRefProp(selectedEchangeProposer);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        scene.getStylesheets().add("GUI/Assets/css/style.css");
        stage.setTitle("Acc");
        stage.show();
    }

    @FXML
    private void moveToAffListEchange(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffListEchange.fxml"));
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
}
