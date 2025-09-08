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
        //setting delle colonne della tabella
        TableColumn<Libro, String> titoloCol = new TableColumn<>("Titolo");
        titoloCol.setCellValueFactory(cell -> cell.getValue().titoloProperty());
        titoloCol.setStyle("-fx-font-size: 16px");
        TableColumn<Libro, String> autoreCol = new TableColumn<>("Autore");
        autoreCol.setStyle("-fx-font-size: 16px");
        autoreCol.setCellValueFactory(cell -> cell.getValue().autoreProperty());
        TableColumn<Libro, String> isbnCol = new TableColumn<>("ISBN");
        isbnCol.setStyle("-fx-font-size: 16px");
        isbnCol.setCellValueFactory(cell -> cell.getValue().isbnProperty());
        TableColumn<Libro, String> genereCol = new TableColumn<>("Genere");
        genereCol.setCellValueFactory(cell -> cell.getValue().genereProperty());
        genereCol.setStyle("-fx-font-size: 16px");
        TableColumn<Libro, Number> valutCol = new TableColumn<>("Valutazione");
        valutCol.setCellValueFactory(cell -> cell.getValue().valutazioneProperty());
        valutCol.setStyle("-fx-font-size: 16px");
        TableColumn<Libro, String> statoCol = new TableColumn<>("Stato");
        statoCol.setCellValueFactory(cell -> cell.getValue().statoLetturaProperty());
        statoCol.setStyle("-fx-font-size: 16px");

        valutCol.setCellFactory(col -> new TableCell<Libro, Number>() {
            @Override
            protected void updateItem(Number item, boolean empty) { //cellfactory viene usato per aggiungere stile di scrittura su tutta la colonna
                super.updateItem(item, empty);
                setText(empty || item == null ? null : "★ " + item.intValue()); //ogni cella valutazione ha una stella oltre che al numero
                setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            }
        });

        titoloCol.setCellFactory(col -> new TableCell<Libro, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item);
                setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            }
        });

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getColumns().addAll(titoloCol, autoreCol, isbnCol, genereCol, valutCol, statoCol);

        // Buttons
        Button aggiungiBtn = new Button("Aggiungi Libro");
        Button modificaBtn = new Button("Modifica Libro");
        Button rimuoviBtn = new Button("Rimuovi Libro");
        Button filtraBtn = new Button("Filtra per Genere");
        Button cercaBtn = new Button("Cerca Libro");
        Button salvaBtn = new Button("Salva");
        Button caricaBtn = new Button("Carica");
        Button switchFormatBtn = new Button("Formato attuale: JSON (clicca per cambiare)");

        // Istanze Command
        AggiungiLibroCommand aggiungiCmd = new AggiungiLibroCommand(controller);
        ModificaLibroCommand modificaCmd = new ModificaLibroCommand(controller);
        RimuoviLibroCommand rimuoviCmd = new RimuoviLibroCommand(controller);
        FiltraGenereCommand filtraCmd = new FiltraGenereCommand(controller);
        CercaLibroCommand cercaCmd = new CercaLibroCommand(controller);
        SalvaCommand salvaCmd = new SalvaCommand(controller, null);
        CaricaCommand caricaCmd = new CaricaCommand(controller);

        // Set actions
        aggiungiBtn.setOnAction(e -> aggiungiCmd.executeFX(libriObservable));
        modificaBtn.setOnAction(e -> modificaCmd.executeFX(table));
        rimuoviBtn.setOnAction(e -> rimuoviCmd.executeFX(table, libriObservable));
        filtraBtn.setOnAction(e -> filtraCmd.executeFX(libriObservable));
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

        // Uso questo layout per mettere tutti i bottoni su una riga
        HBox leftButtons = new HBox(10, salvaBtn, caricaBtn);
        leftButtons.setAlignment(Pos.CENTER_LEFT);
        leftButtons.setStyle("-fx-font-size: 16px;");

        HBox rightButtons = new HBox(10, aggiungiBtn, modificaBtn, rimuoviBtn, filtraBtn, cercaBtn);
        rightButtons.setAlignment(Pos.CENTER_RIGHT);
        rightButtons.setStyle("-fx-font-size: 16px;");

        BorderPane bottomBar = new BorderPane(); //contenitore unico per metterci su una stessa riga tutti i bottoni
        bottomBar.setLeft(leftButtons);
        bottomBar.setRight(rightButtons);
        bottomBar.setPadding(new Insets(10));

        // Metto il bottone switch formato in alto
        HBox topBar = new HBox(10, switchFormatBtn);
        topBar.setPadding(new Insets(10));
        topBar.setStyle("-fx-font-size: 16px;");
        topBar.setAlignment(Pos.TOP_LEFT);

        root.getChildren().addAll(topBar, table, bottomBar);


    }

    public VBox getRoot() {
        return root;
    }
}
