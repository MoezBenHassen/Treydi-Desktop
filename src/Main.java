
import Entities.Trader;
import Entities.Utilisateur;
import Services.UtilisateurService;
import Utils.Enums.Roles;
import java.sql.Date;


public class Main {
    public static void main(String[] args) {
        UtilisateurService us = new UtilisateurService();

        System.out.println("Insertion des categories ----------");


        System.out.println("Affichage tout categories ----------");


        System.out.println("Modification d'une categorie ----------");
        Utilisateur tr1  = new Trader("medadem","nom2","prenom2","mail@mail2","adress2","url2",Roles.trader,2,new Date(2022,10,10),0);
       // System.out.println(us.supprimer(us1)) ;

      us.add(tr1);
          }
}