

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class javafxMain extends Application {
    private double xOffset;
    private double yOffset;
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("GUI/login.fxml"));
            // Image image = new Image("GUI/Assets/images/log-04.png",32,32,true,true);
            //Parent root = loader.load();
            DatabaseMonitor databaseMonitor = new DatabaseMonitor();
            Scene scene = new Scene(root);

            primaryStage.initStyle(StageStyle.UNDECORATED);

            scene.setFill(Color.TRANSPARENT);
            primaryStage.initStyle(StageStyle.TRANSPARENT);

            scene.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });
            scene.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });
            
            // set window size
            /*primaryStage.setWidth(1600);

            primaryStage.setHeight(900);

             */
            // add css to scene
            scene.getStylesheets().add("GUI/Assets/css/style.css");
            //Add app icon
            Image image = new Image("GUI/Assets/images/log-04.png", 32, 32, true, true);
            primaryStage.getIcons().add(image);
            primaryStage.setTitle("Treydi");
            primaryStage.setScene(scene);
            primaryStage.show();
            // databasemonitoring
            Thread databaseThread = new Thread(databaseMonitor);
            databaseThread.setDaemon(true);
            databaseThread.start();

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}