import Entities.Categorie_Items;
import Entities.Item;
import Services.CategorieItemsService;
import Services.ItemService;

public class Old {
    public static void main(String[] args) {

        CategorieItemsService cs = new CategorieItemsService();
        ItemService is = new ItemService();

        System.out.println("Insertion des categories ----------");
        Categorie_Items c1 = new Categorie_Items("Console") ;
        Categorie_Items c2 = new Categorie_Items("Livre") ;
        cs.ajouter(c1) ; cs.ajouter(c2) ;

        System.out.println("Affichage tout categories ----------");
        System.out.println(cs.afficher()) ;

        System.out.println("Modification d'une categorie ----------");
        Categorie_Items cc1 = new Categorie_Items(2,"Console de Jeu") ;
        System.out.println(cs.modifier(cc1)) ;
        System.out.println(cs.afficher()) ;

        System.out.println("Suppression d'une categorie ----------");
        Categorie_Items cc2 = new Categorie_Items(3) ;
        System.out.println(cs.supprimer(cc2)) ;
        System.out.println(cs.afficher()) ;

        System.out.println("Insertion d'un item ----------");
        Item i1 =new Item("Nintendo Switch", "Nintendos latest console released in 2017, the worlds first portable home console", Item.type.Physique,Item.state.Neuf,"null",4,1,0,0,0);
        Item i2 =new Item("Playstation 5", "Sonys latest console released in 2020", Item.type.Physique,Item.state.Occasion,"null",4,1,0,0,0);
        is.ajouter(i1) ; is.ajouter(i2) ;

        System.out.println("Affichage tout items ----------");
        System.out.println(is.afficher()) ;

        System.out.println("Modification d'un item ----------");
        Item ii2 =new Item(2,"Playstation 5", "Sonys latest console released in 2020, the worlds most powerful console", Item.type.Physique,Item.state.Neuf,"null",2,1,0,0,0);
        System.out.println(is.modifier(ii2)) ;
        System.out.println(is.afficher()) ;

        System.out.println("Suppression d'un item ----------");
        System.out.println(is.supprimer(ii2)) ;
        System.out.println(is.afficher()) ;
    }
    }
