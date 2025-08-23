package controller;

import model.Libro;
import persistence.PersistenceManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LibreriaController {
    private List<Libro> libri;
    private PersistenceManager persistence;
    private static LibreriaController instance;
    private LibreriaController(PersistenceManager persistence) {
        this.libri = new ArrayList<>();
        this.persistence = persistence;
    }

    // Metodo per ottenere l’unica istanza (Singleton)
    public static LibreriaController getInstance(PersistenceManager persistence) {
        if (instance == null) {
            instance = new LibreriaController(persistence);
        }
        return instance;
    }

    public void aggiungiLibro(Libro libro) { libri.add(libro); }
    public void rimuoviLibro(String isbn) { libri.removeIf(b -> b.getIsbn().equals(isbn)); }
    public void modificaTitoloLibro(Libro libro,String nuovoTitolo) {
        libro.setTitolo(nuovoTitolo);
    }
    public void modificaAutoreLibro(Libro libro, String nuovoAutore) {
        libro.setAutore(nuovoAutore);
    }

    public void modificaIsbnLibro(Libro libro, String nuovoIsbn) {
        libro.setIsbn(nuovoIsbn);
    }

    public void modificaGenereLibro(Libro libro, String nuovoGenere) {
        libro.setGenere(nuovoGenere);
    }

    public void modificaValutazioneLibro(Libro libro,int nuovaValutazione) {
        libro.setValutazione(nuovaValutazione);
    }

    public void modificaStatoLetturaLibro(Libro libro, String nuovoStato) {
        libro.setStatoLettura(nuovoStato);
    }



    //ricerca e filtro
    public List<Libro> getLibri() { return libri; }

    public Libro cercaPerIsbn(String isbn) {
        return libri.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    public List<Libro> filtraPerGenere(String genere) {
        return libri.stream().filter(b -> b.getGenere().equalsIgnoreCase(genere))
                .collect(Collectors.toList());
    }

    public List<Libro> sortByAuthor() {
        return libri.stream().sorted(Comparator.comparing(Libro::getAutore))
                .collect(Collectors.toList());
    }

    public void save(String nomeFile) { persistence.save(nomeFile, libri); }
    public void load(String nomeFile) { libri = persistence.load(nomeFile); }

    @Override
    public String toString() { return libri.toString(); }
}
