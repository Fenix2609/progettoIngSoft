package commands;

import controller.LibreriaController;

public class OrdinaPerAutoreCommand implements Command {
    private LibreriaController controller;

    public OrdinaPerAutoreCommand(LibreriaController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.ordinaPerAutore().forEach(System.out::println);
    }
}
