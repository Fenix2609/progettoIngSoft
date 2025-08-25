package command;

import controller.LibreriaController;
import java.util.Scanner;

public class CaricaCommand implements Command {
    private LibreriaController controller;
    private Scanner scanner;

    public CaricaCommand(LibreriaController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Nome file: ");
        String f = scanner.nextLine();
        controller.load(f);
        System.out.println("Caricamento completato.");
    }
}
