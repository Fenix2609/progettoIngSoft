package commands;

import controller.LibreriaController;
import javafx.collections.ObservableList;
import javafx.scene.control.TextInputDialog;
import model.Libro;

import java.util.Optional;

public class CaricaCommand implements Command {
    private LibreriaController controller;

    public CaricaCommand(LibreriaController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() { }

    public void executeFX(ObservableList<Libro> libriObservable) {
        TextInputDialog dialog = new TextInputDialog("libreria.csv");
        dialog.setHeaderText("Nome file da caricare:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(nomeFile -> {
            controller.load(nomeFile);
            // aggiorna lista
            libriObservable.clear();
            libriObservable.addAll(controller.getLibri());
        });
    }
}
