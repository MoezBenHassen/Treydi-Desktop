package Services;

import Entities.Echange;
import Entities.Item;
import Utils.MyDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EchangeService implements Services.IService<Echange> {
    Connection con;
    Statement stm;
    public EchangeService() {
        con = MyDB.getInstance().getCon();
    }
    @Override
    public void add(Echange e) {
        java.sql.Date current_date = new java.sql.Date(System.currentTimeMillis());
        String req = "INSERT INTO `echange` ( `date_echange`, `id_user1`, `id_user2`)"
                + "VALUES ('"+current_date+"', '"+e.getId_user1()+"', '"+e.getId_user2()+"')";
        try {
            stm = con.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
        public List afficher()  {
            List<Echange> echanges = new ArrayList<>();

            String query="SELECT * from `echange`";
            try {
                stm = con.createStatement();
                ResultSet result=stm.executeQuery(query);
                while (result.next()){
                    Echange e = new Echange(result.getInt("id_echange"), result.getInt("id_user1"), result.getInt("id_user2"),
                            result.getDate("date_echange"));
                    echanges.add(e);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return echanges;
    }

    @Override
    public Boolean modifier(Echange e) {
        String req = "UPDATE `echange` SET `id_user1` = '"+e.getId_user1()+"', "
                          + "`id_user2` = '"+e.getId_user2()+"' WHERE id_echange = '"+e.getId_echange()+"'";
        //String req = "UPDATE `echange` SET `date_echange` = '"+e.getSqlDate()+"' WHERE id_echange = '"+e.getId_echange()+"' ";
        try {
            stm = con.createStatement();
            int rowsUpdated = stm.executeUpdate(req);
            if (rowsUpdated >0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Boolean supprimer(Echange e) {
        String req = "UPDATE echange SET archived = 1 WHERE id_echange = '"+e.getId_echange()+"' ";
        try {
            stm = con.createStatement();
            int rowsDeleted = stm.executeUpdate(req);
            if (rowsDeleted > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void CreerEchange(Echange e, List<Item> items) {
        java.sql.Date current_date = new java.sql.Date(System.currentTimeMillis());
        int id_echange = -1;

        String req="INSERT INTO `echange` (`id_user1`, `date_echange`) VALUES ('" + e.getId_user1() + "', '"+current_date+"')";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(req, Statement.RETURN_GENERATED_KEYS);
            ResultSet result = stmt.getGeneratedKeys();
            if (result.next()){
                id_echange = result.getInt(1);
            }
            for (Item i : items) {
                stmt = con.createStatement();
                stmt.executeUpdate("UPDATE `item` SET `id_echange` = '" + id_echange + "' WHERE `id_item`='" + i.getId_item() + "'");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void ProposerEchange(Echange e, List<Item> items) {
        java.sql.Date current_date = new java.sql.Date(System.currentTimeMillis());
        int id_echange = -1;

        String req="INSERT INTO `echange` (`id_user2`, `date_echange`) VALUES ('" + e.getId_user2() + "', '"+current_date+"')";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(req, Statement.RETURN_GENERATED_KEYS);
            ResultSet result = stmt.getGeneratedKeys();
            if (result.next()){
                id_echange = result.getInt(1);
            }
            for (Item i : items) {
                stmt = con.createStatement();
                stmt.executeUpdate("UPDATE `item` SET `id_echange` = '" + id_echange + "' WHERE `id_item`='" + i.getId_item() + "'");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
