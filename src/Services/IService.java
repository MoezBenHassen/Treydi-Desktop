package Services;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    void add(T t) throws SQLException;
    List<T> afficher() throws SQLException;
}
