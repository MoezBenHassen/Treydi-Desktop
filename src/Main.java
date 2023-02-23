import Entities.Reclamation;
import Entities.Reponse;
import Services.ServiceReclamation;
import Services.ServiceReponse;

import Entities.Etat_reclamation ;
import java.sql.Date ;

public class Main {
    public static void main(String[] args)  {
     // Reclamation R1 =new Reclamation(8,"reclamation333333","produit33333","En_cours",new Date(2022,3,4),new Date(2022,3,4),9,0);
        ServiceReclamation sr =new ServiceReclamation() ;
        ServiceReponse sre = new ServiceReponse() ;
        //Reponse Re = new Reponse(5,"Reponse 3","acscqsqcscq",new Date(2022,3,4));
     //Reclamation R = new Reclamation("reclamation","produit",Etat_reclamation.En_cours,new Date(2022,3,4),44, 0);
      //  sr.ajouter(R);
        System.out.println(sr.afficher()) ;
        //System.out.println(sr.afficher());
       // sr.supprimer(R1) ;
        //sr.modifier(R) ;
        // sre.ajouter(Re);
        //System.out.println(sre.afficher());
        //sre.supprimer(Re) ;
    }
}