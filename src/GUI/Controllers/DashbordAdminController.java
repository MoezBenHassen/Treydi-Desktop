package GUI.Controllers;
import Entities.Trader;
import Entities.Utilisateur;
import Services.UtilisateurService;
import Utils.Enums.Roles;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Entities.Utilisateur;
import Services.UtilisateurService;
import Utils.Enums.Roles;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DashbordAdminController implements Initializable {
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private TableView<Utilisateur> tableView;
    @FXML
    private TableColumn<Utilisateur, Integer> tfid_user;
    @FXML
    private TableColumn<Utilisateur, Integer> tfarchived;
    @FXML
    private TableColumn<Utilisateur, String> tfnom;

    @FXML
    private TableColumn<Utilisateur, String> tfprenom;

    @FXML
    private TableColumn<Utilisateur, String> tfemail;

    @FXML
    private TableColumn<Utilisateur, String> tfadresse;

    @FXML
    private TableColumn<Utilisateur, Roles> tfrole;
    @FXML
    private TableColumn<Utilisateur, String> tfdate;
    @FXML
    private TableColumn<Utilisateur, Integer> tfscore;
    @FXML
    private TextField textfield_search;
    @FXML
    private CheckBox cb_type_1 ;

    @FXML
    private CheckBox cb_type_2 ;

    @FXML
    private CheckBox cb_type_3 ;

    @FXML
    private Pane chercherPane;
    @FXML
    private ImageView minimize;
    @FXML
    private AnchorPane scenePane;

    private List<Utilisateur> users;


    @FXML
    private ImageView goToItems;
    Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tfarchived.setCellValueFactory(new PropertyValueFactory<>("archived"));
        tfprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tfnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tfemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tfadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tfrole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tfscore.setCellValueFactory(new PropertyValueFactory<>("score"));
        tfdate.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        tfid_user.setCellValueFactory(new PropertyValueFactory<>("id"));


        afficher() ;
        UtilisateurService us = new UtilisateurService();
        users = us.afficher();
        tableView.setItems(FXCollections.observableArrayList(users));


    }

    @FXML
    private void afficher() {
        UtilisateurService us = new UtilisateurService();
        List<Utilisateur> utilisateurs = us.afficher();

        tableView.getItems().clear();
        tableView.getItems().addAll(utilisateurs);

    }

    @FXML
    private void chercher() {
        String searchText = textfield_search.getText().toLowerCase();
        List<Utilisateur> userstream = users.stream()
                .filter((t) -> t.getEmail().toLowerCase().contains(searchText)
                        || t.getNom().toLowerCase().contains(searchText))
                .collect(Collectors.toList());

        List<Utilisateur> filteredUsers = new ArrayList<>();

        if (cb_type_1.isSelected()) {
            filteredUsers.addAll(userstream.stream().filter((t) -> t.getRole() == Roles.admin).collect(Collectors.toList()));
        }
        if (cb_type_2.isSelected()) {
            filteredUsers.addAll(userstream.stream().filter((t) -> t.getRole() == Roles.trader).collect(Collectors.toList()));
        }
        if (cb_type_3.isSelected()) {
            filteredUsers.addAll(userstream.stream().filter((t) -> t.getRole() == Roles.livreur).collect(Collectors.toList()));
        }

        if (filteredUsers.isEmpty()) {
            tableView.setItems(FXCollections.observableArrayList(userstream));
        } else {
            tableView.setItems(FXCollections.observableArrayList(filteredUsers));
        }
    }

    @FXML
    void supprimer(javafx.event.ActionEvent event) {
        Utilisateur selectedre = tableView.getSelectionModel().getSelectedItem();
        if (selectedre != null) {

            UtilisateurService us = new UtilisateurService();

            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation");
            confirmation.setHeaderText(null);
            confirmation.setContentText("Êtes-vous sûr de vouloir supprimer ?");
            Optional<ButtonType> result = confirmation.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                boolean supprime = us.supprimer(selectedre);
                if (supprime) {
                    tableView.getItems().remove(selectedre);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Suppression réussie");
                    alert.setHeaderText(null);
                    alert.setContentText("Suppression effectuée avec succès.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Erreur lors de la suppression.");
                    alert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une réclamation.");
            alert.showAndWait();
        }

    }
    @FXML
    private void modifier(javafx.event.ActionEvent event) throws IOException {
        Utilisateur selectmod = tableView.getSelectionModel().getSelectedItem();
        if (selectmod != null) {
            if (selectmod instanceof Trader) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModifierTrader.fxml"));
                Parent root = loader.load();
                ModifierTraderController controller = loader.getController();
                controller.setSelectedUser((Trader) selectmod);
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.initStyle(StageStyle.UNDECORATED);
                scene.setFill(Color.TRANSPARENT);
                stage.initStyle(StageStyle.TRANSPARENT);

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
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModifierUser.fxml"));
                Parent root = loader.load();
                ModifierUserController controller = loader.getController();
                controller.setSelectedUser(selectmod);
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Image image = new Image("GUI/Assets/images/log-04.png",32,32,true,true);
                stage.getIcons().add(image);

                //Add app title
                stage.setTitle("Treydi");
                scene.setFill(Color.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Operation");
            a.setContentText("Sélectionner item à modifier:");
            a.show();
        }
    }


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
    public void Minimize(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void route_ChercherItem(javafx.event.ActionEvent event) throws IOException {
        chercherPane.setVisible(true);
    }

    @FXML
    private void route_ChercherItemH(MouseEvent event) throws IOException {
        chercherPane.setVisible(false);
    }


    @FXML
    private void route_ChercherItemR(MouseEvent event) throws IOException {
        textfield_search.setText("chercher");
        cb_type_1.setSelected(false);
        cb_type_2.setSelected(false);
        cb_type_3.setSelected(false);
        chercher() ;
    }

    @FXML
    void goToItems(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../AfficherItemAdminFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        //move around here

        scene.setFill(Color.TRANSPARENT);

        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Image image = new Image("GUI/Assets/images/log-04.png",32,32,true,true);
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void goToCoupon(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GestionCoupon.fxml"));
        Parent root = null;
        try {
            root = loader.load();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        //move around here

        scene.setFill(Color.TRANSPARENT);

        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Image image = new Image("GUI/Assets/images/log-04.png", 32, 32, true, true);
        stage.getIcons().add(image);
        stage.setScene(scene);
    }


/*
    @FXML
    private void route_AfficherUtilisateur(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../DashboardAdmin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void route_ModifierUtilisateur(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ModifierUser.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void route_SupprimerUtilisateur(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../SupprimerUser.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
*/

    @FXML
    private void gotoajout(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ReclamationAffichage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}