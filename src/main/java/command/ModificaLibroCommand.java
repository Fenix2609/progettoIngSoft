package command;

import controller.LibreriaController;
import model.Libro;
import java.util.Scanner;

public class ModificaLibroCommand implements Command {
    private LibreriaController controller;
    private Scanner scanner;

    public ModificaLibroCommand(LibreriaController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("ISBN del libro da modificare: ");
        String isbn = scanner.nextLine();
        Libro libro = controller.cercaPerIsbn(isbn);
        if (libro != null) {
            System.out.println("1) Modifica titolo");
            System.out.println("2) Modifica autore");
            System.out.println("3) Modifica genere");
            System.out.println("4) Modifica valutazione");
            System.out.println("5) Modifica stato lettura");
            int mod = scanner.nextInt(); scanner.nextLine();

            switch (mod) {
                case 1 :
                    System.out.print("Nuovo titolo: ");
                    controller.modificaTitoloLibro(libro, scanner.nextLine());
                break;
                case 2 :
                    System.out.print("Nuovo autore: ");
                    controller.modificaAutoreLibro(libro, scanner.nextLine());
                break;
                case 3 :
                    System.out.print("Nuovo genere: ");
                    controller.modificaGenereLibro(libro, scanner.nextLine());break;
                case 4 :
                    System.out.print("Nuova valutazione: ");
                    controller.modificaValutazioneLibro(libro, scanner.nextInt());
                    scanner.nextLine();
                    break;
                case 5 :
                    System.out.print("Nuovo stato lettura: ");
                    controller.modificaStatoLetturaLibro(libro, scanner.nextLine());
                    break;
            }
            System.out.println("Modifica completata.");
        } else {
            System.out.println("Libro non trovato.");
        }
    }
}
