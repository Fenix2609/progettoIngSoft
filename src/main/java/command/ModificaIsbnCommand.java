package command;

import controller.LibreriaController;
import model.Libro;
import java.util.Scanner;

public class ModificaIsbnCommand implements Command {
    private LibreriaController controller;
    private Scanner scanner;

    public ModificaIsbnCommand(LibreriaController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("ISBN del libro da modificare: ");
        String isbn = scanner.nextLine();
        Libro libro = controller.cercaPerIsbn(isbn);
        if (libro != null) {
            System.out.print("Nuovo ISBN: ");
            controller.modificaIsbnLibro(libro, scanner.nextLine());
            System.out.println("ISBN aggiornato.");
        } else {
            System.out.println("Libro non trovato.");
        }
    }
}
