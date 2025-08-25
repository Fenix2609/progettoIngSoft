package command;

import controller.LibreriaController;
import java.util.Scanner;

public class SalvaCommand implements Command {
    private LibreriaController controller;
    private Scanner scanner;

    public SalvaCommand(LibreriaController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Nome file: ");
        String f = scanner.nextLine();
        controller.save(f);
        System.out.println("Salvataggio completato.");
    }
}
