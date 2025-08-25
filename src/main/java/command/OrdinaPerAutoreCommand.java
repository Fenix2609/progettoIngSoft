package command;

import controller.LibreriaController;

public class OrdinaPerAutoreCommand implements Command {
    LibreriaController controller;
    public OrdinaPerAutoreCommand(LibreriaController controller) {
        this.controller = controller;
    }
    public void execute() {
        controller.ordinaPerAutore().forEach(System.out::println);
    }
}
