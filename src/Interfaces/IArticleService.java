package Interfaces;

import java.util.List;

public interface IArticleService<T> {
    public void add(T t );
    public List<T> afficher();
    public List<T> afficherArchived();
    public Boolean update(T t);
    public Boolean delete(T t);
}
