package persistence;

import model.Libro;

import java.util.List;

public class PersistenceProxy implements PersistenceManager {
    private PersistenceManager persistence; // il vero gestore della persistenza

    public PersistenceProxy(PersistenceManager persistence) {
        this.persistence = persistence;
    }

    @Override
    public void save(String nomeFile, List<Libro> libri) {
        // Qui puoi aggiungere controlli o sicurezza prima del salvataggio
        System.out.println("Proxy: avvio del salvataggio su file " + nomeFile);
        persistence.save(nomeFile, libri);
        System.out.println("Proxy: salvataggio completato");
    }

    @Override
    public List<Libro> load(String nomeFile) {
        // Qui puoi aggiungere controlli o sicurezza prima del caricamento
        System.out.println("Proxy: avvio del caricamento da file " + nomeFile);
        List<Libro> libri = persistence.load(nomeFile);
        if (libri != null) {
            System.out.println("Proxy: caricamento completato");
        }
        else{
            System.out.println("Proxy: caricamento non completato");
        }
        return libri;
    }
}