package view;

import commands.*;
import controller.LibreriaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Libro;
import persistence.CsvPersistence;
import persistence.JsonPersistence;

public class LibreriaViewFX {

    private LibreriaController controller;
    private VBox root;
    private TableView<Libro> table;
    private ObservableList<Libro> libriObservable;

    public LibreriaViewFX(LibreriaController controller) {
        this.controller = controller;
        this.libriObservable = FXCollections.observableArrayList(controller.getLibri());
        controller.addObserver(() -> libriObservable.setAll(controller.getLibri()));

        createUI();
    }

    private void createUI() {
        root = new VBox(10);
        root.setPadding(new Insets(10));

        table = new TableView<>();
        table.setItems(libriObservable);

        TableColumn<Libro, String> titoloCol = new TableColumn<>("Titolo");
        titoloCol.setCellValueFactory(cell -> cell.getValue().titoloProperty());
        TableColumn<Libro, String> autoreCol = new TableColumn<>("Autore");
        autoreCol.setCellValueFactory(cell -> cell.getValue().autoreProperty());
        TableColumn<Libro, String> isbnCol = new TableColumn<>("ISBN");
        isbnCol.setCellValueFactory(cell -> cell.getValue().isbnProperty());
        TableColumn<Libro, String> genereCol = new TableColumn<>("Genere");
        genereCol.setCellValueFactory(cell -> cell.getValue().genereProperty());
        TableColumn<Libro, Number> valutCol = new TableColumn<>("Valutazione");
        valutCol.setCellValueFactory(cell -> cell.getValue().valutazioneProperty());
        TableColumn<Libro, String> statoCol = new TableColumn<>("Stato");
        statoCol.setCellValueFactory(cell -> cell.getValue().statoLetturaProperty());

        valutCol.setCellFactory(col -> new TableCell<Libro, Number>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : "★ " + item.intValue());
                setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            }
        });

        titoloCol.setCellFactory(col -> new TableCell<Libro, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item);
                setStyle("-fx-font-weight: bold;");
            }
        });

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getColumns().addAll(titoloCol, autoreCol, isbnCol, genereCol, valutCol, statoCol);

        // Buttons
        Button aggiungiBtn = new Button("Aggiungi Libro");
        Button modificaBtn = new Button("Modifica Libro");
        Button modificaIsbnBtn = new Button("Modifica ISBN");
        Button rimuoviBtn = new Button("Rimuovi Libro");
        Button filtraBtn = new Button("Filtra per Genere");
        //Button ordinaAutoreBtn = new Button("Ordina per Autore");
        //Button ordinaStatoBtn = new Button("Ordina per Stato");
        Button cercaBtn = new Button("Cerca per ISBN");
        Button salvaBtn = new Button("Salva");
        Button caricaBtn = new Button("Carica");
        Button switchFormatBtn = new Button("Cambia formato (CSV/JSON)");

        // Command instances
        AggiungiLibroCommand aggiungiCmd = new AggiungiLibroCommand(controller);
        ModificaLibroCommand modificaCmd = new ModificaLibroCommand(controller);
        ModificaIsbnCommand modificaIsbnCmd = new ModificaIsbnCommand(controller);
        RimuoviLibroCommand rimuoviCmd = new RimuoviLibroCommand(controller);
        FiltraGenereCommand filtraCmd = new FiltraGenereCommand(controller);
        //OrdinaPerAutoreCommand ordinaAutoreCmd = new OrdinaPerAutoreCommand(controller);
        //OrdinaPerStatoCommand ordinaStatoCmd = new OrdinaPerStatoCommand(controller);
        CercaLibroCommand cercaCmd = new CercaLibroCommand(controller);
        SalvaCommand salvaCmd = new SalvaCommand(controller, null);
        CaricaCommand caricaCmd = new CaricaCommand(controller);

        // Set actions
        aggiungiBtn.setOnAction(e -> aggiungiCmd.executeFX(libriObservable));
        modificaBtn.setOnAction(e -> modificaCmd.executeFX(table));
        modificaIsbnBtn.setOnAction(e -> modificaIsbnCmd.executeFX(table, libriObservable));
        rimuoviBtn.setOnAction(e -> rimuoviCmd.executeFX(table, libriObservable));
        filtraBtn.setOnAction(e -> filtraCmd.executeFX(libriObservable));
        //ordinaAutoreBtn.setOnAction(e -> ordinaAutoreCmd.executeFX(libriObservable));
        //ordinaStatoBtn.setOnAction(e -> ordinaStatoCmd.executeFX(libriObservable));
        cercaBtn.setOnAction(e -> cercaCmd.executeFX(table));
        salvaBtn.setOnAction(e -> salvaCmd.executeFX());
        caricaBtn.setOnAction(e -> caricaCmd.executeFX(libriObservable));
        switchFormatBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Vuoi davvero cambiare formato di salvataggio/caricamento?",
                    ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    if (controller.getPersistence() instanceof CsvPersistence) {
                        controller.setPersistence(new JsonPersistence());
                        switchFormatBtn.setText("Formato attuale: JSON (clicca per cambiare)");
                    } else {
                        controller.setPersistence(new CsvPersistence());
                        switchFormatBtn.setText("Formato attuale: CSV (clicca per cambiare)");
                    }
                }
            });
        });

        // Layout
        HBox buttons1 = new HBox(10, aggiungiBtn, modificaBtn, modificaIsbnBtn, rimuoviBtn);

        //HBox buttons2 = new HBox(10, filtraBtn, ordinaAutoreBtn, ordinaStatoBtn, cercaBtn);
        HBox buttons2 = new HBox(10, filtraBtn, cercaBtn);

        HBox buttons3 = new HBox(10, salvaBtn, caricaBtn);
        buttons1.setAlignment(Pos.CENTER);
        buttons1.setPadding(new Insets(10));
        buttons1.setStyle("-fx-font-size: 14px; -fx-padding: 10 20;");
        buttons2.setAlignment(Pos.CENTER);
        buttons2.setPadding(new Insets(10));
        buttons2.setStyle("-fx-font-size: 14px; -fx-padding: 10 20;");
        buttons3.setAlignment(Pos.CENTER);
        buttons3.setPadding(new Insets(10));
        buttons3.setStyle("-fx-font-size: 14px; -fx-padding: 10 20;");

        root.getChildren().addAll(table, buttons1, buttons2, buttons3);


    }

    public VBox getRoot() {
        return root;
    }
}
