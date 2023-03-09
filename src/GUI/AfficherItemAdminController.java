package GUI;

import APIs.ToPDF;
import APIs.ToXLS;
import Entities.Categorie_Items;
import Entities.Item;
import Services.CategorieItemsService;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class AfficherItemAdminController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private ImageView goToUserCRUD;

    @FXML
    private TableView<Item> tableView;

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

    @FXML
    private TableColumn<Item, String> categorieColumn;


    @FXML
    private TextField textfield_libelle;
    @FXML
    private TextArea textarea_description;
    @FXML
    private CheckBox cb_type_1 ;

    @FXML
    private ToggleGroup radio_type ;

    @FXML
    private CheckBox cb_type_2 ;

    @FXML
    private CheckBox cb_type_3 ;

    @FXML
    private CheckBox cb_type_4 ;

    @FXML
    private ComboBox combobox_cat ;

    @FXML
    private Pane chercherPane;

    private  List<Item> items ;
    private  List<Categorie_Items> categories ;

    Stage stage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ItemService sp = new ItemService();
        items = sp.afficherAdmin();
        tableView.setItems(FXCollections.observableArrayList(items));

        CategorieItemsService sp2 = new CategorieItemsService();
        categories = sp2.afficher();

        afficher_combobox_cat();


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

            categorieColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                try {
                    int catid = param.getValue().getId_categorie();
                    CategorieItemsService sp2 = new CategorieItemsService();
                    List<Categorie_Items> list = sp2.afficher();
                    String cat = list.stream().filter((t) -> t.getId_categorie() == catid).limit(1).map((t) -> t.getNom_categorie()).collect(Collectors.joining(", "));
                    ;

                    return new SimpleObjectProperty<>(cat);
                } catch (Exception e) {
                    System.out.println(e);
                }
                return null;
            }
        });


        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // check if it's a double click
                try {
                    route_ItemDetails(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            }
        );


    }


    @FXML
    private void afficher_combobox_cat() {
        CategorieItemsService sp = new CategorieItemsService();
        List<Categorie_Items> list = sp.afficher();
        combobox_cat.getItems().add("Toutes");
        for (Categorie_Items categorieItems : list) {
            combobox_cat.getItems().add(categorieItems.getNom_categorie());
        }
    }


    @FXML
    private void chercher() {


        String c_lib = textfield_libelle.getText();
        String cd_cat = (String) combobox_cat.getValue();
        int c_cat = categories.stream().filter((t) -> t.getNom_categorie().equals(cd_cat)).mapToInt((t) -> t.getId_categorie()).sum();
        String c_mcl = textarea_description.getText();


        List<Item> itemstream = items;

        if (!(c_lib.equals("") || c_lib.equals("Insérer libellé"))) {

            Set<String> searchSetClib = new HashSet<>(Arrays.asList(c_lib.split(" ")));
            itemstream = itemstream.stream().filter(obj -> searchSetClib.stream().anyMatch(word -> obj.getLibelle().toLowerCase().contains(word.toLowerCase()))).collect(Collectors.toList());

        }
        if (!(c_mcl.equals("") || c_mcl.equals("Insérer mots clés"))) {
            Set<String> searchSetCmcl = new HashSet<>(Arrays.asList(c_mcl.split(" ")));
            itemstream = itemstream.stream().filter(obj -> searchSetCmcl.stream().anyMatch(word -> obj.getDescription().toLowerCase().contains(word.toLowerCase()))).collect(Collectors.toList());

        }

        if (cd_cat != null && !cd_cat.equals("Toutes")) {
            itemstream = itemstream.stream().filter((t) -> t.getId_categorie() == c_cat).collect(Collectors.toList());
        }

        List<Item> itemstreamc1 = itemstream;
        List<Item> itemstreamc2 = itemstream;
        List<Item> itemstreamc3 = itemstream;
        List<Item> itemstreamc4 = itemstream;

        if (cb_type_1.isSelected()) {

            itemstreamc1 = itemstreamc1.stream().filter((t) -> t.getType() == Item.type.Physique)
                    .filter((t) -> t.getEtat() == Item.state.Neuf)
                    .collect(Collectors.toList());

        } else {
            itemstreamc1 = new ArrayList<>();
        }

        if (cb_type_2.isSelected()) {
            itemstreamc2 = itemstreamc2.stream().filter((t) -> t.getType() == Item.type.Physique)
                    .filter((t) -> t.getEtat() == Item.state.Occasion)
                    .collect(Collectors.toList());
        } else {
            itemstreamc2 = new ArrayList<>();
        }


        if (cb_type_3.isSelected()) {
            itemstreamc3 = itemstreamc3.stream().filter((t) -> t.getType() == Item.type.Virtuelle)
                    .collect(Collectors.toList());
        } else {
            itemstreamc3 = new ArrayList<>();
        }

        if (cb_type_4.isSelected()) {
            itemstreamc4 = itemstreamc4.stream().filter((t) -> t.getType() == Item.type.Service)
                    .collect(Collectors.toList());
        } else {
            itemstreamc4 = new ArrayList<>();
        }

        if (!((cb_type_1.isSelected()) || (cb_type_2.isSelected()) || (cb_type_3.isSelected()) || (cb_type_4.isSelected()))) {
        } else {
            itemstream = itemstreamc1;
            itemstream.addAll(itemstreamc2);
            itemstream.addAll(itemstreamc3);
            itemstream.addAll(itemstreamc4);

        }

        RadioButton selected_radio_type = (RadioButton) radio_type.getSelectedToggle();

        if (selected_radio_type != null) {
            String srt = selected_radio_type.getText();

            if (srt.equals("Type et état")) {
                itemstream = itemstream.stream().sorted(Comparator.comparing(t -> t.getEtat())).collect(Collectors.toList());
            }

            if (srt.equals("Libellé")) {
                itemstream = itemstream.stream().sorted(Comparator.comparing(t -> t.getLibelle())).collect(Collectors.toList());
            }
        }

        tableView.setItems(FXCollections.observableArrayList(itemstream));
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
        chercherPane.setVisible(true);
    }

    @FXML
    private void route_ChercherItemH(MouseEvent event) throws IOException {
        chercherPane.setVisible(false);
    }

    @FXML
    private void route_ChercherItemR(MouseEvent event) throws IOException {
        textfield_libelle.setText("Insérer libellé");
        textarea_description.setText("Insérer mots clés");
        combobox_cat.setValue("Toutes");
        cb_type_1.setSelected(false);
        cb_type_2.setSelected(false);
        cb_type_3.setSelected(false);
        cb_type_4.setSelected(false);
        radio_type.selectToggle(null);
        chercher() ;
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
        ToXLS exporter = new ToXLS();
        exporter.ToXLSItem(tableView);
    }




    @FXML
    private void epdf(MouseEvent event) throws IOException {
        ToPDF exporter = new ToPDF();
        exporter.ToPDFItem(tableView);

    }

    @FXML
    private void route_CategorieItems(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieItemsAdminFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
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
    private void route_ItemDetails(MouseEvent event) throws IOException {
        Item selectedObject = tableView.getSelectionModel().getSelectedItem();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherItemDetailsAdminFXML.fxml"));
        Parent root = loader.load();
        AfficherItemDetailsAdminController controller = loader.getController();
        controller.setSelectedItem(selectedObject);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
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
    private void Minimize(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }


    public void goToUserCRUD(MouseEvent mouseEvent){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/DashboardAdmin.fxml"));
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
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        scene.setFill(Color.TRANSPARENT);

        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Image image = new Image("GUI/Assets/images/log-04.png",32,32,true,true);
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.show();
    }





}
