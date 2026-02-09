package ghost.library.backend;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Optional;

public final class Database {

    private final static String URL = "jdbc:sqlite:data/library.db";
    private static Connection connection;

    public static String getUrl() {
        return URL;
    }

    public static void connect() {
        try (Connection con = DriverManager.getConnection(URL)) {
            connection = con;

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static int init() {
        try (Connection con = DriverManager.getConnection(URL)) {
            if (con != null) {
                System.out.println("Created new database!");
                initTables(con);
                return 1;
            }
            return -1;
        }catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static void initTables(Connection con) {
        try {
            String createBooks = "CREATE TABLE IF NOT EXISTS books ("
            + "     id INTEGER PRIMARY KEY,"
            + "     title TEXT NOT NULL,"
            + "     author TEXT NOT NULL,"
            + "     release_date TEXT NOT NULL,"
            + "     available INTEGER NOT NULL"
            + ");";

            String createUsers = "CREATE TABLE IF NOT EXISTS users ("
            + "     id INTEGER PRIMARY KEY,"
            + "     first_name TEXT NOT NULL,"
            + "     last_name TEXT NOT NULL"
            + ");";

            String createUserBooks = "CREATE TABLE IF NOT EXISTS user_books ("
            + "     user_id INTEGER NOT NULL,"
            + "     book_id INTEGER NOT NULL,"
            + "     PRIMARY KEY (user_id, book_id),"
            + "     FOREIGN KEY (user_id) REFERENCES users(id),"
            + "     FOREIGN KEY (book_id) REFERENCES books(id)"
            + ");";

            Statement stmnt = con.createStatement();
            stmnt.execute(createBooks);
            System.out.println("Created books table");
            stmnt.execute(createUsers);
            System.out.println("Created users table");
            stmnt.execute(createUserBooks);
            System.out.println("Created user_books table");

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
