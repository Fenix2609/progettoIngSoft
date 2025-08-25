package command;

import controller.LibreriaController;

import java.util.Scanner;

public class RimuoviLibroCommand implements Command {
    private LibreriaController controller;
    private Scanner scanner;

    public RimuoviLibroCommand(LibreriaController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("ISBN del libro da rimuovere: ");
        String isbn = scanner.nextLine();
        controller.rimuoviLibro(isbn);
    }
}