package Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entities.Item;
import Interfaces.IService;
import Utils.MyDB;


public class ItemService implements IService<Item> {

    Connection cnx;
    Statement stm;

    public ItemService() {
        cnx = MyDB.getInstance().getCnx();

    }

    @Override
    public void ajouter(Item i) {
        try {
            String qry = "INSERT INTO `item` (`libelle`, `description`, `type`, `etat` , `imageurl`, `id_user`, `id_categorie`,`id_echange`, `archived`) VALUES ('" + i.getLibelle() + "','" + i.getDescription() + "','" + i.getType() + "','" + i.getEtat() + "','" + i.getImageurl() + "'," + i.getId_user() + "," + i.getId_categorie() + ",0,0);";
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
            String qry = "SELECT * FROM `item` WHERE `archived` = 0";
            stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Item i = new Item();
                i.setId_item(rs.getInt("id_item"));
                i.setLibelle(rs.getString("libelle"));
                i.setDescription(rs.getString("description"));
                i.setType(Item.type.valueOf(rs.getString("type")));
                i.setEtat(Item.state.valueOf(rs.getString("etat")));
                i.setImageurl(rs.getString("imageurl"));
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

    @Override
    public Boolean modifier(Item i) {
        try {
            String qry = "UPDATE `item` SET `libelle`='" + i.getLibelle() + "',`description`='" + i.getDescription() + "',`etat`='" + i.getEtat() + "',`type`='" + i.getType() + "',`imageurl`='"+ i.getImageurl() + "',`id_categorie`='" + i.getId_categorie() + "' WHERE `id_item`='" + i.getId_item() + "';";
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
                String qry = "UPDATE `item` SET `archived`= 1 WHERE `id_item`='" + i.getId_item() + "';";
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


}
