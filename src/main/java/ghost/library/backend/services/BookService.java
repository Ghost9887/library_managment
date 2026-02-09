package ghost.library.backend.services;

import ghost.library.backend.entity.Book;
import ghost.library.backend.repo.BookRepository;

public final class BookService {

    private final BookRepository bookRepository = new BookRepository(); 

    public void addBook(Book book) {
        bookRepository.add(book);
    }

    public void editBook() {
        bookRepository.getAll();
    }

    public void deleteBook() {
    }

}
