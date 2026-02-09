package ghost.library.backend.controllers;

import ghost.library.backend.services.BookService;
import ghost.library.backend.entity.Book;
import java.time.LocalDate;

public class BookController {
    
    private final BookService bookService = new BookService();

    public BookController() {}

    public void createBook(String title, String author, String year) {
        Book newBook = new Book(title, author, year);
        bookService.addBook(newBook);
    }

    public void editBook() {
        bookService.editBook();
    }

    public void deleteBook() {
        System.out.println("deleting book");
    }

    public void getAllBooks() {
        bookService.getAll();
    }
}
