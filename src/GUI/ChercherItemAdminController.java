package GUI;

import APIs.ToPDFItem;
import APIs.ToXLSItem;
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
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ChercherItemAdminController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private TextField textfield_libelle;
    @FXML
    private TextArea textarea_description;
    @FXML
    private CheckBox cb_type_1 ;

    @FXML
    private CheckBox cb_type_2 ;

    @FXML
    private CheckBox cb_type_3 ;

    @FXML
    private CheckBox cb_type_4 ;

    @FXML
    private ComboBox combobox_cat ;

    @FXML
    private ImageView imageview_imageurl ;


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

    private  List<Item> items ;
    private  List<Categorie_Items> list ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        afficher_combobox_cat();

        imageurlColumn.setCellValueFactory(new PropertyValueFactory<>("imageurl"));

        imageurlColumn.setVisible(false);

        imageColumn.setPrefWidth(100);
        imageColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, ImageView>, ObservableValue<ImageView>>() {

            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Item, ImageView> param) {
                try {
                String imagePath = param.getValue().getImageurl();

                    Image image = new Image(imagePath);
                    ImageView imageView = new ImageView();
                    imageView.setImage(image);
                    imageView.setFitHeight(90);
                    imageView.setFitWidth(90);


                return new SimpleObjectProperty<>(imageView);
            } catch (Exception e) {
                    System.out.println(e);
            }
                return null;
            }
        });

        iditemColumn.setCellValueFactory(new PropertyValueFactory<>("id_item"));
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
        iduserColumn.setCellValueFactory(new PropertyValueFactory<>("id_user"));

        ItemService sp = new ItemService();
        CategorieItemsService sp2 = new CategorieItemsService();
        items = sp.afficher();
        list = sp2.afficher();


        chercher();


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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierItemFXML.fxml"));
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


        String c_lib = textfield_libelle.getText() ;
        String cd_cat = (String) combobox_cat.getValue();
        int c_cat = list.stream().filter((t) -> t.getNom_categorie().equals(cd_cat)).mapToInt((t) -> t.getId_categorie()).sum();
        String c_mcl = textarea_description.getText() ;


        List<Item> itemstream = items ;

if (!(c_lib.equals("") || c_lib.equals("Insérer libellé"))) {
    itemstream = itemstream.stream().filter((t) -> t.getLibelle().toLowerCase().contains(c_lib.toLowerCase())).collect(Collectors.toList());
}
        if (!(c_mcl.equals("") || c_mcl.equals("Insérer mots clés"))) {
            itemstream = itemstream.stream().filter(t -> t.getDescription().toLowerCase().contains(c_mcl.toLowerCase())).collect(Collectors.toList());

        }

        if (cd_cat != null && !cd_cat.equals("Toutes")) {
            itemstream = itemstream.stream().filter((t) -> t.getId_categorie() == c_cat).collect(Collectors.toList());
        }
        List<Item> itemstreamc1 = itemstream ;
        List<Item> itemstreamc2 = itemstream ;
        List<Item> itemstreamc3 = itemstream ;
        List<Item> itemstreamc4 = itemstream ;

        if (cb_type_1.isSelected()) {

            itemstreamc1 = itemstreamc1.stream().filter((t) -> t.getType() == Item.type.Physique)
                    .filter((t) -> t.getEtat() == Item.state.Neuf)
                    .collect(Collectors.toList());

        } else {
            itemstreamc1 = new ArrayList<>() ;
        }

        if (cb_type_2.isSelected()) {
            itemstreamc2 = itemstreamc2.stream().filter((t) -> t.getType() == Item.type.Physique)
                    .filter((t) -> t.getEtat() == Item.state.Occasion)
                    .collect(Collectors.toList());
        } else {
            itemstreamc2 = new ArrayList<>() ;
        }


        if (cb_type_3.isSelected()) {
            itemstreamc3 = itemstreamc3.stream().filter((t) -> t.getType() == Item.type.Virtuelle)
                    .collect(Collectors.toList());
        } else {
            itemstreamc3 = new ArrayList<>() ;
        }

        if (cb_type_4.isSelected()) {
            itemstreamc4 = itemstreamc4.stream().filter((t) -> t.getType() == Item.type.Service)
                    .collect(Collectors.toList());
        } else {
            itemstreamc4 = new ArrayList<>() ;
        }

        if (!((cb_type_1.isSelected()) ||(cb_type_2.isSelected() )||(cb_type_3.isSelected()) ||(cb_type_4.isSelected()))) {
            tableView.setItems(FXCollections.observableArrayList(itemstream));
        } else{
            List<Item> itemstreamcf = itemstreamc1 ;
            itemstreamcf.addAll(itemstreamc2) ;
            itemstreamcf.addAll(itemstreamc3) ;
            itemstreamcf.addAll(itemstreamc4) ;
            tableView.setItems(FXCollections.observableArrayList(itemstreamcf));
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