package commands;

import controller.LibreriaController;
import javafx.scene.control.TextInputDialog;
import persistence.CsvPersistence;

import java.util.Optional;
import java.util.Scanner;

public class SalvaCommand implements Command {
    private LibreriaController controller;
    private Scanner scanner;

    public SalvaCommand(LibreriaController controller, Scanner scanner) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        System.out.print("Nome file: ");
        String f = scanner.nextLine();
        controller.save(f);
        System.out.println("Salvato con successo!");
    }

    public void executeFX() {
        TextInputDialog dialog;
        if(controller.getPersistence() instanceof CsvPersistence){
            dialog = new TextInputDialog("libreria.csv");
            dialog.setTitle("Salva Libreria");
            dialog.setHeaderText("Inserisci il nome del file CSV da salvare");
        }
        else{
            dialog = new TextInputDialog("libreria.json");
            dialog.setTitle("Salva Libreria");
            dialog.setHeaderText("Inserisci il nome del file JSON da salvare");
        }
        dialog.setContentText("Nome file:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(controller::save); // salva con il nome inserito
    }
}
