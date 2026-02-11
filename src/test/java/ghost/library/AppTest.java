package ghost.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import ghost.library.backend.Database;
import ghost.library.backend.services.BookService;
import ghost.library.backend.entity.Book;
import ghost.library.backend.services.UserService;
import ghost.library.backend.entity.User;
import java.sql.Connection;
import java.io.File;

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//DO NOT RUN TESTS IN PRODUCTION DB GETS DELETED
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class AppTest {

    @Test
    public void createDatabase() {
        int code = 1;
        assertEquals(code, Database.init());
    }

    @Test
    public void testDBConnection() {
        Connection con = Database.getConnection();
        assertNotNull(con);
    }

    @Test
    public void testCRUDBook() {
        //add
        BookService service = new BookService();
        service.addBook("Title", "Author", "10.02.2026");
        Book book = service.getBookById("0"); 
        assertNotNull(book);

        //update
        service.addBook("Title2", "Author2", "11.02.2026");
        Book book2 = service.getBookById("1");
        book2.setTitle("New Title");
        service.updateBook(book2);
        Book book2Updated = service.getBookById("1");
        assertNotEquals(book2, book2Updated);

        //delete
        service.deleteBookById(String.valueOf(book.getId()));
        service.deleteBookById(String.valueOf(book2Updated.getId()));
    }

    @Test
    public void testCRUDUser() {
        //add
        UserService service = new UserService();
        service.addUser("John", "Doe");
        User user = service.getUserById("0");
        assertNotNull(user);

        //update
        service.addUser("Johnah", "Doeah");
        User user2 = service.getUserById("1");
        user2.setFirstName("Carl");
        service.updateUser(user2);
        User user2Updated = service.getUserById("1");
        assertNotEquals(user2, user2Updated);

        //delete
        service.deleteUserById(String.valueOf(user.getId()));
        service.deleteUserById(String.valueOf(user2Updated.getId()));
    }

    @Test
    public void deleteDB() {
        //DELETE THE DB FILE
        File dbFile = new File("data/library.db");
        dbFile.delete();
    }

}
