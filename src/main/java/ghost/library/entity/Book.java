package ghost.library.entity;

import java.time.LocalDate;

public class Book {

    private String name;
    private String author;
    private LocalDate releaseDate;

    public Book(String name, String author, LocalDate releaseDate) {
        this.name = name;
        this.author = author;
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return "Name: " + name + " :  Author: " + author + " :  Release Date: " + releaseDate;
    }
}
