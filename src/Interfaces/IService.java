package Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    public void ajouter(T t);
    public List<T> afficher();
    public Boolean modifier(T t);
    public Boolean supprimer(T t);

}
