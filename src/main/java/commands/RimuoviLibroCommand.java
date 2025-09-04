package commands;

import controller.LibreriaController;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import model.Libro;

import java.util.Optional;

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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Conferma rimozione");
            alert.setHeaderText("Vuoi davvero rimuovere questo libro?");
            alert.setContentText(selected.getTitolo() + " - " + selected.getAutore());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                controller.rimuoviLibro(selected.getIsbn());
                libriObservable.setAll(controller.getLibri()); // aggiorna la tabella
            }
        }
    }
}
