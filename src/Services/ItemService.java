package Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entities.Echange;
import Entities.Item;
import Interfaces.IItemCategorieService;
import Utils.CurrentUser;
import Utils.MyDB;


public class ItemService implements IItemCategorieService<Item> {

    Connection cnx;
    Statement stm;

    public ItemService() {
        cnx = MyDB.getInstance().getCnx();

    }

    @Override
    public void ajouter(Item i) {
        try {
            String qry = "INSERT INTO `item` (`libelle`, `description`, `type`, `etat` , `imageurl`, `id_user_id`, `id_categorie_id`, `likes`, `dislikes`, `archived`,`views`) VALUES ('" + i.getLibelle() + "','" + i.getDescription() + "','" + i.getType() + "','" + i.getEtat() + "','" + i.getImageurl() + "'," + i.getId_user() + "," + i.getId_categorie() + ",0,0,0,0);";
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
            String qry = "SELECT * FROM `item` WHERE `archived` = 0 AND `id_user_id` = "+ CurrentUser.getInstance().getId_user() +";";
            stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Item i = new Item();
                i.setId_item(rs.getInt("id"));
                i.setLibelle(rs.getString("libelle"));
                i.setDescription(rs.getString("description"));
                i.setType(Item.type.valueOf(rs.getString("type")));
                i.setEtat(Item.state.valueOf(rs.getString("etat")));
                i.setImageurl(rs.getString("imageurl"));
                i.setId_user(rs.getInt("id_user_id"));
                i.setId_categorie(rs.getInt("id_categorie_id"));
                i.setId_echange(rs.getInt("id_echange_id"));
                i.setLikes(rs.getInt("likes"));
                i.setDislikes(rs.getInt("dislikes"));

                items.add(i);
            }
            return items;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return items;

    } ;

    @Override
    public List<Item> afficherAdmin() {
        List<Item> items = new ArrayList();
        try {
            String qry = "SELECT * FROM `item` WHERE `archived` = 0";
            stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Item i = new Item();
                i.setId_item(rs.getInt("id"));
                i.setLibelle(rs.getString("libelle"));
                i.setDescription(rs.getString("description"));
                i.setType(Item.type.valueOf(rs.getString("type")));
                i.setEtat(Item.state.valueOf(rs.getString("etat")));
                i.setImageurl(rs.getString("imageurl"));
                i.setId_user(rs.getInt("id_user_id"));
                i.setId_categorie(rs.getInt("id_categorie_id"));
                i.setId_echange(rs.getInt("id_echange_id"));
                i.setLikes(rs.getInt("likes"));
                i.setDislikes(rs.getInt("dislikes"));

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
            String qry = "UPDATE `item` SET `libelle`='" + i.getLibelle() + "',`description`='" + i.getDescription() + "',`etat`='" + i.getEtat() + "',`type`='" + i.getType() + "',`imageurl`='"+ i.getImageurl() + "',`id_categorie_id`='" + i.getId_categorie() + "', `likes`='" + i.getLikes() + "', `dislikes`='"+i.getDislikes()+"' WHERE `id`='" + i.getId_item() + "';";
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
                String qry = "UPDATE `item` SET `archived`= 1 WHERE `id`='" + i.getId_item() + "';";
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
    public Boolean like(Item i) throws SQLException {
        Boolean x = false ;
        Statement stm2 = cnx.createStatement();
        Statement stm3 = cnx.createStatement();
        try {
            String qry = "SELECT * FROM `like_items` WHERE `userid` = "+CurrentUser.getInstance().getId_user()+" AND `itemid` = "+i.getId_item() + " AND `choice` = 0";
            stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (!rs.next()) {
                String qryy = "SELECT * FROM `like_items` WHERE `userid` = "+CurrentUser.getInstance().getId_user()+" AND `itemid` = "+i.getId_item() + " AND `choice` = 1";
                ResultSet rss = stm3.executeQuery(qryy);
                while (rss.next()) {

                    String qryy3 = "UPDATE `item` SET `dislikes`= `dislikes` - 1  WHERE `itemid`='" + i.getId_item() + "';";
                    stm2.executeUpdate(qryy3);
                    String qryy2 = "DELETE FROM `like_items` WHERE `userid` = "+CurrentUser.getInstance().getId_user()+" AND `itemid` = "+i.getId_item() + " AND `choice` = 1;";
                    stm2.executeUpdate(qryy2);
                }
                rss.close();

                String qry2 = "INSERT INTO `like_items` (`userid`, `itemid`, `choice`) VALUES (" + CurrentUser.getInstance().getId_user() + "," + i.getId_item() + ",0);";
                stm2.executeUpdate(qry2);
                String qry3 = "UPDATE `item` SET `likes`= `likes` + 1  WHERE `itemid`='" + i.getId_item() + "';";
                stm2.executeUpdate(qry3);
            }

            rs.close();

            if (x == false) {
                String qryy3 = "UPDATE `item` SET `likes`= `likes` - 1  WHERE `itemid`='" + i.getId_item() + "';";
                stm2.executeUpdate(qryy3);
                String qryy2 = "DELETE FROM `like_items` WHERE `userid` = "+CurrentUser.getInstance().getId_user()+" AND `itemid` = "+i.getId_item() + " AND `choice` = 0;";
                stm2.executeUpdate(qryy2);


            }





        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public Boolean dislike(Item i) throws SQLException {
        Boolean x = false ;
        Statement stm2 = cnx.createStatement();
        Statement stm3 = cnx.createStatement();
        try {
            String qry = "SELECT * FROM `like_items` WHERE `userid` = "+CurrentUser.getInstance().getId_user()+" AND `itemid` = "+i.getId_item() + " AND `choice` = 1";
            stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (!rs.next()) {
                x = true ;
                String qryy = "SELECT * FROM `like_items` WHERE `userid` = "+CurrentUser.getInstance().getId_user()+" AND `itemid` = "+i.getId_item() + " AND `choice` = 0";
                ResultSet rss = stm3.executeQuery(qryy);

                while (rss.next()) {

                    String qryy3 = "UPDATE `item` SET `likes`= `likes` - 1  WHERE `itemid`='" + i.getId_item() + "';";
                    stm2.executeUpdate(qryy3);
                    String qryy2 = "DELETE FROM `like_items` WHERE `userid` = "+CurrentUser.getInstance().getId_user()+" AND `itemid` = "+i.getId_item() + " AND `choice` = 0;";
                    stm2.executeUpdate(qryy2);
                }
                rss.close();

                String qry2 = "INSERT INTO `like_items` (`userid`, `itemid`, `choice`) VALUES (" + CurrentUser.getInstance().getId_user() + "," + i.getId_item() + ",1);";
                stm2.executeUpdate(qry2);
                String qry3 = "UPDATE `item` SET `dislikes`= `dislikes` + 1  WHERE `itemid`='" + i.getId_item() + "';";
                stm2.executeUpdate(qry3);
            }
            rs.close();


            if (x == false) {
                String qryy3 = "UPDATE `item` SET `dislikes`= `dislikes` - 1  WHERE `itemid`='" + i.getId_item() + "';";
                stm2.executeUpdate(qryy3);
                String qryy2 = "DELETE FROM `like_items` WHERE `userid` = "+CurrentUser.getInstance().getId_user()+" AND `itemid` = "+i.getId_item() + " AND `choice` = 1;";
                stm2.executeUpdate(qryy2);


            }





        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }



    public List<Item> afficherUserItems(int u) {
        List<Item> items = new ArrayList();
        try {
            String qry = "SELECT * FROM item WHERE id_user_id = '"+u+"';";
            stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Item i = new Item();
                i.setId_item(rs.getInt("id"));
                i.setLibelle(rs.getString("libelle"));
                i.setDescription(rs.getString("description"));
                i.setType(Item.type.valueOf(rs.getString("type")));
                i.setEtat(Item.state.valueOf(rs.getString("etat")));
                i.setId_user(rs.getInt("id_user_id"));
                i.setId_categorie(rs.getInt("id_categorie_id"));
                i.setId_echange(rs.getInt("id_echange_id"));

                items.add(i);
            }
            return items;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return items;
    }

    public List<Item> afficherUserItemsEchange(int u, Echange e) {
        List<Item> items = new ArrayList();
        try {
            String qry = "SELECT * FROM item WHERE id_user_id = '"+u+"' AND id_echange_id = '"+e.getId_echange()+"'";
            stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                Item i = new Item();
                i.setId_item(rs.getInt("id"));
                i.setLibelle(rs.getString("libelle"));
                i.setDescription(rs.getString("description"));
                i.setType(Item.type.valueOf(rs.getString("type")));
                i.setEtat(Item.state.valueOf(rs.getString("etat")));
                i.setId_user(rs.getInt("id_user_id"));
                i.setId_categorie(rs.getInt("id_categorie_id"));
                i.setId_echange(rs.getInt("id_echange_id"));

                items.add(i);
            }
            return items;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return items;
    }
}
