package ghost.library.backend.repo;

import ghost.library.backend.Database;
import ghost.library.backend.entity.Book;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//HANDLES QUERYS TO THE DATABASE
public final class BookRepository {
    
    public BookRepository() {}
    
    public void add(Book book) {
        final String query = "INSERT INTO books (title, author, release_date, available)"
        + "VALUES (?, ?, ?, ?);";

        try (Connection con = Database.getConnection()){
            PreparedStatement stmnt = con.prepareStatement(query);

            stmnt.setString(1, book.getTitle());
            stmnt.setString(2, book.getAuthor());
            stmnt.setString(3, book.getReleaseDate());
            stmnt.setInt(4, 1);

            stmnt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Book book) {
        final String query = "UPDATE books "
        + "SET title = ?, "
        + "author = ?, "
        + "release_date = ?, "
        + "available = ? "
        + "WHERE id = ?;";

        try (Connection con = Database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement(query);

            stmnt.setString(1, book.getTitle());
            stmnt.setString(2, book.getAuthor());
            stmnt.setString(3, book.getReleaseDate());
            stmnt.setInt(4, book.getAvailable());
            stmnt.setInt(5, book.getId());

            stmnt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        final String query = "DELETE FROM books WHERE id = ?";

        try (Connection con = Database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement(query);
            stmnt.setInt(1, id);

            stmnt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAll() {
        final String query = "SELECT * FROM books";

        try (Connection con = Database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement(query);
            ResultSet res = stmnt.executeQuery();
            
            List<Book> list = new ArrayList<>();
            while(res.next()) {
                Book temp = new Book(
                    res.getString("title"),
                    res.getString("author"),
                    res.getString("release_date")
                );
                temp.setId(res.getInt("id"));
                temp.setAvailable(res.getInt("available"));

                list.add(temp);
            }

            return list;

        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Book getById(int id) {
        final String query = 
        "SELECT id, title, author, release_date, available FROM books WHERE id = ?";
        
        try (Connection con = Database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement(query);
            stmnt.setInt(1, id);

            ResultSet res = stmnt.executeQuery();
            Book book = new Book(
                res.getString("title"),
                res.getString("author"),
                res.getString("release_date")
            );
            book.setId(res.getInt("id"));
            book.setAvailable(res.getInt("available"));

            return book;

        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
