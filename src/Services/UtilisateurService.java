package Services;

import Entities.Utilisateur;
import Utils.MyDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurService implements IService<Utilisateur> {
    Connection con;
    Statement stm;

    public UtilisateurService() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public void add(Utilisateur u) throws SQLException {
        String req ="INSERT INTO `utilisateur2` (`id_user`,`prenom`, `nom`, `avatar_url`) " +"VALUES ('"+u.getId()+"','"+u.getPrenom()+"','"+u.getNom()+"','"+u.getImage_url()+"')";
        stm = con.createStatement();
        stm.executeUpdate(req);

    }

    @Override
    public List<Utilisateur> afficher() throws SQLException {
        String query="SELECT * from `utilisateur2`";
        stm = con.createStatement();
        ResultSet result=stm.executeQuery(query);
        System.out.println(result.toString());
        List<Utilisateur> users = new ArrayList<>();
        while (result.next()){
            Utilisateur u = new Utilisateur(result.getInt("id_user"), result.getString("prenom"),
                    result.getString("nom"), result.getString("avatar_url"));
            users.add(u);
        }
        System.out.println(">>>> CHOSE :  " + users);
        return users;
    }
}
