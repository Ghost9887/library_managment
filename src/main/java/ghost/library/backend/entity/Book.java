package ghost.library.backend.entity;

import java.time.LocalDate;

public class Book {

    private int id;
    private String title;
    private String author;
    private String year;
    private int available;

    public Book(String title, String author, String year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "ID: " + id + " : Title: " + title + " :  Author: " + author + " :  Year: " + year + " : Available: " + available;
    }
}
