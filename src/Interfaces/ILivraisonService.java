package Interfaces;

import java.util.List;
import java.sql.SQLException;
import java.util.List;

public interface ILivraisonService<T> {
    public void add(T t );
    public List<T> afficher();
    public Boolean update(T t);
    public Boolean delete(int id);
}



