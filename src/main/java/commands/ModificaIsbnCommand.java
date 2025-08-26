package commands;

import controller.LibreriaController;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import model.Libro;

import java.util.Optional;

public class ModificaIsbnCommand implements Command {
    private LibreriaController controller;

    public ModificaIsbnCommand(LibreriaController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() { }

    public void executeFX(TableView<Libro> table, ObservableList<Libro> libriObservable) {
        Libro selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        TextInputDialog dialog = new TextInputDialog(selected.getIsbn());
        dialog.setHeaderText("Modifica ISBN per il libro: " + selected.getTitolo());
        dialog.setContentText("Nuovo ISBN:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newIsbn -> {
            controller.modificaIsbnLibro(selected, newIsbn);
            // Aggiorna ObservableList per riflettere la modifica
            int index = libriObservable.indexOf(selected);
            if (index >= 0) {
                libriObservable.set(index, selected); // forza il refresh della lista
            }
            table.refresh();
        });
    }
}
