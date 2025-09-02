import controller.LibreriaController;
import model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonPersistence;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class LibreriaControllerTest {

    private LibreriaController controller;

    @BeforeEach
    void setUp() {
        controller = LibreriaController.getInstance(new JsonPersistence());
    }

    @Test
    void testAggiungiLibro() {
        Libro libro = new Libro("Naruto", "Kishimoto", "123", "Manga", 5, "Letto");
        controller.aggiungiLibro(libro);

        List<Libro> libri = controller.getLibri();
        assertTrue(libri.contains(libro));
    }

    @Test
    void testRimuoviLibro() {
        Libro libro = new Libro("Bleach", "Kubo", "456", "Manga", 4, "In lettura");
        controller.aggiungiLibro(libro);

        controller.rimuoviLibro("456");
        assertFalse(controller.getLibri().contains(libro));
    }

    @Test
    void testModificaTitoloLibro() {
        Libro libro = new Libro("One Piece", "Oda", "789", "Manga", 5, "In corso");
        controller.aggiungiLibro(libro);

        controller.modificaTitoloLibro(libro, "One Piece New");
        assertEquals("One Piece New", libro.getTitolo());
    }
}
