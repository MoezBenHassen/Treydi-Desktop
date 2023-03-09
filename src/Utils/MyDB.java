package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyDB {
    final String URL ="jdbc:mysql://127.0.0.1:3306/treydi_db";
    final String USER ="root";
    final String PWD ="";
    private  Connection cnx;
    private  static MyDB instance;

    private MyDB(){
        try {
            cnx = DriverManager.getConnection(URL, USER, PWD);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public static MyDB getInstance (){
        if(instance == null){
            instance = new MyDB();
        }
        return instance;
    }
    public Connection getCnx(){
        return cnx;
    }
}