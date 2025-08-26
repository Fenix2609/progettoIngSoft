package commands;

import controller.LibreriaController;
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
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Inserisci ISBN da cercare:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(isbn -> {
            for (Libro libro : table.getItems()) {
                if (libro.getIsbn().equals(isbn)) {
                    table.getSelectionModel().select(libro);
                    table.scrollTo(libro); // opzionale: fa scroll sulla riga
                    break;
                }
            }
        });
    }
}
