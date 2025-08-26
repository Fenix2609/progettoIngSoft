package controller;

import model.Libro;
import observer.Observable;
import persistence.PersistenceManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LibreriaController extends Observable {
    private List<Libro> libri;
    private PersistenceManager persistence;

    // Singleton
    private static LibreriaController instance;

    private LibreriaController(PersistenceManager persistence) {
        this.libri = new ArrayList<>();
        this.persistence = persistence;
    }

    // Ritorna l’unica istanza (la prima call decide la persistence)
    public static synchronized LibreriaController getInstance(PersistenceManager persistence) {
        if (instance == null) {
            instance = new LibreriaController(persistence);
        }
        return instance;
    }

    // (Opzionale) cambio strategia di persistenza a runtime
    public void setPersistence(PersistenceManager persistence) {
        this.persistence = persistence;
    }

    // --- Modifiche dati con notify ---

    public void aggiungiLibro(Libro libro) {
        libri.add(libro);
        notifyObservers();
    }

    public void rimuoviLibro(String isbn) {
        boolean removed = libri.removeIf(b -> b.getIsbn().equals(isbn));
        if (removed) notifyObservers();
    }

    public void modificaTitoloLibro(Libro libro, String nuovoTitolo) {
        libro.setTitolo(nuovoTitolo);
        notifyObservers();
    }

    public void modificaAutoreLibro(Libro libro, String nuovoAutore) {
        libro.setAutore(nuovoAutore);
        notifyObservers();
    }

    public void modificaIsbnLibro(Libro libro, String nuovoIsbn) {
        libro.setIsbn(nuovoIsbn);
        notifyObservers();
    }

    public void modificaGenereLibro(Libro libro, String nuovoGenere) {
        libro.setGenere(nuovoGenere);
        notifyObservers();
    }

    public void modificaValutazioneLibro(Libro libro, int nuovaValutazione) {
        libro.setValutazione(nuovaValutazione);
        notifyObservers();
    }

    public void modificaStatoLetturaLibro(Libro libro, String nuovoStato) {
        libro.setStatoLettura(nuovoStato);
        notifyObservers();
    }

    // --- Query / viste (non mutano lo stato) ---

    public List<Libro> getLibri() {
        return libri;
    }

    public Libro cercaPerIsbn(String isbn) {
        return libri.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    public List<Libro> filtraPerGenere(String genere) {
        return libri.stream()
                .filter(b -> b.getGenere().equalsIgnoreCase(genere))
                .collect(Collectors.toList());
    }

    public List<Libro> ordinaPerAutore() {
        return libri.stream()
                .sorted(Comparator.comparing(Libro::getAutore))
                .collect(Collectors.toList());
    }

    public List<Libro> ordinaPerStato() {
        return libri.stream()
                .sorted(Comparator.comparing(Libro::getStatoLettura))
                .collect(Collectors.toList());
    }

    // --- Persistenza ---

    public void save(String nomeFile) {
        persistence.save(nomeFile, libri);
        // di norma salvare non richiede notify, ma lascio così per coerenza
    }

    public void load(String nomeFile) {
        List<Libro> caricati = persistence.load(nomeFile);
        this.libri = (caricati != null) ? caricati : new ArrayList<>();
        notifyObservers(); // aggiorna subito la GUI
    }

    @Override
    public String toString() {
        return libri.toString();
    }
}
