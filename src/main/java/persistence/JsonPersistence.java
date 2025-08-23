package persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Libro;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonPersistence implements PersistenceManager {
    private Gson gson = new Gson();

    @Override
    public void save(String nomeFile, List<Libro> libri) {

        try (FileWriter writer = new FileWriter(nomeFile)) {
            // ✅ Controllo estensione file
            if (!nomeFile.toLowerCase().endsWith(".json")) {
                System.out.println("Errore: il file deve avere estensione .json");
            }
            else {
                gson.toJson(libri, writer);
            }
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio JSON: " + e.getMessage());
        }
    }

    @Override
    public List<Libro> load(String nomeFile) {
        List<Libro> libri = new ArrayList<>();
        // ✅ Controllo estensione file
        if (!nomeFile.toLowerCase().endsWith(".json")) {
            System.out.println("Errore: il file deve avere estensione .json");
            return null; // restituisce lista vuota senza tentare il caricamento
        }
        try (FileReader reader = new FileReader(nomeFile)) {
            Type listType = new TypeToken<List<Libro>>(){}.getType();
            libri = gson.fromJson(reader, listType);
            if (libri == null) libri = new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Errore durante il caricamento JSON: " + e.getMessage());
        }
        return libri;
    }
}