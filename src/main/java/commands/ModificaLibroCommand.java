package commands;

import controller.LibreriaController;
import javafx.scene.control.*;
import model.Libro;

import java.util.Arrays;
import java.util.List;
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
        if (titolo.get().trim().length() == 0 || titolo.get().length() > 100) {
            showAlert("Titolo non valido! Deve contenere 1-100 caratteri.");
            return;
        }
        titolo.ifPresent(t -> controller.modificaTitoloLibro(selected, t));

        dialog = new TextInputDialog(selected.getAutore());
        dialog.setHeaderText("Nuovo autore:");
        Optional<String> autore = dialog.showAndWait();
        if (autore.get().trim().length() == 0 || autore.get().length() > 100) {
            showAlert("Autore non valido!");
            return;
        }
        autore.ifPresent(a -> controller.modificaAutoreLibro(selected, a));

        dialog = new TextInputDialog(selected.getIsbn());
        dialog.setHeaderText("Nuovo ISBN per il libro: " + selected.getTitolo());
        Optional<String> isbn = dialog.showAndWait();
        if (isbn.get().trim().length() == 0 || isbn.get().length() > 13) {
            showAlert("ISBN non valido!");
            return;
        }
        isbn.ifPresent(i -> controller.modificaIsbnLibro(selected, i));

        dialog = new TextInputDialog(selected.getGenere());
        dialog.setHeaderText("Nuovo genere:");
        Optional<String> genere = dialog.showAndWait();
        if (genere.get().trim().length() == 0 || !genere.get().matches("[a-zA-Z ]+")) {
            showAlert("Genere non valido!");
            return;
        }
        genere.ifPresent(g -> controller.modificaGenereLibro(selected, g));

        dialog = new TextInputDialog(String.valueOf(selected.getValutazione()));
        dialog.setHeaderText("Nuova valutazione:");
        Optional<String> valut = dialog.showAndWait();
        int valutazione;
        try {
            valutazione = Integer.parseInt(valut.orElse("0"));
            if (valutazione < 1 || valutazione > 5) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            showAlert("Valutazione non valida!");
            return;
        }
        valut.ifPresent(v -> controller.modificaValutazioneLibro(selected, Integer.parseInt(v)));

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
        stato.ifPresent(s -> controller.modificaStatoLetturaLibro(selected, s));
    }
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(msg);
        alert.showAndWait();
    }
}
