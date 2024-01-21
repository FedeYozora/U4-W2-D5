package it.epicode.classes;

public abstract class Elemento {
    protected String codiceISBN;
    protected static String titolo;
    protected static int annoPubblicazione;
    protected static String numeroPagine;

    public Elemento(String codiceISBN, String titolo, int annoPubblicazione, String numeroPagine) {
        this.codiceISBN = codiceISBN;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public void setCodiceISBN(String codiceISBN) {
        this.codiceISBN = codiceISBN;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
       this.annoPubblicazione = annoPubblicazione;
    }

    public void setNumeroPagine(String numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public String getCodiceISBN() {
        return codiceISBN;
    }

    public static String getTitolo() {
        return titolo;
    }

    public static int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public static String getNumeroPagine() {
        return numeroPagine;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Elemento elemento = (Elemento) obj;
        return codiceISBN.equals(elemento.codiceISBN);
    }

    @Override
    public int hashCode() {
        return codiceISBN.hashCode();
    }
}