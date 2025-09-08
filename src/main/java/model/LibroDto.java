package model;

public class LibroDto { //Serve Dto per dare dati funzionanti quando si usa la strategia Json
    private String titolo;
    private String autore;
    private String isbn;
    private String genere;
    private int valutazione;
    private String stato;

    public LibroDto(String titolo, String autore, String isbn,
                        String genere, int valutazione, String stato) {
        this.titolo = titolo;
        this.autore = autore;
        this.isbn = isbn;
        this.genere = genere;
        this.valutazione = valutazione;
        this.stato = stato;
    }
    public String getTitolo() { return titolo; }
    public String getAutore() { return autore; }
    public String getIsbn() { return isbn; }
    public String getGenere() { return genere; }
    public int getValutazione() { return valutazione; }
    public String getStato() { return stato; }
}