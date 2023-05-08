package GUI;

import APIs.ToPDF;
import APIs.ToXLS;
import Entities.Categorie_Items;
import Entities.Item;
import Services.CategorieItemsService;
import Services.ItemService;
import Utils.CurrentUser;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class AfficherItemUserController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Label itemTitle;
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
    private TableColumn<Item, String> categorieColumn;


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
    private CheckBox all ;


    @FXML
    private ToggleGroup radio_type ;


    @FXML
    private ComboBox combobox_cat ;

    @FXML
    private ImageView goToProfile;
    @FXML
    private Pane chercherPane;

    @FXML
    private ScrollPane scrollpane;


    private  List<Item> items ;

    private  List<Categorie_Items> categories ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        ItemService sp = new ItemService();
        items = sp.afficher();
        CategorieItemsService sp2 = new CategorieItemsService();
        categories = sp2.afficher();
        afficher(items) ;


        imageurlColumn.setCellValueFactory(new PropertyValueFactory<>("imageurl"));
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
        categorieColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                try {
                    int catid = param.getValue().getId_categorie();
                    String cat = categories.stream().filter((t) -> t.getId_categorie() == catid).limit(1).map((t) -> t.getNom_categorie()).collect(Collectors.joining(", "));
                    ;

                    return new SimpleObjectProperty<>(cat);
                } catch (Exception e) {
                    System.out.println(e);
                }
                return null;
            }
        });


        tableView.setItems(FXCollections.observableArrayList(items));

        combobox_cat.getItems().add("Toutes");
        for (Categorie_Items categorieItems : categories) {
            combobox_cat.getItems().add(categorieItems.getNom_categorie());
        }

        Image image = new Image(CurrentUser.getInstance().getAvatar_url());
        goToProfile.setImage(image);


    }

    @FXML
    private void afficher(List<Item> items) {

        ItemService sp = new ItemService();

        GridPane gridpane = new GridPane();

        scrollpane.setContent(gridpane);

        Slider slider = new Slider(0.5, 2, 1);

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            gridpane.setScaleX(newValue.doubleValue());
            gridpane.setScaleY(newValue.doubleValue());
        });




        int row = 0;
        int col = 0;
        for (Item obj : items) {

            Image image = new Image(obj.getImageurl());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(182);
            imageView.setFitWidth(182);

            Label libelleLabel = new Label(obj.getLibelle());
            libelleLabel.setStyle("-fx-text-fill: #eed1d1; -fx-font-family: impact; -fx-font-size: 20");

            String cat = categories.stream().filter((t) -> t.getId_categorie() == obj.getId_categorie()).limit(1).map((t) -> t.getNom_categorie()).collect(Collectors.joining(", "));
            Label categorieLabel = new Label(cat);
            categorieLabel.setStyle("-fx-text-fill: #eed1d1; -fx-font-size: 15;");

            Label typeetatLabel = new Label();
            typeetatLabel.setStyle("-fx-text-fill: #eed1d1; -fx-font-size: 15");

            Label emp = new Label("");

            if (obj.getType().toString().equals("Physique")) {
                if (obj.getEtat().equals("Neuf")) {
                    typeetatLabel.setText("Physique Neuf");
                } else {
                    typeetatLabel.setText("Physique Occasion");
                }
            } else if (obj.getType().toString().equals("Virtuelle")) {
                typeetatLabel.setText("Virtuelle");
            } else if (obj.getType().toString().equals("Service")) {
                typeetatLabel.setText("Service");
            }

            Image modimage = new Image("GUI/Assets/icons/pink/pencil.png");
            ImageView modimageView = new ImageView(modimage);
            modimageView.setFitHeight(30);
            modimageView.setFitWidth(30);

            modimageView.setOnMouseClicked(event -> {
                try {
                    modifier(event,obj) ;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            Image supimage = new Image("GUI/Assets/icons/pink/trash-bin.png");
            ImageView supimageView = new ImageView(supimage);
            supimageView.setFitHeight(30);
            supimageView.setFitWidth(30);

            supimageView.setOnMouseClicked(event -> {
                supprimer(event,obj) ;
            });

            Image detimage = new Image("GUI/Assets/icons/pink/magnifier.png");
            ImageView detimageView = new ImageView(detimage);
            detimageView.setFitHeight(30);
            detimageView.setFitWidth(30);

            detimageView.setOnMouseClicked(event -> {
                try {
                    route_ItemDetails(event,obj); ;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            HBox buttons = new HBox();

            if (obj.getId_user() == CurrentUser.getInstance().getId_user())
            {
                 buttons = new HBox(modimageView, supimageView, detimageView);
                buttons.setSpacing(24);
                buttons.setAlignment(Pos.CENTER);
            } else {
                 buttons = new HBox(detimageView);
                buttons.setSpacing(24);
                buttons.setAlignment(Pos.CENTER);
            }



            Label like = new Label("👍 "+obj.getLikes());
            like.setStyle("-fx-text-fill: #775cfa; -fx-font-family: impact; -fx-font-size: 25");

            Label dislike = new Label("\uD83D\uDC4E "+obj.getDislikes());
            dislike.setStyle("-fx-text-fill: #775cfa; -fx-font-family: impact; -fx-font-size: 25");

            HBox likes = new HBox(like, dislike);
            likes.setSpacing(15);
            likes.setAlignment(Pos.CENTER);


            like.setOnMouseClicked(event -> {
                try {
                    sp.like(obj) ;
                    chercher();




                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            });

            dislike.setOnMouseClicked(event -> {
                try {
                    sp.dislike(obj) ;
                    chercher();



                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }



            });




            VBox vbox = new VBox(imageView, libelleLabel, categorieLabel, typeetatLabel,buttons, likes);
            vbox.setSpacing(6);
            vbox.setPrefWidth(208);
            vbox.setPrefHeight(370);
            vbox.setAlignment(Pos.CENTER);

            vbox.setStyle("-fx-background-color: rgba(156, 156, 156, 0.24); -fx-background-radius: 22");

            gridpane.add(vbox, col, row);

            col++;
            if (col == 5) {
                col = 0;
                row++;
            }
        }

        gridpane.setHgap(27);
        gridpane.setVgap(31);


    }

    @FXML
    private void supprimer(MouseEvent event,Item selectdel) {
        if (selectdel != null) {
            ItemService sp = new ItemService();

            Alert b = new Alert(Alert.AlertType.CONFIRMATION);
            b.setHeaderText("Operation");

            b.setContentText("Voulez-vous supprimer cet item?");
            b.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    boolean res = sp.supprimer(selectdel);
                    if (res) {
                chercher() ;
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
        private void modifier(MouseEvent event, Item selectmod) throws IOException {
            if (selectmod != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierItemUserFXML.fxml"));
                Parent root = loader.load();
                ModifierItemUserController controller = loader.getController();
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
    private void route_ItemDetails(MouseEvent event, Item selectedObject) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherItemDetailsUserFXML.fxml"));
        Parent root = loader.load();
        AfficherItemDetailsUserController controller = loader.getController();
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
    private void route_AjouterItem(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterItemUserFXML.fxml"));
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
    private void chercher() {


        String c_lib = textfield_libelle.getText();
        String cd_cat = (String) combobox_cat.getValue();
        int c_cat = categories.stream().filter((t) -> t.getNom_categorie().equals(cd_cat)).mapToInt((t) -> t.getId_categorie()).sum();
        String c_mcl = textarea_description.getText();

        ItemService sp = new ItemService();

        if (all.isSelected()) {
            items = sp.afficherAdmin() ;
        } else {
            items = sp.afficher() ;
        }

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



        afficher(itemstream) ;
        tableView.setItems(FXCollections.observableArrayList(itemstream));
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
    private void allitems(ActionEvent event) throws IOException {
        if (all.isSelected()) {
            itemTitle.setText("Tous Items");
            ItemService sp = new ItemService();
            items = sp.afficherAdmin();
            afficher(items);
            chercher();
        } else {
            itemTitle.setText("Vos Items");
            ItemService sp = new ItemService();
            items = sp.afficher();
            afficher(items);
            chercher();
        }
    }





    @FXML
    private void route_AfficherItem(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherItemUserFXML.fxml"));
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
    private void fact(MouseEvent event)  {
        ToPDF exporter = new ToPDF();
        int user = CurrentUser.getInstance().getId_user(); ;
        List<Integer> ech = items.stream().map(t -> t.getId_echange()).collect(Collectors.toList());
        System.out.println(ech);

        exporter.ToPDFEchangeInst(tableView,user, ech);
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

    @FXML
    private void route_Profile(MouseEvent mouseEvent) throws IOException {
        System.out.println("0000");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditProfileTrader.fxml"));
        System.out.println("1");
        Parent root = loader.load();
        System.out.println("2");
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void goToCoupon(MouseEvent mouseEvent){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainFidelite.fxml"));
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

        scene.setFill(Color.TRANSPARENT);

        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Image image = new Image("GUI/Assets/images/log-04.png",32,32,true,true);
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goToListeEchange(MouseEvent mouseEvent){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Controllers/AffListEchange.fxml"));
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

        scene.setFill(Color.TRANSPARENT);

        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Image image = new Image("GUI/Assets/images/log-04.png",32,32,true,true);
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void goToReclamation(MouseEvent mouseEvent){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutReclamationUser.fxml"));
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

        scene.setFill(Color.TRANSPARENT);

        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Image image = new Image("GUI/Assets/images/log-04.png",32,32,true,true);
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.show();
    }


}
