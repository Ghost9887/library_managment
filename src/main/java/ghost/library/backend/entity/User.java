package ghost.library.backend.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    
    private int id;
    private String firstName;
    private String lastName;
    private List<Book> books = new ArrayList<>();

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }   

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
