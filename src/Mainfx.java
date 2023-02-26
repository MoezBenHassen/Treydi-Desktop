import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Mainfx extends Application {
  //  private double xOffset;
   // private double yOffset;
    @Override
    public void start(Stage primaryStage) {

        try {
            Parent root=FXMLLoader.load(getClass().getResource("/GUI/Reclamationfxml.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Treydi");

           /* primaryStage.initStyle(StageStyle.UNDECORATED);

            scene.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            scene.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });
            primaryStage.setWidth(1920);
            primaryStage.setHeight(1080);
*/




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


