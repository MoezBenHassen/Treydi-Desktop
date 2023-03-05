import Utils.MyDB;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.sql.*;

public class DatabaseMonitor extends Task<Void> {
    private String previousEntry = "";

    @Override
    protected Void call() throws Exception {
        Connection con = MyDB.getInstance().getCon();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM echange ORDER BY id_echange DESC LIMIT 1");

        System.out.println("Database connection established");
        System.out.println("Checking database...");

        while (!isCancelled()) {
            try {
                ResultSet resultSet = stm.executeQuery();

                // last entry retrieval
                String latestEntry;
                if (resultSet.next()) {
                    latestEntry = resultSet.getString("titre_echange");
                } else {
                    latestEntry = "";
                }

                // compare
                if (!latestEntry.equals(previousEntry)) {
                    previousEntry = latestEntry;
                    Platform.runLater(() -> updateMessage(latestEntry));
                    System.out.println("New entry added: " + latestEntry);
                } else {
                    System.out.println("No new entry added");
                }

                //timer sleep
                Thread.sleep(5000);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // close db con
        try {
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
