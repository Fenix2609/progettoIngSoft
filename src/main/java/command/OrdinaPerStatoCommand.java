package command;

import controller.LibreriaController;

public class OrdinaPerStatoCommand implements Command {
    LibreriaController controller;
    public OrdinaPerStatoCommand(LibreriaController controller) {
        this.controller = controller;
    }
    public void execute() {
        controller.ordinaPerStato().forEach(System.out::println);
    }
}
