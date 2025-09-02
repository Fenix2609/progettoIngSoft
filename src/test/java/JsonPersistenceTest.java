import controller.LibreriaController;
import model.Libro;
import org.junit.jupiter.api.Test;
import persistence.JsonPersistence;
import persistence.PersistenceManager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;
public class JsonPersistenceTest {

    @Test
    void testSaveAndLoad() throws Exception {
        // Percorso del file di test nella cartella resources/testdata
        String fileName = "src/test/resources/testdata/libri_test.json";

        // Inizializza la persistenza
        PersistenceManager persistence = new JsonPersistence();

        // Crea un libro da salvare
        Libro libro = new Libro("TitoloTest", "AutoreTest", "12345", "GenereTest", 5, "Letto");
        LibreriaController controller = LibreriaController.getInstance(persistence);
        controller.getLibri().clear(); // pulisco per sicurezza
        controller.aggiungiLibro(libro);

        // Salva
        persistence.save(fileName, controller.getLibri());

        // Carica di nuovo
        List<Libro> libriCaricati = persistence.load(fileName);

        // Controllo che il libro sia stato salvato e ricaricato correttamente
        assertEquals(1, libriCaricati.size());
        assertEquals("TitoloTest", libriCaricati.get(0).getTitolo());
        assertEquals("AutoreTest", libriCaricati.get(0).getAutore());

        //IMPORTANTE: elimina il file dopo il test
        new File(fileName).delete();
    }
}

