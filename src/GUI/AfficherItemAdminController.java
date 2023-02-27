package GUI;

import APIs.ToPDFItem;
import APIs.ToXLSItem;
import Entities.Item;
import GUI.ModifierItemAdminController;
import Services.ItemService;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherItemAdminController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;


    @FXML
    private TableView<Item> tableView ;

    @FXML
    private TableColumn<Item, ImageView> imageColumn;

    @FXML
    private TableColumn<Item, String> imageurlColumn;

    @FXML
    private TableColumn<Item, String> libelleColumn;

    @FXML
    private TableColumn<Item, String> descriptionColumn;

    @FXML
    private TableColumn<Item, String> typeColumn;

    @FXML
    private TableColumn<Item, String> etatColumn;

    @FXML
    private TableColumn<Item, String> idechangeColumn;

    @FXML
    private TableColumn<Item, String> iduserColumn;

    @FXML
    private TableColumn<Item, String> iditemColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        imageurlColumn.setCellValueFactory(new PropertyValueFactory<>("imageurl"));

        imageurlColumn.setVisible(false);

        imageColumn.setPrefWidth(130);
        imageColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, ImageView>, ObservableValue<ImageView>>() {

            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Item, ImageView> param) {
                try {
                String imagePath = param.getValue().getImageurl();

                    Image image = new Image(imagePath);
                    ImageView imageView = new ImageView();
                    imageView.setImage(image);
                    imageView.setFitHeight(120);
                    imageView.setFitWidth(120);


                return new SimpleObjectProperty<>(imageView);
            } catch (Exception e) {
                    System.out.println(e);
            }
                return null;
            }
        });

        iditemColumn.setCellValueFactory(new PropertyValueFactory<>("id_item"));
        iduserColumn.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));


        afficher();


    }

    @FXML
    private void supprimer(ActionEvent event) {
        Item selectdel = tableView.getSelectionModel().getSelectedItem();
        if (selectdel != null) {
            ItemService sp = new ItemService();

            Alert b = new Alert(Alert.AlertType.CONFIRMATION);
            b.setHeaderText("Operation");

            b.setContentText("Voulez-vous supprimer cet item?");
            b.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    boolean res = sp.supprimer(selectdel);
                    if (res) {
                    tableView.getItems().remove(selectdel);
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setHeaderText("Notification");
                    a.setContentText("L'item sélectionné a été supprimé avec succès!");
                    a.show();
                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setHeaderText("Erreur");

                        a.setContentText("Une erreur s'est produite, veuillez contacter les administrateurs.");
                        a.show();

                    }

                } else {
                }
            });


        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Operation");

            a.setContentText("Sélectionner item à supprimer:");
            a.show();
        }
    }

        @FXML
        private void modifier(ActionEvent event) throws IOException {
            Item selectmod = tableView.getSelectionModel().getSelectedItem();
            if (selectmod != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierItemAdminFXML.fxml"));
                Parent root = loader.load();
                ModifierItemAdminController controller = loader.getController();
                controller.setSelectedItem(selectmod);
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setHeaderText("Operation");

                a.setContentText("Sélectionner item à modifier:");
                a.show();

            }


    }

    @FXML
    private void afficher() {

        ItemService sp = new ItemService();
        List<Item> items = sp.afficher();
        tableView.setItems(FXCollections.observableArrayList(items));


    }

    @FXML
    private void route_AjouterItem(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterItemAdminFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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

    @FXML
    private void route_ChercherItem(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChercherItemAdminFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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



    @FXML
    private void route_AfficherItem(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherItemAdminFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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

    @FXML
    private void exls(MouseEvent event)  {
        ToXLSItem exporter = new ToXLSItem();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setTitle("Exporter Items XLS");
        FileChooser.ExtensionFilter xlsFilter = new FileChooser.ExtensionFilter("XLS Files (*.xls)", "*.xls");
        fileChooser.getExtensionFilters().add(xlsFilter);
        Window stage = null;
        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {
            exporter.ToXLSItem(tableView, selectedFile);
        }


    }

    @FXML
    private void epdf(MouseEvent event) throws IOException {
        ToPDFItem exporter = new ToPDFItem();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setTitle("Exporter Items PDF");
        FileChooser.ExtensionFilter xlsFilter = new FileChooser.ExtensionFilter("PDF Files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(xlsFilter);
        Window stage = null;
        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {
            exporter.ToPDFItem(tableView, selectedFile.getPath());
        }

    }

    @FXML
    private void route_CategorieItems(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieItemsAdminFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage() ;
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
    }



    @FXML
    private void shutdown(MouseEvent event) throws IOException {
        Platform.exit();

    }

    @FXML
    private void minimize(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
}
