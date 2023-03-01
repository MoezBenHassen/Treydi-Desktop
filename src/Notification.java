import Utils.MyDB;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Notification {
    Connection con;
    Statement stm;


    public void checkDb() throws SQLException {
        String req = "SELECT * FROM echange";

        con = MyDB.getInstance().getCon();
        stm = con.createStatement();
        ResultSet rs= stm.executeQuery(req);
        Runnable check = new Runnable() {
            public void run() {
                System.out.println(rs);
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(check, 0, 3, TimeUnit.SECONDS);
    }

    public static void showNotification(String message) {
        // Create a new stage for the notification
        Stage notificationStage = new Stage();
        notificationStage.initStyle(StageStyle.TRANSPARENT);

        // Create a label for the notification message
        Label label = new Label(message);
        label.setTextFill(Color.WHITE);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-background-color: rgba(51,51,51,0.8); -fx-padding: 20; -fx-background-radius: 10;");

        // Add the label to a VBox
        VBox vBox = new VBox(label);
        vBox.setAlignment(Pos.CENTER);

        // Create a new scene with the VBox as its root
        Scene scene = new Scene(vBox);
        scene.setFill(Color.TRANSPARENT);

        // Add a stylesheet to the scene to remove the window decorations
        scene.getStylesheets().add("GUI/Assets/css/notification.css");

        // Set the scene for the notification stage
        notificationStage.setScene(scene);

        // Set the icon for the notification stage
        notificationStage.getIcons().add(new Image("GUI/Assets/images/log-04.png"));

        // Show the notification stage
        notificationStage.show();

        // Close the notification stage after 3 seconds
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        notificationStage.close();
                    }
                },
                3000
        );
    }
}
