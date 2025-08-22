package persistence;

import model.Libro;
import java.util.List;

public interface PersistenceManager {
    void save(String nomeFile, List<Libro> libri);
    List<Libro> load(String nomeFile);
}

