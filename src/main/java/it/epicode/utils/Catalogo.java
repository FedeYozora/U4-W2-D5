package it.epicode.utils;// package it.epicode.utils;

import it.epicode.classes.Elemento;
import it.epicode.classes.Libro;
import it.epicode.classes.Rivista;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Catalogo {

    private static List<Elemento> archivio = new ArrayList<>();

    // Remove an element from the archive by its ISBN
    public static void rimuoviElemento(String isbn) {
        archivio.removeIf(e -> e.getCodiceISBN().equals(isbn));
    }

    // Search for an element by its ISBN
    public static Elemento ricercaPerISBN(String isbn) {
        return archivio.stream()
                .filter(e -> e.getCodiceISBN().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    // Search for elements by their year of publication
    public static List<Elemento> ricercaPerAnnoPubblicazione(int year) {
        return archivio.stream()
                .filter(e -> e.getAnnoPubblicazione() == year)
                .collect(Collectors.toList());
    }

    // Search for elements by their author
    public static List<Elemento> ricercaPerAutore(String author) {
        return archivio.stream()
                .filter(e -> e instanceof Libro && ((Libro) e).getAutore().equals(author))
                .collect(Collectors.toList());
    }
        public static void saveToDisk(List<Elemento> elements) throws IOException {
        StringBuilder toWrite = new StringBuilder();

        for (Elemento element : elements) {
            StringBuilder str = new StringBuilder();

            if (element instanceof Libro) {
                str.append(((Libro) element).getAutore()).append("@")
                        .append(((Libro) element).getGenere());
            }
            if (element instanceof Rivista) {
                str.append(((Rivista) element).getPeriodicita());

            }

            toWrite.append(element.getCodiceISBN()).append("@")
                    .append(element.getTitolo()).append("@")
                    .append(element.getAnnoPubblicazione()).append("@")
                    .append(element.getNumeroPagine()).append("@")
                    .append(str)
                    .append("#");
        }

        File file = new File("prova.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(toWrite.toString());
        }
    }

    public static List<Elemento> findToDisk(List<Elemento> archivio) throws IOException {
        archivio.clear();
    File file = new File("prova.txt");
    String fileString = FileUtils.readFileToString(file, "UTF-8");
    List<String> splitElementString = Arrays.asList(fileString.split("#"));

    splitElementString.stream().forEach(stringa -> {
        String[] productInfos = stringa.split("@");
        if (productInfos.length == 6) {
            String codiceISBN = productInfos[0];
            String titolo = productInfos[1];
            int annoPubblicazione = Integer.parseInt(productInfos[3]);
            int numPagine = Integer.parseInt(productInfos[2]);
            String autore = "";
            String genere = "";
            if (productInfos[4].contains("@")) {
                String[] authorGenre = productInfos[4].split("@");
                autore = authorGenre[0];
                genere = authorGenre[1];
            } else {
                autore = productInfos[4];
            }
            archivio.add(new Libro(codiceISBN, titolo, annoPubblicazione,numPagine, autore, genere));
        } else if (productInfos.length == 5) {
            String codiceISBN = productInfos[0];
            String titolo = productInfos[1];
            int annoPubblicazione = Integer.parseInt(productInfos[3]);
            int numPagine = Integer.parseInt(productInfos[2]);
            String periodicita = productInfos[4];
            archivio.add(new Rivista(codiceISBN, titolo, annoPubblicazione, numPagine, periodicita));
        }
    });

    return archivio;
}
}