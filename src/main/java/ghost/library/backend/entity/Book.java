package ghost.library.backend.entity;

import java.time.LocalDate;

public class Book {

    private int id;
    private String title;
    private String author;
    private String releaseDate;
    private int available;

    public Book(String title, String author, String releaseDate) {
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getAvailable() {
        return available;
    }

    public boolean isAvailable() {
        return available > 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "ID: " + id + " : Title: " + title + " :  Author: " + author + " :  releaseDate: " + releaseDate + " : Available: " + available;
    }
}
