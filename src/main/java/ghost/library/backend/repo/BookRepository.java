package ghost.library.backend.repo;

import ghost.library.backend.Database;
import ghost.library.backend.entity.Book;
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

            System.out.println("added new book");

        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAll() {
        final String query = "SELECT * FROM books";

        try (Connection con = Database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement(query);
            ResultSet res = stmnt.executeQuery();

            while(res.next()) {
                Book temp = new Book(
                    res.getString("title"),
                    res.getString("author"),
                    res.getString("release_date")
                );
                temp.setId(res.getInt("id"));
                temp.setAvailable(res.getInt("available"));

                System.out.println(temp.toString());
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
