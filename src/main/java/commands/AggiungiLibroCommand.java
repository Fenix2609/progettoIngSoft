package commands;

import controller.LibreriaController;
import javafx.collections.ObservableList;
import javafx.scene.control.TextInputDialog;
import model.Libro;

import java.util.Optional;

public class AggiungiLibroCommand implements Command {
    private LibreriaController controller;

    public AggiungiLibroCommand(LibreriaController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() { /* console version */ }

    public void executeFX(ObservableList<Libro> libriObservable) {
        TextInputDialog titoloDialog = new TextInputDialog();
        titoloDialog.setHeaderText("Titolo:");
        Optional<String> titolo = titoloDialog.showAndWait();

        TextInputDialog autoreDialog = new TextInputDialog();
        autoreDialog.setHeaderText("Autore:");
        Optional<String> autore = autoreDialog.showAndWait();

        TextInputDialog isbnDialog = new TextInputDialog();
        isbnDialog.setHeaderText("ISBN:");
        Optional<String> isbn = isbnDialog.showAndWait();

        TextInputDialog genereDialog = new TextInputDialog();
        genereDialog.setHeaderText("Genere:");
        Optional<String> genere = genereDialog.showAndWait();

        TextInputDialog valutDialog = new TextInputDialog();
        valutDialog.setHeaderText("Valutazione (1-5):");
        Optional<String> valutazioneStr = valutDialog.showAndWait();

        TextInputDialog statoDialog = new TextInputDialog();
        statoDialog.setHeaderText("Stato lettura:");
        Optional<String> stato = statoDialog.showAndWait();

        if (titolo.isPresent() && autore.isPresent() && isbn.isPresent()
                && genere.isPresent() && valutazioneStr.isPresent() && stato.isPresent()) {
            int valutazione = Integer.parseInt(valutazioneStr.get());
            Libro l = new Libro(titolo.get(), autore.get(), isbn.get(), genere.get(), valutazione, stato.get());
            controller.aggiungiLibro(l);
        }
    }
}
