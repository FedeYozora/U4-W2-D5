package it.epicode.utils;

import it.epicode.classes.Elemento;
import it.epicode.classes.Libro;
import it.epicode.classes.Rivista;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Catalogo {
    public static List<Elemento> archivio = new ArrayList<>();

    // Rimuovi un elemento dall'archivio in base al suo ISBN
    public static void rimuoviElemento(String isbn) {
        Iterator<Elemento> iterator = archivio.iterator();
        while (iterator.hasNext()) {
            Elemento elemento = iterator.next();
            if (elemento.getCodiceISBN().equals(isbn)) {
                iterator.remove();
                break;
            }
        }
    }

    // Ricerca di un elemento nell'archivio usando il suo ISBN
    public static Elemento ricercaPerISBN(String isbn) {
        Optional<Elemento> optionalElemento = archivio.stream()
                .filter(e -> e.getCodiceISBN().equals(isbn))
                .findFirst();

        if (optionalElemento.isPresent()) {
            Elemento elemento = optionalElemento.get();
            System.out.println("Titolo: " + elemento.getTitolo());
            System.out.println("Anno di pubblicazione: " + elemento.getAnnoPubblicazione());
            return elemento;
        } else {
            System.out.println("Elemento non trovato.");
            return null;
        }
    }

    // Ricerca nell'archivio di un elemento in base all'anno di pubblicazione
    public static List<Elemento> ricercaPerAnnoPubblicazione(int year) {
        List<Elemento> filteredElements = archivio.stream()
                .filter(e -> e.getAnnoPubblicazione() == year)
                .collect(Collectors.toList());

        for (Elemento elemento : filteredElements) {
            System.out.println("Titolo: " + elemento.getTitolo());
            System.out.println("Anno di pubblicazione: " + elemento.getAnnoPubblicazione());
        }

        return filteredElements;
    }

    // Ricerca nell'archivio in base all'autore
    public static List<Elemento> ricercaPerAutore(String author) {
        List<Elemento> filteredElements = archivio.stream()
                .filter(e -> e instanceof Libro && ((Libro) e).getAutore().equals(author))
                .collect(Collectors.toList());

        for (Elemento elemento : filteredElements) {
            System.out.println("Titolo: " + elemento.getTitolo());
            System.out.println("Anno di pubblicazione: " + elemento.getAnnoPubblicazione());
            System.out.println("Autore: " + ((Libro) elemento).getAutore());
        }

        return filteredElements;
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
                    .append(str).append("#");
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
            int annoPubblicazione = Integer.parseInt(productInfos[2]);
            int numPagine = Integer.parseInt(productInfos[3]);
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
            int annoPubblicazione = Integer.parseInt(productInfos[2]);
            int numPagine = Integer.parseInt(productInfos[3]);
            String periodicita = productInfos[4];
            archivio.add(new Rivista(codiceISBN, titolo, annoPubblicazione, numPagine, periodicita));
        }
    });

    return archivio;
}
}