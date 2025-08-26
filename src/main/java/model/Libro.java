package model;
import javafx.beans.property.*;

public class Libro {
    private StringProperty titolo;
    private StringProperty autore;
    private StringProperty isbn;
    private StringProperty genere;
    private IntegerProperty valutazione;
    private StringProperty statoLettura;

    public Libro(String titolo, String autore, String isbn, String genere, int valutazione, String statoLettura) {
        this.titolo = new SimpleStringProperty(titolo);
        this.autore = new SimpleStringProperty(autore);
        this.isbn = new SimpleStringProperty(isbn);
        this.genere = new SimpleStringProperty(genere);
        this.valutazione = new SimpleIntegerProperty(valutazione);
        this.statoLettura = new SimpleStringProperty(statoLettura);
    }

    // Titolo
    public String getTitolo() { return titolo.get(); }
    public void setTitolo(String titolo) { this.titolo.set(titolo); }
    public StringProperty titoloProperty() { return titolo; }

    // Autore
    public String getAutore() { return autore.get(); }
    public void setAutore(String autore) { this.autore.set(autore); }
    public StringProperty autoreProperty() { return autore; }

    // ISBN
    public String getIsbn() { return isbn.get(); }
    public void setIsbn(String isbn) { this.isbn.set(isbn); }
    public StringProperty isbnProperty() { return isbn; }

    // Genere
    public String getGenere() { return genere.get(); }
    public void setGenere(String genere) { this.genere.set(genere); }
    public StringProperty genereProperty() { return genere; }

    // Valutazione
    public int getValutazione() { return valutazione.get(); }
    public void setValutazione(int valutazione) { this.valutazione.set(valutazione); }
    public IntegerProperty valutazioneProperty() { return valutazione; }

    // Stato lettura
    public String getStatoLettura() { return statoLettura.get(); }
    public void setStatoLettura(String stato) { this.statoLettura.set(stato); }
    public StringProperty statoLetturaProperty() { return statoLettura; }


@Override
    public String toString() {
        return titolo + " - " + autore + " (" + isbn + "), Genere: " + genere +
                ", Valutazione: " + valutazione + ", Stato: " + statoLettura;
    }
}
