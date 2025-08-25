package app;

import controller.LibreriaController;
import model.Libro;
import persistence.CsvPersistence;
import persistence.JsonPersistence;
import persistence.PersistenceManager;
import persistence.PersistenceProxy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Scegli formato di persistenza: 1) CSV  2) JSON");
        int choice = scanner.nextInt();
        scanner.nextLine();

        PersistenceManager persistence =
                (choice == 1) ? new CsvPersistence() : new JsonPersistence();
        PersistenceManager proxy = new PersistenceProxy(persistence);
        LibreriaController controller = LibreriaController.getInstance(proxy);

        boolean running = true;
        while (running) {
            System.out.println("\n=== Menu Libreria ===");
            System.out.println("1) Aggiungi libro");
            System.out.println("2) Visualizza libreria");
            System.out.println("3) Cerca per ISBN");
            System.out.println("4) Modifica libro");
            System.out.println("5) Filtra per genere");
            System.out.println("6) Ordina per autore");
            System.out.println("7) Ordina per stato di lettura");
            System.out.println("8) Salva");
            System.out.println("9) Carica");
            System.out.println("10) Rimuovi libro");
            System.out.println("11) Modifica ISBN");
            System.out.println("0) Esci");
            System.out.print("Scelta: ");

            int op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Titolo: "); String t = scanner.nextLine();
                    System.out.print("Autore: "); String a = scanner.nextLine();
                    System.out.print("ISBN: "); String i = scanner.nextLine();
                    System.out.print("Genere: "); String g = scanner.nextLine();
                    System.out.print("Valutazione (1-5): "); int r = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Stato lettura: "); String s = scanner.nextLine();
                    controller.aggiungiLibro(new Libro(t, a, i, g, r, s));
                    break;

                case 2:
                    controller.getLibri().forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("ISBN da cercare: "); String isbn = scanner.nextLine();
                    Libro trovato = controller.cercaPerIsbn(isbn);
                    System.out.println(trovato != null ? trovato : "Nessun libro trovato.");
                    break;

                case 4:
                    System.out.print("ISBN del libro da modificare: "); isbn = scanner.nextLine();
                    Libro libro = controller.cercaPerIsbn(isbn);
                    if (libro != null) {
                        System.out.println("1) Modifica titolo");
                        System.out.println("2) Modifica autore");
                        System.out.println("3) Modifica genere");
                        System.out.println("4) Modifica valutazione");
                        System.out.println("5) Modifica stato lettura");
                        int mod = scanner.nextInt();
                        scanner.nextLine();

                        switch (mod) {
                            case 1:
                                System.out.print("Nuovo titolo: ");
                                controller.modificaTitoloLibro(libro, scanner.nextLine());
                                break;
                            case 2:
                                System.out.print("Nuovo autore: ");
                                controller.modificaAutoreLibro(libro, scanner.nextLine());
                                break;
                            case 3:
                                System.out.print("Nuovo genere: ");
                                controller.modificaGenereLibro(libro, scanner.nextLine());
                                break;
                            case 4:
                                System.out.print("Nuova valutazione: ");
                                controller.modificaValutazioneLibro(libro, scanner.nextInt());
                                scanner.nextLine();
                                break;
                            case 5:
                                System.out.print("Nuovo stato lettura: ");
                                controller.modificaStatoLetturaLibro(libro, scanner.nextLine());
                                break;
                        }
                    } else {
                        System.out.println("Libro non trovato.");
                    }
                    break;

                case 5:
                    System.out.print("Genere: "); g = scanner.nextLine();
                    controller.filtraPerGenere(g).forEach(System.out::println);
                    break;

                case 6:
                    System.out.print("Autore: ");
                    controller.ordinaPerAutore().forEach(System.out::println);
                    break;
                case 7:
                    System.out.print("Stato di lettura: ");
                    controller.ordinaPerStato().forEach(System.out::println);
                    break;
                case 8:
                    System.out.print("Nome file: "); String f = scanner.nextLine();
                    controller.save(f);
                    break;

                case 9:
                    System.out.print("Nome file: "); f = scanner.nextLine();
                    controller.load(f);
                    break;

                case 10: // ✅ Rimuovere libro
                    System.out.print("ISBN del libro da rimuovere: ");
                    isbn = scanner.nextLine();
                    controller.rimuoviLibro(isbn);
                    System.out.println("Libro rimosso (se esiste).");
                    break;

                case 11: // ✅ Modificare ISBN
                    System.out.print("ISBN del libro da modificare: ");
                    isbn = scanner.nextLine();
                    libro = controller.cercaPerIsbn(isbn);
                    if (libro != null) {
                        System.out.print("Nuovo ISBN: ");
                        controller.modificaIsbnLibro(libro, scanner.nextLine());
                        System.out.println("ISBN aggiornato.");
                    } else {
                        System.out.println("Libro non trovato.");
                    }
                    break;

                case 0:
                    running = false;
                    break;
            }
        }
        scanner.close();
    }
}
