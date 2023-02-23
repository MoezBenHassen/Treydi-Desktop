package Services;

import java.util.List;

public interface IService<T> {
    public void add(T t );
    public List<T> afficher();
    public Boolean modifier(T t);
    public Boolean supprimer(T t);
}
