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
        // Prima di ogni test creo un nuovo controller con persistenza JSON
        controller = LibreriaController.getInstance(new JsonPersistence());
    }

    @Test
    void testAggiungiLibro() {
        // Creo un libro
        Libro libro = new Libro("Naruto", "Kishimoto", "123", "Manga", 5, "Letto");
        // Lo aggiungo al controller
        controller.aggiungiLibro(libro);
        // Recupero i libri e verifico che contengano il libro aggiunto
        List<Libro> libri = controller.getLibri();
        assertTrue(libri.contains(libro));
    }

    @Test
    void testRimuoviLibro() {
        // Creo e aggiungo un libro
        Libro libro = new Libro("Bleach", "Kubo", "456", "Manga", 4, "In lettura");
        controller.aggiungiLibro(libro);

        // Lo rimuovo usando l’ISBN
        controller.rimuoviLibro("456");

        // Controllo che non sia più presente
        assertFalse(controller.getLibri().contains(libro));
    }

    @Test
    void testModificaTitoloLibro() {
        // Creo e aggiungo un libro
        Libro libro = new Libro("One Piece", "Oda", "789", "Manga", 5, "In corso");
        controller.aggiungiLibro(libro);

        // Cambio il titolo
        controller.modificaTitoloLibro(libro, "One Piece New");

        // Verifico che il titolo sia stato modificato
        assertEquals("One Piece New", libro.getTitolo());
    }
}
