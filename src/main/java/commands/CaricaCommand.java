package commands;

import controller.LibreriaController;
import javafx.collections.ObservableList;
import javafx.scene.control.TextInputDialog;
import model.Libro;
import persistence.CsvPersistence;

import java.util.Optional;

public class CaricaCommand implements Command {
    private LibreriaController controller;

    public CaricaCommand(LibreriaController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() { }

    public void executeFX(ObservableList<Libro> libriObservable) {
        TextInputDialog dialog;
        if(controller.getPersistence() instanceof CsvPersistence){
            dialog = new TextInputDialog("libreria.csv");

        }
        else {
            dialog = new TextInputDialog("libreria.json");

        }
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
