package command;

import controller.LibreriaController;
import java.util.Scanner;

public class FiltraGenereCommand implements Command {
    private LibreriaController controller;
    private Scanner scanner;

    public FiltraGenereCommand(LibreriaController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Genere: ");
        String g = scanner.nextLine();
        controller.filtraPerGenere(g).forEach(System.out::println);
    }
}
