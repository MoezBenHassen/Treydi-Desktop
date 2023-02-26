package Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entities.Item;
import Utils.MyDB;


public class ItemService implements Services.IService<Item> {

    Connection cnx;
    Statement stm;

    public ItemService() {
        cnx = MyDB.getInstance().getCon();

    }

    @Override
    public void add(Item i) {
        try {
            String qry = "INSERT INTO `item` (`libelle`, `description`, `type`, `etat` , `id_user`, `id_categorie`) VALUES ('" + i.getLibelle() + "','" + i.getDescription() + "','" + i.getType() + "','" + i.getEtat() + "'," + i.getId_user() + "," + i.getId_categorie() + ");";
            stm = cnx.createStatement();

            int res = stm.executeUpdate(qry);

            if (res > 0) {
                System.out.println("ligne "+i.getLibelle()+" est insérée");
            } else {
                System.out.println("ligne "+i.getLibelle()+" n'est pas insérée");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Item> afficher() {
        List<Item> items = new ArrayList();
        try {
            String qry = "SELECT * FROM `item`";
            stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Item i = new Item();
                i.setId_item(rs.getInt("id_item"));
                i.setLibelle(rs.getString("libelle"));
                i.setDescription(rs.getString("description"));
                i.setType(Item.type.valueOf(rs.getString("type")));
                i.setEtat(Item.state.valueOf(rs.getString("etat")));
                i.setId_user(rs.getInt("id_user"));
                i.setId_categorie(rs.getInt("id_categorie"));

                items.add(i);
            }
            return items;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return items;

    }

    @Override
    public Boolean modifier(Item i) {
        try {
            String qry = "UPDATE `item` SET `libelle`='" + i.getLibelle() + "',`description`='" + i.getDescription() + "',`etat`='" + i.getEtat() + "',`type`='" + i.getType() + "',`id_categorie`='" + i.getId_categorie() + "' WHERE `id_item`='" + i.getId_item() + "';";
            stm = cnx.createStatement();
            int res = stm.executeUpdate(qry);
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public Boolean supprimer(Item i) {
        try {
            String qry = "DELETE FROM `item` WHERE `id_item`='" + i.getId_item() + "';";
            stm = cnx.createStatement();
            int res = stm.executeUpdate(qry);
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public List<Item> afficherUserItems(int u) {
        List<Item> items = new ArrayList();
        try {
            String qry = "SELECT * FROM `item` WHERE `id_user` = '"+u+"';";
            stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Item i = new Item();
                i.setId_item(rs.getInt("id_item"));
                i.setLibelle(rs.getString("libelle"));
                i.setDescription(rs.getString("description"));
                i.setType(Item.type.valueOf(rs.getString("type")));
                i.setEtat(Item.state.valueOf(rs.getString("etat")));
                i.setId_user(rs.getInt("id_user"));
                i.setId_categorie(rs.getInt("id_categorie"));
                i.setId_echange(rs.getInt("id_echange"));

                items.add(i);
            }
            return items;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return items;
    }
}
