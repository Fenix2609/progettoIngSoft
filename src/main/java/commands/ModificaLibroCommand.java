package commands;

import controller.LibreriaController;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import model.Libro;

import java.util.Optional;

public class ModificaLibroCommand implements Command {
    private LibreriaController controller;

    public ModificaLibroCommand(LibreriaController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() { }

    public void executeFX(TableView<Libro> table) {
        Libro selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        TextInputDialog dialog = new TextInputDialog(selected.getTitolo());
        dialog.setHeaderText("Nuovo titolo:");
        Optional<String> titolo = dialog.showAndWait();
        titolo.ifPresent(t -> controller.modificaTitoloLibro(selected, t));

        dialog = new TextInputDialog(selected.getAutore());
        dialog.setHeaderText("Nuovo autore:");
        Optional<String> autore = dialog.showAndWait();
        autore.ifPresent(a -> controller.modificaAutoreLibro(selected, a));

        dialog = new TextInputDialog(selected.getGenere());
        dialog.setHeaderText("Nuovo genere:");
        Optional<String> genere = dialog.showAndWait();
        genere.ifPresent(g -> controller.modificaGenereLibro(selected, g));

        dialog = new TextInputDialog(String.valueOf(selected.getValutazione()));
        dialog.setHeaderText("Nuova valutazione:");
        Optional<String> valut = dialog.showAndWait();
        valut.ifPresent(v -> controller.modificaValutazioneLibro(selected, Integer.parseInt(v)));

        dialog = new TextInputDialog(selected.getStatoLettura());
        dialog.setHeaderText("Nuovo stato lettura:");
        Optional<String> stato = dialog.showAndWait();
        stato.ifPresent(s -> controller.modificaStatoLetturaLibro(selected, s));
    }
}
