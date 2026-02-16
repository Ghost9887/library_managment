package ghost.library.backend.entity;

public class UserBooks {
     
    private int userId;
    private int bookId;
    
    public UserBooks(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public int getBookId() {
        return bookId;
    }

}
