package commands;

import controller.LibreriaController;

public class SortByAuthorCommand implements Command {
    private LibreriaController controller;

    public SortByAuthorCommand(LibreriaController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.sortByAuthor().forEach(System.out::println);
    }
}
