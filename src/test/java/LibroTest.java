import model.Libro;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibroTest {

    @Test
    void testCostruttoreEGetters() {
        // Creo un libro con tutti i campi
        Libro libro = new Libro("Naruto", "Kishimoto", "12345", "Manga", 5, "Letto");
        // Verifico che i getter restituiscano i valori giusti
        assertEquals("Naruto", libro.getTitolo());
        assertEquals("Kishimoto", libro.getAutore());
        assertEquals("12345", libro.getIsbn());
        assertEquals("Manga", libro.getGenere());
        assertEquals(5, libro.getValutazione());
        assertEquals("Letto", libro.getStatoLettura());
    }

    @Test
    void testSetters() {
        // Creo un libro con tutti i campi
        Libro libro = new Libro("Titolo", "Autore", "111", "Genere", 3, "In lettura");
        // Modifico i campi con i setter
        libro.setTitolo("Bleach");
        libro.setAutore("Kubo");
        libro.setIsbn("222");
        libro.setGenere("Shonen");
        libro.setValutazione(4);
        libro.setStatoLettura("Finito");
        // Verifico che i valori siano cambiati correttamente
        assertEquals("Bleach", libro.getTitolo());
        assertEquals("Kubo", libro.getAutore());
        assertEquals("222", libro.getIsbn());
        assertEquals("Shonen", libro.getGenere());
        assertEquals(4, libro.getValutazione());
        assertEquals("Finito", libro.getStatoLettura());
    }
}