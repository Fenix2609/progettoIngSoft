package app;

import controller.LibreriaController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistence.CsvPersistence;
import persistence.JsonPersistence;
import persistence.PersistenceManager;
import persistence.PersistenceProxy;
import view.LibreriaViewFX;

public class LibreriaAppFX extends Application {

    @Override
    public void start(Stage stage) {
        PersistenceManager persistence = new PersistenceProxy(new JsonPersistence());
        LibreriaController controller = LibreriaController.getInstance(persistence);

        LibreriaViewFX view = new LibreriaViewFX(controller);

        Scene scene = new Scene(view.getRoot(), 1000, 500);
        stage.setTitle("📚 Libreria");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
