import Entities.Echange;
import Entities.EchangeProposer;
import Services.EchangeProposerService;
import Services.EchangeService;
import Utils.MyDB;
import javafx.application.Platform;
import javafx.concurrent.Task;
import org.controlsfx.control.Notifications;

import java.io.*;
import java.sql.*;

public class DatabaseMonitor extends Task<Void> {
    private int previousEntry = 0;
    EchangeService es = new EchangeService();
    EchangeProposerService eps = new EchangeProposerService();
    EchangeProposer ep = new EchangeProposer();
    Echange e = new Echange();

    @Override
    protected Void call() throws Exception {
        // read the last value from file
        File file = new File("last_value.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                previousEntry = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Connection con = MyDB.getInstance().getCnx();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM echange_proposer ORDER BY id DESC LIMIT 1");

        System.out.println("Database connection established");
        System.out.println("Checking database...");

        while (!isCancelled()) {
            try {
                ResultSet resultSet = stm.executeQuery();

                // retrieve last entry
                int latestEntry;

                if (resultSet.next()) {
                    latestEntry = resultSet.getInt("id");
                    ep = eps.getProp(latestEntry);
                    e = es.getEchangeByProp(ep);
                } else {
                    latestEntry = 0;
                }

                // compare
                if (latestEntry != previousEntry) {
                    previousEntry = latestEntry;

                    // last value write
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        writer.write(String.valueOf(previousEntry));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Platform.runLater(() -> {
                        // show notification
                        Notifications.create()
                                .title("Nouvelle Proposition")
                                .text(String.valueOf("Vous avais une nouvelle propoisition pour l'Echange: " + e.getTitre_echange() ))
                                .showInformation();
                    });
                    System.out.println("New entry added: " + previousEntry);
                } else {
                    System.out.println("No new entry added");
                }

                //Timerrrrrrrrr
                Thread.sleep(1000);
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
