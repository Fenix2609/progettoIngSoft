import model.Libro;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibroTest {

    @Test
    void testCostruttoreEGetters() {
        Libro libro = new Libro("Naruto", "Kishimoto", "12345", "Manga", 5, "Letto");

        assertEquals("Naruto", libro.getTitolo());
        assertEquals("Kishimoto", libro.getAutore());
        assertEquals("12345", libro.getIsbn());
        assertEquals("Manga", libro.getGenere());
        assertEquals(5, libro.getValutazione());
        assertEquals("Letto", libro.getStatoLettura());
    }

    @Test
    void testSetters() {
        Libro libro = new Libro("Titolo", "Autore", "111", "Genere", 3, "In lettura");

        libro.setTitolo("Bleach");
        libro.setAutore("Kubo");
        libro.setIsbn("222");
        libro.setGenere("Shonen");
        libro.setValutazione(4);
        libro.setStatoLettura("Finito");

        assertEquals("Bleach", libro.getTitolo());
        assertEquals("Kubo", libro.getAutore());
        assertEquals("222", libro.getIsbn());
        assertEquals("Shonen", libro.getGenere());
        assertEquals(4, libro.getValutazione());
        assertEquals("Finito", libro.getStatoLettura());
    }
}