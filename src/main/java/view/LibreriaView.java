// view/LibreriaView.java
package view;

import controller.LibreriaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.Libro;
import observer.Observer;

public class LibreriaView implements Observer {
    private final LibreriaController controller;
    private final TableView<Libro> table;
    private final ObservableList<Libro> data;

    private final BorderPane root;

    public LibreriaView(LibreriaController controller) {
        this.controller = controller;
        this.controller.addObserver(this);

        data = FXCollections.observableArrayList(controller.getLibri());
        table = new TableView<>(data);

        TableColumn<Libro, String> titoloCol = new TableColumn<>("Titolo");
        titoloCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getTitolo()));

        TableColumn<Libro, String> autoreCol = new TableColumn<>("Autore");
        autoreCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getAutore()));

        TableColumn<Libro, String> isbnCol = new TableColumn<>("ISBN");
        isbnCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getIsbn()));

        table.getColumns().addAll(titoloCol, autoreCol, isbnCol);

        // Form per aggiungere libro
        TextField titoloField = new TextField();
        titoloField.setPromptText("Titolo");
        TextField autoreField = new TextField();
        autoreField.setPromptText("Autore");
        TextField isbnField = new TextField();
        isbnField.setPromptText("ISBN");

        Button addBtn = new Button("Aggiungi");
        addBtn.setOnAction(e -> {
            if (!titoloField.getText().isEmpty() && !autoreField.getText().isEmpty() && !isbnField.getText().isEmpty()) {
                controller.aggiungiLibro(new Libro(
                        titoloField.getText(),
                        autoreField.getText(),
                        isbnField.getText(),
                        "N/A", 0, "N/A"
                ));
                titoloField.clear();
                autoreField.clear();
                isbnField.clear();
            }
        });

        HBox form = new HBox(10, titoloField, autoreField, isbnField, addBtn);
        form.setPadding(new Insets(10));

        root = new BorderPane();
        root.setCenter(table);
        root.setBottom(form);
    }

    public BorderPane getRoot() {
        return root;
    }

    @Override
    public void update() {
        data.setAll(controller.getLibri());
    }
}
