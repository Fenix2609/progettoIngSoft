package app;

import controller.LibreriaController;
import model.Libro;
import persistence.CsvPersistence;
import persistence.JsonPersistence;
import persistence.PersistenceManager;
import persistence.PersistenceProxy;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PersistenceManager persistence = new PersistenceProxy(new CsvPersistence());
        LibreriaController lc=LibreriaController.getInstance(persistence);
        lc.aggiungiLibro(new Libro("dragon ball", "toriyama",  "0000",  "comb",  5,  "letto"));
        lc.aggiungiLibro(new Libro("Op", "boh",  "0001",  "comb",  5,  "da leggere"));
        System.out.println("prima "+lc.toString());
        lc.modificaAutoreLibro(lc.cercaPerIsbn("0001"),"Oda");
        System.out.println("dopo "+lc.toString());

        lc.save("libreria.csv");

        lc.load("libreria.csv");
        System.out.println("\n📚 Collezione libri caricata:");
        lc.getLibri().forEach(l ->
                System.out.println(l.getTitolo() + " - " + l.getAutore() +
                        " [" + l.getGenere() + "] (" + l.getStatoLettura() + ")")
        );
    }

}
