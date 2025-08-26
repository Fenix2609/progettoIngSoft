package app;

import controller.LibreriaController;
import persistence.CsvPersistence;
import persistence.JsonPersistence;
import persistence.PersistenceManager;
import persistence.PersistenceProxy;

import commands.*; // importa tutte le classi Command

import java.util.*;

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

        // Mappa dei comandi
        Map<Integer, Command> comandi = new HashMap<>();
        comandi.put(1, new AggiungiLibroCommand(controller));
        comandi.put(2, new VisualizzaLibreriaCommand(controller));
        comandi.put(3, new CercaLibroCommand(controller));
        comandi.put(4, new ModificaLibroCommand(controller));
        comandi.put(5, new FiltraGenereCommand(controller));
        comandi.put(6, new OrdinaPerAutoreCommand(controller));
        comandi.put(7, new OrdinaPerStatoCommand(controller));
        comandi.put(8, new SalvaCommand(controller, scanner));
        comandi.put(9, new CaricaCommand(controller));
        comandi.put(10, new RimuoviLibroCommand(controller));
        comandi.put(11, new ModificaIsbnCommand(controller));

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

            if (op == 0) {
                running = false;
            } else {
                Command cmd = comandi.get(op);
                if (cmd != null) {
                    cmd.execute();
                } else {
                    System.out.println("Opzione non valida.");
                }
            }
        }

        scanner.close();
    }
}