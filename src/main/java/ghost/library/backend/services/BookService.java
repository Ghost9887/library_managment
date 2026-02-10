package ghost.library.backend.services;

import ghost.library.backend.entity.Book;
import ghost.library.backend.repo.BookRepository;

public final class BookService {

    private final BookRepository bookRepository = new BookRepository(); 

    public void add(String title, String author, String year) {
        Book newBook = new Book(title, author, year);
        bookRepository.add(newBook);
    }

    public void editBook() {
    }

    public void deleteBook() {
    }

    public void getAll() {
        bookRepository.getAll();
    }

}
