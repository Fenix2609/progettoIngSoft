package command;

import controller.LibreriaController;

public class VisualizzaLibreriaCommand implements Command {
    private LibreriaController controller;

    public VisualizzaLibreriaCommand(LibreriaController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.getLibri().forEach(System.out::println);
    }
}