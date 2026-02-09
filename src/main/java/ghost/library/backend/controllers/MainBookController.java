package ghost.library.backend.controllers;

import ghost.library.backend.services.BookService;
import ghost.library.backend.entity.Book;
import java.time.LocalDate;

public class MainBookController {
    
    private final BookService bookService = new BookService();

    public MainBookController() {}

    public void createBook() {
        Book newBook = new Book("test", "tester", "2024");
        bookService.addBook(newBook);
    }

    public void editBook() {
        bookService.editBook();
    }

    public void deleteBook() {
        System.out.println("deleting book");
    }
}
