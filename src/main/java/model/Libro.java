package model;

public class Libro {
    private String titolo;
    private String autore;
    private String isbn;
    private String genere;
    private int valutazione;
    private String statoLettura;

    public Libro(String title, String author, String isbn, String genre, int rating, String readingStatus) {
        this.titolo = titolo;
        this.autore = autore;
        this.isbn = isbn;
        this.genere = genere;
        this.valutazione = valutazione;
        this.statoLettura = statoLettura;
    }

    public String getTitolo() { return titolo; }
    public String getAutore() { return autore; }
    public String getIsbn() { return isbn; }
    public String getGenere() { return genere; }
    public int getValutazione() { return valutazione; }
    public String getStatoLettura() { return statoLettura; }

    @Override
    public String toString() {
        return titolo + " - " + autore + " (" + isbn + "), Genere: " + genere +
                ", Valutazione: " + valutazione + ", Stato: " + statoLettura;
    }
}
