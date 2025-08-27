package commands;

import controller.LibreriaController;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputDialog;
import model.Libro;

import java.util.Arrays;
import java.util.List;
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
        if (titolo.get().trim().length() == 0 || titolo.get().length() > 100) {
            showAlert("Titolo non valido! Deve contenere 1-100 caratteri.");
            return;
        }

        TextInputDialog autoreDialog = new TextInputDialog();
        autoreDialog.setHeaderText("Autore:");
        Optional<String> autore = autoreDialog.showAndWait();
        if (autore.get().trim().length() == 0 || autore.get().length() > 100) {
            showAlert("Autore non valido!");
            return;
        }

        TextInputDialog isbnDialog = new TextInputDialog();
        isbnDialog.setHeaderText("ISBN:");
        Optional<String> isbn = isbnDialog.showAndWait();
        if (isbn.get().trim().length() == 0 || isbn.get().length() > 13) {
            showAlert("ISBN non valido!");
            return;
        }

        TextInputDialog genereDialog = new TextInputDialog();
        genereDialog.setHeaderText("Genere:");
        Optional<String> genere = genereDialog.showAndWait();
        if (genere.get().trim().length() == 0 || !genere.get().matches("[a-zA-Z ]+")) {
            showAlert("Genere non valido!");
            return;
        }

        TextInputDialog valutDialog = new TextInputDialog();
        valutDialog.setHeaderText("Valutazione (1-5):");
        Optional<String> valutazioneStr = valutDialog.showAndWait();
        int valutazione;
        try {
            valutazione = Integer.parseInt(valutazioneStr.orElse("0"));
            if (valutazione < 1 || valutazione > 5) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            showAlert("Valutazione non valida!");
            return;
        }

        ComboBox<String> statoCombo = new ComboBox<>();
        statoCombo.getItems().addAll("in corso", "letto", "da leggere");
        statoCombo.setValue("da leggere");

        List<String> stati = Arrays.asList("in corso", "letto", "da leggere");
        ChoiceDialog<String> statoDialog = new ChoiceDialog<>(stati.get(0), stati);
        statoDialog.setHeaderText("Seleziona lo stato di lettura:");
        Optional<String> stato = statoDialog.showAndWait();

        if (!stato.isPresent()) {
            showAlert("Nessuno stato selezionato!");
            return;
        }

        if (titolo.isPresent() && autore.isPresent() && isbn.isPresent()
                && genere.isPresent() && valutazioneStr.isPresent() && stato.isPresent()) {
            valutazione = Integer.parseInt(valutazioneStr.get());
            Libro l = new Libro(titolo.get(), autore.get(), isbn.get(), genere.get(), valutazione, stato.get());
            controller.aggiungiLibro(l);
        }

    }
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(msg);
        alert.showAndWait();
    }
}
