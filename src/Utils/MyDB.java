package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDB {
    final String url = "jdbc:mysql://localhost:3306/treydi_db";
    final String user = "root";
    final String password = "";
    static MyDB instance;
    private Connection con;
    private MyDB(){
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established");
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public static MyDB getInstance(){
        if (instance == null){
            instance = new MyDB();
        }
        return instance;
    }

    public Connection getCon() {
        return con;
    }
}
