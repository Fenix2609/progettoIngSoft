package persistence;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Libro;
import model.LibroDto;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonPersistence implements PersistenceManager {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void save(String nomeFile, List<Libro> libri) {
        try (Writer writer = new FileWriter(nomeFile)) {
            if (!nomeFile.toLowerCase().endsWith(".json")) {
                System.out.println("Errore: il file deve avere estensione .json");
                return;
            }

            // Converto Libro -> LibroDTO
            List<LibroDto> dtoList = new ArrayList<>();
            for (Libro l : libri) {
                dtoList.add(new LibroDto(
                        l.getTitolo(),
                        l.getAutore(),
                        l.getIsbn(),
                        l.getGenere(),
                        l.getValutazione(),
                        l.getStatoLettura()
                ));
            }

            gson.toJson(dtoList, writer);
            System.out.println("Salvataggio JSON completato in: " + nomeFile);

        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio JSON: " + e.getMessage());
        }
    }

    @Override
    public List<Libro> load(String nomeFile) {
        if (!nomeFile.toLowerCase().endsWith(".json")) {
            System.out.println("Errore: il file deve avere estensione .json");
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(nomeFile)) {
            Type listType = new TypeToken<List<LibroDto>>(){}.getType();
            List<LibroDto> dtoList = gson.fromJson(reader, listType);

            List<Libro> libri = new ArrayList<>();
            if (dtoList != null) {
                for (LibroDto dto : dtoList) {
                    libri.add(new Libro(
                            dto.getTitolo(),
                            dto.getAutore(),
                            dto.getIsbn(),
                            dto.getGenere(),
                            dto.getValutazione(),
                            dto.getStato()
                    ));
                }
            }
            return libri;

        } catch (IOException e) {
            System.out.println("Errore durante il caricamento JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}