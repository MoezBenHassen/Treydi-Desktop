package Services;

import Entities.Echange;
import Entities.EchangeProposer;
import Entities.Item;
import Utils.MyDB;

import Interfaces.IService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class EchangeProposerService implements IService<EchangeProposer> {
    Connection con;
    Statement stm;

    public EchangeProposerService() { con = MyDB.getInstance().getCnx(); }

    @Override
    public void ajouter(EchangeProposer echangeProposer) {

    }

    @Override
    public void add(EchangeProposer echangeProposer) {
        java.sql.Date current_date = new java.sql.Date(System.currentTimeMillis());
        String req = "INSERT INTO `echange_proposer` (`id_echange`, `date_proposer`, `id_user`)"
                + "VALUES ('" + echangeProposer.getId_echange() + "', '" + current_date + "', '" + echangeProposer.getId_user() + "')";
        try {
            stm = con.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<EchangeProposer> afficher() {
        List<EchangeProposer> epl = new ArrayList<>();

        String query = "SELECT * from `echange_proposer` WHERE archived = 0";
        try {
            stm = con.createStatement();
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                EchangeProposer ep = new EchangeProposer(result.getInt("id"), result.getInt("id_echange"), result.getDate("date_proposer"), result.getInt("id_user"));
                epl.add(ep);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return epl;
    }

    @Override
    public Boolean modifier(EchangeProposer echangeProposer) {
        return null;
    }

    @Override
    public Boolean supprimer(EchangeProposer echangeProposer) {
        return null;
    }


    public EchangeProposer getProp(int id) {
        String query = "SELECT * from `echange_proposer` WHERE id = '"+id+"'";
        try {
            stm = con.createStatement();
            ResultSet result = stm.executeQuery(query);
            if (result.next()) {
                EchangeProposer ep = new EchangeProposer(result.getInt("id"), result.getInt("id_echange"), result.getDate("date_proposer"), result.getInt("id_user"));
                return ep;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    //TO BE REPLACED WITH CURRENT USER ID
    public void ProposerEchange(Echange e, List<Item> items, int id) {
        java.sql.Date current_date = new java.sql.Date(System.currentTimeMillis());

        String req = "INSERT INTO `echange_proposer` (`id_echange`, `date_proposer`, `id_user`) VALUES ('" + e.getId_echange() + "', '" + current_date + "', '" + id + "')";
        try {
            stm = con.createStatement();
            stm.executeUpdate(req, Statement.RETURN_GENERATED_KEYS);
            for (Item i : items) {
                stm = con.createStatement();
                stm.executeUpdate("UPDATE `item` SET `id_echange` = '" + e.getId_echange() + "' WHERE `id_item`='" + i.getId_item() + "'");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<EchangeProposer> afficherPropEch(Echange ee) {
        List<EchangeProposer> epl = new ArrayList<>();

        String query = "SELECT * from `echange_proposer` WHERE `id_echange` = '"+ee.getId_echange()+"' AND archived = 0";
        try {
            stm = con.createStatement();
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                EchangeProposer ep = new EchangeProposer(result.getInt("id"), result.getInt("id_echange"), result.getDate("date_proposer"), result.getInt("id_user"));
                epl.add(ep);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return epl;
    }

    public String getUsernameProp(EchangeProposer e){
        String req = "SELECT `prenom` FROM `utilisateur` WHERE id_user = '"+e.getId_user()+"'";
        try {
            stm = con.createStatement();
            ResultSet result = stm.executeQuery(req);
            if (result.next()) {
                return result.getString("prenom");
            } else {
                throw new RuntimeException("No record found");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Echange getEchangeByProp(EchangeProposer ep) {
        String req = "SELECT * FROM echange WHERE id_echange = '"+ep.getId_echange()+"'";
        try {
            stm = con.createStatement();
            ResultSet result = stm.executeQuery(req);
            if (result.next()){
                Echange e = new Echange();
                e.setId_echange(result.getInt("id_echange"));
                e.setTitre_echange(result.getString("titre_echange"));
                e.setId_user1(result.getInt("id_user1"));
                e.setId_user2(result.getInt("id_user2"));
                return e;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void accProposition(EchangeProposer ep, List<Item> items, Echange ee){
        String req = "UPDATE echange SET id_user2 = '"+ep.getId_user()+"'";
        try {
            stm = con.createStatement();
            stm.executeUpdate(req);
            for (Item i : items) {
                stm = con.createStatement();
                stm.executeUpdate("UPDATE `item` SET `id_echange` =  NULL WHERE `id_echange`='" + ep.getId_echange() + "' AND `id_user` NOT IN ('"+ep.getId_user()+"' , '"+ee.getId_user1()+"')");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void refuserPropostion(EchangeProposer ep ,List<Item> items) {
        String req="UPDATE `echange_proposer` SET archived = 1 WHERE id = '"+ep.getId()+"'";
        try {
            stm = con.createStatement();
            stm.executeUpdate(req);
            for (Item i : items) {
                stm = con.createStatement();
                stm.executeUpdate("UPDATE `item` SET `id_echange` = NULL WHERE `id_item`='" + i.getId_item() + "'");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Item> getListItemsByProp(EchangeProposer ep) {
        List<Item> items = new ArrayList();

        String qry = "SELECT * FROM `item` WHERE id_echange = '"+ep.getId_echange()+"'";
        try {

            stm = con.createStatement();

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
}