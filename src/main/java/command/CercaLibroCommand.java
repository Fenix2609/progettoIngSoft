package command;

import controller.LibreriaController;
import model.Libro;

import java.util.Scanner;

public class CercaLibroCommand implements Command {
    private LibreriaController controller;
    private Scanner scanner;

    public CercaLibroCommand(LibreriaController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("ISBN da cercare: ");
        String isbn = scanner.nextLine();
        Libro trovato = controller.cercaPerIsbn(isbn);
        System.out.println(trovato != null ? trovato : "Nessun libro trovato.");
    }
}
