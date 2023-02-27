package Interfaces;

import java.util.List;

public interface IItemCategorieService<T> {
    public void ajouter(T t);
    public List<T> afficher();
    public List<T> afficherAdmin();
    public Boolean modifier(T t);
    public Boolean supprimer(T t);

}
