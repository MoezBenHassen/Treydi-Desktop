package Utils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainjavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            Parent root= FXMLLoader.load(getClass().getResource("/GUI/DashboardAdmin.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Treydi Desktop Edition");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("Err"+ex.getMessage());
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
