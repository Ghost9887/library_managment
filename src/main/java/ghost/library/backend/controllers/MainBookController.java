package ghost.library.backend.controllers;

import ghost.library.backend.services.BookService;

public class MainBookController {
    
    private final BookService bookService = new BookService();

    public MainBookController() {}

    public void addBook() {
        System.out.println("adding book");
    }

    public void editBook() {
        System.out.println("editing book");
    }

    public void deleteBook() {
        System.out.println("deleting book");
    }
}
