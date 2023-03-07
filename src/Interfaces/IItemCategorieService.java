package Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IItemCategorieService<T> {
    public void ajouter(T t);
    public List<T> afficher();
    public List<T> afficherAdmin();
    public Boolean modifier(T t);
    public Boolean supprimer(T t);

    public Boolean like(T t) throws SQLException;

    public Boolean dislike(T t) throws SQLException;

}
