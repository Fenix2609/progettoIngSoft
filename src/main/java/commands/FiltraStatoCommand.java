package commands;
import controller.LibreriaController;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import model.Libro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FiltraStatoCommand implements Command {
    private LibreriaController controller;

    public FiltraStatoCommand(LibreriaController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        // non usato in questo caso
    }

    public void executeFX(ObservableList<Libro> libriObservable) {
        List<String> scelte = List.of("letto", "In corso", "da leggere","tutti");
        ChoiceDialog <String> dialog = new ChoiceDialog("letto",scelte);

        dialog.setHeaderText("Stato di lettura da filtrare (es. 'Letto', 'In corso', 'Da leggere'):");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(stato -> {
            List<Libro> filtered;
            if (stato.equalsIgnoreCase("tutti")) {
                // Se l’utente seleziona "tutti", mostriamo tutti i libri senza filtrare
                filtered = controller.getLibri();
            } else {
                // Altrimenti filtra per lo stato scelto
                filtered = controller.filtraPerStato(stato);
            }
            libriObservable.setAll(filtered);
        });
    }
}