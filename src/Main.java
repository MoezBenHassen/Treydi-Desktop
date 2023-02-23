import java.io.IOException;

import Services.ItemService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/CreerEchangeInterface.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Echange");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        //EchangeService e = new EchangeService();
        //Echange ee = new Echange(2, 4, 5,);
        //System.out.println(e.afficher());

        /*LivraisonService l = new LivraisonService();
        Livraison ll = new Livraison(4,9, 2, "modifier", Livraison.ETAT.Termine);
        l.modifier(ll);*/

        ItemService iss = new ItemService();
        System.out.println(iss.afficher());

        launch(args);

    }

}