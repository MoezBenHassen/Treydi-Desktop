

package GUI.Controllers;
import Entities.CategorieCoupon;
import Entities.Coupon;
import Entities.Utilisateur;
import Services.CategorieCouponService;
import Services.CouponService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;



public class AfficherCouponsUser implements Initializable {

    @FXML
    private ScrollPane scrollpane;
    Stage stage;
    @FXML
    private AnchorPane scenePane;
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Pane pane;
    @FXML
    private TextField titre;
    @FXML
    private TextField motscles;
    @FXML
    private ComboBox categoriecouponsbox;

    @FXML
    private ComboBox etatbox;
    private  List<Coupon> coupons ;
    private List<CategorieCoupon> categories;


    Utilisateur u= new Utilisateur(5);

    @FXML
    public void logout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to exit ! ");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("EXIT MODE");
            stage.close();
        }
    }


    public void initialize(URL location, ResourceBundle resources) {
        CouponService cs = new CouponService();
        coupons = cs.afficherParUser(u);
        CategorieCouponService c = new CategorieCouponService();
        categories = c.afficher();
        afficher(coupons);

        categoriecouponsbox.getItems().add("Toutes");
        for (CategorieCoupon categorieCoupon : categories) {
            categoriecouponsbox.getItems().add(categorieCoupon.getNom_categorie());
        }

        etatbox.getItems().add("Tous");
        etatbox.getItems().add("VALID");
        etatbox.getItems().add("NOT_VALID");
            }




    @FXML
    private void afficher(List<Coupon> coupons) {
        GridPane gridpane = new GridPane();

        scrollpane.setContent(gridpane);

        Slider slider = new Slider(0.5, 2, 1);

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            gridpane.setScaleX(newValue.doubleValue());
            gridpane.setScaleY(newValue.doubleValue());
        });

        int row = 0;
        int col = 0;


        for (Coupon obj : coupons) {
            {
                if (obj.getId_categoriecoupon() == 1) {
                    Image image = new Image(getClass().getResourceAsStream("../Assets/images/bronze.png"));
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(182);
                    imageView.setFitWidth(182);

                    Label libelleNom = new Label(obj.getTitre_coupon());
                    libelleNom.setStyle("-fx-text-fill: #eed1d1; -fx-font-family: impact; -fx-font-size: 20");

                    String cat = categories.stream().filter((t) -> t.getId_categoriecoupon() == obj.getId_categoriecoupon()).limit(1).map((t) -> t.getNom_categorie()).collect(Collectors.joining(", "));
                    Label categorieLabel = new Label(cat);
                    categorieLabel.setStyle("-fx-text-fill: #eed1d1; -fx-font-size: 15;");

                    Label descLabel = new Label(obj.getDescription_coupon());
                    descLabel.setStyle("-fx-text-fill: #eed1d1; -fx-font-size: 15");

                    Label typeetatLabel = new Label(String.valueOf(obj.getEtat_coupon()));
                    typeetatLabel.setStyle("-fx-text-fill: #eed1d1; -fx-font-size: 15");

                    Label dateLabel = new Label(obj.getDate_expiration());
                    typeetatLabel.setStyle("-fx-text-fill: #eed1d1; -fx-font-size: 15");

                    Label codeLabel = new Label(obj.getCode());
                    typeetatLabel.setStyle("-fx-text-fill: #eed1d1; -fx-font-size: 15");

                    VBox vbox = new VBox(imageView, libelleNom, categorieLabel,descLabel, typeetatLabel, codeLabel, dateLabel);
                    vbox.setSpacing(4);
                    vbox.setPrefWidth(208);
                    vbox.setPrefHeight(283);
                    vbox.setAlignment(Pos.CENTER);

                    vbox.setStyle("-fx-background-color: rgba(156, 156, 156, 0.24); -fx-background-radius: 22");

                    gridpane.add(vbox, col, row);

                    col++;
                    if (col == 4) {
                        col = 0;
                        row++;
                    }
                } else if (obj.getId_categoriecoupon() == 2) {
                    Image image = new Image(getClass().getResourceAsStream("../Assets/images/silver.png"));
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(182);
                    imageView.setFitWidth(182);

                    Label libelleNom = new Label(obj.getTitre_coupon());
                    libelleNom.setStyle("-fx-text-fill: #eed1d1; -fx-font-family: impact; -fx-font-size: 20");

                    String cat = categories.stream().filter((t) -> t.getId_categoriecoupon() == obj.getId_categoriecoupon()).limit(1).map((t) -> t.getNom_categorie()).collect(Collectors.joining(", "));
                    Label categorieLabel = new Label(cat);
                    categorieLabel.setStyle("-fx-text-fill: #eed1d1; -fx-font-size: 15;");

                    Label descLabel = new Label(obj.getDescription_coupon());
                    descLabel.setStyle("-fx-text-fill: #eed1d1; -fx-font-size: 15");

                    Label typeetatLabel = new Label(String.valueOf(obj.getEtat_coupon()));
                    typeetatLabel.setStyle("-fx-text-fill: #eed1d1; -fx-font-size: 15");

                    Label dateLabel = new Label(obj.getDate_expiration());
                    typeetatLabel.setStyle("-fx-text-fill: #eed1d1; -fx-font-size: 15");

                    Label codeLabel = new Label(obj.getCode());
                    typeetatLabel.setStyle("-fx-text-fill: #eed1d1; -fx-font-size: 15");

                    VBox vbox = new VBox(imageView, libelleNom, categorieLabel, descLabel, typeetatLabel, codeLabel, dateLabel);
                    vbox.setSpacing(4);
                    vbox.setPrefWidth(208);
                    vbox.setPrefHeight(283);
                    vbox.setAlignment(Pos.CENTER);

                    vbox.setStyle("-fx-background-color: rgba(156, 156, 156, 0.24); -fx-background-radius: 22");

                    gridpane.add(vbox, col, row);

                    col++;
                    if (col == 4) {
                        col = 0;
                        row++;
                    }
                } else if (obj.getId_categoriecoupon() == 3) {
                    Image image = new Image(getClass().getResourceAsStream("../Assets/images/gold.png"));
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(182);
                    imageView.setFitWidth(182);

                    Label libelleNom = new Label(obj.getTitre_coupon());
                    libelleNom.setStyle("-fx-text-fill: #eed1d1; -fx-font-family: impact; -fx-font-size: 20");

                    String cat = categories.stream().filter((t) -> t.getId_categoriecoupon() == obj.getId_categoriecoupon()).limit(1).map((t) -> t.getNom_categorie()).collect(Collectors.joining(", "));
                    Label categorieLabel = new Label(cat);
                    categorieLabel.setStyle("-fx-text-fill: #eed1d1; -fx-font-size: 15;");

                    Label descLabel = new Label(obj.getDescription_coupon());
                    descLabel.setStyle("-fx-text-fill: #eed1d1; -fx-font-size: 15");

                    Label typeetatLabel = new Label(String.valueOf(obj.getEtat_coupon()));
                    typeetatLabel.setStyle("-fx-text-fill: #eed1d1; -fx-font-size: 15");

                    Label dateLabel = new Label(obj.getDate_expiration());
                    typeetatLabel.setStyle("-fx-text-fill: #eed1d1; -fx-font-size: 15");

                    Label codeLabel = new Label(obj.getCode());
                    typeetatLabel.setStyle("-fx-text-fill: #eed1d1; -fx-font-size: 15");

                    VBox vbox = new VBox(imageView, libelleNom, categorieLabel, descLabel, typeetatLabel, codeLabel, dateLabel);
                    vbox.setSpacing(4);
                    vbox.setPrefWidth(208);
                    vbox.setPrefHeight(283);
                    vbox.setAlignment(Pos.CENTER);

                    vbox.setStyle("-fx-background-color: rgba(156, 156, 156, 0.24); -fx-background-radius: 22");

                    gridpane.add(vbox, col, row);

                    col++;
                    if (col == 4) {
                        col = 0;
                        row++;
                    }
                }

                gridpane.setHgap(27);
                gridpane.setVgap(35);
            }

        }

    }



    @FXML
    public void gotocoupon(javafx.scene.input.MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainFidelite.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void Minimize(javafx.scene.input.MouseEvent mouseEvent) {
        Stage stage1 = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage1.setIconified(true); }


    @FXML
    public void envoyer() {
        String titreText = titre.getText();
        String catText = (String) categoriecouponsbox.getValue();
        String etatText= (String) etatbox.getValue();
        System.out.println(etatText);
        System.out.println(catText);
        int c_cat = categories.stream().filter((t) -> t.getNom_categorie().equals(catText)).mapToInt((t) -> t.getId_categoriecoupon()).sum();
        String descText = motscles.getText();

        List<Coupon> couponstream = coupons;

         if (!(titreText.equals("") || titreText.equals("Titre"))) {
            Set<String> searchSetTitre = new HashSet<>(Arrays.asList(titreText.split(" ")));
            couponstream = couponstream.stream().filter(obj -> searchSetTitre.stream().anyMatch(word -> obj.getTitre_coupon().toLowerCase().contains(word.toLowerCase()))).collect(Collectors.toList());
        }

        if (!(descText.equals("") || descText.equals("Mots clés"))) {
            Set<String> searchSetDesc = new HashSet<>(Arrays.asList(descText.split(" ")));
            couponstream = couponstream.stream().filter(obj -> searchSetDesc.stream().anyMatch(word -> obj.getDescription_coupon().toLowerCase().contains(word.toLowerCase()))).collect(Collectors.toList());
        }

        if (etatText != null && !etatText.equals("Tous")) {
            Set<String> searchSetEtat = new HashSet<>(Arrays.asList(etatText.split(" ")));
            couponstream = couponstream.stream().filter(obj -> searchSetEtat.stream().anyMatch(word -> obj.getEtat_coupon().toLowerCase().contains(word.toLowerCase()))).collect(Collectors.toList());
        }

        if (catText != null && !catText.equals("Toutes")) {
            couponstream = couponstream.stream().filter((t) -> t.getId_categoriecoupon() == c_cat).collect(Collectors.toList());
        }

        afficher(couponstream) ;

    }

    public void chercher(javafx.scene.input.MouseEvent mouseEvent) {
        pane.setVisible(true);
    }

    @FXML
    public void refresh(javafx.scene.input.MouseEvent mouseEvent) {
        titre.clear();
        motscles.clear();
        categoriecouponsbox.getItems().clear();
        etatbox.getItems().clear();

        afficher(coupons);
        categoriecouponsbox.getItems().add("Toutes");
        for (CategorieCoupon categorieCoupon : categories) {
            categoriecouponsbox.getItems().add(categorieCoupon.getNom_categorie());
        }

        etatbox.getItems().add("Tous");
        etatbox.getItems().add("VALID");
        etatbox.getItems().add("NOT VALID");
    }

}


