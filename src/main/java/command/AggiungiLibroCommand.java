package command;

import controller.LibreriaController;
import model.Libro;

import java.util.Scanner;

public class AggiungiLibroCommand implements Command {
    private LibreriaController controller;
    private Scanner scanner;

    public AggiungiLibroCommand(LibreriaController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Titolo: "); String t = scanner.nextLine();
        System.out.print("Autore: "); String a = scanner.nextLine();
        System.out.print("ISBN: "); String i = scanner.nextLine();
        System.out.print("Genere: "); String g = scanner.nextLine();
        System.out.print("Valutazione (1-5): "); int r = scanner.nextInt(); scanner.nextLine();
        System.out.print("Stato lettura: "); String s = scanner.nextLine();

        controller.aggiungiLibro(new Libro(t, a, i, g, r, s));
        System.out.println("Libro aggiunto!");
    }
}
