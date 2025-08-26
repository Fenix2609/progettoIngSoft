package commands;

import controller.LibreriaController;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import model.Libro;

public class RimuoviLibroCommand implements Command {
    private LibreriaController controller;

    public RimuoviLibroCommand(LibreriaController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() { }

    public void executeFX(TableView<Libro> table, ObservableList<Libro> libriObservable) {
        Libro selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            controller.rimuoviLibro(selected.getIsbn());
        }
    }
}
