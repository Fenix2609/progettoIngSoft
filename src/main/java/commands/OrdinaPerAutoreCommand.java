package commands;

import controller.LibreriaController;
import javafx.collections.ObservableList;
import model.Libro;

import java.util.List;

public class OrdinaPerAutoreCommand implements Command {
    private LibreriaController controller;

    public OrdinaPerAutoreCommand(LibreriaController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() { }

    public void executeFX(ObservableList<Libro> libriObservable) {
        List<Libro> sorted = controller.ordinaPerAutore();
        libriObservable.setAll(sorted);
    }
}
