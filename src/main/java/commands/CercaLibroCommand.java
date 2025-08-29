package commands;

import controller.LibreriaController;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TableView;
import model.Libro;

import java.util.Optional;

public class CercaLibroCommand implements Command {
    private LibreriaController controller;

    public CercaLibroCommand(LibreriaController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() { /* console version */ }

    public void executeFX(TableView<Libro> table) {
        // chiediamo prima cosa vuole cercare
        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>("ISBN", "ISBN", "Titolo");
        choiceDialog.setHeaderText("Vuoi cercare per ISBN o per Titolo?");
        Optional<String> scelta = choiceDialog.showAndWait();

        if (!scelta.isPresent()) return; // se annulla, esci

        // input valore da cercare
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText("Inserisci " + scelta.get() + " da cercare:");
        Optional<String> valore = inputDialog.showAndWait();

        if (!valore.isPresent()) return;

        String ricerca = valore.get().trim();
        if (ricerca.isEmpty()) return;

        for (Libro libro : table.getItems()) {
            boolean trovato = false;

            if (scelta.get().equals("ISBN") && libro.getIsbn().equalsIgnoreCase(ricerca)) {
                trovato = true;
            } else if (scelta.get().equals("Titolo") && libro.getTitolo().equalsIgnoreCase(ricerca)) {
                trovato = true;
            }

            if (trovato) {
                table.getSelectionModel().select(libro);
                table.scrollTo(libro); // porta in vista
                break;
            }
        }
    }
}
