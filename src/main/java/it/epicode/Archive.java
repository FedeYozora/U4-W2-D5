package it.epicode;

import com.github.javafaker.Faker;
import it.epicode.classes.Libro;
import it.epicode.classes.Rivista;
import it.epicode.utils.Catalogo;

import java.io.IOException;
import java.util.Random;

import static it.epicode.utils.Catalogo.archivio;

public class Archive {

    public static void main(String[] args) {
        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            if (random.nextBoolean()) {
                // Crea un libro random
                String author = faker.book().author();
                Libro libro = new Libro(
                        faker.code().isbn10(),
                        faker.book().title(),
                        faker.number().numberBetween(1900, 2022),
                        faker.number().numberBetween(200, 300),
                        author,
                        faker.book().genre()
                );
                archivio.add(libro);
            } else {
                // Crea una rivista random
                String periodicity = faker.options().option("SETTIMANALE", "MENSILE", "SEMESTRALE");
                Rivista rivista = new Rivista(
                        faker.code().isbn13(),
                        faker.book().title(),
                        faker.number().numberBetween(1900, 2022),
                        faker.number().numberBetween(100, 200),
                        periodicity
                );
                archivio.add(rivista);
            }
        }
Libro libroTest = new Libro("123456789", "Epicode Java Course",2024,320,"Federico","Thriller");
        archivio.add(libroTest);

        System.out.println("Before elimination");
        archivio.forEach(System.out::println);
        System.out.println();

       Catalogo.rimuoviElemento(archivio.get(0).getCodiceISBN());


        System.out.println("After elimination");
        archivio.forEach(System.out::println);
        System.out.println();

        System.out.println("Result of ISBN search");
        Catalogo.ricercaPerISBN(archivio.get(0).getCodiceISBN());
        System.out.println();

        System.out.println("Result of year of Publication");
        Catalogo.ricercaPerAnnoPubblicazione(archivio.get(2).getAnnoPubblicazione());
        System.out.println();

        System.out.println("Result of Author");
        Catalogo.ricercaPerAutore("Federico");
        System.out.println();

        try {
            System.out.println("Saved to Disk");
            Catalogo.saveToDisk(archivio);
            System.out.println();
            System.out.println("Read from Disk");
            Catalogo.findToDisk(archivio);
            archivio.forEach(System.out::println);
        } catch (IOException ignored) {
        }
    }
}