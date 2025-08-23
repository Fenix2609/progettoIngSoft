package model;

public class Libro {
    private String titolo;
    private String autore;
    private String isbn;
    private String genere;
    private int valutazione;
    private String statoLettura;

    public Libro(String titolo, String autore, String isbn, String genere, int valutazione, String statoLettura) {
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
    public void setTitolo(String titolo) { this.titolo = titolo; }
    public void setAutore(String autore) { this.autore = autore; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setGenere(String genere) { this.genere = genere; }
    public void setValutazione(int valutazione) { this.valutazione = valutazione; }

    public void setStatoLettura(String statoLettura) {
        this.statoLettura = statoLettura;
    }

    @Override
    public String toString() {
        return titolo + " - " + autore + " (" + isbn + "), Genere: " + genere +
                ", Valutazione: " + valutazione + ", Stato: " + statoLettura;
    }
}
