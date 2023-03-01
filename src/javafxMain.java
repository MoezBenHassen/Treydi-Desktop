import javafx.application.Application;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class javafxMain extends Application {
    private double xOffset;
    private double yOffset;
    private Label entryLabel;

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AffListEchange.fxml"));
            Parent root = loader.load();

            DatabaseMonitor databaseMonitor = new DatabaseMonitor();

            Image image = new Image("GUI/Assets/images/log-04.png", 32, 32, true, true);
            Scene scene = new Scene(root);
            primaryStage.initStyle(StageStyle.UNDECORATED);

            scene.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            scene.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });

            primaryStage.setWidth(1600);
            primaryStage.setHeight(900);

            scene.getStylesheets().add("GUI/Assets/css/style.css");

            primaryStage.getIcons().add(image);
            primaryStage.setTitle("Treydi");
            primaryStage.setScene(scene);
            primaryStage.show();

            // databasemonitoring
            Thread databaseThread = new Thread(databaseMonitor);
            databaseThread.setDaemon(true);
            databaseThread.start();

            // ui notif label changes text after new entry
            entryLabel = (Label) loader.getNamespace().get("entryLabel");
            entryLabel.textProperty().bind(databaseMonitor.messageProperty());
            databaseMonitor.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    entryLabel.textProperty().unbind();
                    entryLabel.setText("");
                }
            });

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
