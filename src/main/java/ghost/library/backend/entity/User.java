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

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }   

    public String getLastName() {
        return lastName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
