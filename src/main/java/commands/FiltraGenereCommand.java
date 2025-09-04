package commands;

import controller.LibreriaController;
import javafx.collections.ObservableList;
import javafx.scene.control.TextInputDialog;
import model.Libro;

import java.util.List;
import java.util.Optional;

public class FiltraGenereCommand implements Command {
    private LibreriaController controller;

    public FiltraGenereCommand(LibreriaController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() { }

    public void executeFX(ObservableList<Libro> libriObservable) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Genere da filtrare (lascia vuoto per mostrare tutti):");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(genere -> {
            if (genere.trim().isEmpty()) {
                // Se non è stato inserito niente → mostra tutti i libri
                libriObservable.setAll(controller.getLibri());
            } else {
                // Altrimenti applica il filtro
                List<Libro> filtered = controller.filtraPerGenere(genere);
                libriObservable.setAll(filtered);
            }
        });
    }
}
