package it.epicode.classes;

public abstract class Elemento {
    protected String codiceISBN;
    protected String titolo;
    protected int annoPubblicazione;
    protected String numeroPagine;

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

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public String getNumeroPagine() {
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