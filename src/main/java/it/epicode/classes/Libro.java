package it.epicode.classes;

public class Libro extends Elemento {
    private static String autore;
    private String genere;

    public Libro(String codiceISBN, String titolo,int numeroPagine, int annoPubblicazione, String autore, String genere) {
        super(codiceISBN, titolo, annoPubblicazione, String.valueOf(numeroPagine));
        this.autore = autore;
        this.genere = genere;
    }

    public static String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getGenere() {
        return genere;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                ", codiceISBN='" + codiceISBN + '\'' +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + numeroPagine +
                ", numeroPagine=" + annoPubblicazione +
                '}';
    }
}