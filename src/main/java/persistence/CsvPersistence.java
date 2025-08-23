package persistence;

import model.Libro;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvPersistence implements PersistenceManager {

    @Override
    public void save(String nomeFile, List<Libro> libri) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFile))) {
            // ✅ Controllo estensione file
            if (!nomeFile.toLowerCase().endsWith(".csv")) {
                System.out.println("Errore: il file deve avere estensione .csv");
            }
            else {
                for (Libro l : libri) {
                    writer.write(l.getTitolo() + "," + l.getAutore() + "," + l.getIsbn() + "," +
                            l.getGenere() + "," + l.getValutazione() + "," + l.getStatoLettura());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio CSV: " + e.getMessage());
        }
    }

    @Override
    public List<Libro> load(String nomeFile) {
        List<Libro> libri = new ArrayList<>();

        // ✅ Controllo estensione file
        if (!nomeFile.toLowerCase().endsWith(".csv")) {
            System.out.println("Errore: il file deve avere estensione .csv");
            return null; // restituisce lista vuota senza tentare il caricamento
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFile))) {
            String riga;
            while ((riga = reader.readLine()) != null) {
                String[] parti = riga.split(",");
                if (parti.length == 6) {
                    libri.add(new Libro(
                            parti[0], // titolo
                            parti[1], // autore
                            parti[2], // isbn
                            parti[3], // genere
                            Integer.parseInt(parti[4]), // valutazione
                            parti[5]  // stato lettura
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println("Errore durante il caricamento CSV: " + e.getMessage());
        }
        return libri;
    }
}