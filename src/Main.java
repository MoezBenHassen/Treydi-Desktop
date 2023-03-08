import Entities.Admin;
import Entities.Livreur;
import Entities.Trader;
import Entities.Utilisateur;
import Services.UtilisateurService;
import Utils.Enums.Roles;

import java.sql.SQLException;
import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        UtilisateurService us = new UtilisateurService();
        Utilisateur tr = new Trader("aaz", "NewNom5", "NewPrenom5", "aaz","Tunis","file:/D:/avatar1.png",Roles.trader, 12,"2000-1-1",0);

        us.add(tr);

    }
}