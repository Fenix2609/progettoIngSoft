package view;

import controller.LibreriaController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistence.CsvPersistence;
import persistence.PersistenceManager;

public class LibreriaApp extends Application {
    @Override
    public void start(Stage stage) {
        // Decidiamo quale persistenza usare (per ora CSV di default)
        PersistenceManager persistence = new CsvPersistence();
        LibreriaController controller = LibreriaController.getInstance(persistence);

        LibreriaView view = new LibreriaView(controller);

        Scene scene = new Scene(view.getRoot(), 600, 400);
        stage.setTitle("📚 Libreria");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}