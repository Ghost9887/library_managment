package ghost.library;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import ghost.library.backend.repo.Database;

public class AppTest {

    @Test
    public void testDBConnection() {
        Database.connect();
    }
}
