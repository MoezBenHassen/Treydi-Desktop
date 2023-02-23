package Controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Entities.Echange;
import Services.EchangeService;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AfficherListEchangeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textfield_libelle1;

    @FXML
    private TableView<Echange> tableView;

    @FXML
    private TableColumn<Echange, String> idColumn;

    @FXML
    private TableColumn<Echange, String> dateColumn;

    @FXML
    private TableColumn<Echange, String> user1Column;

    @FXML
    private TableColumn<Echange, String> user2Column;

    @FXML
    void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id_echange"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date_echange"));
        user1Column.setCellValueFactory(new PropertyValueFactory<>("id_user1"));
        user2Column.setCellValueFactory(new PropertyValueFactory<>("id_user2"));

        afficher_echange_list();
    }

    @FXML
    private void afficher_echange_list() {
        EchangeService es = new EchangeService();
        List<Echange> echanges = es.afficher();

        tableView.getItems().clear();
        tableView.getItems().addAll(echanges);

    }

}
