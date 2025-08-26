package view;

import commands.*;
import controller.LibreriaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Libro;

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

        table.getColumns().addAll(titoloCol, autoreCol, isbnCol, genereCol, valutCol, statoCol);

        // Buttons
        Button aggiungiBtn = new Button("Aggiungi Libro");
        Button modificaBtn = new Button("Modifica Libro");
        Button modificaIsbnBtn = new Button("Modifica ISBN");
        Button rimuoviBtn = new Button("Rimuovi Libro");
        Button filtraBtn = new Button("Filtra per Genere");
        Button ordinaAutoreBtn = new Button("Ordina per Autore");
        Button ordinaStatoBtn = new Button("Ordina per Stato");
        Button cercaBtn = new Button("Cerca per ISBN");
        Button salvaBtn = new Button("Salva");
        Button caricaBtn = new Button("Carica");

        // Command instances
        AggiungiLibroCommand aggiungiCmd = new AggiungiLibroCommand(controller);
        ModificaLibroCommand modificaCmd = new ModificaLibroCommand(controller);
        ModificaIsbnCommand modificaIsbnCmd = new ModificaIsbnCommand(controller);
        RimuoviLibroCommand rimuoviCmd = new RimuoviLibroCommand(controller);
        FiltraGenereCommand filtraCmd = new FiltraGenereCommand(controller);
        OrdinaPerAutoreCommand ordinaAutoreCmd = new OrdinaPerAutoreCommand(controller);
        OrdinaPerStatoCommand ordinaStatoCmd = new OrdinaPerStatoCommand(controller);
        CercaLibroCommand cercaCmd = new CercaLibroCommand(controller);
        SalvaCommand salvaCmd = new SalvaCommand(controller, null);
        CaricaCommand caricaCmd = new CaricaCommand(controller);

        // Set actions
        aggiungiBtn.setOnAction(e -> aggiungiCmd.executeFX(libriObservable));
        modificaBtn.setOnAction(e -> modificaCmd.executeFX(table));
        modificaIsbnBtn.setOnAction(e -> modificaIsbnCmd.executeFX(table, libriObservable));
        rimuoviBtn.setOnAction(e -> rimuoviCmd.executeFX(table, libriObservable));
        filtraBtn.setOnAction(e -> filtraCmd.executeFX(libriObservable));
        ordinaAutoreBtn.setOnAction(e -> ordinaAutoreCmd.executeFX(libriObservable));
        ordinaStatoBtn.setOnAction(e -> ordinaStatoCmd.executeFX(libriObservable));
        cercaBtn.setOnAction(e -> cercaCmd.executeFX(table));
        salvaBtn.setOnAction(e -> salvaCmd.executeFX());
        caricaBtn.setOnAction(e -> caricaCmd.executeFX(libriObservable));

        // Layout
        HBox buttons1 = new HBox(10, aggiungiBtn, modificaBtn, modificaIsbnBtn, rimuoviBtn);
        HBox buttons2 = new HBox(10, filtraBtn, ordinaAutoreBtn, ordinaStatoBtn, cercaBtn);
        HBox buttons3 = new HBox(10, salvaBtn, caricaBtn);

        root.getChildren().addAll(table, buttons1, buttons2, buttons3);
    }

    public VBox getRoot() {
        return root;
    }
}
