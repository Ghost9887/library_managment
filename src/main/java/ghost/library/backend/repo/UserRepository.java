package ghost.library.backend.repo;

import ghost.library.backend.Database;
import ghost.library.backend.entity.User;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//HANDLES QUERYS TO THE DATABASE
public final class UserRepository {
    
    public UserRepository() {}

    public void add(User user) {
        final String query = "INSERT INTO users (first_name, last_name)"
        + "VALUES (?, ?);";

        try (Connection con = Database.getConnection()){
            PreparedStatement stmnt = con.prepareStatement(query);

            stmnt.setString(1, user.getFirstName());
            stmnt.setString(2, user.getLastName());

            stmnt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(User user) {
        final String query = "UPDATE users "
        + "SET first_name = ?, "
        + "last_name = ? "
        + "WHERE id = ?;";

        try (Connection con = Database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement(query);

            stmnt.setString(1, user.getFirstName());
            stmnt.setString(2, user.getLastName());
            stmnt.setInt(3, user.getId());

            stmnt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
   

    public void delete(int id) {
        final String query = "DELETE FROM users WHERE id = ?";

        try (Connection con = Database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement(query);
            
            stmnt.setInt(1, id);

            stmnt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAll() {
        final String query = "SELECT * FROM users;";

        try (Connection con = Database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement(query);
            ResultSet res = stmnt.executeQuery();
            
            List<User> list = new ArrayList<>();
            while(res.next()) {
                User temp = new User(
                    res.getString("first_name"),
                    res.getString("last_name")
                );
                temp.setId(res.getInt("id"));

                list.add(temp);
            }

            return list;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getById(int id) {
        final String query = 
        "SELECT id, first_name, last_name FROM users WHERE id = ?";
        
        try (Connection con = Database.getConnection()) {
            PreparedStatement stmnt = con.prepareStatement(query);
            stmnt.setInt(1, id);

            ResultSet res = stmnt.executeQuery();
            User user = new User(
                res.getString("first_name"),
                res.getString("last_name")
            );
            user.setId(res.getInt("id"));

            return user;

        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
