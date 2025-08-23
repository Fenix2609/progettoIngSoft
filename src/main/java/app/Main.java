package app;

import controller.LibreriaController;
import model.Libro;
import persistence.JsonPersistence;
import persistence.PersistenceManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PersistenceManager persistenceManager=new JsonPersistence();
        LibreriaController lc=LibreriaController.getInstance(persistenceManager);
        lc.aggiungiLibro(new Libro("dragon ball", "toriyama",  "0000",  "comb",  5,  "letto"));
        lc.aggiungiLibro(new Libro("Op", "boh",  "0001",  "comb",  5,  "da leggere"));
        System.out.println("prima "+lc.toString());
        lc.modificaAutoreLibro(lc.cercaPerIsbn("0001"),"Oda");
        System.out.println("dopo "+lc.toString());

    }

}
